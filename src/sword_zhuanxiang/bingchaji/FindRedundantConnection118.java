package sword_zhuanxiang.bingchaji;

/**
 * @author: ZwZ
 * @date: 2022-08-18 10:23
 * @desc:剑指 Offer II 118. 多余的边
 * 树可以看成是一个连通且 无环 的 无向 图。
 * 给定往一棵 n 个节点 (节点值 1～n) 的树中添加一条边后的图。
 * 添加的边的两个顶点包含在 1 到 n 中间，且这条附加的边不属于树中已存在的边。
 * 图的信息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。
 * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。
 * 如果有多个答案，则返回数组 edges 中最后出现的边。
 * <p>
 * 示例 1：
 * <p>
 * 输入: edges = [[1,2],[1,3],[2,3]]
 * 输出: [2,3]
 * 示例 2：
 * <p>
 * 输入: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
 * 输出: [1,4]
 * <p>
 * <p>
 * 提示:
 * <p>
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai < bi <= edges.length
 * ai != bi
 * edges 中无重复元素
 * 给定的图是连通的
 * <p>
 * <p>
 * 注意：本题与主站 684 题相同： https://leetcode-cn.com/problems/redundant-connection/
 */
public class FindRedundantConnection118 {
    public int[] findRedundantConnection(int[][] edges) {
        /*并查集
         * edges.length表示边的条数，因为该图中由一颗二叉树添加一条边构成的
         * 所以边的条数刚好等于节点个数
         * edges.length + 1是因为edges中节点的编号是从1开始计数而不是0，
         * 这里错开一位是为了使得parents[i]中的i和edges中存储的节点编号对应
         * 而不是edges[i]表示edges中编号为i+1的节点
         * */
        int[] parents = new int[edges.length + 1];
        //初始化并查集
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            if (isSameSet(parents, node1, node2)) {
                return edges[i];
            }
            union(parents, node1, node2);
        }
        return null;
    }

    /**
     * @return 返回i的最上层的节点，即它所属的集合中的"老大"
     */
    private int find(int[] parents, int i) {
        if (parents[i] == i) return i;
        int f = find(parents, parents[i]);
        parents[i] = f;
        return f;
    }

    /**
     * 将i和j所在的两个集合合并
     */
    private void union(int[] parent, int i, int j) {
        parent[find(parent, i)] = find(parent, j);
    }

    /**
     * 判断两个节点是否在同一个集合中
     */
    private boolean isSameSet(int[] parents, Integer i, Integer j) {
        return find(parents, i) == find(parents, j);
    }
}
