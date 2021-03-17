package leetcode.array;

/**
 * @author - ZwZ
 * @date - 2021/3/16 - 19:25
 * @Description:88. 合并两个有序数组
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
 * <p>
 * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。你可以假设nums1 的空间大小等于m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 示例 2：
 * <p>
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 */
public class MergeArr {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null) return;
        int ptr1 = m - 1;
        int ptr2 = n - 1;
        int ptr = nums1.length - 1;
        while (ptr1 >= 0 && ptr2 >= 0) {
            if(nums1[ptr1] <= nums2[ptr2]){
                nums1[ptr] = nums2[ptr2--];
            }else{
                nums1[ptr] = nums1[ptr1--];
            }
            ptr--;
        }
        //这里是重点
        int j = 0;
        for (int i = 0; i <= ptr2; i++) {
            nums1[j++] = nums2[i];
        }
    }

    public static void main(String[] args) {
        MergeArr mergeArr = new MergeArr();
        mergeArr.merge(new int[]{4, 0, 0, 0, 0, 0}, 1, new int[]{1, 2, 3, 5, 6}, 5);
    }
}
