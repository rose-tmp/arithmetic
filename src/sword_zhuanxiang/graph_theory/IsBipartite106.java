package sword_zhuanxiang.graph_theory;

import java.util.LinkedList;
import java.util.Queue;

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
     * 这个题是用临界矩阵存储的图（除了用临界矩阵 还可以用邻接表存储图）的问题，要好好吃透
     * 因为平时写的图的问题比较少，尤其是广搜 不是很熟悉
     *
     * 还可以用并查集做，详见包bingchaji
     *
     * 思路：详见官方题解 中心思想：直接相连的两个节点一定不会在同一个集合里，所以可以在遍历的过程中对每个节点进行染色
     * https://leetcode.cn/problems/vEAB3K/solution/pan-duan-shi-fou-ke-yi-luo-ji-zi-qia-jav-9wda/
     * 深度优先遍历
     *
     * 时间复杂度是o(m+n)而不是o(m*n) 具体原因在isBipartite2有提及
     */
    public boolean isBipartite1(int[][] graph) {
        /* 没有必要做这个判断，而且逻辑有误:1.graph.length==0的时候应该返回true 2.graph[i]的长度不一致，所以对graph[0]判断无意义
        if (graph.length == 0 || graph[0].length == 0) {
            return false;
        }*/
        //对每个节点进行标记的标记数组 0:未标记;1:标记为红色;2:标记为蓝色
        int[] flag = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            //只对没有染过色的节点进行dfs.因为是深搜,所以染过色的节点都一定遍历了一遍
            if (flag[i] == 0) {
                //没有染过色,就初始化为任意一个颜色 这里初始化为红色
                flag[i] = 1;
                boolean ans = dfs(graph, flag, i);
                if (!ans) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param i 当前遍历到的节点的编号
     * @return 与i节点连通的节点是否不满足”直接相连的两个节点一定不会在同一个集合里“这个条件
     */
    public boolean dfs(int[][] graph, int[] flag, int i) {
        /*if (i > graph.length) {
            return true;
        }*/
        int[] arr = graph[i];//与节点i连通的节点的编号
        for (int j = 0; j < arr.length; j++) {
            //该节点还没有访问到,则对其进行染色
            if (flag[arr[j]] == 0) {
                flag[arr[j]] = flag[i] == 1 ? 2 : 1;
                //将该节点染色后继续向下遍历
                boolean ans = dfs(graph, flag, arr[j]);
                if (!ans) {
                    return false;
                }
            } else {
                if (flag[arr[j]] == flag[i]) {
                    return false;
                }
                /* 不需要在向下遍历.因为是深搜,所以染过色的节点向下的所有连通节点也都遍历过了一遍
                else {
                    //继续向下遍历
                    boolean ans = dfs(graph, flag, arr[j]);
                    if (!ans) {
                        return false;
                    }
                }*/
            }
        }
        return true;
    }

    /**
     * 广度优先遍历 ->广度优先遍历中一般都会借助queue 像二叉树的层次遍历那样去存储每一层的节点
     * 时间复杂度是O(m+n)而不是o(m*n) m:节点个数 n:无向图中边个条数
     * 因为虽然这里表面上看是双层for但是并不是在遍历每个节点的时候都会把与它相连的节点遍历一遍
     * 而是:与它相连的节点（与它相连的节点个数=与它相连的边的条数）中只有没有访问过的节点才会访问一遍 -> if(flag[i]==0)
     *
     * 所以时间复杂度不是o(m*n) 而是o(m+n)
     */
    public boolean isBipartite2(int[][] graph) {
        //flag[i]:第i个节点状态  0:未访问过 1:染色为红色 2:染色为蓝色
        int[] flag = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            /*当前节点没有被访问过则进行操作->对于已经访问过的节点，在访问的过程中就已经对与其连通的节点进行了操作,所以不需要考虑它们
             * 这样写其实就是在考虑图为非连通图的情况，因为题干上没有说给定的图一定是连通图
             * 如果图确定是连通图的话，其实加不加这句话，最终的结果都一样。因为在连通图中从一个未被访问过的节点出发就可以遍历到图中所有节点
             * */
            if (flag[i] == 0) {
                //没有染过色,就初始化为任意一个颜色 这里初始化为红色
                flag[i] = 1;
                //借助队列完成对每一层节点的存储(和层次遍历中的queue作用相同)
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int n = queue.poll();
                    //对n连通的所有节点进行染色
                    for (int j = 0; j < graph[n].length; j++) {
                        //没有被访问过,则将其染成和n相反的颜色
                        if (flag[graph[n][j]] == 0) {
                            flag[graph[n][j]] = flag[n] == 1 ? 2 : 1;
                            queue.offer(graph[n][j]);
                        }
                        //与n连通的某节点染过色并且颜色和n相同
                        else if (flag[graph[n][j]] == flag[n]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
