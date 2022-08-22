package sword_zhuanxiang.graph_theory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: ZwZ
 * @date: 2022-08-17 11:45
 * @desc:剑指 Offer II 110. 所有路径
 * 给定一个有 n 个节点的有向无环图，用二维数组 graph 表示，请找到所有从 0 到 n-1 的路径并输出（不要求按顺序）。
 * graph 的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些结点
 * （译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a ），若为空，就是没有下一个节点了。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：graph = [[1,2],[3],[3],[]]
 * 输出：[[0,1,3],[0,2,3]]
 * 解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
 * 示例 2：
 * <p>
 * 输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * 输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 * 示例 3：
 * <p>
 * 输入：graph = [[1],[]]
 * 输出：[[0,1]]
 * 示例 4：
 * <p>
 * 输入：graph = [[1,2,3],[2],[3],[]]
 * 输出：[[0,1,2,3],[0,2,3],[0,3]]
 * 示例 5：
 * <p>
 * 输入：graph = [[1,3],[2],[3],[]]
 * 输出：[[0,1,2,3],[0,3]]
 * <p>
 * 提示：
 * <p>
 * n == graph.length
 * 2 <= n <= 15
 * 0 <= graph[i][j] < n
 * graph[i][j] != i
 * 保证输入为有向无环图 (GAD)
 * <p>
 * 注意：本题与主站 797 题相同：https://leetcode-cn.com/problems/all-paths-from-source-to-target/
 */
public class AllPathsSourceTarget110 {
    /**
     * 这道题用dfs更直观，容易一些
     * 当然bfs也可以做
     * 时间复杂度:o(m*n) 其中n是节点个数 m是有向图的边长
     */
    public List<List<Integer>> allPathsSourceTarget1(int[][] graph) {
        if (graph.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        //存储所有从0节点出发遍历过的路径
        Queue<List<Integer>> queue = new LinkedList<>();
        //初始化
        List<Integer> l = new ArrayList<>();
        l.add(0);
        queue.offer(l);
        while (!queue.isEmpty()) {
            int size = queue.size();
            //判断遍历过的路径中是否有满足条件的路径
            for (int i = 0; i < size; i++) {
                List<Integer> cur = queue.poll();
                //满足条件
                if (cur.get(cur.size() - 1) == graph.length - 1) {
                    ans.add(cur);
                    continue;
                }
                //不满足条件,则沿着这个路径继续向下走
                int[] arr = graph[cur.get(cur.size() - 1)];
                for (int k : arr) {
                    //新建一个路径并且把之前走过的路加入进来
                    List<Integer> list = new ArrayList<>(cur);
                    //加入新的节点
                    list.add(k);
                    //将该路径加入队列 以供下次遍历
                    queue.add(list);
                }
            }
        }
        return ans;
    }

    /**
     * dfs
     */
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        if (graph.length == 0) {
            return ans;
        }
        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(graph, list, 0);
        return ans;
    }

    /**
     * @param i 当前遍历到的节点编号
     */
    private void dfs(int[][] graph, List<Integer> list, int i) {
        if (i == graph.length - 1) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int j = 0; j < graph[i].length; j++) {
            list.add(graph[i][j]);
            dfs(graph, list, graph[i][j]);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        AllPathsSourceTarget110 all = new AllPathsSourceTarget110();
        all.allPathsSourceTarget1(new int[][]{{1, 2}, {3}, {3}, {}});

    }
}
