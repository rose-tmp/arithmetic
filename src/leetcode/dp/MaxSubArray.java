package leetcode.dp;

/**
 * @author - ZwZ
 * @date - 2020/10/11 - 21:02
 * @Description:
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        int i = 1;
        while(i < len){
            dp[i] = Math.max(nums[i],dp[i - 1] + nums[i]);
            max = Math.max(max,dp[i]);
            i++;
        }
        return max;
    }
}
