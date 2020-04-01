package day181124;

/**
 * //                       .::::.
 * //                     .::::::::.
 * //                    :::::::::::
 * //                 ..:::::::::::'
 * //              '::::::::::::'
 * //                .::::::::::
 * //           '::::::::::::::..
 * //                ..::::::::::::.
 * //              ``::::::::::::::::
 * //               ::::``:::::::::'        .:::.
 * //              ::::'   ':::::'       .::::::::.
 * //            .::::'      ::::     .:::::::'::::.
 * //           .:::'       :::::  .:::::::::' ':::::.
 * //          .::'        :::::.:::::::::'      ':::::.
 * //         .::'         ::::::::::::::'         ``::::.
 * //     ...:::           ::::::::::::'              ``::.
 * //    ```` '            ':::::::::'                  ::::..
 * //                       '.:::::'                    ':'````..
 *
 * @author - ZwZ呀咿呀咿哟
 * @date - 2018/11/24 - 13:40
 * @Description:买卖股票最佳时机2
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/
 */
public class Quesions1 {
    //暴力法 递归调用
    public int maxProfit(int[] prices) {
        return calculate(prices, 0);
    }
    public int calculate(int prices[], int s) {
        if (s >= prices.length) {
            return 0;
        }
        int max = 0;
        for (int start = s; start < prices.length; start++) {
            int maxprofit = 0;
            for (int i = start + 1; i < prices.length; i++) {
                if (prices[start] < prices[i]) {
                    int profit = calculate(prices, i + 1) + prices[i] - prices[start];
                    if (profit > maxprofit)
                        maxprofit = profit;
                }
            }
            if (maxprofit > max)
                max = maxprofit;
        }
        return max;
    }

    //此方法要画图模拟出多种情况来分析和理解代码
    public int maxProfit2(int[] prices) {
        int i = 0;
        int valley = prices[0];//波谷
        int peak = prices[0];//波峰
        int maxprofit = 0;
        //一个此循环寻找一个连续的波峰和波谷
        while (i < prices.length - 1) {
            //找波谷
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];
            //找波峰
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];
            maxprofit = maxprofit + peak - valley;
            System.out.println("测试"+valley+"  "+peak);
        }
        return maxprofit;
    }
    //在方法2的基础上进行优化
    public int maxProfit3(int[] prices){
        int max = 0;
        for(int i = 1;i < prices.length;i++){
            if(prices[i] > prices[i - 1]){
             max += prices[i] - prices[i - 1];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Quesions1 quesions1 = new Quesions1();
        int[] prices = new int[]{7, 1, 3, 5, 6, 4};
        int[] prices2 = new int[]{1,7,2,4,3,6,5};
        System.out.println(quesions1.maxProfit(prices));
        System.out.println(quesions1.maxProfit2(prices));
        System.out.println(quesions1.maxProfit2(prices2));

    }
}
