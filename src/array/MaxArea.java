package array;

/**
 * @author - ZwZ
 * @date - 2021/3/21 - 15:52
 * @Description:11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 */
public class MaxArea {
    /**
     * 暴力
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int temp = (j - i) * Math.min(height[i], height[j]);
                if (max < temp) {
                    max = temp;
                }
            }
        }
        return max;
    }

    /**
     * 双指针
     *
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int pre = 0;
        int rear = height.length - 1;
        int max = Integer.MIN_VALUE;
        while (pre <= rear) {
            max = Math.max(max, (rear - pre) * Math.min(height[pre], height[rear]));
            /*
            * 当我们想要寻找其他的“矩形”看看面积是否可以增大时，接下来的矩形的底肯定是要减小的
            * (不是pre++就是rear--)
            * 但是到底让pre++呢还是rear--呢
            * 我们要寻求“矩形”的高的最大化，那么肯定是谁数值小就放弃谁 而保留另外一个
            * */
            if(height[pre] < height[rear]){
                pre++;
            }else{
                rear--;
            }
        }
        return max;
    }
}
