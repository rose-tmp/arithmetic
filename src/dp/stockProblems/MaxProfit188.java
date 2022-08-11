package dp.stockProblems;

/**
 * @author - ZwZ
 * @date - 2022/3/21-21:53 - 周一
 * @Description:188. 买卖股票的最佳时机 IV
 * 给定一个整数数组prices ，它的第 i 个元素prices[i] 是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 * <p>
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxProfit188 {
    /**
     * 尤其需要注意：并不是完整的一次交易完成之后才算是进行到了第N次交易，而是说当前交易进行到了第N次。
     * 比如：正在进行的是第N次完整的买卖交易中的买交易，那么也算是正在进行第N次交易
     * */
    public int maxProfit(int k, int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        k = Math.min(k, prices.length / 2);
        //dp[i][j][0]:第i天进行到了第j次交易，且当天结束时没有股票
        //dp[i][j][1]:第i天进行到了第j次交易，且当天结束时有股票
        int[][][] dp = new int[prices.length][k + 1][2];
        //赋初值
        for (int j = 1; j <= k; j++) {
            dp[0][j][1] = -prices[0];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <= k; j++) {
                //前一天进行了第j次交易的卖操作 或者 前一天进行了第j次交易的买操作，今天进行第j次交易的卖操作
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                //前一天进行了第j次交易的买操作 或者 前一天进行了第j-1次交易的的卖操作，今天进行第j次交易的买操作
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[dp.length - 1][k][0];
    }
    /*public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k <= 0) {
            return 0;
        }
        // hold[i][j]:表示第i天结束时进行了j笔交易且持有股票(完整的一次买卖算作一笔交易)
        int[][] hold = new int[prices.length][k + 1];
        // sail[i][j]:表示第i天结束时进行了j笔交易且不持有股票(完整的一次买卖算作一笔交易)
        int[][] sail = new int[prices.length][k + 1];
        for (int i = 1; i <= k; i++) {
            hold[0][i] = -prices[0];
        }

        for (int i = 1; i < hold.length; i++) {
            for (int j = 1; j <= k; j++) {
                hold[i][j] = Math.max(hold[i - 1][j], sail[i - 1][j] - prices[i]);
                sail[i][j] = Math.max(sail[i - 1][j], hold[i - 1][j - 1] + prices[i]);
            }
        }
        return Math.max(hold[prices.length - 1][k], sail[prices.length - 1][k]);
    }*/
    /*public int maxProfit(int K, int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        //一次交易至少涉及两天，所以如果k大于总天数的一半，就直接取天数一半即可，多余的交易次数是无意义的
        K = Math.min(K, prices.length / 2);

        //dp[i][j][k]代表 第i天交易第k次时的最大利润，其中j代表当天是否持有股票，0不持有，1持有
        int[][][] dp = new int[prices.length][2][K + 1];
        for (int k = 0; k <= K; k++) {
            dp[0][0][k] = 0;
            dp[0][1][k] = -prices[0];
        }

        *//*状态方程：
        dp[i][0][k]，当天不持有股票时，看前一天的股票持有情况
        dp[i][1][k]，当天持有股票时，看前一天的股票持有情况*//*
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i]);
                dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i]);
            }
        }
        return dp[prices.length - 1][0][K];
    }*/

    public static void main(String[] args) {
        MaxProfit188 maxProfit = new MaxProfit188();
        maxProfit.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3});

    }
}
