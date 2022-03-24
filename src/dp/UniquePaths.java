package dp;

/**
 * @author - ZwZ
 * @date - 2020/11/1 - 20:57
 * @Description:62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 * <p>
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * <p>
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 */
public class UniquePaths {
    /**
     * m:列
     * n:行
     */
    public int uniquePaths1(int m, int n) {
        int[][] dp = new int[n][m]; //dp[i][j]表示从[0,0]到[i,j]几种可能的路径
        //网格边界赋初值
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];//状态转移方程
            }
        }
        return dp[n - 1][m - 1];
    }
    /**
     * dp换一个定义方式，则赋值的方向和上一个就有所不同
     * 又之前的从（0，0）向（m-1，n-1）赋值变为由(m-1,n-1)向（0，0）赋值
     * */
    public int uniquePaths2(int m, int n) {
        if (m == 0 || n == 0) {
            return 1;
        }
        //dp[i][j]:从[i][j]到[m-1,n-1]一共有多少条路径
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;
        //初始化dp数组
        for (int row = m - 2; row >= 0; row--) {
            dp[row][n - 1] = 1;
        }
        for (int col = n - 2; col >= 0; col--) {
            dp[m - 1][col] = 1;
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[0][0];
    }
}
