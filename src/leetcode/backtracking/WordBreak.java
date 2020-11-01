package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<Integer> lengths = new ArrayList<>();
        //收集wordDicts中每一个单词的长度并存储在lenghs中(不重复)
        for (int i = 0; i < wordDict.size(); i++) {
            int len = wordDict.get(i).length();
            //如果lengths中不存在len则加入
            boolean flag = false;
            for (int j = 0; j < lengths.size(); j++) {
                if (lengths.get(j) == len) {
                    flag = true;
                    continue;
                }
            }
            if (flag == true)
                continue;
            else {
                lengths.add(len);
            }
        }
        //将集合中的元素加入数组 方便接下来使用sort()排序
        int[] lens = new int[lengths.size()];
        for (int i = 0; i < lengths.size(); i++) {
            lens[i] = lengths.get(i);
        }
        Arrays.sort(lens);
    }

    /**
     * lengths:记录wordDict中所有单词出现过的长度
     * indexLengths:lengths中的下标
     * indexPre:指向当前匹配的s中某个连续字符串(单词)的第一个下标
     * indexRear:指向当前匹配的s中某个连续字符串(单词)的最后一个下标
     */
    public void backTrack(List<String> res, StringBuilder sb, String s, List<String> wordDict,
                          int[] lens, int indexLens, int indexPre, int indexRear) {
        //以遍历完字符串s作为base case
        if (indexRear == s.length() - 1) {
            res.add(sb.toString());
        }
        StringBuilder word = new StringBuilder(s.substring(indexPre, indexRear + 1));
        //在单词字典中查找是否有当前word
        for (int i = 0; i < wordDict.size(); i++) {
            if (word.toString() == wordDict.get(i)) {
                sb.append(word + " ");
                //修改indexPre和indexRear的值
                indexPre = indexRear + 1;
                indexRear =
                continue;
            }
        }

        backTrack(res, sb, s, wordDict, lens,indexLens + 1, indexPre, indexRear);
    }
}
