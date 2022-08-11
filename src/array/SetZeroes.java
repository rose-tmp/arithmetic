package array;

import java.util.Arrays;

/**
 * @author - ZwZ
 * @date - 2021/3/21 - 14:34
 * @Description:73. 矩阵置零
 * 给定一个m x n的矩阵 如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用原地算法。
 * <p>
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用 O(mn)的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m+n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个仅使用常量空间的解决方案吗？
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 * <p>
 * 示例 2：
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/set-matrix-zeroes
 */
public class SetZeroes {
    /**
     * 暴力
     * 时间复杂度O(m*n)
     * 空间复杂度O(m+n)
     *
     * @param matrix
     */
    public void setZeroes1(int[][] matrix) {
        if (matrix == null || matrix.length == 1 && matrix[0].length == 1) {
            return;
        }
        //记录某一行或者某一列中是否含有0元素
        boolean[] flagRow = new boolean[matrix.length];
        boolean[] flagCol = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    flagCol[j] = true;
                    flagRow[i] = true;
                }
            }
        }
        //通过flag标记对Matrix数值修改
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (flagCol[j] || flagRow[i])
                    matrix[i][j] = 0;
            }
        }
    }

    /**
     * 使用矩阵的第一行和第一列去模拟方法1中的两个标记数组
     * 时间复杂度O(m*n)
     * 空间复杂度O(1)
     * <p>
     * 为什么用第0行第0列模拟是可行的？
     * 1. 如果某一行某一列的某个元素是0，那么对应的一整行，一整列都要被置0
     * 将对应的第0行，第0列标记为0之后虽然修改了它们原始数据，但是这个位置上本来就要被置0的，所以不对结果产生影响
     * 2. 如果某一行或者某一列都没有0，那么对应的第0行第0列就不会被修改，所以不对结果产生影响
     * 3. 因为起初对第0行第0列是否含有0做了标记，所以最后可以根据标记对第0行和第0列做专门的修改，可以保证第0行和第0列的数据正确性
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        if (matrix == null || matrix.length == 1 && matrix[0].length == 1) {
            return;
        }
        //标记第0行和第0列是否含有0元素
        boolean flagRow0 = false;
        boolean flagCol0 = false;
        //初始化flagRow0
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                flagRow0 = true;
                break;
            }
        }
        //初始化flagCol0
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
                break;
            }
        }
        //更新标记
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        //更新矩阵
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }
        //更新第0行和第0列
        if (flagRow0) {
            Arrays.fill(matrix[0], 0);
        }
        if (flagCol0) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        SetZeroes zeroes = new SetZeroes();
        int[][] arr = new int[1][3];
        arr[0][0] = 1;
        arr[0][1] = 0;
        arr[0][2] = 3;

        zeroes.setZeroes2(arr);
    }
}

