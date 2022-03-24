package dp.stockProblems;

/**
 * @author - ZwZ
 * @date - 2022/3/17-18:46 - 周四
 * @Description:剑指 Offer 63. 股票的最大利润 与 121. 买卖股票的最佳时机 是同一道题目
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxProfit121 {
    public int maxProfit1(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        //dp[i]:prices[0...i]买卖一次一次股票可能获得的最大利润
        int[] dp = new int[prices.length];
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = Math.max(prices[i] - min, dp[i - 1]);
            min = Math.min(min, prices[i]);
        }
        return dp[prices.length - 1];
    }
    /**
     * dp优化:状态转移过程中只是用到了上一次的结果，所以直接用一个Int变量记录
     * */
    public int maxProfit2(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        //dp[i]:prices[0...i]买卖一次一次股票可能获得的最大利润
        int res = 0;
        int pre = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            res = Math.max(prices[i] - min, pre);
            pre = res;
            min = Math.min(min, prices[i]);
        }
        return res;
    }
}
