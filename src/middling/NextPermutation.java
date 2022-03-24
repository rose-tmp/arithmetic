package middling;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2020/11/10 - 21:22
 * @Description:31. 下一个排列
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return;
        }
        int index = Integer.MIN_VALUE;
        //从后向前寻找第一个出现前者小于后者的位置
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] >= nums[i]) {
                continue;
            } else {
                index = i - 1;
                break;
            }
        }
        //没有找到  即整个数组满足从大到小排列的顺序
        if (index == Integer.MIN_VALUE) {
            //将数组逆序
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < nums.length; i++) {
                stack.push(nums[i]);
            }
            for (int i = 0; i < nums.length; i++) {
                nums[i] = stack.pop();
            }
            return;
        }
        int min = index + 1;
        //从index开始向后遍历开始寻找比其大的数中最小的数的下标
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] > nums[index] && nums[i] < nums[min]) {
                min = i;
            }
        }
        //将min位置上的元素和index位置上的元素交换
        int temp = nums[min];
        nums[min] = nums[index];
        nums[index] = temp;
        //对下标index+1---nums.length-1范围上的元素进行从小到大排序
        Arrays.sort(nums, index + 1, nums.length);
    }
}
