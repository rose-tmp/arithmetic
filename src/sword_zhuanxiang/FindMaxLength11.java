package sword_zhuanxiang;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2022/7/30-11:32 - 周六
 * @Description:剑指 Offer II 011. 0 和 1 个数相同的子数组
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
 * 示例 2：
 * <p>
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量 0 和 1 的最长连续子数组。
 * https://leetcode-cn.com/problems/contiguous-array/
 */
public class FindMaxLength11 {
    /**
     * 前缀和
     * 把0看作-1
     */
    public int findMaxLength(int[] nums) {
        int preSum = 0;
        int maxRes = 0;
        //key:前缀和 value:第一次出现这个前缀和时的nums下标
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i] == 0 ? -1 : 1;
            //nums[map.get(presum)...i]这段和为0
            if (map.containsKey(preSum)) {
                maxRes = Math.max(maxRes, i - map.get(preSum));
            } else {
                map.put(preSum, i);
            }
        }
        return maxRes;
    }
}
