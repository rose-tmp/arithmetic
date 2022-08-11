package sword_zhuanxiang;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/8/5-10:43 - 周五
 * @Description:剑指 Offer II 033. 变位词组
 * 给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。
 * 注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。
 *
 *
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 *
 *
 * 注意：本题与主站 49 题相同： https://leetcode-cn.com/problems/group-anagrams/
 */
public class GroupAnagrams033 {
    /**
     * 如果两个字符串之间是易位词那么对于两个字符串中的字符排序之后所形成的字符串相同
     * 用该字符串做key list做value
     * */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] c = strs[i].toCharArray();
            Arrays.sort(c);
            String str = new String(c);
            List<String> list = map.getOrDefault(str, new ArrayList<>());
            list.add(strs[i]);
            map.put(str, list);
        }
        for (String k : map.keySet()) {
            res.add(map.get(k));
        }
        return res;
    }
}
