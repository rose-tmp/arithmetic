package dp.subsequenceAndSubstring;

/**
 * @author - ZwZ
 * @date - 2022/3/16-20:00 - 周三
 * @Description:918. 环形子数组的最大和
 * <p>
 * 给定一个长度为 n 的环形整数数组nums，返回nums的非空 子数组 的最大可能和。
 * <p>
 * 环形数组意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i]的前一个元素是 nums[(i - 1 + n) % n] 。
 * 子数组 最多只能包含固定缓冲区nums中的每个元素一次。形式上，对于子数组nums[i], nums[i + 1], ..., nums[j]，不存在i <= k1, k2 <= j其中k1 % n == k2 % n。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * 示例 2：
 * <p>
 * 输入：nums = [5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * 示例 3：
 * <p>
 * 输入：nums = [3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-sum-circular-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSubarraySumCircular {
    /**
     * 53. 最大子序和 MaxSubArray的高阶版
     * 需要考虑两种情况：
     * 1. 最大和子数组中含有第一个位置的元素和最后一个位置的元素------>res = Sum(nums) - minSubarray(nums)
     * 2. 最大和子数组落在了nums数组的中间，即没有同时含有第一个位置和最后一个位置的元素------>此问题就退化成了53. 最大子序和问题
     */
    public int maxSubarraySumCircular(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        //dpMax[i]:nums[0...i]（nums[i]要被选中）中子数组的最大和
        int[] dpMax = new int[nums.length];
        //dpMin[i]:nums[0...i]（nums[i]要被选中）中子数组的最小和
        int[] dpMin = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int sum = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(dpMax[i - 1] + nums[i], nums[i]);
            dpMin[i] = Math.min(dpMin[i - 1] + nums[i], nums[i]);
            sum += nums[i];
            max = Math.max(max, dpMax[i]);
            min = Math.min(min, dpMin[i]);
        }
        /*max<0意味着nums内的所有数都是负数
        此时sum - min == 0如果直接return Math.max(max,sum - min)
        的话会得到一个错误的结果*/
        return max > 0 ? Math.max(max, sum - min) : max;
    }
}
