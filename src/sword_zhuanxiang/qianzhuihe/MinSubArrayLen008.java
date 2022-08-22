package sword_zhuanxiang.qianzhuihe;

/**
 * @author - ZwZ
 * @date - 2022/7/26-15:13 - 周二
 * @Description:剑指 Offer II 008. 和大于等于 target 的最短子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * <p>
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 */
public class MinSubArrayLen008 {
    /**
     * 前缀和+二分查找 -->这个思路挺有意思 虽然时间复杂度不敌滑动窗口，但是思路新颖
     *
     * 示例1：[2,3,1,2,4,3]
     * 根据示例1构造前缀和数组：[2,5,6,8,12,15]
     * 遍历前缀和数组arr,在遍历到每个位置i的时候,在前缀和数组中查找值大于target + arr[i]的最小下标
     * 例如示例1中，遍历到arr[0]的时候，就在arr中查找大于7+arr[0]=9的元素，也就是12
     * 而12的下标是4，那么这就说明在原数组中[2,3,1,2,4]所构成的子数组[3,1,2,4]的元素和大于target(7)
     * 此时更新ans的值
     * 然后依次遍历前缀和数组中的后续元素，不断地更新ans，即可得到最终结果
     *
     * 而“在前缀和数组中查找值大于target+arr[i]的最小下标”这一操作借助二分查找，将时间复杂度降到logN
     * （因为元素组中的元素全部>0,所以前缀和数组中的元素是有序的，所以是可以用二分查找的）
     * <p>
     * 时间复杂度o(NlongN)
     */
    public int minSubArrayLen1(int target, int[] nums) {
        return 0;
    }


    /**
     * 滑动窗口
     * 时间复杂度o(n)
     */
    public int minSubArrayLen2(int target, int[] nums) {
        int i = 0, j = 0;//i<j j:右指针 i:左指针
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        while (j < nums.length) {
            //sum < target的时候右指针一直右移
            sum += nums[j++];
            //sum >= target的时候左指针一直左移
            while (sum >= target) {
                ans = Math.min(ans, j - i);
                sum -= nums[i];//由于左指针的左移,sum也要将移出滑动窗口的元素减去
                i++;//左指针左移
            }
        }
        return ans;
    }
    /**
     * dp
     * 时间复杂度o(n^2)
     * 而且不能创建dp数组，否则会“超出内存限制”,无法AC
     */
    public int minSubArrayLen3(int target, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int pre = nums[i];
            int cur = pre;
            for (int j = i; j < nums.length; j++) {
                if (i < j) {
                    cur = pre + nums[j];
                    pre = cur;
                }
                if (cur >= target && (j - i + 1) < min) {
                    min = j - i + 1;
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    /**
     * 没有优化过的dp
     * dp[i][j]:nums[i...j]的和
     * 时间复杂度：o(n^2)
     * */
    /*public int minSubArrayLen3(int target, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int[][] dp = new int[nums.length][nums.length];
        for(int i = 0;i < nums.length;i++) {
            dp[i][i] = nums[i];
        }
        for(int i = 0;i < nums.length;i++) {
            for(int j = i;j < nums.length;j++) {
                if (i < j) {
                    dp[i][j] = dp[i][j - 1] + nums[j];
                }
                if (dp[i][j] >= target && (j - i + 1) < min) {
                    min = j - i + 1;
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }*/
}
