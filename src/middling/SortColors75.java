package middling;

/**
 * @author: ZwZ
 * @date: 2022-09-27 14:57
 * @desc:
 */
public class SortColors75 {
    public void sortColors(int[] nums) {
        int nums0 = 0, nums1 = 0, nums2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums0++;
            } else if (nums[i] == 1) {
                nums1++;
            } else {
                nums2++;
            }
        }
        int index = 0;
        while (nums0 > 0) {
            nums[index] = 0;
            index++;
            nums0--;
        }
        while (nums1 > 0) {
            nums[index] = 1;
            index++;
            nums1--;
        }
        while (nums2 > 0) {
            nums[index] = 2;
            index++;
            nums2--;
        }
    }
}
