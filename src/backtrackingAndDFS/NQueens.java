package backtrackingAndDFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author - ZwZ
 * @date - 2020/10/18 - 12:52
 * @Description:51. N 皇后
 * 皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 *
 * n 皇后问题研究的是如何将n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的 解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 示例：
 * 输入：4
 * 输出：[
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 *
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 */
public class NQueens {
    public List<List<String>> solveNQueens1(int n) {
        List<List<String>> result = new ArrayList();
        Set<Integer> cols = new HashSet();
        Set<Integer> diagonals1 = new HashSet();
        Set<Integer> diagonals2 = new HashSet();
        boolean[][] map = new boolean[n][n];
        backTrack(result, cols, map, diagonals1, diagonals2, n, 0);
        return result;
    }

    /**
     * cols 存储进入递归时，哪一列放置了皇后
     * map 用于记录皇后存放的每一个位置 用于添加到result中每一种
     * 符合条件的情况  在N皇后II中只让求count没有让记录具体结果
     * row 代表行用于递归终止
     *
     *
     * 流程：一行一行的放皇后，而在每一行的每一列（也就是该函数中的for）都有两种选择：放或者不放，然后依次遍历
     * 所以整个流程总结下来就是：从[0,0]开始一直遍历到[n,n] 每个位置都判断一些皇后能不能放，即：
     * 如果第0行的第0列放了皇后，然后接下来第1行第0列怎么办，第2行第0列怎么办，...第n行第0列怎么办
     * 回溯树还是可以画出来的：https://labuladong.github.io/algo/4/30/104/
     */
    public void backTrack(List<List<String>> result, Set<Integer> cols, boolean[][] map,
                          Set<Integer> diagonals1, Set<Integer> diagonals2, int n, int row) {
        if (row == n) {
            List<String> list = new ArrayList<>();
            //遍历map i控制行 j控制列
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (map[i][j]) {
                        sb.append('Q');
                    } else {
                        sb.append('.');
                    }
                }
                list.add(sb.toString());
            }
            result.add(list);
        }
        //for控制列   而行使用变量中的row控制
        for (int j = 0; j < n; j++) {
            //(同一对角线上每个位置满足行下标与列下标之差或者行下标与列下标之和相等)
            //cols.contains(j) == true就代表着在此递归之前的行中，有在这一列中放皇后
            if (cols.contains(j) || diagonals1.contains(row - j) || diagonals2.contains(row + j)) {
                continue;
            }
            cols.add(j);
            map[row][j] = true;
            diagonals1.add(row - j);
            diagonals2.add(row + j);
            backTrack(result, cols, map, diagonals1, diagonals2, n, row + 1);
            cols.remove(j);
            map[row][j] = false;
            diagonals1.remove(row - j);
            diagonals2.remove(row + j);
        }
    }

    /**
     * 对方法1进行优化：
     * 将使用set的地方换成boolean类型的数组以提高执行时间上的效率
     */
    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> result = new ArrayList();
        boolean[] cols = new boolean[n];
        boolean[] diagonals1 = new boolean[n*2];
        boolean[] diagonals2 = new boolean[n*2];
        boolean[][] map = new boolean[n][n];
        backTrack2(result, cols, map, diagonals1, diagonals2, n, 0);
        return result;
    }

    /**
     * cols 存储进入递归时，哪一列放置了皇后
     * map 用于记录皇后存放的每一个位置 用于添加到result中每一种
     * 符合条件的情况  在N皇后II中只让求count没有让记录具体结果
     * row 代表行用于递归终止
     */
    public void backTrack2(List<List<String>> result, boolean[] cols, boolean[][] map,
                           boolean[] diagonals1, boolean[] diagonals2, int n, int row) {
        if (row == n) {
            List<String> list = new ArrayList();
            //遍历map i控制行 j控制列
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (map[i][j]) {
                        sb.append('Q');
                    } else {
                        sb.append('.');
                    }
                }
                list.add(sb.toString());
            }
            result.add(list);
        }
        //for控制列   而行使用变量中的row控制
        for (int i = 0; i < n; i++) {
            if (cols[i] || diagonals1[row - i + (n - 1)] || diagonals2[row + i]) {
                continue;
            }
            cols[i] = true;
            map[row][i] = true;
            /*加(n - 1)防止数组下标越界
            * 当列数为1,行数为n时，其差值为1 - n此时差值为最小的负数
            * 所以在总数上加上(n - 1)就可以很好的防止数组下标为负数这个问题
            * */
            diagonals1[row - i + (n - 1)] = true;
            diagonals2[row + i] = true;
            backTrack2(result, cols, map, diagonals1, diagonals2, n, row + 1);
            cols[i] = false;
            map[row][i] = false;
            diagonals1[row - i + (n - 1)] = false;
            diagonals2[row + i] = false;
        }
    }
}
