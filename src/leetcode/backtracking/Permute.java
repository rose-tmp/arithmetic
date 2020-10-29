package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2020/10/29 - 15:11
 * @Description:46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 */
public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTrack(res,new ArrayList<>(),nums,0);
        return res;
    }
    /**
     * res : 存储最终的结果
     * list: 存储每次的数字序列
     * count: 记录list中存储的序列的长度，如果到了指定长度，则加入res
     * */
    public void backTrack(List<List<Integer>> res, List<Integer> list,
                          int[] nums, int count) {
        if (count == nums.length) {
            res.add(new ArrayList<>(list));
        }
        /*
        * 每次backTrack函数中进入这个for循环，其实就是对应字符串每个位置
        * for可以遍历出三个数字：1,2,3 然后分别放在三个位置上
        * */
        for (int i = 0; i < nums.length; i++) {
            //剪枝
            if(list.contains(nums[i])){
                continue;
            }
            list.add(nums[i]);
            backTrack(res, list, nums, count + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        Permute permute = new Permute();
        int[] nums = {1,2,3};
        permute.permute(nums);
    }
}
