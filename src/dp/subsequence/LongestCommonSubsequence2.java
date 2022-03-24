package dp.subsequence;

/**
 * @author - ZwZ
 * @date - 2022/3/24-22:19 - 周四
 * @Description:最长公共子序列(二)
 * 给定两个字符串str1和str2，输出两个字符串的最长公共子序列。如果最长公共子序列为空，则返回"-1"。
 * 目前给出的数据，仅仅会存在一个最长的公共子序列
 * 数据范围：0 <= |str1|,|str2| <= 20000
 * 要求：空间复杂度 O(n^2),时间复杂度 O(n^2)
 * <p>
 * 示例1
 * 输入：
 * "1A2C3D4B56","B1D23A456A"
 * 返回值：
 * "123456"
 * <p>
 * 示例2
 * 输入：
 * "abc","def"
 * 返回值：
 * "-1"
 * <p>
 * 示例3
 * 输入："abc","abc"
 * 返回值：
 * "abc"
 * <p>
 * 示例4
 * 输入："ab",""
 * 返回值：
 * "-1"
 * <p>
 * https://www.nowcoder.com/practice/6d29638c85bb4ffd80c020fe244baf11
 */
public class LongestCommonSubsequence2 {
    /**
     * longest common subsequence
     *
     * @param s1 string字符串 the string
     * @param s2 string字符串 the string
     * @return string字符串
     * <p>
     * 思路:与leetcode上最长公共子序列不同的是,这道题要返回子序列而leetcode上是需要返回子序列的长度即可
     */
    public String LCS(String s1, String s2) {
        /**
         * 上边依旧是最长公共子序列的过程
         * */
        if (s1.length() == 0 || s2.length() == 0) {
            return "-1";
        }
        //dp[i][j]:s1[0...i]和s2[0...j]这两个字符串的最长子序列
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], Math.max(dp[i - 1][j - 1], dp[i][j - 1]));
                }
            }
        }
        //找出一个最长的公共子序列
        StringBuilder sb = new StringBuilder();
        int s1L = s1.length(), s2L = s2.length();
        while (s1L != 0 && s2L != 0) {
            if (s1.charAt(s1L - 1) == s2.charAt(s2L - 1)) {
                sb.append(s1.charAt(s1L - 1));
                s1L--;
                s2L--;
            } else {
                //选最长的那个
                if (dp[s1L - 1][s2L] > dp[s1L][s2L - 1]) {
                    s1L--;
                } else {
                    s2L--;
                }
            }
        }
        if (sb.length() == 0)
            return "-1";
        //反转
        return sb.reverse().toString();
    }
}
