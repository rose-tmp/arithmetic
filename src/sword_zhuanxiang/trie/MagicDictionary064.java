package sword_zhuanxiang.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZwZ
 * @date: 2022-08-15 19:05
 * @desc:剑指 Offer II 064. 神奇的字典
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。
 * 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于已构建的神奇字典中。
 * 实现 MagicDictionary 类：
 * <p>
 * MagicDictionary() 初始化对象
 * void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
 * bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 示例：
 * <p>
 * 输入
 * inputs = ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * inputs = [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * 输出
 * [null, null, false, true, false, false]
 * <p>
 * 解释
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // 返回 False
 * magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
 * magicDictionary.search("hell"); // 返回 False
 * magicDictionary.search("leetcoded"); // 返回 False
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= dictionary.length <= 100
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] 仅由小写英文字母组成
 * dictionary 中的所有字符串 互不相同
 * 1 <= searchWord.length <= 100
 * searchWord 仅由小写英文字母组成
 * buildDict 仅在 search 之前调用一次
 * 最多调用 100 次 search
 * <p>
 * <p>
 * 注意：本题与主站 676 题相同： https://leetcode-cn.com/problems/implement-magic-dictionary/
 */
public class MagicDictionary064 {
    List<String> list;

    public MagicDictionary064() {
        list = new ArrayList<>();
    }

    public void buildDict(String[] dictionary) {
        for (String s : dictionary) {
            list.add(s);
        }
    }

    public boolean search(String searchWord) {
        for (int i = 0; i < list.size(); i++) {
            int count = 0;//修改次数
            int j = 0;
            for (j = 0; j < searchWord.length(); j++) {
                if (j < list.get(i).length() && searchWord.charAt(j) != list.get(i).charAt(j)) {
                    count++;
                }
                if (count > 1) {
                    break;
                }
            }
            //刚好修改了一次并且词典中该单词刚好遍历完
            if (count == 1 && (j == list.get(i).length())) {
                return true;
            }
        }
        return false;
    }
    /*class Trie {
        //当前节点的子节点的指针,大小固定为26
        Trie[] children;
        //是否为叶子节点,即该节点是否是字符串的结尾
        boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        *//**
     * Inserts a word into the trie.
     * 前缀树中的根节点不代表任何字符,insert(String word)中一开始node = this也可实现这个逻辑
     *//*
        public void insert(String word) {
            //用于遍历前缀树的指针 该指针从根节点一直向下移动
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            //将最后一个节点的叶子节点标记设置为true
            node.isEnd = true;
        }

        *//**
     * 在前缀树的search( )的基础上做修改
     *//*
        public boolean canFind(String word) {
            Trie node = this;
            int count = 0;//记录匹配不上的字符的数量
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                //指向下一个节点，继续for循环去在下一个节点的children中寻找word[i+1]
                if (node.children[index] != null) {
                    node = node.children[index];
                } else if (node.children[index] == null && count == 0) {
                    count++;
                    node = node.children[index];
                }
                //向下没有路了 但是word中的字符还没有全部匹配上
                else if (node.children[index] == null && count > 1) {
                    return false;
                }
            }
            return (node.isEnd && count == 1);
        }
    }

    *//**
     * Initialize your data structure here.
     *//*
    Trie root;

    public MagicDictionary064() {
        this.root = new Trie();
    }

    public void buildDict(String[] dictionary) {
        for (String s : dictionary) {
            root.insert(s);
        }
    }

    public boolean search(String searchWord) {
        return root.canFind(searchWord);
    }*/
}
