package dp.subsequenceAndSubstring;

import java.util.Arrays;

/**
 * @author - ZwZ
 * @date - 2020/12/7 - 9:30
 * @Description:300. 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度
 * <p>
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是[2,5,7,101]                                                                                                                                                       ,3,7,101]，它的长度是 4
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可
 * 你算法的时间复杂度应该为O(n^2)
 * 进阶:你能将算法的时间复杂度降低到O(nlogn)吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 */
public class LengthOfLIS300 {
    /**
     * 动态规划 即自下而上为dp表赋值
     * 时间复杂度O(N^2)
     */
    public int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        //dp[i] 以nums[i]为结尾处字符的字符序列中最长上升子序列的长度
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        //赋值dp table
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
        }
        int res = Integer.MIN_VALUE;
        //寻找最长上升子序列
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    /**
     * 贪心+二分查找
     * 时间复杂度O(nlogn)
     * */
    public int lengthOfLIS3(int[] nums) {

        return 0;
    }
    /**
     * 带备忘录的递归 即自顶向下去为dp表赋值
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //memo[i] 以nums[i]为结尾处字符的字符序列中最长上升子序列的长度
        int[] memo = new int[nums.length];
        Arrays.fill(memo, 1);

        for (int i = nums.length - 1; i >= 0; i--) {
            dp(nums, memo, i);
        }
        int res = 0;
        for (int i = 0; i < memo.length; i++) {
            res = Math.max(res, memo[i]);
        }
        return res;
    }

    /**
     * 为index下标处的memo赋值
     */
    public int dp(int[] nums, int[] memo, int index) {
        if (index == 0) {
            return 1;
        }
        //之前计算过
        if (memo[index] > 1) {
            return memo[index];
        }
        int res = 1;
        for (int i = index - 1; i >= 0; i--) {
            if (nums[i] < nums[index]) {
                res = Math.max(res, 1 + dp(nums, memo, i));
            }
        }
        //计算出结果之后先不着急返回 而是存入备忘录
        memo[index] = res;
        return res;
    }

    public static void main(String[] args) {
        LengthOfLIS300 lis = new LengthOfLIS300();
        int[] arr = new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(lis.lengthOfLIS2(arr));
    }
}
