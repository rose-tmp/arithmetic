package array;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2021/3/24 - 9:31
 * @Description:15. 三数之和
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，
 * 使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 */
public class ThreeSum {
    /**
     * 双指针
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;
        Arrays.sort(nums);
        //双指针
        int left, right;
        for (int i = 0; i < nums.length - 1; i++) {
            //避免加入重复序列
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    //去重   防止在-2,0,0,2,2例子中[-2,0,2]被加入两次的情况
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    /**
     * 回溯
     *
     * @param nums
     * @return
     */
    List<List<Integer>> res = new ArrayList<>();
    //保证序列不重复
    Set<List<Integer>> set = new HashSet<>();

    public List<List<Integer>> threeSum2(int[] nums) {
        if (nums == null) return res;
        Arrays.sort(nums);
        dfs(nums, new ArrayList<>(), 0, 0);
        for (List list : set) {
            res.add(list);
        }
        return res;
    }

    /**
     * @param nums
     * @param index 从index下标开始 向后找和为sum的数
     * @param list
     * @param sum
     */
    public void dfs(int[] nums, List<Integer> list,
                    int index, int sum) {
        if (index > nums.length || list.size() > 3) return;
        if (sum == 0 && list.size() == 3) {
            set.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(nums, list, index + 1, sum - nums[i]);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        ThreeSum sum = new ThreeSum();
        int[] arr = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = sum.threeSum1(arr);
        for (List<Integer> list : res) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
            }
            System.out.println("-----");
        }
    }
}
