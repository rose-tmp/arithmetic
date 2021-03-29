package leetcode.backtrackingAndDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2021/3/29 - 20:33
 * @Description:131. 分割回文串
 * 這道題還有一個变形题目：132. 分割回文串 II 是使用的动态规划
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串 。返回 s 所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * <p>
 * 示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 * <p>
 * 提示：
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 */
public class Partition {
    List<List<String>> res = new ArrayList<>();

    /**
     * 首先這道題很明顯可以選擇枚舉出來所有的子串 然後判斷他們每一個子串是不是回文串
     * 而且題目上邊有告訴：1 <= s.length <= 16 可見用回溯去暴力解的話應該是可以通過所有的示例的
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        dfs(new ArrayList<>(), s);
        return res;
    }

    public void dfs(List<String> list, String s) {
        if (s.equals("")) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 1; i < s.length() + 1; i++) {
            String temp = s.substring(0, i);
            if (judge(temp)) {
                list.add(s.substring(0, i));
                dfs(list, s.substring(i));
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 判斷一個字符串是不是回文串
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
}
