package dp;

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
     * 暴力递归 把所有的组合都枚举出来(画出递归树便于理解)
     *                 11
     *          /      |     \
     *         10      9      6
     *       / | \   / | \   / | \
     *     9  8  5  8  7 4  1  4  1
     *       ...      ...     ...
     */
    public int coinChange1(int[] coins, int amount) {
        //base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int ans = Integer.MAX_VALUE - 1;
        for (int i = 0; i < coins.length; i++) {
            //子问题的结果
            int sub = coinChange1(coins, amount - coins[i]);
            //子问题有解  也就是留下coins[i]这个硬币之后 剩下的硬币可以凑够数量amount - coins[i]
            if (sub != -1) {
                ans = Math.min(ans, sub + 1);
            }
        }
        //for循环遍历完毕之后ans==Integert.MAX_VALUE说明coins中的硬币根本凑不出amount
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * 方法1的暴力递归画出递归树之后就可以很直观的发现存在大量的重复计算
     * 使用备忘录就可以完美的解决掉暴力递归中重复计算的问题
     * 做法：
     * <p>
     * 每次计算出某个子问题的答案之后不着急返回，而是先存入备忘录
     * 每次遇到一个子问题的时候先不着急计算，而是先去备忘录中查一下看看之前有没有计算过这个问题
     * <p>
     * 其与动态规划的不同是：带递归的备忘录属于自顶向下的，动态规划是自底向上的
     */
    public int coinChange2(int[] coins, int amount) {
        //memo[i]:凑成总金额i所需的最少的硬币个数 即一个子问题的答案 我们想要的答案就是memo[amount]
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, Integer.MAX_VALUE - 1);
        memo[0] = 0;
        return dp(coins, memo, amount);
    }

    public int dp(int[] coins, int[] memo, int amount) {
        //base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        //该问题被计算过
        if (memo[amount] != Integer.MAX_VALUE - 1) return memo[amount];

        int res = Integer.MAX_VALUE - 1;
        for (int i = 0; i < coins.length; i++) {
            int sub = dp(coins, memo, amount - coins[i]);
            //选coins[i]后 子问题无解
            if (sub == -1) continue;
            res = Math.min(res, sub + 1);
        }

        //计算出答案 存备忘录
        memo[amount] = res == Integer.MAX_VALUE - 1 ? -1 : res;

        return memo[amount];
    }

    /**
     * 动态规划
     * dp(i) : 可以凑成总金额 i 所需的最少的硬币个数
     * dp(amount) = min{dp(amount - coin1),dp(amount - coin2)......}
     * 其中要保证(amount - coin)大于0
     * <p>
     * 动态规划的本质就是穷举 带备忘录的递归和动态规划中的dp table做到了聪明穷举
     */
    public int coinChange3(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                //选coins[j]后 子问题无解
                if (i < coins[j])
                    continue;
                else
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        return dp[amount] == Integer.MAX_VALUE - 1 ? -1 : dp[amount];
    }
}
