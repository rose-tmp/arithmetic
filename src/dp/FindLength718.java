package dp;

/**
 * @author: ZwZ
 * @date: 2022-09-26 18:25
 * @desc:718. 最长重复子数组
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * 输出：3
 * 解释：长度最长的公共子数组是 [3,2,1] 。
 * 示例 2：
 * <p>
 * 输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * 输出：5
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 * 通过次数155,037提交次数271,429
 */
public class FindLength718 {
    public int findLength(int[] nums1, int[] nums2) {
        //dp[i][j]:nums1[i:]和nums2[j:]前缀和的最大长度
        int[][] dp = new int[nums1.length][nums2.length];
        for (int i = dp[0].length - 1; i >= 0; i--) {
            if (nums1[nums1.length - 1] == nums2[i]) {
                dp[nums1.length - 1][i] = 1;
            }
        }
        for (int i = dp.length - 1; i >= 0; i--) {
            if (nums1[i] == nums2[nums2.length - 1]) {
                dp[i][nums2.length - 1] = 1;
            }
        }
        int res = 0;
        //从后向前开始遍历
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = dp[0].length - 2; j >= 0; j--) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                    res = Math.max(dp[i][j], res);
                }
            }
        }
        return res;
    }
}
