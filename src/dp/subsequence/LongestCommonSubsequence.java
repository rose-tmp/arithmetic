package dp.subsequence;

/**
 * @author - ZwZ
 * @date - 2020/10/29 - 12:04
 * @Description:1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * 若这两个字符串没有公共子序列，则返回 0。
 * <p>
 * 示例 1:
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * <p>
 * 示例 2:
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * <p>
 * 示例 3:
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 * <p>
 *
 * 相似题目：
 *      712. 两个字符串的最小ASCII删除和MinimumDeleteSum
 *      583. 两个字符串的删除操作MinDistance
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 * <p>
 * 详细讲解可以参考https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247487860&idx=1&sn=f5759ae4f22f966db8ed5a85821edd34&chksm=9bd7ef7caca0666a628fe838dee6d5da44b05eadf01fd7e87fcef813430c8e6dc3eb3c23e15f&mpshare=1&scene=1&srcid=1029egejOLkD9x33TTXxekdA&sharer_sharetime=1603937174909&sharer_shareid=60fe53036e3f365b12f8b38c78916f27&key=55250639f247efc1f9ff7879f51fc62b3e0ac6335d14635a50650bbb6c37aff461dcb3ea22235612bbedb34f296bec9e78c6754a58003ec6d1039e7c01a70bffe03e9aeb8a5e695b293ec7d79d28b3fbc670b3f4e17c89886a8099777608743ab099c36021f60c109f3e98b28b2cd2e3e9d4263c44fb164693198dbf52a23296&ascene=1&uin=MjM0MDUzMTEwOQ%3D%3D&devicetype=Windows+10+x64&version=6300002f&lang=zh_CN&exportkey=AyfCWTQ6whM%2FT2KxAOY3rnQ%3D&pass_ticket=ZU%2FjJMq%2F3dHvGR18DuVdw1AkG2HHaCijSligxbBV%2FsbvQ3zO8BmCSrqIEJzzjeQM&wx_header=0
 */
public class LongestCommonSubsequence {
    /**
     * 对于两个字符串求子序列的问题，都是用两个指针i和j分别在两个字符串上移动，大概率是动态规划思路
     *
     * 暴力递归
     * 从前向后匹配字符串的每一个位置上的字符
     * 每一个位置上的字符只会有两种情况：相同和不相同
     * 当相同的时候，那么剩余的子串又是一个同类型的子问题  可以继续采取同样的方式去匹配
     * 所以可以使用递归的思想去解决
     */
    public int longestCommonSubsequence1(String text1, String text2) {
        if (text1 == null || text2 == null
                || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        return dp(text1, text2, 0, 0);
    }

    /**
     * 匹配text1[i]和text2[j]位置
     *
     * @param text1
     * @param text2
     * @param i
     * @param j
     * @return text1[0...i]和text2[0...j]中出现的最长公共子序列
     */
    public int dp(String text1, String text2, int i, int j) {
        //base case 当有一个字符串遍历完了最后一个位置的时候则结束
        if (i == text1.length() || j == text2.length()) {
            return 0;
        }
        if (text1.charAt(i) == text2.charAt(j)) {
            return 1 + dp(text1, text2, i + 1, j + 1);
        } else {
            /*
             * dp(text1, text2, i, j + 1):text2[j]没有在最终的最长公共子序列
             * dp(text1, text2, i + 1, j):text1[i]没有在最终的最长公共子序列中
             * dp(text1, text2, i + 1, j + 1):text1[i]和text2[j]都没有在最终的最长公共子序列中
             * */
            //return Math.max(dp(text1, text2, i, j + 1), Math.max(dp(text1, text2, i + 1, j), dp(text1, text2, i + 1, j + 1)));

            return Math.max(dp(text1, text2, i, j + 1), dp(text1, text2, i + 1, j));
        }
    }

    /**
     * 带备忘录的递归
     * 暴力递归中存在重复计算
     * dp(i,j)会在dp(i,j+1)和dp(i+1,j)两个过程中都计算一遍
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || text2 == null
                || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        //memo[i][j]:text1[0...i]和text2[0...j]的最长公共子序列
        int[][] memo = new int[text1.length()][text2.length()];
        return dp2(text1, text2, memo, 0, 0);
    }

    /**
     * @param text1
     * @param text2
     * @param memo
     * @param i
     * @param j
     * @return
     */
    private int dp2(String text1, String text2, int[][] memo, int i, int j) {
        //base case 当有一个字符串遍历完了最后一个位置的时候则结束
        if (i == text1.length() || j == text2.length()) {
            return 0;
        }
        //在计算前先检查之前是否计算过
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (text1.charAt(i) == text2.charAt(j)) {
            memo[i][j] = 1 + dp2(text1, text2, memo, i + 1, j + 1);
        } else {
            /*
             * dp(text1, text2, i, j + 1):text2[j]没有在最终的最长公共子序列
             * dp(text1, text2, i + 1, j):text1[i]没有在最终的最长公共子序列中
             * dp(text1, text2, i + 1, j + 1):text1[i]和text2[j]都没有在最终的最长公共子序列中
             * */
            //Math.max(dp(text1, text2, i, j + 1), Math.max(dp(text1, text2, i + 1, j), dp(text1, text2, i + 1, j + 1)));
            memo[i][j] = Math.max(dp2(text1, text2, memo, i, j + 1), dp2(text1, text2, memo, i + 1, j));
        }
        return memo[i][j];
    }

    /**
     * 动态规划
     * 只要可以使用带备忘录的递归写出来的题目一定可以用动态规划的形式写出来
     * 也就是由自顶向下改成了自底向上  由递归过程改成了非递归过程  并且算法逻辑是一样的
     */
    public int longestCommonSubsequence3(String text1, String text2) {
        if (text1 == null || text2 == null
                || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        /*dp[i][j]:text1[0...i]和text2[0...j]最长公共子序列长度是不可以的
         * 因为我们赋初值时候如果dp[0][j]=0的话意义就变成了text1[0]和text2组成的最长公共子序列为0
         * 但是我们想要的是逻辑是：空串""和text2组成的最长公共子序列为0
         * 所以我们这里让dp[i][j]表示的含义是：text1[0...i-1]和text2[0...j-1]的LCS长度为0
         * 也就是错开一位 让dp table上的下标比text1和text2上跑的快一位
         * 这样我们最终想要的结果就变成了dp[text1.length()][text2.length()]
         * */
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}
