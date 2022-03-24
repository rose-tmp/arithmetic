package array;

/**
 * @author - ZwZ
 * @date - 2020/11/19 - 10:54
 * @Description:283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * <p>
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 */
public class MoveZeroes {
    public void moveZeroes1(int[] nums) {
        /**
         * 冒泡
         * */
        for (int end = nums.length - 1; end > 0; end--) {
            boolean flag = true;
            for (int j = 0; j < end; j++) {
                if (nums[j] == 0) {
                    int temp = 0;
                    nums[j] = nums[j + 1];
                    nums[j + 1] = 0;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    /**
     * 双指针1
     */
    public void moveZeroes2(int[] nums) {
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
    }
    /**
     * 双指针2
     * */
    public void moveZeroes3(int[] nums){
        int i = 0;//记录0点的位置
        for (int j = 0; j < nums.length; j++) {
            if(nums[j] != 0){
                nums[i] = nums[j];
                i++;
            }
        }
        for (int k = i; k < nums.length; k++) {
            nums[k] = 0;
        }
    }
}
