package zuoPrimary.shu;

/**
 * @author - ZwZ
 * @date - 2020/3/27 - 14:48
 * @Description:前缀树（又称为字典树）
 */
public class TrieTree {
    public static class TrieNode {
        public int path;//有多少个字符串经过过这个节点 用来删除(当path==1的时候说明只有一个字符串在用这个节点，删除这个字符串的时候就可以删除，要是path>1说明有不止一个字符串在使用它，所以就不可以删除)也可以用来查询以某个字符串的为前缀的字符串的数量是多少  
        public int end;//有多少个字符串的终点是这个节点
        public TrieNode[] nexts;//此节点下面可能会出现多少路 就是通过它来记录的每个节点下面的连接线上的字母

        public TrieNode() {
            path = 0;
            end = 0;
            nexts = new TrieNode[26];//这里默认使用的是26个字母，所以一个节点下面的边只可能是26种
        }
    }

    private TrieNode root;

    public TrieTree() {
        root = new TrieNode();//头
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }
        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';//ASCII码作为数组下标 表示哪条路上有了向下的节点
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];//指针向下走
            node.path++;
        }
        node.end++;
    }

    /**
     *  
     *
     * @Author: ZwZ
     * @Description:删除 
     * @Param: [word] 
     * @return: void 
     * @Date: 2020/3/27-15:39
     */
    public void delete(String word) {
        if (search(word) != 0) {
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (--node.nexts[index].path == 0) {
                    node.nexts[index] = null;
                    return;//此时下面的字符串就不用再一个个的减了，直接拿掉(return)就可以了
                }
                node = node.nexts[index];
            }
            node.end--;
        }
    }

    /**
     *  
     *
     * @Author: ZwZ
     * @Description:查询word，即看word字符串是否插入过此树中 
     * @Param: [word] 
     * @return: int 
     * @Date: 2020/3/27-15:39
     */
    public int search(String word) {
        if (word == null) {
            return 0;
        }
        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.end;
    }

    /**
     *  
     *
     * @Author: ZwZ
     * @Description:查询以某个字符串的为前缀的字符串的数量是多少  
     * @Param: [pre] 
     * @return: int 
     * @Date: 2020/3/27-15:46
     */
    public int prefixNumber(String pre) {
        if (pre == null) {
            return 0;
        }
        char[] chs = pre.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.path;
    }

    public static void main(String[] args) {
        TrieTree trie = new TrieTree();
        System.out.println(trie.search("zuo"));
        trie.insert("zuo");
        System.out.println(trie.search("zuo"));
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.insert("zuo");
        trie.insert("zuo");
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.insert("zuoa");
        trie.insert("zuoac");
        trie.insert("zuoab");
        trie.insert("zuoad");
        trie.delete("zuoa");
        System.out.println(trie.search("zuoa"));
        System.out.println(trie.prefixNumber("zuo"));
    }
}
