package leetcode.middling;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author - ZwZ
 * @date - 2020/11/1 - 14:39
 * @Description:5556. 可以到达的最远建筑(第213场leetcode周赛第3题)
 * 给你一个整数数组 heights ，表示建筑物的高度。另有一些砖块 bricks 和梯子 ladders 。
 * 你从建筑物 0 开始旅程，不断向后面的建筑物移动，期间可能会用到砖块或梯子。
 * 当从建筑物 i 移动到建筑物 i+1（下标 从 0 开始 ）时：
 * 如果当前建筑物的高度 大于或等于 下一建筑物的高度，则不需要梯子或砖块
 * 如果当前建筑的高度 小于 下一个建筑的高度，您可以使用 一架梯子 或 (h[i+1] - h[i]) 个砖块
 * 如果以最佳方式使用给定的梯子和砖块，返回你可以到达的最远建筑物的下标（下标 从 0 开始 ）。
 * <p>
 * 示例 1：
 * 输入：heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
 * 输出：4
 * 解释：从建筑物 0 出发，你可以按此方案完成旅程：
 * - 不使用砖块或梯子到达建筑物 1 ，因为 4 >= 2
 * - 使用 5 个砖块到达建筑物 2 。你必须使用砖块或梯子，因为 2 < 7
 * - 不使用砖块或梯子到达建筑物 3 ，因为 7 >= 6
 * - 使用唯一的梯子到达建筑物 4 。你必须使用砖块或梯子，因为 6 < 9
 * 无法越过建筑物 4 ，因为没有更多砖块或梯子。
 * <p>
 * 示例 2：
 * 输入：heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
 * 输出：7
 * <p>
 * 示例 3：
 * 输入：heights = [14,3,19,3], bricks = 17, ladders = 0
 * 输出：3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/furthest-building-you-can-reach
 */
public class FurthestBuilding {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        Queue<Integer> queue = new PriorityQueue();//从小到大排列的队列
        /*
        * queue里面就存储差值最大的那ladders个
        * 只要是queue.size() > ladders，那么就说明队列里边比较小的那些就要用砖而不是梯子
        * 至于此时队列中剩下的那ladders是不是就是最终使用梯子的那几个，这个不确定
        * 因为在没有遍历完的时候，有可能后面进队列的值会把此时队列中的最小值挤掉
        * 但是能保证的就是：queue中一直存储了差值最大的ladders个
        * */
        for (int i = 0; i < heights.length - 1; i++) {
            if (heights[i] < heights[i + 1]) {
                queue.add(heights[i + 1] - heights[i]);
                //相差最大的ladders个才用梯子，其他的都要用砖
                if(queue.size() > ladders){
                    bricks -= queue.poll();
                }
                if(bricks < 0){
                    return i;
                }
            }
        }
        return heights.length - 1;
    }
}
