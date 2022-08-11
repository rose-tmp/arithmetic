package sword_zhuanxiang;

/**
 * @author - ZwZ
 * @date - 2022/7/26-15:13 - 周二
 * @Description:剑指 Offer II 008. 和大于等于 target 的最短子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *
 *
 * 进阶：
 *
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 */
public class MinSubArrayLen008 {
    /**
     * dp
     * 而且不能创建dp数组，否则会“超出内存限制”,无法AC
     * */
    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0;i < nums.length;i++) {
            int pre = nums[i];
            int cur = pre;
            for(int j = i;j < nums.length;j++) {
                if (i < j) {
                    cur = pre + nums[j];
                    pre = cur;
                }
                if (cur >= target && (j - i + 1) < min) {
                    min = j - i + 1;
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
