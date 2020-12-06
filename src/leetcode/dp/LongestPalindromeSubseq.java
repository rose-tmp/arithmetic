package leetcode.dp;

import java.util.Arrays;

/**
 * @author - ZwZ
 * @date - 2020/12/6 - 12:16
 * @Description:516. 最长回文子序列
 * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * "bbbab"
 * 输出:
 * 4
 * 一个可能的最长回文子序列为 "bbbb"。
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * "cbbd"
 * 输出:
 * 2
 * 一个可能的最长回文子序列为 "bb"。
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 只包含小写英文字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
 */
public class LongestPalindromeSubseq {
    /**
     * 参考 https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484666&idx=1&sn=e3305be9513eaa16f7f1568c0892a468&chksm=9bd7faf2aca073e4f08332a706b7c10af877fee3993aac4dae86d05783d3d0df31844287104e&scene=21#wechat_redirect
     */
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        //dp[i][j]:s[i...j]所形成的字符串的最大回文子序列
        int[][] dp = new int[s.length()][s.length()];
        //所有位置初始化为0
        for (int[] arr : dp) {
            Arrays.fill(arr, 0);
        }
        //对角线赋初值
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        //赋值 注意遍历顺序
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}
