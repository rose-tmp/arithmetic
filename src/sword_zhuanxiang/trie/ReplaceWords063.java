package sword_zhuanxiang.trie;

import java.util.List;

/**
 * @author: ZwZ
 * @date: 2022-08-14 21:29
 * @desc:剑指 Offer II 063. 替换单词
 * 在英语中，有一个叫做 词根(root) 的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为
 * 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 * 现在，给定一个由许多词根组成的词典和一个句子，需要将句子中的所有继承词用词根替换掉。
 * 如果继承词有许多可以形成它的词根，则用最短的词根替换它。需要输出替换之后的句子。
 * <p>
 * 示例 1：
 * <p>
 * 输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 * 示例 2：
 * <p>
 * 输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * 输出："a a b c"
 * 示例 3：
 * <p>
 * 输入：dictionary = ["a", "aa", "aaa", "aaaa"], sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
 * 输出："a a a a a a a a bbb baba a"
 * 示例 4：
 * <p>
 * 输入：dictionary = ["catt","cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 * 示例 5：
 * <p>
 * 输入：dictionary = ["ac","ab"], sentence = "it is abnormal that this solution is accepted"
 * 输出："it is ab that this solution is ac"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] 仅由小写字母组成。
 * 1 <= sentence.length <= 10^6
 * sentence 仅由小写字母和空格组成。
 * sentence 中单词的总量在范围 [1, 1000] 内。
 * sentence 中每个单词的长度在范围 [1, 1000] 内。
 * sentence 中单词之间由一个空格隔开。
 * sentence 没有前导或尾随空格。
 * <p>
 * <p>
 * 注意：本题与主站 648 题相同： https://leetcode-cn.com/problems/replace-words/
 */
public class ReplaceWords063 {
    /**
     * 在Trie062的字典树的数据结构上做了定制化处理
     */
    static class Trie {
        //当前节点的子节点的指针,大小固定为26
        Trie[] children;
        //是否为叶子节点,即该节点是否是字符串的结尾
        boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        /**
         * 前缀树中的根节点不代表任何字符,insert(String word)中一开始node = this也可实现这个逻辑
         */
        private void insert(String word) {
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
    }

    /**
     * 思路: 利用字典树
     *
     * 如果是笔试的时候，就直接用哈希表+暴力的方式解决->详见leetcode官方题解方法1
     */
    Trie root = new Trie();//字典树的根节点

    public String replaceWords(List<String> dictionary, String sentence) {
        String[] sArr = sentence.split(" ");
        //构建字典树
        buildTire(dictionary);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sArr.length; i++) {
            //寻找词根
            String r = getRoot(sArr[i]);
            sb.append(r);
            sb.append(" ");
        }
        sb = sb.deleteCharAt(sb.length() - 1);//删除添加的最后一个" "
        return sb.toString();
    }

    /**
     * 将词根建成字典树
     */
    private void buildTire(List<String> dictionary) {
        for (String s : dictionary) {
            root.insert(s);
        }
    }

    /**
     * 在创建好的字典树中寻找给定单词的词根
     *
     * @return 当前单词的词根->如果字典树中不包含当前单词的词根,则返回该单词
     */
    private String getRoot(String word) {
        StringBuilder sb = new StringBuilder();
        //字典树中可能含有当前单词的词根->寻找最短的词根并返回
        Trie node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            //当前字符在字典树中找不到
            if (node.children[index] == null) {
                return word;
            }
            //找到一个词根
            if (node.children[index].isEnd) {
                sb.append(c);
                return sb.toString();
            }
            //当前字符在字典树中可以找到,并且这个字符还不是某个词根的结尾
            sb.append(c);
            node = node.children[index];
        }
        return sb.toString();
    }
}
