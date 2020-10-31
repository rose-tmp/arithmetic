package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2020/10/31 - 10:54
 * @Description:78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0)
            return res;
        backTrack(res, nums, new ArrayList<>(), 0);
        return res;
    }
    /**
     * 回溯函数
     * res:存储最终的结果
     * list:存储每个结果
     * 依旧使用index == nums.length作为base case的老套路
     * 每个位置上都面临着两种选择：要 和 不要
     * */
    public void backTrack(List<List<Integer>> res, int[] nums, List<Integer> list, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        //取此位置
        list.add(nums[index]);
        backTrack(res, nums, list, index + 1);
        //不取此位置
        list.remove(list.size() - 1);
        backTrack(res, nums, list, index + 1);
    }
}
