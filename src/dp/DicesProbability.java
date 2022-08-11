package dp;

import java.util.Arrays;

/**
 * @author - ZwZ
 * @date - 2022/7/20-11:06 - 周三
 * @Description:剑指 Offer 60. n个骰子的点数
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * <p>
 *  
 * <p>
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * 示例 2:
 * <p>
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DicesProbability {
    public double[] dicesProbability(int n) {
        if (n == 1) {
            double[] res = new double[6];
            Arrays.fill(res, 1.0 / 6);
            return res;
        }
        int[][] dp = new int[n + 1][n * 6 + 1];//dp[i][j]:投i个筛子，总和为j的次数
        for (int i = 1; i < 7; i++) {
            dp[1][i] = 1;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = i; j < dp[0].length; j++) {
                for (int k = 1; k < 7; k++) {
                    if (j - k > 0) {
                        dp[i][j] += dp[i - 1][j - k];
                    }
                }
            }
        }
        double[] res = new double[n * 5 + 1];
        double count = Math.pow(6, n);
        for (int i = 0; i < res.length; i++) {
            res[i] = dp[n][n + i] / count;
        }
        return res;
    }
}
