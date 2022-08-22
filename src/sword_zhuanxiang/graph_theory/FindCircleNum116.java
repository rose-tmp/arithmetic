package sword_zhuanxiang.graph_theory;

import java.util.LinkedList;
import java.util.Queue;

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
     * bfs
     */
    public int findCircleNum1(int[][] isConnected) {
        if (isConnected.length == 0) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        //第i+1个节点是否被访问过
        boolean[] visited = new boolean[isConnected.length];
        int ans = 0;
        /*
         * 从每一个没有被访问过的节点出发去”传染“其他的可达节点，把与它相连的其他节点全部标记为”已访问过“
         * */
        for (int i = 0; i < isConnected.length; i++) {
            //没有访问过，所以从它开始访问，这就代表着一个省
            if (!visited[i]) {
                visited[i] = true;
                queue.add(i);
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    int[] arr = isConnected[cur];
                    //把它的可达节点也都加入到队列中,并且标记为已经访问过
                    for (int j = 0; j < arr.length; j++) {
                        if (arr[j] == 1 && !visited[j]) {
                            visited[j] = true;
                            queue.add(j);
                        }
                    }
                }
                ans++;
            }
        }
        return ans;
    }

    /**
     * dfs
     * 和bfs的思路一样:从每一个没有被访问过的节点出发去”传染“其他的可达节点，把与它相连的其他节点全部标记为”已访问过“
     */
    public int findCircleNum2(int[][] isConnected) {
        int n = isConnected.length;
        //flag[i]:i节点是否访问过 true:访问过 false:没有访问过
        boolean[] flag = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!flag[i]) {
                //从该节点出发，用dfs的方式对其他节点进行”传染“
                dfs(flag, isConnected, i);//Java中实例对象的传递是引用传递,所以可以直接把flag传进去
                ans++;
            }
        }
        return ans;
    }

    private void dfs(boolean[] flag, int[][] isConnected, int i) {
        if (i >= isConnected.length) {
            return;
        }
        for (int j = 0; j < isConnected[i].length; j++) {
            if (j == i) {
                continue;
            }
            if (isConnected[i][j] == 1 && !flag[j]) {
                flag[j] = true;
                dfs(flag, isConnected, j);
            }
        }
    }

    /**
     * 并查集
     * 详见包bingchaji中的FindCircleNum
     */
    public int findCircleNum3(int[][] isConnected) {

        return 0;
    }

}
