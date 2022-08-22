package sword_zhuanxiang.bingchaji;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-08-15 21:36
 * @desc:剑指 Offer II 106. 二分图
 * 存在一个 无向图 ，图中有 n 个节点。其中每个节点都有一个介于 0 到 n - 1 之间的唯一编号。
 * 给定一个二维数组 graph ，表示图，其中 graph[u] 是一个节点数组，由节点 u 的邻接节点组成。
 * 形式上，对于 graph[u] 中的每个 v ，都存在一条位于节点 u 和节点 v 之间的无向边。该无向图同时具有以下属性：
 * 不存在自环（graph[u] 不包含 u）。
 * 不存在平行边（graph[u] 不包含重复值）。
 * 如果 v 在 graph[u] 内，那么 u 也应该在 graph[v] 内（该图是无向图）
 * 这个图可能不是连通图，也就是说两个节点 u 和 v 之间可能不存在一条连通彼此的路径。
 * 二分图 定义：如果能将一个图的节点集合分割成两个独立的子集 A 和 B ，并使图中的每一条边的两个节点一个来自 A 集合，一个来自 B 集合，就将这个图称为 二分图 。
 * 如果图是二分图，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * 输出：false
 * 解释：不能将节点分割成两个独立的子集，以使每条边都连通一个子集中的一个节点与另一个子集中的一个节点。
 * 示例 2：
 * <p>
 * 输入：graph = [[1,3],[0,2],[1,3],[0,2]]
 * 输出：true
 * 解释：可以将节点分成两组: {0, 2} 和 {1, 3} 。
 * <p>
 * 提示：
 * <p>
 * graph.length == n
 * 1 <= n <= 100
 * 0 <= graph[u].length < n
 * 0 <= graph[u][i] <= n - 1
 * graph[u] 不会包含 u
 * graph[u] 的所有值 互不相同
 * 如果 graph[u] 包含 v，那么 graph[v] 也会包含 u
 * <p>
 * <p>
 * 注意：本题与主站 785 题相同： https://leetcode-cn.com/problems/is-graph-bipartite/
 */
public class IsBipartite106 {

    /**
     * 拷贝UnionFind中的关于并查集的代码
     * 在此基础上做修改 -> Node直接删掉了，改成了直接存储Integer
     */
    private static class UnionFind {
        //Integer1:某节点 Integer2:此节点所在集合的最上方节点
        public HashMap<Integer, Integer> fatherMap;
        //Integer指当前节点所在的整个集合的大小
        public HashMap<Integer, Integer> sizeMap;

        public UnionFind(List<Integer> Integers) {
            fatherMap = new HashMap<Integer, Integer>();
            sizeMap = new HashMap<Integer, Integer>();
            makeSets(Integers);
        }

        public void makeSets(List<Integer> Integers) {
            fatherMap.clear();
            sizeMap.clear();
            for (Integer Integer : Integers) {
                //一开始的时候每一个节点单独构成一个集合
                fatherMap.put(Integer, Integer);
                sizeMap.put(Integer, 1);
            }
        }

        /**
         * 查找某个节点所在的集合的最上边的节点
         */
        private Integer findHead(Integer Integer) {
            Integer father = fatherMap.get(Integer);
            //如果它的上边的节点不是它自身就一直想上找，直到找到指向自身的那个节点 即根节点
            if (father != Integer)
                father = findHead(father);
            fatherMap.put(Integer, father);
            return father;
        }

        /**
         * 判断两个节点是否在相同的集合中
         */
        public boolean isSameSet(Integer a, Integer b) {
            return findHead(a) == findHead(b);
        }

        /**
         * 合并两个节点所在的集合
         */
        public void union(Integer a, Integer b) {
            if (a == null || b == null) {
                return;
            }
            //找到各自所在集合的头节点
            Integer aHead = findHead(a);
            Integer bHead = findHead(b);
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                //小的集合挂在大集合上
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aHead, bHead);
                    sizeMap.put(bHead, aSetSize + bSetSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize);
                }
            }
        }
    }


    /**
     * 这道题也收录到了dfs包中  因为除了并查集的方法还可以用深搜和广搜
     * 或者说图的问题一般都是用深搜或者广搜
     * <p>
     * 思路的中心思想:因为两个相连的节点肯定不能在同一个集合里，而题目要求是最终把所有的节点放在两个不同的集合中
     * 所以可以用并查集试图将所有节点分为两部分
     * 拿示例1举例：
     * 操作1. 起初遍历节点0的所有连通节点，因为0一定不能和其连通的节点在同一个集合中，所以将[1,2,3]放在同一个集合中
     * （1 2 3）一定都在同一个集合中，因为一共有两个集合，如果他们不都在同一个集合中，则意味着必定有节点和0在同一个集合
     * 遍历完节点0后，此时的结合状态从[[0][1][2][3]]变成了[[0][1 2 3]]其中在[1 2 3]中1是最上层节点
     * 操作2. 遍历节点1的所有连通节点，1不能和[0 2]放在同一个集合中，然而在遍历到2的时候，因为在操作1中，已经把1 2放入了同一个
     * 集合中，就意味着操作1中要求1 2必须在同一个集合中（一共有两个集合，他俩不在同一个集合的话就意味着其中一个一定和0在同一个集合中），
     * 而操作2中要求1 2必须不能放在同一个集合中，发生了矛盾，所以一定完不成”把所有节点放在两个不同集合中“的要求，直接return false
     * <p>
     * 时间复杂度:O(N^2) : 因为不像bfs和dfs中那样，每条边只遍历一次 在并查集这种方法中，每条边遍历很多次
     * 以上关于时间复杂度的分析不知道对不对（leetcode上没有人分析）
     */
    public boolean isBipartite(int[][] graph) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            list.add(i);
        }
        //初始化并查集
        UnionFind unionFind = new UnionFind(list);
        for (int i = 0; i < graph.length; i++) {
            int[] arr = graph[i];
            for (int j = 0; j < arr.length; j++) {
                //两个连通的节点在同一个集合里
                if (unionFind.isSameSet(i, arr[j])) {
                    return false;
                }
                //将与i节点相连的所有节点加入到同一个集合中->这个集合就是i节点的对立集合
                unionFind.union(arr[0], arr[j]);
            }
        }
        return true;
    }
}
