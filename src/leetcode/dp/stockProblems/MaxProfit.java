package leetcode.dp.stockProblems;

/**
 * @author - ZwZ
 * @date - 2020/10/28 - 10:40
 * @Description:309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *     你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *     卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 *
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 */
public class MaxProfit {
    /**
     * dp函数的设定：
     *     f(i)表示第i天结束的时候所获得的最大利润
     * 第i天结束的时候的状态分析：
     *     1. 持有股票  f(i)(0)
     *     2. 不持有股票且不在冷冻期  f(i)(1)
     *     3. 不持有股票且在冷冻期  f(i)(2)
     *     而我们需要用数组去存储这三个状态 所以dp数组会是一个n*3的二维数组
     *  分析状态转换方程：
     *     1.持有股票时：
     *          1).股票是之前买的，第i天没有任何交易   f(i)(0) = f(i - 1)(0)
     *          2).股票是第i天买的,此时第i - 1天结束时必须不持有股票且不在冷冻期  f(i)(0) = f(i - 1)(1) - prices[i]
     *          故，f(i)(0) = MAX(f(i - 1)(0),f(i - 1)(1) - prices[i])
     *     2.不持有股票且不在冷冻期：
     *          第i天结束时不在冷冻期说明第i天没有进行交易，所以第i-1天也是没有持有股票的
     *          1).第i - 1天不持有股票，但是处在冷冻期   f(i)(1) = f(i - 1)(2)
     *          2).第i - 1天不持有股票且不处在冷冻期    f(i)(1) = f(i - 1)(1)
     *          故，f(i)(1) = MAX(f(i - 1)(2),f(i - 1)(1))
     *     3.不持有股票且在冷冻期：
     *          第i天结束的时候在冷冻期说明在这一天抛了股票
     *          故，f(i)(2) = f(i - 1)(0) +  prices[i]
     *     我们最终是要MAX(f(i)(0),f(i)(1),f(i)(2))
     * */
    public int maxProfit1(int[] prices) {
        if(prices.length == 0)
            return 0;
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];//第0天买入
        dp[0][1] = 0;
        dp[0][2] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0],dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][2],dp[i - 1][1]);
            dp[i][2] = dp[i - 1][0] + prices[i];
        }
        return Math.max(Math.max(dp[prices.length - 1][0],dp[prices.length - 1][1]),dp[prices.length - 1][2]);
    }
    /**
     * 优化
     * */
    public int maxProfit2(int[] prices) {
        if(prices.length == 0)
            return 0;
        int preF0 = -prices[0];//第0天买入
        int preF1 = 0;
        int preF2 = 0;
        for (int i = 1; i < prices.length; i++) {
            int f0 = Math.max(preF0,preF1 - prices[i]);
            int f1 = Math.max(preF2,preF1);
            int f2 = preF0 + prices[i];
            preF0 = f0;
            preF1 = f1;
            preF2 = f2;
        }
        return Math.max(Math.max(preF0,preF1),preF2);
    }
}
