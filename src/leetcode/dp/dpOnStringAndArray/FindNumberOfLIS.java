package leetcode.dp.dpOnStringAndArray;

import java.util.Arrays;

/**
 * @author - ZwZ
 * @date - 2020/12/7 - 9:07
 * @Description:673. 最长递增子序列的个数
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * <p>
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * <p>
 * 示例 2:
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * 注意:给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
 */
public class FindNumberOfLIS {
    /**
     * 300. 最长上升子序列 的改进
     */
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return dp(nums);
    }

    /**
     * 太   他  妈  难
     */
    private int dp(int[] nums) {
        //前i个元素，上升子序列中以第i个数字作为结尾的最长上升子序列的长度，注意nums[i]必须被选取
        int[] dp = new int[nums.length];
        //记录以第i个元素结尾的，最长递增子序列的个数
        int[] count = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    //nums[i]可以加到nums[j]后面形成一个上升子序列
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                        count[i] = count[j];
                    }
                    //说明在j之前已经存在了一个元素使得i位置和它构成一个子序列与i位置和j构成的子序列的长度相同
                    else if (dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }
        }
        //寻找最长子序列
        int max = 0;
        for (int i : dp) {
            max = Math.max(max, i);
        }
        //寻找最长子序列对应的个数
        int res = 0;
        for (int i = 0; i < count.length; i++) {
            if (dp[i] == max) {
                res += count[i];
            }
        }
        return res;
    }
}
