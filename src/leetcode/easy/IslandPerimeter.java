package leetcode.easy;

/**
 * @author - ZwZ
 * @date - 2020/10/30 - 10:07
 * @Description:463. 岛屿的周长
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * <p>
 * 示例 :
 * 输入:
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * <p>
 * 输出: 16
 * 解释: 它的周长是下面图片中的 16 个黄色的边：
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/island-perimeter
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        if (grid == null)
            return 0;
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    //遍历格子的四个方向
                    int left = j - 1;
                    int right = j + 1;
                    int up = i - 1;
                    int down = i + 1;
                    //格子为最左一列或者其左边为水域
                    if (left < 0 || grid[i][left] == 0)
                        sum++;
                    //格子为最右一列或者其右边为水域
                    if (right >= grid[0].length || grid[i][right] == 0)
                        sum++;
                    //格子为最上一行或者其上边为水域
                    if (up < 0 || grid[up][j] == 0)
                        sum++;
                    //格子为最下一行或者其下边为水域
                    if (down >= grid.length || grid[down][j] == 0)
                        sum++;
                }
            }
        }
        return sum;
    }
}
