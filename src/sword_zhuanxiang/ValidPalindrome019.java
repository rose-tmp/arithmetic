package sword_zhuanxiang;

/**
 * @author - ZwZ
 * @date - 2022/7/31-16:22 - 周日
 * @Description:剑指 Offer II 019. 最多删除一个字符得到回文
 * 给定一个非空字符串 s，请判断如果 最多 从字符串中删除一个字符能否得到一个回文字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "aba"
 * 输出: true ->删除0个也算是”最多删除一个字符"
 * 示例 2:
 * <p>
 * 输入: s = "abca"
 * 输出: true
 * 解释: 可以删除 "c" 字符 或者 "b" 字符
 * 示例 3:
 * <p>
 * 输入: s = "abc"
 * 输出: false
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length <= 105
 * s 由小写英文字母组成
 * <p>
 * <p>
 * 注意：本题与主站 680 题相同： https://leetcode-cn.com/problems/valid-palindrome-ii/
 */
public class ValidPalindrome019 {
    /**
     * 双指针
     * 贪心策略：如果两个位置上的元素不等，则分别删除两个之后判断剩下的字符串是否为回文串
     * 如果其中一个是，返回true;如果两个都不是，返回false
     */
    public boolean validPalindrome(String s) {
        return isValid(s, false);
    }

    /**
     * @param flag 是否为分割后的字符 如果是，并且有s.charAt(i)!=s.charAt(j)那么就不用在继续
     *             删除i或j位置上的元素继续递归，而是直接返回false
     *             因为题目要求只能删除一个元素
     */
    public boolean isValid(String s, boolean flag) {
        if (s.length() == 0) {
            return true;
        }
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else if (!flag) {
                return isValid(s.substring(left + 1, right + 1), true) ||
                        isValid(s.substring(left, right), true);
            } else if (flag) {
                return false;
            }
        }
        return true;
    }
}
