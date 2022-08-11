package sword_zhuanxiang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2022/7/26-15:11 - 周二
 * @Description:
 */
public class TreeSum007 {
    /**
     * 三个指针 index指向负数 p1 p2指向它后边的数
     * */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int index = 0;
        List<List<Integer>> res = new ArrayList<>();
        while (index < nums.length && nums[index] <= 0) {
            int p1 = index + 1;
            int p2 = nums.length - 1;
            while (p1 < p2) {
                int sum = nums[index] + nums[p1] + nums[p2];
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[index]);
                    list.add(nums[p1]);
                    list.add(nums[p2]);
                    res.add(list);
                    //去重
                    while (p1 + 1 < nums.length && nums[p1] == nums[p1 + 1]) {
                        p1++;
                    }
                    while (p2 > p1 && nums[p2] == nums[p2 - 1]) {
                        p2--;
                    }
                    p1++;
                    p2--;
                }
                if (sum < 0) {
                    p1++;
                }
                if (sum > 0) {
                    p2--;
                }
            }
            //去重
            while ((index + 1) < nums.length && nums[index] == nums[index + 1]) {
                index++;
            }
            index++;
        }
        return res;
    }
}
