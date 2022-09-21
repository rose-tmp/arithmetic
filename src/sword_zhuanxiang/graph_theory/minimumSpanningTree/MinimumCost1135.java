package sword_zhuanxiang.graph_theory.minimumSpanningTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: ZwZ
 * @date: 2022-09-06 20:42
 * @desc:这道题需要plus会员才能看
 * 想象一下你是个城市基建规划者，地图上有n座城市，它们按以1 到n的次序编号。
 * 给你整数 n 和一个数组conections，其中connections[i] = [xi, yi, costi]
 * 表示将城市xi和城市yi连接所要的costi（连接是双向的）。
 * 返回连接所有城市的最低成本，每对城市之间至少有一条路径。如果无法连接所有 n个城市，返回 -1
 * 该 最小成本 应该是所用全部连接成本的总和。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3, conections = [[1,2,5],[1,3,6],[2,3,1]]
 * 输出：6
 * 解释：选出任意 2 条边都可以连接所有城市，我们从中选取成本最小的 2 条。
 * 示例 2：
 * <p>
 * 输入：n = 4, conections = [[1,2,3],[3,4,4]]
 * 输出：-1
 * 解释：即使连通所有的边，也无法连接所有城市。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 104
 * 1 <= connections.length <= 104
 * connections[i].length == 3
 * 1 <= xi, yi<= n
 * xi!= yi
 * 0 <= costi<= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/connecting-cities-with-minimum-cost
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumCost1135 {
    /**
     *
     * 1. 找到该无向图中的最小生成树，则为最终答案
     *    至于最小生成树怎么找：这里使用的是克鲁斯卡尔算法
     *
     * 2. 找不到则返回-1 ->只有一种情况下找不到最小生成树，即构成连通图的时候
     * 这种情况直接通过边的数量去判断
     */
    public int minimumCost(int n, int[][] connections) {
        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < connections.length; i++) {
            list.add(new Edge(connections[i][0] - 1, connections[i][1] - 1, connections[i][2]));
        }
        /*
         * 构成最小生成树，前提是现有的边有可能构成连通图，
         * 而构成连通图的边数最低标准就是n-1,如果满足不了这个标准，则肯定构不成连通图，
         * 即不可能构成最小生成树
         */
        if (list.size() < n - 1) {
            return -1;
        }
        list.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.val - o2.val;
            }
        });
        //并查集数组初始化
        int[] father = new int[n];
        for (int j = 0; j < n; j++) {
            father[j] = j;
        }
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            Edge cur = list.get(i);
            if (find(father, cur.x) == find(father, cur.y)) {
                continue;
            }
            sum += cur.val;
            union(father, cur.x, cur.y);
        }
        return sum;
    }

    /**
     * 存储边信息的数据结构
     */
    static class Edge {
        /**
         * 对应每行的三个整数xi，yi，zi
         */
        int x;
        int y;
        int val;

        public Edge(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    /**
     * 并查集中的find方法
     */
    private static int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        int f = find(parent, parent[i]);
        parent[i] = f;
        return f;
    }

    /**
     * 并查集中的union方法
     */
    private static void union(int[] parent, int i, int j) {
        parent[find(parent, i)] = find(parent, j);
    }
}
