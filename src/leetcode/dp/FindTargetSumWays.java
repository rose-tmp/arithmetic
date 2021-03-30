package leetcode.dp;

/**
 * @author - ZwZ
 * @date - 2021/3/30 - 18:27
 * @Description:494. 目标和
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号+和-。
 * 对于数组中的任意一个整数，你都可以从+或-中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * <p>
 * <p>
 * 示例：
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * 一共有5种方法让最终目标和为3。
 * <p>
 * 提示：
 * <p>
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 */
public class FindTargetSumWays {
    /**
     * 动态规划
     * 首先，如果我们把 nums 划分成两个子集 A 和 B，分别代表分配 + 的数和分配 - 的数，
     * 那么他们和 target 存在如下关系：
     * sum(A) = target + sum(B)
     * sum(A) + sum(A) = target + sum(B) + sum(A)
     * sum(A) = (target + sum(nums))/2
     * <p>
     * 也就是说我们本来是要找nums中含有+ -之后的target 但是由于这样子直接去定义dp数组(dp[i][j] : nums[0...i]通过+-能得到目标和j的方法数)
     * 的前提下：dp[i][j] = dp[i - 1][j + nums[i]] + dp[i - 1][j - nums[i]]
     * 而由于数组不支持负数下标，所以j - nums[i]在数组中无法表示
     * <p>
     * 所以通过以上分析，我们就可以将含有+-的运算变成了只含有+的运算，现在的问题就变成了：在数组中找到一组数，使得他们加起来
     * 的和为target + sum(nums) / 2
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays1(int[] nums, int S) {
        int sums = 0;
        for (int i = 0; i < nums.length; i++) {
            sums += nums[i];
        }
        int target = (S + sums) / 2;
        if (target > sums || (S + sums) % 2 == 1) {
            return 0;
        }
        //dp[i][j]:nums[0...i]中取出几个数使他们的和为j 一共有多少种取法
        int[][] dp = new int[nums.length][target + 1];
        //base case
        if (nums[0] < target + 1) {
            dp[0][nums[0]] = 1;
        }
        /*在nums[0...i]中取随意个数的数使他们的和为0 只有一种取法：每个元素都不取
        但是这里有个坑，那就是当有元素为0的时候
        如果有一个元素为0,那么个数就要翻倍，因为这个位置不再是只有一种选择即不取了  而是有两种选择：取和不取*/
        int count = 1;
        for (int i = 0; i < dp.length; i++) {
            if (nums[i] == 0) {
                count *= 2;
            }
            dp[i][0] = count;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j - nums[i] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[dp.length - 1][target];
    }

    int res = 0;

    public int findTargetSumWays2(int[] nums, int S) {
        backTrack(0, nums, S, 0);
        return res;
    }

    public void backTrack(int sum, int[] nums, int s, int index) {
        if (sum == s && index == nums.length) {
            res++;
        }
        if (index == nums.length) {
            return;
        }
        backTrack(sum + nums[index], nums, s, index + 1);
        backTrack(sum - nums[index], nums, s, index + 1);
    }

    public static void main(String[] args) {
        FindTargetSumWays sumWays = new FindTargetSumWays();
        System.out.println(sumWays.findTargetSumWays1(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}, 1));
        ;
    }
}
