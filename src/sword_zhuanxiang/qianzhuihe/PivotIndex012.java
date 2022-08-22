package sword_zhuanxiang.qianzhuihe;

/**
 * @author - ZwZ
 * @date - 2022/7/30-16:07 - 周六
 * @Description:剑指 Offer II 012. 左右两边子数组的和相等
 * 给你一个整数数组 nums ，请计算数组的 中心下标 。
 * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
 * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
 *
 * 示例 1：
 * 输入：nums = [1,7,3,6,5,6]
 * 输出：3
 * 解释：
 * 中心下标是 3 。
 * 左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
 * 右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
 * 示例 2：
 *
 * 输入：nums = [1, 2, 3]
 * 输出：-1
 * 解释：
 * 数组中不存在满足此条件的中心下标。
 * https://leetcode-cn.com/problems/find-pivot-index/
 */
public class PivotIndex012 {
    /**
     * 前缀和
     * sumL + nums[i] + sumR = sum
     * 2sumL + nums[i] = sum
     * num[i] = sum - 2sumL
     *
     * 时间复杂度o(N)
     * */
    public int pivotIndex1(int[] nums) {
        int sumL = 0,sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        //nums[0] = sum,即2sumL=0,所以直接返回0
        if (sum - nums[0] == 0) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            sumL += nums[i-1];
            if (nums[i] == sum - 2 * sumL) {
                return i;
            }
        }
        return -1;
    }
    /**
     * 遍历
     * */
    public int pivotIndex2(int[] nums) {
        int sumL = 0, sumR = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum - nums[0] == 0) {
            return 0;
        }
        sumR = sum - nums[0];
        for (int i = 1; i < nums.length; i++) {
            sumL += nums[i-1];
            sumR -= nums[i];
            if (sumL == sumR) {
                return i;
            }
        }
        return -1;
    }
}
