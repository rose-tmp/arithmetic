package leetcode;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2021/3/19 - 11:48
 * @Description:
 */
public class Demo {
    List<List<Integer>> res = new ArrayList<>();
    //保证序列不重复
    Set<List<Integer>> set = new HashSet<>();

    public List<List<Integer>> threeSum(int[] nums) {
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
        if (index > nums.length || list.size() > 3) {
            return;
        }
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
}
