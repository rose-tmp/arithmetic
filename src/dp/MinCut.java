package dp;

/**
 * @author - ZwZ
 * @date - 2021/3/29 - 21:01
 * @Description:132. 分割回文串 II
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 * 返回符合要求的 最少分割次数 。
 * <p>
 * <p>
 * 示例 1：
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * <p>
 * 示例 2：
 * 输入：s = "a"
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：s = "ab"
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii
 */
public class MinCut {
    /**
     * 非二叉树的题目中 只要是題目中说“最XXX”一般都是使用贪心或者DP
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        //dp[i]:子串s[0...i]最少分割次数
        int[] dp = new int[s.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = i;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (judge(s.substring(j, i + 1))) {
                    if (j == 0) {
                        dp[i] = 0;
                        break;
                    }
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * 判斷一個字符串是不是回文串  这个judge会造成重复计算
     * 可以优化：使用boolean[][]优化，arr[i][j]意思为：s[i...j]此子串是否为回文子串
     *
     * @param str
     * @return
     */
    public boolean judge(String str) {
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i++) != str.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MinCut minCut = new MinCut();
        System.out.println(minCut.minCut("aab"));
        ;
    }
}
