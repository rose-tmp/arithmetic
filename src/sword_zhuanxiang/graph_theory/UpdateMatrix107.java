package sword_zhuanxiang.graph_theory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: ZwZ
 * @date: 2022-08-16 17:04
 * @desc:剑指 Offer II 107. 矩阵中的距离
 * 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，
 * 其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1 。
 * 示例 1：
 * <p>
 * 输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：[[0,0,0],[0,1,0],[0,0,0]]
 * 示例 2：
 * <p>
 * 输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
 * 输出：[[0,0,0],[0,1,0],[1,2,1]]
 * <p>
 * 提示：
 * <p>
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * mat[i][j] is either 0 or 1.
 * mat 中至少有一个 0
 * <p>
 * <p>
 * 注意：本题与主站 542 题相同：https://leetcode-cn.com/problems/01-matrix/
 */
public class UpdateMatrix107 {
    /**
     * 广度优先遍历
     * 官方题解中说的”超级0“是虚拟出来便于理解题目的，多读几遍就看的懂了
     * 其实不个人感觉没必要理解的这么绕,直接:
     * 把所有的0加入队列中然后进行广度优先遍历,遍历到每个0的时候对它周边4个位置赋值,然后再把它周边刚遍历到
     * 的位置添加到队列中，如此往复，就跟传染似的，一点一点扩大”毒圈“
     *
     * 时间复杂度:O(m*n) m,n分别为行列数 -> 因为每个位置入且只入队列一次
     */
    public int[][] updateMatrix1(int[][] mat) {
        if (mat.length == 0 || mat[0].length == 0) {
            return new int[][]{};
        }
        //存储结果
        int[][] ans = new int[mat.length][mat[0].length];
        boolean[][] flag = new boolean[mat.length][mat[0].length];//标记数组 true:访问过; false:没有访问过
        Queue<int[]> queue = new LinkedList<>();
        //将所有的0添加到队列中
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    flag[i][j] = true;//所有的0位置都标记为访问过
                }
            }
        }
        int[][] dict = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        //开始广搜
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0], j = cur[1];
            /*
             *  这部分可以用位置矩阵去优化一下，代码量会少很多
             *  而且写的时候不容易出错
             * */
            /*for (int[] ints : dict) {
                int newI = i + ints[0];
                int newJ = j + ints[1];
                if (newI >= 0 && newI < mat.length && newJ >= 0 && newJ < mat[0].length && !flag[newI][newJ]) {
                    ans[newI][newJ] = 1 + ans[i][j];
                    queue.offer(new int[]{newI, newJ});
                    flag[newI][newJ] = true;
                }
            }*/

            //邻上没有被访问过
            if (i - 1 >= 0 && !flag[i - 1][j]) {
                ans[i - 1][j] = 1 + ans[i][j];
                queue.offer(new int[]{i - 1, j});
                flag[i - 1][j] = true;
            }
            //邻下没有被访问过
            if (i + 1 < mat.length && !flag[i + 1][j]) {
                ans[i + 1][j] = 1 + ans[i][j];
                queue.offer(new int[]{i + 1, j});
                flag[i + 1][j] = true;
            }
            //邻左没有被访问过
            if (j - 1 >= 0 && !flag[i][j - 1]) {
                ans[i][j - 1] = 1 + ans[i][j];
                queue.offer(new int[]{i, j - 1});
                flag[i][j - 1] = true;
            }
            //邻右没有被访问过
            if (j + 1 < mat[0].length && !flag[i][j + 1]) {
                ans[i][j + 1] = 1 + ans[i][j];
                queue.offer(new int[]{i, j + 1});
                flag[i][j + 1] = true;
            }
        }
        return ans;
    }
    /**
     * 动态规划
     * */
    public int[][] updateMatrix2(int[][] mat) {


        return null;
    }
    /**
     * 这道题大眼一看用dp和回溯可以做出来
     * 思路：回溯 ->这个是一开始拿到这道题时候写的一个思路,里边包含了大量的重复运算
     */
    /*public int[][] updateMatrix(int[][] mat) {
        int[][] ans = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                ans[i][j] = dfs(mat, i, j);
            }
        }
        return ans;
    }*/

    /**
     * @return mat[i][j]距离最近的0的距离
     */
    /*public int dfs(int[][] mat, int i, int j) {
        if (i < 0 || j < 0 || i >= mat.length || j >= mat[0].length) {
            return 0;
        }
        if (mat[i][j] == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        min = Math.min(min, 1 + dfs(mat, i - 1, j));//上
        min = Math.min(min, 1 + dfs(mat, i + 1, j));//下
        min = Math.min(min, 1 + dfs(mat, i, j - 1));//左
        min = Math.min(min, 1 + dfs(mat, i, j + 1));//右
        return min;
    }*/
}
