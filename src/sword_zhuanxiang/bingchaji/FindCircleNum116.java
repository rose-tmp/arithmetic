package sword_zhuanxiang.bingchaji;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-08-17 20:51
 * @desc:剑指 Offer II 116. 省份数量
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，
 * 且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1
 * 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 * <p>
 * 示例 1：
 * <p>
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 * <p>
 * 注意：本题与主站 547 题相同： https://leetcode-cn.com/problems/number-of-provinces/
 * <p>
 * 听张赛说，这是他华为校招提前批二面的面试题
 */
public class FindCircleNum116 {
    /**
     * 并查集
     * findCircleNum1是直接copy的UnionFind里边的代码
     * 但是代码很臃肿，没必要这么多代码，所以在findCircleNum2中对并查集的核心代码做了"瘦身”
     * <p>
     * dfs和bfs也可以做，收录在了dfsANDbfs包中
     */
    public int findCircleNum1(int[][] isConnected) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < isConnected.length; i++) {
            list.add(i);
        }
        UnionFind unionFind = new UnionFind(list);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }

        int ans = 0;
        //统计省份数量 -> 谁的father指向了自己,谁就是头，有几个头就代表有几个省份
        for (Integer i : unionFind.fatherMap.keySet()) {
            if (unionFind.fatherMap.get(i) == i) {
                ans++;
            }
        }
        return ans;
    }

    private class UnionFind {
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
         * <p>
         * 在查找过程中会将集合进行压缩，即查询时走过的所有节点都挂到根节点上去
         * 也可以不使用递归：使用一个栈或者其他数据结构存储每次查询向上跑的时候经过的节点
         * 当找到根节点的时候，就在数据结构中依次拿出来节点将它们的father改成根节点
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
     * “瘦身”之后的代码
     */
    public int findCircleNum2(int[][] isConnected) {
        /*
        * “瘦身”后的代码这里的parents就是UnionFind.java中的fatherMap
        * 但是没有定义sizeMap对应的数据结构,因为sizeMap的目的是union()中
        * 根据集合的大小决定”谁挂在谁下边“,这里是随即挂的,所以不需要
        * */
        int[] parents = new int[isConnected.length];
        //初始化并查集
        for (int i = 0; i < parents.length; i++) {
            //把每一个位置上的“父”都指向自己
            parents[i] = i;
        }

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1) {
                    union(parents, i, j);
                }
            }
        }

        int ans = 0;
        //统计省份数量 -> 谁的father指向了自己,谁就是头，有几个头就代表有几个省份
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == i) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * @param parent parent[i]存储了i节点的从属的集合中的”老大“是哪个节点
     * @return 返回i的最上层的节点 这个代码的逻辑和UnionFind.java中的findHead一模一样
     * 即在查找过程中会将集合进行压缩，即查询时走过的所有节点都挂到根节点上去
     */
    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        //找它的爹
        int f = find(parent, parent[i]);
        //更新它的爹
        parent[i] = f;
        return f;
    }

    /**
     * 将i和j所在的两个集合合并
     *
     * @param parent parent[i]=j表示i节点的从属的集合中的”老大“是节点j
     */
    private void union(int[] parent, int i, int j) {
        //这里是随机挂的，并没有和UnionFind中一样遵循"小挂大"的原则
        parent[find(parent, i)] = find(parent, j);
    }
}
