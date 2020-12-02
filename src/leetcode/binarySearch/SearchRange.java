package leetcode.binarySearch;

/**
 * @author - ZwZ
 * @date - 2020/12/1 - 18:57
 * @Description:34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 * <p>
 * 进阶：
 * 你可以设计并实现时间复杂度为O(log n)的算法解决此问题吗？
 * <p>
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * <p>
 * 示例2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * <p>
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 * <p>
 * 提示：
 * 0 <= nums.length <= 105
 * -109<= nums[i]<= 109
 * nums是一个非递减数组
 * -109<= target<= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 */
public class SearchRange {
    /**
     * 暴力  双指针
     */
    public int[] searchRange1(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return res;
        }
        /*if (nums.length == 1) {
            if (nums[0] == target) {
                res[0] = 0;
                res[1] = 0;
                return res;
            } else {
                return res;
            }
        }
        int pre = 0;
        int rear = nums.length - 1;
        while (pre < nums.length && nums[pre] != target) {
            pre++;
        }
        if(pre == nums.length && nums[pre - 1] == target){
            res[0] = res[1] = pre;
            return res;
        }
        if(pre == nums.length && nums[pre - 1] != target){
            return res;
        }
        while (rear > 0 && nums[rear] != target) {
            rear--;
        }
        res[0] = pre;
        res[1] = rear;*/
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            if (nums[i] == target && nums[j] == target) {
                break;
            }
            if (nums[i] < target) {
                i++;
            }
            if (nums[j] > target) {
                j--;
            }
        }
        if (i > nums.length - 1 || j < 0 || nums[i] != target || nums[j] != target) {
            return res;
        }
        res[0] = i;
        res[1] = j;
        return res;
    }

    /**
     * 二分查找实现复杂度O(logN)
     * 参考 https://labuladong.gitbook.io/algo/di-ling-zhang-bi-du-xi-lie/er-fen-cha-zhao-xiang-jie
     */
    public int[] searchRange2(int[] nums, int target) {
        int[] res = new int[2];
        if (nums.length == 0 || nums == null) {
            return new int[]{-1, -1};
        }
        res[0] = findLeft(nums, target);
        res[1] = findRight(nums, target);
        return res;
    }

    private int findLeft(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        //[left,right]  退出while得条件就是：left == right + 1
        while (left <= right) {
            //(left + right) / 2可能会造成int值溢出
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定左侧边界
                right = mid - 1;
            }
        }
        /*left得范围[0,nums.length]
        当整个数组中得元素都比target小得时候，最后left == nums.length
        当整个数组中得元素都比target大得时候，最后left == 0 并且此时nums[left] != target
         */
        if (left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }

    private int findRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定右侧边界
                left = mid + 1;
            }
        }
        /*当target比所有得元素都小得时候 right会减到-1
        当target比所有得元素都大得时候 right == nums.length - 1
        */
        if (right < 0 || nums[right] != target)
            return -1;
        return right;
    }

    public static void main(String[] args) {
        SearchRange range = new SearchRange();
        int[] arr = new int[]{2, 2};
        int[] res = range.searchRange1(arr, 3);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }
}
