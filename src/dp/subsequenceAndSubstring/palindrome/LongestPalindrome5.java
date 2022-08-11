package dp.subsequenceAndSubstring.palindrome;

/**
 * @author - ZwZ
 * @date - 2020/9/23 - 21:39
 * @Description:5 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 */
public class LongestPalindrome5 {
    public String longestPalindrome(String s) {
        int n = s.length();
        int maxLen = 1;
        int start = 0;
        if (n < 2) {
            return s;
        }
        //dp[i][j]:s[i...j]是回文子串
        boolean[][] dpArr = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dpArr[i][i] = true;
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                //一头一尾不相等则一定不是回文串
                if (s.charAt(i) != s.charAt(j)) {
                    dpArr[i][j] = false;
                } else {
                    //1. s[i]和s[j] 2. s[i]和s[j]中间夹一个数
                    if ((j - i) < 3) {
                        dpArr[i][j] = true;
                    } else {
                        //i是递增赋值的，这里却等于了[i+1]，会导致用还没有被遍历到的位置给当前位置赋值嘛？
                        //不会。可以画一个二维数组走一下整个流程中的赋值流程
                        dpArr[i][j] = dpArr[i + 1][j - 1];
                    }
                }
                //记录最大长度和初始位置
                if (dpArr[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

}
