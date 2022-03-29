package dp.subsequenceAndSubstring.matchProblems;

/**
 * @author - ZwZ
 * @date - 2022/3/28-22:22 - 周一
 * @Description:10. 正则表达式匹配
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例3：
 * <p>
 * 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsMatch10 {
    /**
     * 思路:重点关注*因为它可以将前边的字符消除掉，也可以无限增加前边的字符
     * 其他的地方没什么难点。但是本题有一个坑：
     * isMatch2的代码提交之后，大多数用例能过，但是在s=aa,p=a*的时候卡死
     * 究其原因：dp数组的设计，以及状态转移方程的推导都没有错，问题出在一开始赋初值的时候，把dp[0][j]也就是第一行数据除了dp[0][0]
     * 外全部都赋值为T这个操作就是不对的:如s=aa p=a*的情况下，dp[0][2]应该为T,因为*可以直接让p变为""所以dp[0][2]应该为F
     * 而isMatch2的代码为什么过不去，就是因为这个原因(isMatch是改过的可以顺利通过的代码)
     *
     * */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        //在MinDastance里边有介绍关于dp扩大一个位置初始化的原因
        //dp[i][j] : s[0...i-1]和p[0...j-1]是否能够匹配上
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        //s的下标从0开始
        for (int i = 0; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                //题目中有说可以保证*前边会有一个合法字符，所以不需要考虑j-2<0导致数组下标越界的情况
                if (p.charAt(j - 1) == '*') {
                    //i == 0的情况也考虑进去
                    if (dp[i][j - 2] || i == 0) {
                        dp[i][j] = dp[i][j -2];
                        continue;
                    }
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        //dp[i - 1][j]:匹配掉s[i-1]
                        //一个*可以匹配多个字符，所以这里是dp[i - 1][j]而不是dp[i-1]
                        dp[i][j] = dp[i - 1][j] | dp[i][j - 2];
                    }
                    //匹配不上，则让*和*前边的那个字符消失
                    else if (s.charAt(i - 1) != p.charAt(j - 2)) {
                        dp[i][j] = dp[i][j - 2];
                    }
                } else  {
                    //防止数组下标越界
                    if (i == 0){
                        continue;
                    }
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        //在MinDastance里边有介绍关于dp扩大一个位置初始化的原因
        //dp[i][j] : s[0...i-1]和p[0...j-1]是否能够匹配上
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    if (s.charAt(i - 1) == p.charAt(j - 2)) {
                        dp[i][j] = dp[i - 1][j] | dp[i][j - 2];
                    }
                    //匹配不上，则让*前边的那个字符消失
                    else if (s.charAt(i - 1) != p.charAt(j - 2)) {
                        dp[i][j] = dp[i][j - 2];
                    }
                    //*在p的第一个位置
                    else {
                        dp[i][j] = false;
                    }
                } else if (p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
