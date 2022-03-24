//leetcode全部有关于股票的题目
package leetcode.dp.stockProblems;

import java.util.Arrays;

/**
 * @author - ZwZ
 * @date - 2022/3/23-14:59 - 周三
 * @Description:2110. 股票平滑下跌阶段的数目
 * 给你一个整数数组prices，表示一支股票的历史每日股价，其中prices[i]是这支股票第i天的价格。
 * 一个平滑下降的阶段定义为：对于连续一天或者多天，每日股价都比前一日股价恰好少 1，这个阶段第一天的股价没有限制。
 * 请你返回平滑下降阶段的数目。
 * <p>
 * <p>
 * 示例 1：
 * 输入：prices = [3,2,1,4]
 * 输出：7
 * 解释：总共有 7 个平滑下降阶段：
 * [3], [2], [1], [4], [3,2], [2,1] 和 [3,2,1]
 * 注意，仅一天按照定义也是平滑下降阶段。
 * 示例 2：
 * 输入：prices = [8,6,7,7]
 * 输出：4
 * 解释：总共有 4 个连续平滑下降阶段：[8], [6], [7] 和 [7]
 * 由于 8 - 6 ≠ 1 ，所以 [8,6] 不是平滑下降阶段。
 * 示例 3：
 * 输入：prices = [1]
 * 输出：1
 * 解释：总共有 1 个平滑下降阶段：[1]
 * <p>
 * 提示：
 * <p>
 * 1 <= prices.length <= 105
 * 1 <= prices[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-smooth-descent-periods-of-a-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GetDescentPeriods {
    /*
     * dp
     * */
    public long getDescentPeriods1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        //dp[i]:0---i天(含0和i)中以第i天为结束点，共有多少次平滑下跌
        int[] dp = new int[prices.length];
        //初始化dp 3 2 1 4
        Arrays.fill(dp, 1);
        long res = 1;
        for (int i = 1; i < dp.length; i++) {
            if (prices[i - 1] - prices[i] == 1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
            res += dp[i];
        }
        return res;
    }

    /*
     * 寻找等差数列
     * 思路:每个prices都是由若干个等差数列构成(通过寻找等差数列的方式加速遍历过程中的计算)
     * */
    public long getDescentPeriods2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int prev = 0;
        int next = 1;

        long res = 0;
        while (prev < next && next < prices.length) {
            while (prices[next - 1] - prices[next] == 1) {
                next++;
                if (next >= prices.length) {
                    break;
                }
            }
            int n = next - prev;
            res += ((long) n * (n + 1)) / 2;

            prev = next;
            next++;
        }
        //当最后一个位置上的数”自成一派“的时候,上边的while是计算不到的
        if (prev == prices.length - 1) {
            res++;
        }
        return res;
    }
}
