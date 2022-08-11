package sword_zhuanxiang.huaDongChuangKou;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/7/30-18:03 - 周六
 * @Description:剑指 Offer II 016. 不含重复字符的最长子字符串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度。
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子字符串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子字符串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 *
 * 输入: s = ""
 * 输出: 0
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 *
 *
 * 注意：本题与主站 3 题相同：
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class LengthOfLongestSubstring016 {
    /**
     * 滑动窗口
     * 这里采用固定left然后right就一直自增的方式
     * 因为left++了之后，比现在的子字符串更长的不包含重复字符的串的right一定大于当前right
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int right = 1;
        int max = 1;
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        for (int left = 0; left < s.length(); left++) {
            //左括号右移
            if (left != 0) {
                set.remove(s.charAt(left - 1));
            }
            while (right < s.length() && !set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }
}
