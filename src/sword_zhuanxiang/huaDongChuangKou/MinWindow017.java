package sword_zhuanxiang.huaDongChuangKou;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2022/7/30-18:27 - 周六
 * @Description:剑指 Offer II 017. 含有所有字符的最短字符串
 * 给定两个字符串 s 和 t 。返回 s 中包含 t 的所有字符的最短子字符串。如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。
 * 如果 s 中存在多个符合条件的子字符串，返回任意一个。
 * <p>
 * 注意： 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * <p>
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最短子字符串 "BANC" 包含了字符串 t 的所有字符 'A'、'B'、'C'
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3：
 * 输入：s = "a", t = "aa"
 * 输出：""
 * 解释：t 中两个字符 'a' 均应包含在 s 的子串中，因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * 提示：
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 * <p>
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 * <p>
 * 注意：本题与主站 76 题相似（本题答案不唯一）：https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class MinWindow017 {
    public static void main(String[] args) {
        MinWindow017 min = new MinWindow017();
        min.minWindow("ADOBECODEBANC", "ABC");
    }

    /**
     * 滑动窗口 官方题解的思路
     */
    public String minWindow(String s, String t) {
        int l1 = s.length(), l2 = t.length();
        if (l1 < l2) {
            return "";
        }
        int right = 1;
        int[] res = new int[2];
        int min = Integer.MAX_VALUE;
        //key:字符 value:出现次数
        Map<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0), 1);
        for (int left = 0; left < s.length(); left++) {
            if (left != 0) {
                map.put(s.charAt(left - 1), map.get(s.charAt(left - 1)) - 1);
            }
            while (right < s.length() && !isContainsAll(map, t)) {
                map.put(s.charAt(right), 1 + map.getOrDefault(s.charAt(right), 0));
                right++;
            }
            if (isContainsAll(map, t) && right - left < min) {
                min = right - left;
                res[0] = left;
                res[1] = right;
            }
        }
        return s.substring(res[0], res[1]);
    }

    public boolean isContainsAll(Map<Character, Integer> map, String t) {
        for (int i = 0; i < t.length(); i++) {
            if (!map.containsKey(t.charAt(i)) || map.get(t.charAt(i)) <= 0) {
                return false;
            }
        }
        Map<Character, Integer> mapT = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            mapT.put(t.charAt(i), mapT.getOrDefault(t.charAt(i), 0) + 1);
        }
        //防止个数不够
        for (Character key : mapT.keySet()) {
            if (map.get(key) < mapT.get(key)) {
                return false;
            }
        }

        return true;
    }
}
