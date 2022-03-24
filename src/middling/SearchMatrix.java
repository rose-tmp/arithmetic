package middling;

/**
 * @author - ZwZ
 * @date - 2021/3/30 - 14:04
 * @Description:74. 搜索二维矩阵
 * 编写一个高效的算法来判断m x n矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *
 * 示例 1：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 *
 * 示例 2：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = getRow(matrix, target);
        if (row == -1) {
            return false;
        } else {
            boolean flag = binarySearch(matrix[row], target);
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public int getRow(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][matrix[0].length - 1] > target) {
                return i;
            }
        }
        return -1;
    }

    public boolean binarySearch(int[] arr, int target) {
        int pre = 0;
        int rear = arr.length - 1;
        while (pre < rear) {
            int mid = pre + (rear - pre) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                pre = mid + 1;
            } else {
                rear = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SearchMatrix searchMatrix = new SearchMatrix();
        int[][] matrix = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        searchMatrix.searchMatrix(matrix, 13);
    }
}
