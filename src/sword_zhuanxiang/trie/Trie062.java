package sword_zhuanxiang.trie;

/**
 * @author: ZwZ
 * @date: 2022-08-14 20:52
 * @desc:剑指 Offer II 062. 实现前缀树
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，
 * 用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * 请你实现 Trie 类：
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * <p>
 * 示例：
 * 输入
 * inputs = ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * inputs = [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * <p>
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 * <p>
 * 注意：本题与主站 208 题相同：https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 */
public class Trie062 {
    //当前节点的子节点的指针,大小固定为26
    Trie062[] children;
    //是否为叶子节点,即该节点是否是字符串的结尾
    boolean isEnd;

    public Trie062() {
        children = new Trie062[26];
        isEnd = false;
    }

    /**
     * Inserts a word into the trie.
     * 前缀树中的根节点不代表任何字符,insert(String word)中一开始node = this也可实现这个逻辑
     */
    public void insert(String word) {
        //用于遍历前缀树的指针 该指针从根节点一直向下移动
        Trie062 node = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie062();
            }
            node = node.children[index];
        }
        //将最后一个节点的叶子节点标记设置为true
        node.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie062 node = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (node.children[index] != null) {
                //指向下一个节点，继续for循环去在下一个节点的children中寻找word[i+1]
                node = node.children[index];
            }
            //还没遍历完word的时候,向下没有"路"了或者该节点已经是叶子节点
            else if (node.children[index] == null) {
                return false;
            }
        }
        //包含单词word的话，遍历完word后指向最后一个节点的指针的isEnd一定要为true
        return node.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Trie062 node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int index = c - 'a';
            if (node.children[index] != null) {
                node = node.children[index];
            } else {
                return false;
            }
        }
        return true;
    }
}
