package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author - ZwZ
 * @date - 2020/11/2 - 8:41
 * @Description:349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * <p>
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * <p>
 * <p>
 * <p>
 * 说明：
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 */
public class Intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet();
        //将数组1中的元素加入set集合
        for (int i = 0; i < len1; i++) {
            set.add(nums1[i]);
        }
        boolean flag = false;
        //遍历数组2
        for (int i = 0; i < len2; i++) {
            if (set.contains(nums2[i]) && !list.contains(nums2[i])) {
                list.add(nums2[i]);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
