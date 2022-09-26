package sword_zhuanxiang.graph_theory.zuiduanlujing;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-09-25 22:32
 * @desc:743. 网络延迟时间
 * 有 n 个网络节点，标记为 1 到 n。
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。
 * times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 * https://leetcode.cn/problems/network-delay-time/
 */
public class NetworkDelayTime743 {
    /**
     * 这道题是标准迪杰斯特拉算法的应用
     *
     *
     * 思路：迪杰斯特拉算法是用来计算加权图中某个节点到图中其他所有节点的最短路径的
     * 那么这道题就是计算从k节点出发到其他所有节点的最短路径
     * 然后从这些路径中找到最大的那条路径的长度返回即可
     * <p>
     * 迪杰斯特拉算法模板详见：https://labuladong.gitee.io/algo/2/22/56/
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // 节点编号是从 1 开始的，所以要一个大小为 n + 1 的邻接表
        List<int[]>[] graph = new ArrayList<>[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        // 构造图
        for (int[] edge : times) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            // from -> List<(to, weight)>
            // 邻接表存储图结构，同时存储权重信息
            graph[from].add(new int[]{to, weight});
        }

        int[] arr = dijkstra(k, graph);

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == Integer.MAX_VALUE) {
                return -1;
            }
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    /**
     * @param graph 表示图 graph[i]:第i个节点的所有邻居
     *              其中List<int[]>中的arr数组表示：
     *              arr[0] 邻居id
     *              arr[1] i到该邻居的距离
     * @return start到其他每个节点的最短距离的数组
     */
    private int[] dijkstra(int start, List<int[]>[] graph) {
        int[] res = new int[graph.length];

        //Integer.MAX_VALUE表示不可达
        Arrays.fill(res, Integer.MAX_VALUE);
        res[start] = 0;

        /*迪杰斯特拉算法中用优先队列是为了优化过程：
         * 93行可知只有当当前找到的路径比之前达到该节点的路径短的时候才会加入queue
         * 那么我们如果用优先队列优先去处理权重最小的那些的话，就会导致93行中的if (distNew < res[neiID])
         * 判断为false,从而少向queue中加入一些元素
         * */
        Queue<Dijkstra1.State> queue = new PriorityQueue<>(new Comparator<Dijkstra1.State>() {
            @Override
            public int compare(Dijkstra1.State o1, Dijkstra1.State o2) {
                return o1.dist - o2.dist;
            }
        });

        //从start出发开始进行bfs
        queue.offer(new Dijkstra1.State(start, 0));
        while (!queue.isEmpty()) {
            Dijkstra1.State cur = queue.poll();
            //之前已经有一条更短的路径到达当前节点
            if (cur.dist > res[cur.id]) {
                continue;
            }

            for (int[] nei : graph[cur.id]) {
                int neiID = nei[0];
                int distNew = res[cur.id] + nei[1];
                //从cur这个点到达neiID这个点比之前到达neiID的距离更短,所以更新res表
                if (distNew < res[neiID]) {
                    res[neiID] = distNew;
                    queue.offer(new Dijkstra1.State(neiID, distNew));
                }
            }
        }
        return res;
    }
}
