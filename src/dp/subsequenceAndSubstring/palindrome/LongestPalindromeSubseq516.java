package dp.subsequenceAndSubstring.palindrome;

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
public class LongestPalindromeSubseq516 {
    public int longestPalindromeSubseq(String s) {
        if (s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int ans = 1;
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }
        //从下到上按行遍历
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j <s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = j - i + 1;
                    } else {
                        dp[i][j] = 2 + dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    /**
     * 这个遍历顺序是错误的，dp[i][j]和dp[i + 1][j - 1],dp[i][j - 1],dp[i + 1][j]有关
     * 所以要确保在遍历到dp[i][j]的时候dp[i + 1][j - 1],dp[i][j - 1],dp[i + 1][j]这三个位置已经被遍历到
     * 但是这种遍历顺序中，遍历到dp[i][j]的时候因为dp[i+1][j]还没有遍历到，所以dp[i+1][j]不是正确值，进而也无法得到dp[i][j]的正确值
     */
    public int longestPalindromeSubseq1(String s) {
        if (s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int ans = 1;
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }
        for (int j = 1; j < dp.length; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = j - i + 1;
                    } else {
                        dp[i][j] = 2 + dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    /**
     * 参考 https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484666&idx=1&sn=e3305be9513eaa16f7f1568c0892a468&chksm=9bd7faf2aca073e4f08332a706b7c10af877fee3993aac4dae86d05783d3d0df31844287104e&scene=21#wechat_redirect
     */
    public int longestPalindromeSubseq2(String s) {
        if (s == null)
            return 0;
        /*dp[i][j]:s[i...j]中最长回文子序列的长度
         * s[i] == s[j]时: dp[i][j] = dp[i + 1][j - 1] + 2
         * s[i] != s[j]时: dp[i][j] = Max(dp[i][j - 1],dp[i + 1][j]) 即此时s[i]和s[j]不同时出现在s[i...j]的最长回文子序列中
         * 由上方的式子可知: dp[i][j]和dp[i + 1][j - 1],dp[i][j - 1],dp[i + 1][j]有关
         * 而dp table初始时只有主对角线及其下方的位置有初始值
         * 所以在为dp table赋值的时候要注意遍历的顺序
         * */
        int[][] dp = new int[s.length()][s.length()];
        //赋初值 对角线上值为1 对角线以下即i>j的位置初值为0
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }
        //第一种赋值dp table的方法:平行于主对角线从上到下 斜着遍历->这种for循环不好写

        //第二种赋值dp table的方法:从下到上逐行赋值
        return 0;
    }
}
