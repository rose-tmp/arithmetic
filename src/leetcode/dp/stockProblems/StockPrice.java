package leetcode.dp.stockProblems;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/3/23-20:01 - 周三
 * @Description:2034. 股票价格波动
 * 给你一支股票价格的数据流。数据流中每一条记录包含一个时间戳和该时间点股票对应的价格。
 * <p>
 * 不巧的是，由于股票市场内在的波动性，股票价格记录可能不是按时间顺序到来的。某些情况下，有的记录可能是错的。
 * 如果两个有相同时间戳的记录出现在数据流中，前一条记录视为错误记录，后出现的记录更正前一条错误的记录。
 * <p>
 * 请你设计一个算法，实现：
 * 更新股票在某一时间戳的股票价格，如果有之前同一时间戳的价格，这一操作将更正之前的错误价格。
 * 找到当前记录里最新股票价格。最新股票价格定义为时间戳最晚的股票价格。
 * 找到当前记录里股票的最高价格。
 * 找到当前记录里股票的最低价格。
 * 请你实现StockPrice类：
 * <p>
 * StockPrice()初始化对象，当前无股票价格记录。
 * void update(int timestamp, int price)在时间点timestamp更新股票价格为price。
 * int current()返回股票最新价格。
 * int maximum()返回股票最高价格。
 * int minimum()返回股票最低价格。
 * <p>
 * 示例 1：
 * 输入：
 * ["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
 * [[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
 * 输出：
 * [null, null, null, 5, 10, null, 5, null, 2]
 * <p>
 * 解释：
 * StockPrice stockPrice = new StockPrice();
 * stockPrice.update(1, 10); // 时间戳为 [1] ，对应的股票价格为 [10] 。
 * stockPrice.update(2, 5);  // 时间戳为 [1,2] ，对应的股票价格为 [10,5] 。
 * stockPrice.current();     // 返回 5 ，最新时间戳为 2 ，对应价格为 5 。
 * stockPrice.maximum();     // 返回 10 ，最高价格的时间戳为 1 ，价格为 10 。
 * stockPrice.update(1, 3);  // 之前时间戳为 1 的价格错误，价格更新为 3 。
 * // 时间戳为 [1,2] ，对应股票价格为 [3,5] 。
 * stockPrice.maximum();     // 返回 5 ，更正后最高价格为 5 。
 * stockPrice.update(4, 2);  // 时间戳为 [1,2,4] ，对应价格为 [3,5,2] 。
 * stockPrice.minimum();     // 返回 2 ，最低价格时间戳为 4 ，价格为 2 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stock-price-fluctuation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class StockPrice {
    int maxTimestamp;
    HashMap<Integer, Integer> timePriceMap;
    TreeMap<Integer, Integer> prices;

    public StockPrice() {
        maxTimestamp = 0;
        //key:timestamp value:prices
        timePriceMap = new HashMap<Integer, Integer>();
        //key:prices value:当前价格的次数 记录次数用来决定update的时候要不要remove某个价格
        //Java里的TreeMap是一个很重要且很好用的数据结构，其实它就可以看作每个元素都能带一个家属(value)的优先队列
        prices = new TreeMap<Integer, Integer>();
    }

    public void update(int timestamp, int price) {
        maxTimestamp = Math.max(maxTimestamp, timestamp);
        //此时间戳之前对应的prices
        int prevPrice = timePriceMap.getOrDefault(timestamp, 0);
        timePriceMap.put(timestamp, price);
        //之前存在时间戳为timestamp的数据
        if (prevPrice > 0) {
            prices.put(prevPrice, prices.get(prevPrice) - 1);
            if (prices.get(prevPrice) == 0) {
                prices.remove(prevPrice);
            }
        }
        prices.put(price, prices.getOrDefault(price, 0) + 1);
    }

    public int current() {
        return timePriceMap.get(maxTimestamp);
    }

    public int maximum() {
        return prices.lastKey();
    }

    public int minimum() {
        return prices.firstKey();
    }
}
