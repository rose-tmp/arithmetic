package leetcode.dp;

/**
 * @author - ZwZ
 * @date - 2021/1/12 - 12:43
 * @Description:72. 编辑距离
 * 给你两个单词word1和word2，请你计算出将word1转换成word2使用的最少操作数。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 示例1：
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例2：
 * <p>
 * 输入：word1 = "intention", w ord2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 思路：https://mp.weixin.qq.com/s/uWzSvWWI-bWAV3UANBtyOw
 */
public class Distance {
    /**
     * 暴力递归
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minDistance1(String s1, String s2) {
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
    public int minDistance2(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        int[][] memo = new int[s1.length()][s2.length()];
        return dp2(s1, s2, memo, s1.length() - 1, s2.length() - 1);
    }

    /**
     * 动态规划
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minDistance3(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        /*
         * dp[i][j]:将s1[i:]转换成s2[j:]所需要的最少操作数
         * */
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        //赋初值
        for (int i = 0; i < dp.length; i++) {
            //S1[i:]转成""
            dp[i][s2.length()] = s1.length() - i;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[s1.length()][i] = s2.length() - i;
        }
        for (int row = dp.length - 2; row >= 0; row--) {
            for (int col = dp[0].length - 2; col >= 0; col--) {
                if (s1.charAt(row) == s2.charAt(col)) {
                    dp[row][col] = dp[row + 1][col + 1];
                } else {
                    dp[row][col] = 1 + Math.min(dp[row + 1][col],
                            Math.min(dp[row][col + 1], dp[row + 1][col + 1]));
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 暴力递归函数
     * 将s1[i:]转换成s2[j:]所需要的最少操作数
     *
     * @param s1
     * @param s2
     * @param i
     * @param j
     * @return
     */
    public int dp1(String s1, String s2, int i, int j) {
        //在s1后面插入s2剩余所有字符
        if (i == s1.length()) {
            return s2.length() - j;
        }
        //删除s1后面的所有字符
        if (j == s2.length()) {
            return s1.length() - i;
        }
        //dp1(s1, s2, i, j) == dp1(s1, s2, i + 1, j + 1)
        if (s1.charAt(i) == s2.charAt(j)) {
            return dp1(s1, s2, i + 1, j + 1);
        } else {
            /*
             * dp1(s1, s2, i, j + 1):在s1的i位置前插入字符s2[j] 这样就匹配上了s2[j]所以继续向下匹配s2[j+1:]
             * dp1(s1,s2,i + 1,j):删除s1的i位置的字符 然后继续匹配
             * dp1(s1,s2,i + 1,j + 1):用s2[j]替换s1[i]
             * */
            return 1 + Math.min(dp1(s1, s2, i, j + 1),
                    Math.min(dp1(s1, s2, i + 1, j),
                            dp1(s1, s2, i + 1, j + 1)));
        }
    }

    /**
     * 带备忘录的递归
     *
     * @param s1
     * @param s2
     * @param memo memo[i][j]:将s1[i:]转换成s2[j:]所需要的最少操作数
     * @param i
     * @param j
     * @return
     */
    private int dp2(String s1, String s2, int[][] memo, int i, int j) {
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = dp2(s1, s2, memo, i - 1, j - 1);
        } else {
            memo[i][j] = 1 + Math.min(dp2(s1, s2, memo, i, j - 1),
                    Math.min(dp2(s1, s2, memo, i - 1, j),
                            dp2(s1, s2, memo, i - 1, j - 1)));
        }
        return memo[i][j];
    }
}
