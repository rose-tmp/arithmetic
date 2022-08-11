package dp.subsequenceAndSubstring;

/**
 * @author - ZwZ
 * @date - 2022/3/11-18:04 - 周五
 * @Description: 44.通配符匹配
 * 给定一个字符串(s) 和一个字符模式(p) ，实现一个支持'?'和'*'的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * s可能为空，且只包含从a-z的小写字母。
 * p可能为空，且只包含从a-z的小写字母，以及字符?和*。
 * 示例1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释:'*' 可以匹配任意字符串。
 * 示例3:
 * <p>
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释:'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例4:
 * <p>
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释:第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例5:
 * <p>
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wildcard-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * 思路和dp文件中提到的思路一样。暴力递归：
 * adceb *a*b
 * *用来匹配空/          \*用来匹配掉a
 * adceb a*b        dceb *a*b
 * /               /      \
 * dceb *b        ceb *a*b   dceb a*b
 * *用来匹配掉d/   \        /   \
 * ceb *b   dceb b eb *a*b ceb a*b   ...
 */
public class IsMatch44 {
    public boolean isMatchNew(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        for (int i = 1; i < dp[0].length; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    for (int k = 1; k < j; k++) {
                        if (dp[i][k]) {
                            dp[i][j] = true;
                        }
                    }
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /**
     * 动态规划
     */
    public boolean isMatch(String s, String p) {
        if ((s.length() == 0) && (p.length() == 0)) {
            return true;
        }
        if (p.length() == 0) {
            return false;
        }
        //dp[i][j]表示s[0...i-1]和p[0...j-1]能否匹配上
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //s p均为空
        dp[0][0] = true;
        //s为空时 （p为空时除dp[0][0]外不需考虑，因为s不为空，p为空的话一定为false）
        for (int i = 1; i < dp[0].length; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (p.charAt(j - 1) == '?' || p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                //dp[i-1][j]该*用于匹配掉s[i]  dp[i][j-1]该*用于匹配空（即把*去掉，给*后边的字符让路用于匹配s中的对应位置的值）
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
