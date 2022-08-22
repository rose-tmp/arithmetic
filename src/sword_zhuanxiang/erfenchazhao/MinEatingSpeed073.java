package sword_zhuanxiang.erfenchazhao;

/**
 * @author: ZwZ
 * @date: 2022-08-19 15:42
 * @desc:剑指 Offer II 073. 狒狒吃香蕉
 * 狒狒喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * 狒狒可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉，下一个小时才会开始吃另一堆的香蕉。
 * 狒狒喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * <p>
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 * 示例 3：
 * <p>
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
 * <p>
 * <p>
 * 注意：本题与主站 875 题相同： https://leetcode-cn.com/problems/koko-eating-bananas/
 */
public class MinEatingSpeed073 {
    /**
     * 对每个小时吃掉的香蕉的数量模拟一个二分查找的过程
     */
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;//每小时最少吃掉多少香蕉->初始化为1,即每小时至少吃掉一个
        int high = 0;//每小时最多吃掉多少香蕉
        //high初始化为香蕉堆中的最大香蕉数量
        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        int ans = high;
        while (low < high) {
            //如果每小时吃掉的相交的数量为cur个
            int cur = (high - low) / 2 + low;
            int t = getTime(piles, cur);
            //当前所用时间小于等于h,那么就把上界调到cur -> 看能不能再“压榨"一下极限
            if (t <= h) {
                high = cur;
                ans = Math.min(ans, cur);
            }
            //当前所用时间大于h,所以每小时吃掉cur个是不行的，吃少了 -> 下界调高
            else {
                low = cur + 1;
            }
        }
        return ans;
    }

    /**
     * @return 每小时吃n根香蕉 吃完所有香蕉所花时间
     */
    private int getTime(int[] piles, int n) {
        int ans = 0;
        for (int pile : piles) {
            //没有用pile/n是为了使得pile/n有余数时依然有正确输出
            ans += ((pile + n - 1) / n);
        }
        return ans;
    }
}
