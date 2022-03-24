package SlidingWindow;

/**
 * @author - ZwZ
 * @date - 2021/3/31 - 18:01
 * @Description:713. 乘积小于K的子数组
 * 给定一个正整数数组nums。
 * <p>
 * 找出该数组内乘积小于k的连续的子数组的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * <p>
 * 说明:
 * 0 < nums.length <= 50000
 * 0 < nums[i] < 1000
 * 0 <= k < 10^6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-product-less-than-k
 */
public class NumSubarrayProductLessThanK {
    /**
     * 暴力枚举
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK1(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            long sum = 1;
            for (int j = i; j < nums.length; j++) {
                sum *= nums[j];
                if (sum < 100) {
                    ans++;
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    /**
     * 滑动窗口
     * 窗口中从始至终仅包含乘积小于100的子数组
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        int ans = 0;
        //乘积
        int prod = 1;
        //滑动窗口的左边界
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            //窗口中的元素乘积大于k,窗口左边界右移
            while (prod >= k) {
                prod /= nums[left];
                left++;
            }
            //[left,...,right]中包含right - left + 1个满足条件的子数组
            ans += right - left + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        NumSubarrayProductLessThanK thanK = new NumSubarrayProductLessThanK();
        thanK.numSubarrayProductLessThanK2(new int[]{10, 5, 2, 6}, 100);
    }
}
