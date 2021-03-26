package leetcode.dp.dpOnStringAndArray;

/**
 * @author - ZwZ
 * @date - 2021/3/26 - 20:06
 * @Description:64. 最小路径和
 * 给定一个包含非负整数的 m*n网格grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 *
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 */
public class MinPathSum {
    /**
     * dp
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        int[][] dp = new int[grid.length][grid[0].length];
        dp[grid.length - 1][grid[0].length - 1] = grid[grid.length - 1][grid[0].length - 1];
        //赋初值
        for (int j = dp[0].length - 2; j >= 0; j--) {
            dp[dp.length - 1][j] = grid[dp.length - 1][j] + dp[dp.length - 1][j + 1];
        }
        for (int i = dp.length - 2; i >= 0; i--) {
            dp[i][dp[0].length - 1] = grid[i][dp[0].length - 1] + dp[i + 1][dp[0].length - 1];
        }
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = dp[0].length - 2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i][j + 1], dp[i + 1][j]) + grid[i][j];
            }
        }
        return dp[0][0];
    }
}
