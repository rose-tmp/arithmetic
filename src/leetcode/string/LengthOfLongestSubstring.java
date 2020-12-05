package leetcode.string;

import java.util.HashSet;

/**
 * @author - ZwZ
 * @date - 2020/9/28 - 11:10
 * @Description: 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 * <p>
 * 示例1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();//字符串长度
        if(len == 0 || len == 1)
            return len;
        if(len == 2){
            if(s.charAt(0) == s.charAt(1)){
                return 1;
            }else{
                return 2;
            }
        }
        HashSet<Character> set = new HashSet();//Hash集合
        int right = -1;
        int res = 0;
        for (int left = 0; left < len; left++) {
            //Hash中删除上一个左边界对应的元素  本质上相当于将左边界index向右滑动
            if(left != 0){
                set.remove(s.charAt(left - 1));
            }
            //扩大右边界
            while(right + 1 < len && !set.contains(s.charAt(right + 1))){
                set.add(s.charAt(right + 1));
                right++;
            }
            res = Math.max(res,right - left +1);
        }
        return res;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring substring = new LengthOfLongestSubstring();
        String str = "aab";
        System.out.println(substring.lengthOfLongestSubstring(str));
    }
}
