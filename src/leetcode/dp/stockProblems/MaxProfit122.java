package leetcode.dp.stockProblems;

/**
 * @author - ZwZ
 * @date - 2020/11/8 - 15:29
 * @Description:122. 买卖股票的最佳时机 II
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * <p>
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * 示例 3:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 */
public class MaxProfit122 {
    /**
     * 动态规划
     */
    public int maxProfit1(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        //dp[i][0]:第i天持有股票 此时获得的最大收益
        //dp[i][1]:第i天不持股票 此时获得的最大收益
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        //详见dp.txt
        /*for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    dp[i][j] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
                }
            }
        }*/
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }
        return dp[prices.length - 1][1];
    }

    /**
     * 贪心
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    int res = Integer.MIN_VALUE;
    /**
     * 暴力递归
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        dfs(prices,0,0,false);
        return res;
    }

    /**
     * prices:原始数组
     * index:当前遍历的结点
     * profit:当前所获收益
     * status:当下手里是否持有股票
     *                                         钱
     *                 不做任何操作  /                      \ 买
     *                            钱                      股票
     *            不做任何操作/         \买       不做任何操作/   \卖
     *                       ......
     *
     */
    private void dfs(int[] prices, int index, int profit,boolean status) {
        if (index == prices.length) {
            res = Math.max(res,profit);
            return;
        }
        //不做任何操作直接进入下一步
        dfs(prices,index + 1,profit,status);
        //当下持有股票并卖掉
        if (status) {
            dfs(prices,index + 1,profit + prices[index],false);
        }else {
            dfs(prices,index + 1,profit - prices[index],true);
        }
    }
}
