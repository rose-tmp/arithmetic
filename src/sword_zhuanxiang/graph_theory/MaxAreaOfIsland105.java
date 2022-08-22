package sword_zhuanxiang.graph_theory;

/**
 * @author: ZwZ
 * @date: 2022-08-15 21:10
 * @desc:剑指 Offer II 105. 岛屿的最大面积
 * 给定一个由 0 和 1 组成的非空二维数组 grid ，用来表示海洋岛屿地图。
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，
 * 这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 找到给定的二维数组中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出: 6
 * 解释: 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 * 示例 2:
 * <p>
 * 输入: grid = [[0,0,0,0,0,0,0,0]]
 * 输出: 0
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1
 * <p>
 * <p>
 * 注意：本题与主站 695 题相同： https://leetcode-cn.com/problems/max-area-of-island/
 */
public class MaxAreaOfIsland105 {
    int maxArea = 0;

    public int maxAreaOfIsland(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //从某个标记为1的位置出发开始dfs,在dfs的过程中对1的位置进行置0
                if (grid[i][j] == 1) {
                    int curCount = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, curCount);
                }
            }
        }
        return maxArea;
    }

    /**
     * @param row 当前行数
     * @param col 当前列数
     * @return 从[row, col]出发的所形成的岛屿的大小
     *
     * 这道题有个坑:一开始的时候以为在主方法中是从上到下从左到右遍历过来的，所以在dfs中只需要保留向下走和向右走就好了
     * 其实不是这样，反例：1. 题目中的示例(如果只保留向下走和向右走，输出结果是5不是6)
     * 2. [[0,1],[1,1]]
     */
    public int dfs(int[][] grid, int row, int col) {
        if (row >= grid.length || col >= grid[0].length || grid[row][col] == 0) {
            return 0;
        }
        int ans = 1;//1:它自身
        grid[row][col] = 0;//置0
        //向下走
        if ((row + 1) < grid.length) {
            ans += dfs(grid, row + 1, col);
        }
        //向上走
        if ((row - 1) >= 0) {
            ans += dfs(grid, row - 1, col);
        }
        //向右走
        if ((col + 1) < grid[0].length) {
            ans += dfs(grid, row, col + 1);
        }
        //向左走
        if ((col - 1) >= 0) {
            ans += dfs(grid, row, col - 1);
        }
        return ans;
    }
}
