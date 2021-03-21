package leetcode.greedy;

/**
 * @author - ZwZ
 * @date - 2021/3/20 - 10:06
 * @Description:45. 跳跃游戏 II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 示例:
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。
 * 说明:
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 */
public class Jump {
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1)
            return 0;
        int max = Integer.MIN_VALUE;
        //记录上一个max    因为当max的值由preMax 修改成 当前max的值的时候就意味着需要一次跳跃
        int preMax = 0;
        int step = 0;
        //题目中已经告诉了一定可以走到最后一个位置 所以就没有遍历最后一个位置 因为遍历最后一个位置有可能进for中的第二个if从而使得结果比真实结果大了1
        for (int i = 0; i < nums.length - 1; i++) {
            if (max < nums[i] + i) {
                max = nums[i] + i;
            }
            if (i == preMax) {
                step++;
                preMax = max;
            }
        }
        return step;
    }
}
