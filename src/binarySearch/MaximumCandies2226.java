package binarySearch;

/**
 * @author: ZwZ
 * @date: 2022-09-17 22:20
 * @desc:2226. 每个小孩最多能分到多少糖果
 * 给你一个 下标从 0 开始 的整数数组 candies 。数组中的每个元素表示大小为 candies[i] 的一堆糖果。
 * 你可以将每堆糖果分成任意数量的 子堆 ，但 无法 再将两堆合并到一起。
 * 另给你一个整数 k 。你需要将这些糖果分配给 k 个小孩，使每个小孩分到 相同 数量的糖果。
 * 每个小孩可以拿走 至多一堆 糖果，有些糖果可能会不被分配。
 * 返回每个小孩可以拿走的 最大糖果数目 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：candies = [5,8,6], k = 3
 * 输出：5
 * 解释：可以将 candies[1] 分成大小分别为 5 和 3 的两堆，然后把 candies[2] 分成大小分别为 5 和 1 的两堆。
 * 现在就有五堆大小分别为 5、5、3、5 和 1 的糖果。可以把 3 堆大小为 5 的糖果分给 3 个小孩。可以证明无法让每个小孩得到超过 5 颗糖果。
 * 示例 2：
 * <p>
 * 输入：candies = [2,5], k = 11
 * 输出：0
 * 解释：总共有 11 个小孩，但只有 7 颗糖果，但如果要分配糖果的话，必须保证每个小孩至少能得到 1 颗糖果。
 * 因此，最后每个小孩都没有得到糖果，答案是 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= candies.length <= 105
 * 1 <= candies[i] <= 107
 * 1 <= k <= 1012
 * 通过次数9,397提交次数28,458
 */
public class MaximumCandies2226 {
    /**
     * 20220916博乐科技笔试题
     * 思路：https://leetcode.cn/problems/maximum-candies-allocated-to-k-children/solution/by-wa-pian-d-qw2q/
     */
    public int maximumCandies(int[] candies, long k) {
        long sum = 0;
        for (int j : candies) {
            sum += j;
        }
        if (sum < k) {
            return 0;
        }
        if (sum == k) {
            return 1;
        }
        long l = 1;
        long r = sum;
        long res = 0;
        while (l <= r) {
            //每人mid个
            long mid = l + (r - l) / 2;
            long count = 0;
            for (int candy : candies) {
                count += candy / mid;
            }
            //每人mid个的话能分不止k个，那么就试着再增多一点
            if (count >= k) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return (int) res;
    }

    public static int show() {
        try {
            //发生异常
            int a = 8 / 0;
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            System.out.println("执行finally模块");
        }
    }

    public static void main(String args[]) {
        System.out.println(show());
    }

}