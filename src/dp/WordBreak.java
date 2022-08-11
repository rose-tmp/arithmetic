package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2020/11/1 - 10:18
 * @Description: 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，
 * 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * <p>
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * <p>
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 * 注意你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 */
public class WordBreak {
    List<String> wordRes = new ArrayList<>();

    /**
     * 20220722
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        dfs7(new ArrayList<>(), s, wordDict, 0);
        return wordRes;
    }

    public void dfs7(List<String> list, String s, List<String> wordDict, int index) {
        if (index == s.length()) {
            StringBuilder sb = new StringBuilder();
            for (String value : list) {
                sb.append(value);
                sb.append(" ");
            }
            wordRes.add(sb.substring(0, sb.length() - 1));
            return;
        }
        if (index > s.length()) {
            return;
        }
        for (int i = index; i <= s.length(); i++) {
            String cur = s.substring(index, i);
            if (wordDict.contains(cur)) {
                list.add(cur);
                dfs7(list, s, wordDict, i);
                list.remove(list.size() - 1);
            }
        }
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return false;
        }
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j + 1, i + 1))) {
                    dp[i] = true;
                }
            }
            if (wordDict.contains(s.substring(0, i + 1))) {
                dp[i] = true;
            }
        }
        return dp[dp.length - 1];
    }
    /*public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        *//*dp[i] = dp[j] && check(s.subString(j,i)) 其中0 < j < i
     *dp[i]表示字符串 s 前 i 个字符组成的字符串 s[0..i−1]是否能被空格拆分成若干个字典中出现的单词
     *//*
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }*/

}
