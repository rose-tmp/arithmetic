package sword_zhuanxiang.graph_theory.zuiduanlujing;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-09-25 23:40
 * @desc:标准的迪杰斯特拉算法 即：
 * 求有向图中某个点到其他所有点的最短路径
 */
public class Dijkstra1 {
    public static class State {
        int id;//图中节点id
        int dist;//从start出发道当前节点所需要的最短距离

        State(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
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
        Queue<State> queue = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                return o1.dist - o2.dist;
            }
        });

        //从start出发开始进行bfs
        queue.offer(new State(start, 0));
        while (!queue.isEmpty()) {
            State cur = queue.poll();
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
                    queue.offer(new State(neiID, distNew));
                }
            }
        }
        return res;
    }
}
