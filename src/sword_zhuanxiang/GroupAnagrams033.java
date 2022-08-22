package sword_zhuanxiang;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/8/5-10:43 - 周五
 * @Description:剑指 Offer II 033. 变位词组
 * 给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。
 * 注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 * <p>
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 * <p>
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 * <p>
 * <p>
 * 注意：本题与主站 49 题相同： https://leetcode-cn.com/problems/group-anagrams/
 */
public class GroupAnagrams033 {
    /**
     * 如果两个字符串之间是易位词那么对于两个字符串中的字符排序之后所形成的字符串相同
     * 用该字符串做key list做value
     * <p>
     * 时间复杂度:O(nklog k)，其中 n是strs 中的字符串的数量，k是 strs中的字符串的的最大长度。
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
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

    /**
     * 通过对26个字母创建标记数组去代替groupAnagrams1中的对每个字符串的排序
     * 从而降低时间复杂度为：o(n(k+26))
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        //key:每个字母出现的次数拼接成的字符串 value:所对应的strs中的字符串的集合
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();

        for (String s : strs) {
            //记录每个字母的出现次数
            int[] counts = new int[26];
            for (int i = 0; i < s.length(); i++) {
                counts[s.charAt(i) - 'a']++;
            }

            //将每个字母出现的次数拼接成一个字符串作为map的key
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                //必须在数量的前边拼接上字符 要不然无法通过全部示例
                key.append((char) ('a' + i));
                key.append(counts[i]);
            }
            List<String> list = map.getOrDefault(key.toString(), new ArrayList<>());
            list.add(s);
            map.put(key.toString(), list);
        }
        for (String k : map.keySet()) {
            ans.add(map.get(k));
        }
        return ans;
    }
}
