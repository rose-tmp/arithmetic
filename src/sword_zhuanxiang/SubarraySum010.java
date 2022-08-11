package sword_zhuanxiang;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2022/7/30-10:48 - 周六
 * @Description:给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
 *
 * 示例 1：
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2
 * 解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
 * 示例 2：
 *
 * 输入:nums = [1,2,3], k = 3
 * 输出: 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/QTMn0o
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SubarraySum010 {
    /**
     * 前缀和
     * sum[i...j]和为k的问题可以转换为:sum[0...j]-sum[0...i-1]=k的问题
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int res = 0;
        // array/PrefixSum中有对这个位置元素的解释
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
