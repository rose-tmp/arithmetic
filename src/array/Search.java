package array;

/**
 * @author - ZwZ
 * @date - 2021/3/25 - 19:49
 * @Description:33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的索引，否则返回-1。
 * <p>
 * 示例 1：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * <p>
 * 示例2：
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：nums = [1], target = 0
 * 输出：-1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 */
public class Search {
    /**
     * 数组局部有序
     * 照样可以使用二分查找的思想
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        /*这种类型的问题有时候把<改成<=就可以解决问题
        [4,5,6,7,0,1,2] 0 这个示例在left < right的条件下得不到正确答案
        在left<=right下就可以   这种问题不debug一下的话不好排查
        以后遇到这种问题之后可以从<=这个符号入手排查*/
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            //左半部分有序
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                /*這個if條件不加也是可以的 因為进了这个else则说明左半部分是无序的
                而左半部分无序的话只会有一种情况就是：6,7,1,2,3,4,5
                所以这样子的话，右边肯定是有序的，加不加这个if执行的顺序和效果都是一样的
                if(nums[mid] <= nums[right]){
                    if (target >= nums[mid] && target <= nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }*/
                if (target >= nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
