package binarySearch;

import java.util.Arrays;

/**
 * @author: ZwZ
 * @date: 2022-09-27 8:21
 * @desc:611. 有效三角形的个数
 * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,2,3,4]
 * 输出: 3
 * 解释:有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * 示例 2:
 * <p>
 * 输入: nums = [4,2,3,4]
 * 输出: 4
 * <p>
 * 提示:
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 */
public class TriangleNumber611 {
    public int triangleNumber(int[] nums) {
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int l = j + 1;
                int r = nums.length - 1;
                int k = j;
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    //寻找两边之和大于第三边的最大数组下标
                    if (nums[i] + nums[j] > nums[mid]) {
                        k = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                res += (k - j);
            }
        }
        return res;
    }
}
