package leetcode.middling;

/**
 * @author - ZwZ
 * @date - 2021/3/26 - 9:31
 * @Description:48. 旋转图像
 * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * <p>
 * 示例 2：
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * <p>
 * 示例 3：
 * 输入：matrix = [[1]]
 * 输出：[[1]]
 * <p>
 * 示例 4：
 * 输入：matrix = [[1,2],[3,4]]
 * 输出：[[3,1],[4,2]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-image
 */
public class Rotate {
    public void rotate(int[][] matrix) {
        if (matrix.length == 1) {
            return;
        }
        int[] arr = new int[matrix.length - 1];
        rotateOne(matrix, arr, 0);
    }

    /**
     * 旋转一层
     *
     * @param matrix
     * @param arr
     * @param index  用于记录旋转的是哪一层
     */
    public void rotateOne(int[][] matrix, int[] arr, int index) {
        if (index >= matrix.length) {
            return;
        }
        int row = index;
        int col = matrix.length - 1 - index;
        //记录最后一列除第一个元素以外的数据
        for (int i = index + 1, j = 0; i < matrix.length; i++, j++) {
            arr[j] = matrix[i][col];
        }
        //将第一行旋转到最后一列
        for (int i = col, j = matrix.length - 1; i >= 0; i--, j--) {
            matrix[j][col] = matrix[row][i];
        }
        //将第一列旋转到第一行
        for (int i = col - 1, j = index; i >= 0; i--, j++) {
            matrix[index][i] = matrix[j + 1][0];
        }
        //将旋转后这一层剩下的元素都提前，然后将刚刚数组中记录的最后一列的元素数据插入到最后一列 然后剩下的子矩阵就是一个子问题 进行递归
        for (int i = index + 1, k = 0; i < matrix.length; i++, k++) {
            int j = 0;
            //移动某行
            while (j < col) {
                if (j == col - 1) {
                    matrix[i][j] = arr[k];
                    break;
                }
                matrix[i][j] = matrix[i][j + 1];
                j++;
            }
        }
        rotateOne(matrix, arr, index + 1);
    }

    public static void main(String[] args) {
        Rotate rotate = new Rotate();
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate.rotate(matrix);
    }
}
