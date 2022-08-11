package dp.subsequenceAndSubstring;

/**
 * @author - ZwZ
 * @date - 2022/4/1 8:51 下午
 * @Description:115. 不同的子序列
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE"是"ABCDE"的一个子序列，而"AEC"不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 * 示例1：
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * rabbbit
 * rabbbit
 * rabbbit
 * 示例2：
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * 提示：
 * 0 <= s.length, t.length <= 1000
 * s 和 t 由英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distinct-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumDistinct {
    public int numDistinct(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return 0;
        }
        //dp[i][j]:s[0...i-1]中有多少个子序列能匹配t[0...j-1]
        //这里为什么错一位是因为有空串的情况，0位置用来表示空串
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        //t为空串时代表着什么也没有，s中肯定存在”空气“啊，所以全部置1
        for (int j = 0; j < s.length() + 1; j++) {
            dp[j][0] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                //dp[i-1][j-1]:用t[j-1]匹配掉s[i-1],那么s[0...i-1]中有多少个子序列可以匹配t[0..j-1]这个问题就变成了
                //s[0...i-2]中有多少个子序列可以匹配t[0...j-2]
                //dp[i-1][j]:不用t[j-1]匹配掉s[i-1],而是再从s[0...i-2]去找t[0...j-1]这个串
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }
}
