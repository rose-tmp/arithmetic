package sword_zhuanxiang.graph_theory.minimumSpanningTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: ZwZ
 * @date: 2022-09-07 9:23
 * @desc:1584. 连接所有点的最小费用
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 * <p>
 * 示例 1：
 * <p>
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 * 解释：
 * <p>
 * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
 * 注意到任意两个点之间只有唯一一条路径互相到达。
 * 示例 2：
 * <p>
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 * 示例 3：
 * <p>
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 * 示例 4：
 * <p>
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 * 示例 5：
 * <p>
 * 输入：points = [[0,0]]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= points.length <= 1000
 * -106 <= xi, yi <= 106
 * 所有点 (xi, yi) 两两不同。
 */
public class MinCostConnectPoints1584 {
    class Edge {
        //点1在points数组中的下标
        int p1;
        //点2在points数组中的下标
        int p2;
        //p1和p2形成的边的权值
        int val;

        public Edge(int p1, int p2, int val) {
            this.p1 = p1;
            this.p2 = p2;
            this.val = val;
        }
    }

    /**
     * 1. 将所有节点之间连线并且将其曼哈顿距离设置为每条边的权值
     * 2. 使用克鲁斯卡尔算法求该完全图的最小生成树
     */
    public int minCostConnectPoints(int[][] points) {
        List<Edge> list = new ArrayList<>();
        //每个点之间都构成了一条边
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int val = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                Edge edge = new Edge(i, j, val);
                list.add(edge);
            }
        }
        list.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.val - o2.val;
            }
        });

        //并查集数组
        int[] fa = new int[points.length];
        //并查集数组初始化
        for (int i = 0; i < points.length; i++) {
            fa[i] = i;
        }

        int sum = 0;
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            Edge edge = list.get(i);
            //points[p1]和points[p2]所表示的两个点已经连通
            if (find(fa, edge.p1) == find(fa, edge.p2)) {
                continue;
            }
            sum += edge.val;
            count++;
            //已经选出了n-1条边，已经构成了一个最小生成树
            if (count == points.length - 1) {
                break;
            }
            union(fa, edge.p1, edge.p2);
        }
        return sum;
    }

    public int find(int[] fa, int p) {
        if (fa[p] == p) {
            return p;
        }
        int f = find(fa, fa[p]);
        fa[p] = f;
        return f;
    }

    public void union(int[] fa, int p1, int p2) {
        int f1 = find(fa, p1);
        int f2 = find(fa, p2);

        fa[f1] = f2;
    }
}