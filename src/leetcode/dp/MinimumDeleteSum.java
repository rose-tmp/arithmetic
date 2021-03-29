package leetcode.dp;

/**
 * @author - ZwZ
 * @date - 2021/1/10 - 19:43
 * @Description:712. 两个字符串的最小ASCII删除和
 * 给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。
 * <p>
 * 示例 1:
 * 输入: s1 = "sea", s2 = "eat"
 * 输出: 231
 * 解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
 * 在 "eat" 中删除 "t" 并将 116 加入总和。
 * 结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
 * <p>
 * 示例2:
 * 输入: s1 = "delete", s2 = "leet"
 * 输出: 403
 * 解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
 * 将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
 * 结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
 * 如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
 * <p>
 * 注意:
 * 0 < s1.length, s2.length <= 1000。
 * 所有字符串中的字符ASCII值在[97, 122]之间。
 * <p>
 * 相似题目：
 * 1143. 最长公共子序列LongestCommonSubsequence
 * 583. 两个字符串的删除操作MinDistance  几乎和本题是一模一样只不过一个是+1一个是+ASCII值
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-ascii-delete-sum-for-two-strings
 */
public class MinimumDeleteSum {
    /**
     * 暴力递归
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum1(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        return dp1(s1, s2, 0, 0);
    }

    /**
     * 带备忘录的递归
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum2(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        int[][] memo = new int[s1.length()][s2.length()];
        return dp2(s1, s2, memo, 0, 0);
    }

    /**
     * 动态规划1
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum3(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        //dp[i][j]:s1[0...i-1]s2[0...i-2]两个字符串相等所需删除字符的ASCII值的最小和
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        //第一行赋初值
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + s2.charAt(i - 1);
        }
        //第一列赋初值
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1] + s2.charAt(j - 1),
                            s1.charAt(i - 1) + dp[i - 1][j]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    /**
     * 动态规划2  与上边的区别在于dp[i][j]的定义不一样 这样就会导致给
     * dp table赋值时候的顺序会变
     * 当然这种方法也可以在583. 两个字符串的删除操作MinDistance这道题上适用
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum4(String s1, String s2) {
        /*dp[i][j] 表示字符串 s1[i:] 和 s2[j:]（s1[i:] 表示字符串 s1 从第i位到末尾的子串，
        s2[j:] 表示字符串s2从第j位到末尾的子串，达到相等所需删除的字符的 ASCII 值的最小和*/
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        //dp table赋初值
        for (int i = s1.length() - 1; i >= 0; i--) {
            dp[i][s2.length()] = dp[i + 1][s2.length()] + s1.codePointAt(i);
        }
        for (int j = s2.length() - 1; j >= 0; j--) {
            dp[s1.length()][j] = dp[s1.length()][j + 1] + s2.codePointAt(j);
        }
        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j] + s1.codePointAt(i),
                            dp[i][j + 1] + s2.codePointAt(j));
                }
            }
        }
        return dp[0][0];
    }

    public int dp1(String s1, String s2, int i, int j) {
        //base case
        if (i == s1.length() && j == s2.length()) {
            return 0;
        }
        int res = 0;
        if (i == s1.length()) {
            for (; j < s2.length(); j++) {
                res += s2.charAt(j);
            }
            return res;
        }
        if (j == s2.length()) {
            for (; i < s1.length(); i++) {
                res += s1.charAt(i);
            }
            return res;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return dp1(s1, s2, i + 1, j + 1);
        } else {
            return Math.min(dp1(s1, s2, i, j + 1) + s2.charAt(j), s1.charAt(i) + dp1(s1, s2, i + 1, j));
        }
    }

    private int dp2(String s1, String s2, int[][] memo, int i, int j) {
        //base case
        if (i == s1.length() && j == s2.length()) {
            return 0;
        }
        int res = 0;
        if (i == s1.length()) {
            for (; j < s2.length(); j++) {
                res += s2.charAt(j);
            }
            return res;
        }
        if (j == s2.length()) {
            for (; i < s1.length(); i++) {
                res += s1.charAt(i);
            }
            return res;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = dp2(s1, s2, memo, i + 1, j + 1);
        } else {
            memo[i][j] = Math.min(s2.charAt(j) + dp2(s1, s2, memo, i, j + 1),
                    s1.charAt(i) + dp2(s1, s2, memo, i + 1, j));
        }
        return memo[i][j];
    }
}
