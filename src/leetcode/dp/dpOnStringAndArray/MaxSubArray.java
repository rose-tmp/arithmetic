package leetcode.dp.dpOnStringAndArray;

/**
 * @author - ZwZ
 * @date - 2021/1/8 - 15:45
 * @Description:53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），
 * 返回其最大和。
 * <p>
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 */
public class MaxSubArray {
    /**
     * 动态规划
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        /*dp[i]:以nums[i]结尾的子数组的最大和
         * 没有将dp[i]定义成nums[0...i]这一子数组内的序列最大和
         * 是因为子数组要求连续 这样不能保证nums[0...i]内的最大子数组与nums[i]连续
         * 所以也就是由dp[i - 1]推不出dp[i]
         * */
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
