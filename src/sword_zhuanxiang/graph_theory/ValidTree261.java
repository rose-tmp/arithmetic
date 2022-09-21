package sword_zhuanxiang.graph_theory;

/**
 * @author: ZwZ
 * @date: 2022-09-06 19:51
 * @desc:这道题需要plus会员才能看
 * 给定编号从 0 到 n - 1的n 个结点。给定一个整数n和一个edges列表，
 * 其中edges[i] = [ai, bi]表示图中节点ai和bi之间存在一条无向边。
 * 如果这些边能够形成一个合法有效的树结构，则返回 true ，否则返回 false 。
 * 示例 1：
 * <p>
 * 输入: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
 * 输出: false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 2000
 * 0 <= edges.length <= 5000
 * edges[i].length == 2
 * 0 <= ai, bi< n
 * ai!= bi
 * 不存在自循环或重复的边
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/graph-valid-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidTree261 {
    /**
     * 思路:这道题就是给定无环图中的所有的边，去判断这个无环图是否含有环
     * 解决办法:遍历所有的边，使用并查集，每条边两端的点都加入到同一个集合中，
     * 如果遍历的过程中，遇到了某条边两端的点本就属于同一个集合，这就说明它俩连通了，即存在环
     * <p>
     * 以上解决了是否存在环的问题，这道题还有一个问题就是:没有环不代表就一定能够构成树，还要保证连通，即所有节点之间的连通性
     * 因为连通且能构成树的话，边的数量一定是n-1，所以可以用这个去判断
     */
    public boolean validTree(int n, int[][] edges) {
        /*
         * 没有环不代表就一定能够构成树，还要保证连通，即所有节点之间的连通性
         * 因为连通且能构成树的话，边的数量一定是n-1，所以可以用这个去判断
         * */
        if (edges.length != n - 1) {
            return false;
        }

        int[] father = new int[n];
        //并查集数组初始化
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            /*edges[i][0]和edges[i][1]这俩点已经连通了->
             * 因为find(edges[i][0]) == find(edges[i][1])就说明从它俩各自出发都可以到达某节点
             * 而又因为edges[i][0]和edges[i][1]之间又有一条边
             * 所以肯定是存在环的
             * */
            if (find(father, edges[i][0]) == find(father, edges[i][1])) {
                return false;
            } else {
                union(father, edges[i][0], edges[i][1]);
            }
        }
        return true;
    }

    /*
     * 查找i的爹是谁
     * */
    public int find(int[] father, int i) {
        if (father[i] == i) {
            return i;
        }
        int f = find(father, father[i]);
        father[i] = f;
        return f;
    }

    public void union(int[] father, int i, int j) {
        father[find(father, i)] = find(father, j);
    }
}
