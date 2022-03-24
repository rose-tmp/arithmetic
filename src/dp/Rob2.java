package dp;

/**
 * @author - ZwZ
 * @date - 2022/3/14 11:40 下午
 * @Description:213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈 ，
 * 这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * <p>
 * <p>
 * 示例1：
 * <p>
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 3：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Rob2 {
    //在198. 打家劫舍的基础上考虑首尾不能同时偷。即分情况讨论问题
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length];
        int max = Integer.MIN_VALUE;
        //不偷第一个房间
        dp[1] = nums[1];
        if (nums[1] < nums[2]) {
            dp[2] = nums[2];
        } else {
            dp[2] = dp[1];
        }
        for (int i = 3; i < dp.length; i++) {
            if (nums[i] + dp[i - 2] < dp[i - 1]) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = nums[i] + dp[i - 2];
            }
        }
        max = dp[dp.length - 1];
        //偷第一个房间
        dp[0] = nums[0];
        if (nums[1] < nums[0]) {
            dp[1] = nums[0];
        } else {
            dp[1] = dp[0];
        }
        for (int i = 2; i < dp.length - 1; i++) {
            if (nums[i] + dp[i - 2] < dp[i - 1]) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = nums[i] + dp[i - 2];
            }
        }
        max = Math.max(max, dp[dp.length - 2]);
        return max;
    }
}
