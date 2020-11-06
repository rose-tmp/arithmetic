package leetcode.BFS;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2020/11/5 - 9:23
 * @Description:127. 单词接龙
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord
 * 的最短转换序列的长度。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * <p>
 * 说明:
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * <p>
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出: 5
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * <p>
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: 0
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 */
public class LadderLength {
    /**
     * 无向图中两个节点之间的最短路径的长度可以由广度优先遍历得到
     * 因为可以把起点和终点所在的路径拉直来看，两点之间线段最短
     * 所以本题就是使用了图论，先建模将问题转化为图论中的相关问题然后再进行BFS
     * <p>
     * 该算法的大致思路：
     * 大体框架是广度优先遍历，在广度优先遍历的过程中每遍历到一个字符串都按顺序将每一个字符串
     * 中的每一个位置依次换成a~z 然后去判断给定的字典中有没有相对应的字符串
     * 如果有，则说明他在无向图中是与当前节点连接的下一个节点
     * 如果此时这个节点没有被访问过，那么就要将他加入到队列中
     * <p>
     * (根据思路可以画出一个无向图便于理解)
     * https://leetcode-cn.com/problems/word-ladder/solution/yan-du-you-xian-bian-li-shuang-xiang-yan-du-you-2/
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);//用于快速判断单词是否在List中
        //集合中不存在单词endWorld
        if (!wordSet.contains(endWord) || wordList.size() == 0) {
            return 0;
        }
        /*图的BFS必须用一个存储节点的队列和一个visited集合 其中visited用来判断某节点是否访问过
        因为图中存在环，如果不对已经访问过的节点加以标记的话会形成死循环*/
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        int step = 1;//扩散的步数包含起点 所以step初始化为1
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            //遍历队列当前存在的所有元素
            for (int i = 0; i < currentSize; i++) {
                String word = queue.poll();//出队
                char[] wordArray = word.toCharArray();
                //开始遍历出队的字符串的每一个位置
                for (int j = 0; j < wordArray.length; j++) {
                    char temp = wordArray[j];
                    //对每一个位置进行从a~z的改写
                    for (char k = 'a'; k <= 'z'; k++) {
                        //此时说明向下扩展的时候此位置上的这个字符不会对下一步有影响
                        if (wordArray[j] == k) {
                            continue;
                        }
                        //修改当前位置的字符，用于下面转换成字符串后在wordSet中查询
                        wordArray[j] = k;
                        //将数组再转化为字符串后 去wordList字典中寻找 看是否有此字符串
                        String newWord = String.valueOf(wordArray);
                        if (wordSet.contains(newWord)) {
                            //已经找到了endWord
                            if (newWord.equals(endWord)) {
                                return step + 1;
                            }
                            //说明newWord是在无向图中与当前字符串连接且没有加入到队列中的字符串
                            if (!visited.contains(newWord)) {
                                queue.add(newWord);
                                visited.add(newWord);
                            }
                        }
                    }
                    //恢复之前修改过的此位置
                    wordArray[j] = temp;
                }
            }
            step++;
        }
        return 0;
    }
    /**
     *
     *
     * */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList){
        return 0;
    }
    public static void main(String[] args) {
        LadderLength length = new LadderLength();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> list = new ArrayList<>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");
        System.out.println(length.ladderLength1(beginWord,endWord,list));;
    }
}
