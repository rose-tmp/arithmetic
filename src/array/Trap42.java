package array;

/**
 * @author: ZwZ
 * @date: 2022-08-26 10:51
 * @desc:42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 示例 1：
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 */
public class Trap42 {
    /**
     * 经典接雨水问题
     * 思路：按列去计算，每一列所能存到的水的量由左右最大高度种的最小值去决定
     */
    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        if (height.length == 1) {
            return 0;
        }
        /*
         * 位于0和height.length-1处的柱子，无论多高，都存不到水
         * l:遍历每个位置的过程中，遍历到的左边界
         * r:遍历每个位置的过程中，遍历到的右边界
         * */
        int l = 0, r = height.length - 1;
        /*
         * lMax:l的左边的最大值
         * rMax:r的右边的最大值
         * */
        int lMax = height[l], rMax = height[r];
        int ans = 0;
        while (l < r) {
            //此时index这一列能装多少水，由height[l]或者lMax决定
            if (height[l] < height[r]) {
                if (height[l] >= lMax) {
                    lMax = height[l];
                } else {
                    ans += lMax - height[l];
                }
                l++;
            }
        }
        return ans;
    }
}
