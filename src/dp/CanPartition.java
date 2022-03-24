package dp;

/**
 * @author - ZwZ
 * @date - 2020/10/11 - 10:32
 * @Description:
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，
 * 使得两个子集的元素和相等。
 * 注意:
 *     每个数组中的元素不会超过 100
 *     数组的大小不会超过 200
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 */
public class CanPartition {
    public boolean canPartition(int[] nums) {
        if(nums.length < 2){
            return false;
        }
        int sum = 0;
        int max = 0;
        //计算数组元素总和和最大值
        for(int i = 0;i < nums.length;i++){
            sum += nums[i];
            if(nums[i] > max){
                max = nums[i];
            }
        }
        //此时数组中元素不可能分成两份总和相等的子数组 target = sum/2
        if(sum % 2 != 0 || max > sum/2){
            return false;
        }
        //dp[i][j] 数组中下标0---i范围内是否存在和为j的元素
        boolean[][] dp = new boolean[nums.length][sum/2 + 1];
        //dp表赋初值
        for(int i = 0;i < nums.length;i++){
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        //填满dp表
        for(int i = 1;i < nums.length; i++){
            for(int j = 1;j < sum/2 + 1;j++){
                if(nums[i] <= j){
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i]];
                }else{
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }
        return dp[nums.length - 1][sum / 2];
    }
}
