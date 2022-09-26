package sword_zhuanxiang.graph_theory.zuiduanlujing;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-09-25 23:54
 * @desc:787. K 站中转内最便宜的航班
 * 有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，
 * 表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
 * <p>
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，
 * 你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。
 * 如果不存在这样的路线，则输出 -1。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * 输出: 200
 * 解释:
 * 城市航班图如下
 * <p>
 * <p>
 * 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
 * 示例 2：
 * <p>
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * 输出: 500
 * 解释:
 * 城市航班图如下
 * <p>
 * <p>
 * 从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * 航班没有重复，且不存在自环
 * 0 <= src, dst, k < n
 * src != dst
 */
public class FindCheapestPrice787 {
    /**
     * 这道题是变型的迪杰斯特拉算法的应用 即Dijkstra2中:某一点到某一点的最短路径
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 这种构建图的方式要牢记！！！！！！
        // graph[i]:i节点所能到达的所有点
        // arr[0]:点的id arr[1]:到达该点的权值
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] f : flights) {
            graph[f[0]].add(new int[]{f[1], f[2]});
        }
        return dijkstra(graph, src, dst, ++k);
    }

    class Edge {
        int id;
        int cost;
        int count;

        Edge(int id, int cost, int count) {
            this.id = id;
            this.cost = cost;
            this.count = count;
        }
    }

    public int dijkstra(List<int[]>[] graph, int start, int end, int k) {
        Queue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.cost - o2.cost;
            }
        });
        queue.offer(new Edge(start, 0, 0));
        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            if (cur.id == end) {
                return cur.cost;
            }
            if (cur.count == k) {
                continue;
            }
            for (int[] nei : graph[cur.id]) {
                int nextID = nei[0];
                int costNew = cur.cost + nei[1];
                int countNew = cur.count + 1;

                queue.offer(new Edge(nextID, costNew, countNew));
            }
        }
        return -1;
    }
}
