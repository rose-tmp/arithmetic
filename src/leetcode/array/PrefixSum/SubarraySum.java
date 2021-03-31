package leetcode.array.PrefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2021/3/31 - 13:30
 * @Description:560. 和为K的子数组
 * 给定一个整数数组和一个整数k，你需要找到该数组中和为k的连续的子数组的个数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * <p>
 * 数组的长度为 [1, 20,000]。
 * <p>
 * 数组中元素的范围是 [-1000, 1000] ，且整数k的范围是[-1e7, 1e7]。
 *
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 */
public class SubarraySum {
    /**
     * 枚举
     * 时间复杂度O(n^2)
     * 空间复杂度O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 借助前缀和的思想
     * sum[]为前缀和数组
     * sum[i]表示：nums[0...i - 1]所有元素的和
     * <p>
     * 时间复杂度O(n^2)
     * 空间复杂度O(N)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] sum = new int[nums.length + 1];
        //赋值前缀和数组
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int res = 0;
        //寻找nums[j...i]和为k的子数组
        for (int i = 1; i < sum.length; i++) {
            for (int j = 0; j < i; j++) {
                if (sum[i] - sum[j] == k) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 将带有前缀和思想的方法2改进
     * <p>
     * 方法2中的双重for的内层for目的是找到一个前缀和为sum[i] - k的子数组
     * 那么如果我们把每一个前缀和都存储在map里，岂不是就不用遍历而是直接O(1)的时间复杂度就可以解决问题了
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum3(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        int ans = 0;
        //key 前缀和   value 此前缀和出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        /*重点说一下这个位置
        一开始写代码是没有加这一行的，但是[1,-1,0] 0这种情况正确答案应该是3但是结果得到的是2
        可以字节走一遍这个例子，如果不加map.put(0,1)的话，一开始sum = 1 + (-1) = 0的时候这个时候就应该将ans+1了
        但是因为map.containKey(target)为false所以而少加了一次*/
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int target = sum - k;
            if (map.containsKey(target)) {
                ans += map.get(target);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        SubarraySum sum = new SubarraySum();
        sum.subarraySum3(new int[]{1, -1, 0}, 0);
    }
}
