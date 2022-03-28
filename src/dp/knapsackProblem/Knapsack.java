package dp.knapsackProblem;

/**
 * @author - ZwZ
 * @date - 2020/11/3 - 20:46
 * @Description:0-1背包问题
 * 给你一个可装载重量为W的背包和N个物品，
 * 每个物品有重量和价值两个属性
 * 其中第i个物品的重量为wt[i]，价值为val[i]
 * 现在让你用这个背包装物品，最多能装的价值是多少？
 */
public class Knapsack {
    /**
     * @param w        物品的重量数组
     * @param v        物品的价值数组
     * @param index    当前待选择的物品索引
     * @param capacity 当前背包有效容量
     * @return 最大价值
     */
    private static int solve(int[] w, int[] v, int index, int capacity) {
        if (w.length == 0 || capacity < 0) {
            return 0;
        }
        //dp[i][j] 有且只有前i个物品，背包容量为j时所获得的最大价值 即在前i个物品里选取总重量不超过j的物品使得总价值最大
        int[][] dp = new int[v.length + 1][capacity + 1];
        //i为0时，dp[i][j] == 0
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = 0;
        }
        //j为0时，dp[i][j] == 0
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                //装不下第i个物品
                if (j - v[i] < 0) {
                    dp[i][j] = dp[i - 1][j];
                }
                //两种选择：第i个物品放入背包和不放背包  选最优
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - v[i]] + v[i]);
            }
        }
        return dp[v.length][capacity];
    }
}
