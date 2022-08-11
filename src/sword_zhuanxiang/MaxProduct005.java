//只记录没写出来，具有挑战性或者使用的方法不够优化的题
package sword_zhuanxiang;

import java.util.HashSet;
import java.util.Set;

/**
 * @author - ZwZ
 * @date - 2022/7/26-14:18 - 周二
 * @Description:剑指 Offer II 005. 单词长度的最大乘积
 * 给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: words = ["abcw","baz","foo","bar","fxyz","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "fxyz"。它们不包含相同字符，且长度的乘积最大。
 * 示例 2:
 * <p>
 * 输入: words = ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * 示例 3:
 * <p>
 * 输入: words = ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 */
public class MaxProduct005 {
    /**
     * 借助位运算判断两个字符串中是否含有相同字符
     * */
    public int maxProduct1(String[] words) {
        if (words.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int[] mask = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                mask[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }
        for (int end = words.length - 1; end >= 0; end--) {
            for (int i = 0; i < end; i++) {
                if ((mask[end] & mask[i]) == 0) {
                    max = Math.max(max, words[end].length() * words[i].length());
                }
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }
    /**
     * 借助hash判断两个字符串中是否含有相同字符
     * */
    public int maxProduct2(String[] words) {
        if (words.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int end = words.length - 1;end >= 0;end--) {
            for (int i = 0;i < end;i++) {
                if (!haveSameChar(words[end],words[i])) {
                    max = Math.max(max,words[end].length() * words[i].length());
                }
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }
    public boolean haveSameChar(String word1,String word2) {
        Set<Character> set = new HashSet<>();
        for (int i = 0;i < word1.length();i++) {
            set.add(word1.charAt(i));
        }
        for(int j = 0;j < word2.length();j++) {
            if (set.contains(word2.charAt(j))) {
                return true;
            }
        }
        return false;
    }
}
