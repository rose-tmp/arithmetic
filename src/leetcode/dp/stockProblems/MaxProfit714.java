package leetcode.dp.stockProblems;

/**
 * @author - ZwZ
 * @date - 2022/3/19-20:48 - 周六
 * @Description:714. 买卖股票的最佳时机含手续费
 * 给定一个整数数组prices，其中 prices[i]表示第i天的股票价格 ；整数fee代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * 示例 1：
 * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润:((8 - 1) - 2) + ((9 - 4) - 2) = 8
 * <p>
 * 示例 2：
 * 输入：prices = [1,3,7,5,10,3], fee = 3
 * 输出：6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxProfit714 {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0 || fee < 0) {
            return 0;
        }
        //dp[i][0]:第i天结束时持有股票，此时获得的最大收益
        //dp[i][1]:第i天结束时不持有股票，此时获得的最大收益
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }
        return Math.max(dp[dp.length - 1][0], dp[dp.length - 1][1]);
    }
}
