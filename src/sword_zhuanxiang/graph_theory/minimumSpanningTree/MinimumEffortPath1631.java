package sword_zhuanxiang.graph_theory.minimumSpanningTree;

/**
 * @author: ZwZ
 * @date: 2022-09-07 11:36
 * @desc:1631. 最小体力消耗路径
 * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，
 * 其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，
 * 且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。
 * 你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
 * 输出：1
 * 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
 * 示例 3：
 * <p>
 * <p>
 * 输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * 输出：0
 * 解释：上图所示路径不需要消耗任何体力。
 * <p>
 * <p>
 * 提示：
 * <p>
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 106
 * https://leetcode.cn/problems/path-with-minimum-effort/
 */
public class MinimumEffortPath1631 {
    /**
     * 跟62乍一看挺像
     * https://leetcode.cn/problems/path-with-minimum-effort/solution/duo-tu-xiang-xi-fen-xi-jie-ti-si-lu-fen-7z89x/
     * <p>
     * 思路:使用克鲁斯卡尔算法，将每个格子看作一个节点 ->
     * 克鲁斯卡尔算法每次用的时候确实有点像模板，但是有一点需要针对不同得题目去灵活变通，即Edge类中如何存储一条边两端
     * 的点,1584中用每个点（虽然确定每个点需要x,y坐标两个数）在points数组中的下标去确定一个点
     * 而在这里，用i*N+j的方式去唯一确定某个格子的位置
     */
    public int minimumEffortPath(int[][] heights) {
        return 1;
    }
}
