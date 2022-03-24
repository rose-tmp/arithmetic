package array.PrefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2021/3/31 - 15:49
 * @Description:523. 连续的子数组和
 * 给定一个包含非负数的数组和一个目标 整数k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，且总和为k的倍数，
 * 即总和为n * k ，其中n也是一个整数。
 * <p>
 * 示例 1：
 * 输入：[23,2,4,6,7], k = 6
 * 输出：True
 * 解释：[2,4]是一个大小为 2 的子数组，并且和为 6。
 * <p>
 * 示例 2：
 * 输入：[23,2,6,4,7], k = 6
 * 输出：True
 * 解释：[23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/continuous-subarray-sum
 */
public class CheckSubarraySum {
    /**
     * 暴力枚举
     * 时间复杂度O(N^2)
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum1(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int start = i; start < nums.length; start++) {
                sum += nums[start];
                //子数组和为k的倍数且长度>=2
                if (sum % k == 0 && start - i >= 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 前缀和
     * 时间复杂度O(N^2)
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum2(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        //sums[i]:nums[0...i-1]的和
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 2; i < sums.length; i++) {
            for (int j = 0; j < i - 1; j++) {
                if ((sums[i] - sums[j]) % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 优化前缀和方法
     * 和subarraySum优化的思想类似，第二层的for是用来寻找nums[i...j]看看哪个子数组的和 %k ==0
     * 也正是这一层for导致时间复杂度O(N^2)
     * 而我们把(sums[i] - sums[j]) % k == 0变型：
     * ---> (sums[i] % k) - (sums[j] % k) == 0
     * ------> sums[i] % k == sums[j] % k
     * -------->此时，sums[i]和sums[j]扯上了关系  我们寻找(sums[i] - sums[j]) % k == 0的问题就变成了在[0...i]上找一个j位置
     * 然后sum[j]满足sums[i] % k == sums[j] % k
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum3(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k == 0) {
            return false;
        }
        //sums[i]:nums[0...i-1]的和
        int sum = 0;
        //前i个数的和为sum    key:sum%k  value:i
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum % k)) {
                if ((i - map.get(sum % k)) > 1) {
                    return true;
                }
            } else {
                map.put(sum % k, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CheckSubarraySum sum = new CheckSubarraySum();
        sum.checkSubarraySum1(new int[]{23, 6, 9}, 6);
    }
}
