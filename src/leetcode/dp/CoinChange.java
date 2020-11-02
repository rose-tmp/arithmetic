package leetcode.dp;

import java.util.Arrays;

/**
 * @author - ZwZ
 * @date - 2020/11/2 - 8:59
 * @Description:322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数
 * 如果没有任何一种硬币组合能组成总金额，返回 -1
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * <p>
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 * <p>
 * 示例 4：
 * 输入：coins = [1], amount = 1
 * 输出：1
 * <p>
 * 示例 5：
 * 输入：coins = [1], amount = 2
 * 输出：2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 */
public class CoinChange {
    /**
     * 动态规划
     * dp(amount) = min{dp(amount - coin1),dp(amount - coin2)......}
     * 其中要保证(amount - coin)大于0
     */
    public int coinChange1(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE - 1 ? -1 : dp[amount];
    }
}
