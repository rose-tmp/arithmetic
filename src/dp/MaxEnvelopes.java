package dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author - ZwZ
 * @date - 2021/1/7 - 19:16
 * @Description:354. 俄罗斯套娃信封问题
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式(w, h)出现。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 说明:
 * 不允许旋转信封。
 * <p>
 * 示例:
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
 */
public class MaxEnvelopes {
    /**
     * 动态规划
     * 本题最主要的是排序的思想
     * 排好序之后就变成了 LengthOfLIS最长上升子序列 的问题
     */
    public int maxEnvelopes1(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }
        int[] dp = new int[envelopes.length];
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                /*宽度相同时降序排列
                 * 防止等一下用LIS思想去解决这个信封嵌套问题时出现[1,2],[1,3],[1,4]这个序列中
                 * [1,2]被套进[1,3]而[1,3]又被套进[1,4]的情况出现
                 * */
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                }
                //按照宽度升序排列
                return o1[0] - o2[0];
            }
        });
        //接下来就变成了在高度h这一个维度上进行LIS
        Arrays.fill(dp, 1);
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 带备忘录的递归 过程和LengthOfLIS相同
     */
    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                /*宽度相同时降序排列
                 * 防止等一下用LIS思想去解决这个信封嵌套问题时出现[1,2],[1,3],[1,4]这个序列中
                 * [1,2]被套进[1,3]而[1,3]又被套进[1,4]的情况出现
                 * */
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                }
                //按照宽度升序排列
                return o1[0] - o2[0];
            }
        });
        int[] memo = new int[envelopes.length];
        Arrays.fill(memo,1);
        int res = 0;
        for (int i = 1; i < envelopes.length; i++) {
            dp(envelopes, memo, i);
            res = Math.max(res,memo[i]);
        }
        return res;
    }

    public int dp(int[][] envelopes, int[] memo, int index) {
        if (index == 0) {
            return 1;
        }
        if (memo[index] != 1) {
            return memo[index];
        }
        for (int i = index - 1; i >= 0; i--) {
            if (envelopes[i][1] < envelopes[index][1]) {
                memo[index] = Math.max(memo[index], 1 + dp(envelopes, memo, i));
            }
        }
        return memo[index];
    }

    public static void main(String[] args) {
        MaxEnvelopes maxEnvelopes = new MaxEnvelopes();
        maxEnvelopes.maxEnvelopes2(new int[][]{{30, 50}, {12, 2}, {3, 4}, {12, 15}});
    }
}
