package string;

import java.util.Arrays;

/**
 * @author - ZwZ
 * @date - 2020/11/22 - 11:15
 * @Description:242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * <p>
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 */
public class IsAnagram {
    /**
     * 题目的意思翻译成:s中的字符排序后和t中的字符排序后形成的单词是一样的
     */
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] word1 = s.toCharArray();
        char[] word2 = t.toCharArray();
        Arrays.sort(word1);
        Arrays.sort(word2);
        for (int i = 0; i < word1.length; i++) {
            if (word1[i] != word2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 如果是有效的字符异位词每个单词出现的次数都是一样的
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] word1 = new int[26];
        int[] word2 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            word1[s.charAt(i) - 'a']++;
            word2[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < word1.length; i++) {
            if(word1[i] != word2[i]){
                return false;
            }
        }
        return true;
    }
}
