package sword_zhuanxiang.huaDongChuangKou;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2022/7/29-20:06 - 周五
 * @Description:剑指 Offer II 009. 乘积小于 K 的子数组
 * 给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
 *
 * 示例 1:
 *
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8 个乘积小于 100 的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * 示例 2:
 *
 * 输入: nums = [1,2,3], k = 0
 * 输出: 0
 */
public class NumSubarrayProductLessThanK009 {
    /**
     * 滑动窗口 官方题解
     * 这个题和008很像，一个是求和，一个是求乘积
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        int res = 0;
        int sum = 1;
        for (int j = 0; j < nums.length; j++) {
            sum *= nums[j];
            //寻找j之前会导致sum<k的最小的i
            while (i <= j && sum >= k) {
                sum /= nums[i];
                i++;
            }
            //j-i+1:[i...j]之间以j结尾的所有子数组的个数
            res += j - i + 1;
        }
        return res;
    }

    /**
     * 暴力
     */
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        int res = 0;
        //寻找以每个元素开头的所有子数组中有多少符合条件
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            res++;
            for (int j = i + 1; j < nums.length; j++) {
                sum *= nums[j];
                if (sum < k) {
                    res++;
                } else {
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 一开始选用回溯就是不对的
     * 子数组问题要保证每次选出来的数连续，但是回溯无法保证
     * 例如：[10,5,2,6]中会选出来[10,2]
     */
    public int numSubarrayProductLessThanK3(int[] nums, int k) {
        dfs(nums, k, new ArrayList<>(), 0);
        return res;
    }

    int res = 0;
    List<List<Integer>> listR = new ArrayList<>();

    public void dfs(int[] nums, int k, List<Integer> list, int index) {
        int sum = 1;
        for (Integer integer : list) {
            sum *= integer;
        }
        if (sum < k) {
            res++;
            listR.add(new ArrayList<>(list));
        }
        for (int i = index; i < nums.length; i++) {
            if (sum * nums[i] >= k) {
                continue;
            }
            list.add(nums[i]);
            dfs(nums, k, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        NumSubarrayProductLessThanK009 thanK009 = new NumSubarrayProductLessThanK009();
        thanK009.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100);
        for (int i = 0; i < thanK009.listR.size(); i++) {
            System.out.println(thanK009.listR.get(i));
        }
    }
}
