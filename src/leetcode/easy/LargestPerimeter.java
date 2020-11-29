package leetcode.easy;

import java.util.Arrays;

/**
 * @author - ZwZ
 * @date - 2020/11/29 - 18:25
 * @Description:976. 三角形的最大周长
 * 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
 *
 * 如果不能形成任何面积不为零的三角形，返回0。
 *
 * 示例 1：
 *
 * 输入：[2,1,2]
 * 输出：5
 * 示例 2：
 *
 * 输入：[1,2,1]
 * 输出：0
 * 示例 3：
 *
 * 输入：[3,2,3,4]
 * 输出：10
 * 示例 4：
 *
 * 输入：[3,6,2,3]
 * 输出：8
 *
 * 提示：
 *
 * 3 <= A.length <= 10000
 * 1 <= A[i] <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-perimeter-triangle
 */
public class LargestPerimeter {
    public int largestPerimeter(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        Arrays.sort(A);
        int high = A.length - 1;
        //要想得到的周长最大，肯定是在能拼成三角形的基础上选最长的边
        while (high >= 2) {
            if (A[high - 1] + A[high - 2] > A[high]) {
                return A[high - 1] + A[high - 2] + A[high];
            } else {
                high--;
            }
        }
        return 0;
    }
}
