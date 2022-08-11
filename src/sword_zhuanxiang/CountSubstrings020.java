package sword_zhuanxiang;

/**
 * @author - ZwZ
 * @date - 2022/8/1-15:02 - 周一
 * @Description:剑指 Offer II 020. 回文子字符串的个数
 * 给定一个字符串 s ，请计算这个字符串中有多少个回文子字符串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 * 示例 1：
 *
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 *
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 *
 * 注意：本题与主站 647 题相同：https://leetcode-cn.com/problems/palindromic-substrings/
 */
public class CountSubstrings020 {
    public int countSubstrings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += getCount(s, i, i);
            res += getCount(s, i, i + 1);
        }
        return res;
    }

    /**
     * 计算以某个字符或者某两个字符为回文串中心时，所有回文串的长度
     */
    public int getCount(String s, int start, int end) {
        int count = 0;
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            count++;
            start--;
            end++;
        }
        return count;
    }

    /**
     * 动态规划
     * 思路是错的
     */
    /*public int countSubstrings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int res = 0;
        //dp[i]:以s[i]结尾的字符串的回文串的长度
        int[] dp = new int[s.length()];
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            if ((i - dp[i - 1]) > 0 && s.charAt(i - dp[i - 1]) == s.charAt(i)) {
                dp[i] = dp[i - 1] + 2;
            }
            res += dp[i];
        }
        return res;
    }*/
}
