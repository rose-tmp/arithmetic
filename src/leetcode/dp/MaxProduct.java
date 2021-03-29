package leetcode.dp;

/**
 * @author - ZwZ
 * @date - 2021/1/12 - 16:12
 * @Description:152. 乘积最大子数组
 * 给你一个整数数组 nums，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），
 * 并返回该子数组所对应的乘积。
 * <p>
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释:子数组 [2,3] 有最大乘积 6。
 * <p>
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释:结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 */
public class MaxProduct {
    /**
     * 这个题目看起来和最大子序和MaxSubArray是完全相似的题目
     * 所以就直接按照其思路提交了一次代码(见下边被注释的maxProduct(int[] nums))
     * 但是这样子是错误的，因为忽略了数组里边存在负数这个问题 例如：[-2,3,-4]
     * 如果用注释掉的那个maxProduct计算出的结果会是3 但是实际结果应该是24
     *
     * 思考一个问题：我们为dp数组i位置赋值的时候，肯定想要最大的那个结果
     * 当nums[i]<0时，这个最大的结果来自于之前结果中的最小那个负数结果和nums[i]的乘积或者nums[i]本身
     * 当nums[i]>0时，这个最大的结果来自于之前结果中的最大的那个整数结果和nums[i]的乘积或者nums[i]本身
     * 所以我们要在dp表中对每一个位置记录两个值：最大和最小结果
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums == null) {
            return 0;
        }
        //dp[i][0]:nums[0...i]最大的连续子数组的乘积
        //dp[i][1]:nums[0...i]最小连续子数组的乘积
        int[][] dp = new int[nums.length][2];
        int res = Integer.MIN_VALUE;
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] * nums[i],
                    Math.max(dp[i - 1][1] * nums[i], nums[i]));
            dp[i][1] = Math.min(dp[i - 1][0] * nums[i],
                    Math.min(dp[i - 1][1] * nums[i], nums[i]));
        }

        /*
         *寻找最大值
         */
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i][0]);
        }
        return res == Integer.MIN_VALUE ? 0 : res;
    }

    /*public int maxProduct(int[] nums) {
        if (nums == null) {
            return 0;
        }
        //nums[0...i]最大的连续子数组的乘积
        int[] dp = new int[nums.length];
        int res = Integer.MIN_VALUE;
        dp[0] = nums[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] * nums[i] > nums[i] ? dp[i - 1] * nums[i] : nums[i];
        }

        *//*
         *寻找最大值
         *//*
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res == Integer.MIN_VALUE ? 0 : res;
    }*/
}
