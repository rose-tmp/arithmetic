package leetcode.dp;

/**
 * @author - ZwZ
 * @date - 2021/3/19 - 16:12
 * @Description:字节跳动后端实习一面题目
 * 55. 跳跃游戏
 * 给定一个非负整数数组nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 示例1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * <p>
 * 示例2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ，所以永远不可能到达最后一个下标。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 */
public class CanJump {
    /**
     * 贪心
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        //目前可以跳到的最远的位置
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (i > max) {
                return false;
            }
            max = max < nums[i] + i ? nums[i] + i : max;
        }
        return true;
    }

    /**
     * 暴力
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        return get(nums, 0);
    }

    /**
     * dp[i] : 能否跳到i这个位置
     * @param nums
     * @return
     */
    public boolean canJump3(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if(dp[j] && nums[j] + j >= i){
                    dp[i] = true;
                }
            }
        }
        return dp[nums.length - 1];
    }
    public boolean get(int[] nums, int index) {
        if (index == nums.length) {
            return true;
        }
        boolean res = false;
        for (int i = 0; i < nums[i]; i++) {
            res = res || get(nums, index + i);
        }
        return res;
    }
}
