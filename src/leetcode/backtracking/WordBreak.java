package leetcode.backtracking;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2020/11/1 - 9:35
 * @Description:140. 单词拆分 II
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，
 * 使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * 说明：
 * <p>
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * <p>
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * <p>
 * 示例 2：
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break-ii
 */
public class WordBreak {
    /**
     * 完全使用回溯算法解决
     */
    public List<String> wordBreak1(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if (s.length() == 0) {
            return res;
        }
        //剪枝 s中出现的字符必须在wordDict中出现 否则直接返回false  set集合中不存储重复元素
        Set<Character> set = new HashSet<>();
        for (String str : wordDict) {
            for (int i = 0; i < str.length(); i++) {
                set.add(str.charAt(i));
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if(!set.contains(s.charAt(i))){
                return res;
            }
        }
        backTrack(res, new StringBuilder(), s, wordDict, 0, 0);
        return res;
    }

    /**
     * begin:匹配开始时 s字符串的下标
     * countBlankSpace:加入的空格的数量，作为删除某个单词时的偏移量
     */
    public void backTrack(List<String> res, StringBuilder sb, String s,
                          List<String> wordDict, int begin, int countBlankSpace) {
        //以遍历完字符串s作为base case
        if (begin == s.length()) {
            sb.deleteCharAt(sb.length() - 1);//删除末尾添加的空字符
            res.add(sb.toString());
            return;
        }
        //在单词字典中查找是否有当前word
        for (int i = begin; i < s.length(); i++) {
            String temp = s.substring(begin, i + 1);
            if (wordDict.contains(temp)) {
                sb.append(temp + ' ');
                backTrack(res, sb, s, wordDict, i + 1, countBlankSpace + 1);
                //i在countBlankSpace后又加2是为了将要删除的此单词后面加入的空字符也删除还有一个原因是delete()前包后不包含
                sb.delete(begin + countBlankSpace, i + 2 + countBlankSpace);
            } else {
                continue;
            }
        }
    }

    /**
     * 完全看不懂这个方法是什么意思，，，，，，，
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        Map<String, List<String>> map = new HashMap<>(); //该哈希表用来存储字符串对应的句子列表
        Set<String> dict = new HashSet<>();
        for (String str : wordDict) //将词典转换为哈希表，也可以直接用词典的contains方法
        {
            dict.add(str);
        }
        return helper(s, map, dict);
    }

    public List<String> helper(String s, Map<String, List<String>> map, Set<String> dict) {
        if (map.containsKey(s)) //首先，看一下字符串s是否已经存在在Map中，如果存在，直接返回对应的值也就是句子列表
        {
            return map.get(s);
        }
        List<String> cur = new ArrayList<>();
        if (dict.contains(s)) //这一步很重要，对应着递归的最底层
        {
            cur.add(s);
        }
        for (int i = 0; i < s.length(); i++) {
            String subRight = s.substring(i); //找子串
            if (!dict.contains(subRight)) {
                continue;
            }
            // 如果右边的字符串在字典里面
            List<String> subLeft = helper(s.substring(0, i), map, dict); //递归找左边的子串对应的句子列表
            List<String> tmpp = append(subLeft, subRight); //将左边的子串返回的句子列表跟右边的单词一一拼接
            for (String tt : tmpp) {
                cur.add(tt); //将所有句子加入到结果中
            }
        }
        map.put(s, cur); //放入表里
        return map.get(s); //返回该字符串对应的句子列表
    }

    public List<String> append(List<String> tmp, String str) {
        List<String> build = new ArrayList<>();
        for (String ss : tmp) //将句子列表中的每一个句子跟单词拼接，然后生成一个新的句子列表
        {
            StringBuilder sb = new StringBuilder();
            sb.append(ss).append(" ").append(str);
            build.add(sb.toString());
        }
        return build; //返回结果
    }
}
