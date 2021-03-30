package leetcode.middling;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2021/3/30 - 18:17
 * @Description:347. 前 K 个高频元素
 * * 给定一个非空的整数数组，返回其中出现频率前k高的元素。
 * *
 * * 示例 1:
 * * 输入: nums = [1,1,1,2,2,3], k = 2
 * * 输出: [1,2]
 * *
 * * 示例 2:
 * * 输入: nums = [1], k = 1
 * * 输出: [1]
 * *
 * * 提示：
 * *
 * * 你可以假设给定的k总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * * 你的算法的时间复杂度必须优于 O(n log n) ,n是数组的大小。
 * * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * * 你可以按任意顺序返回答案。
 * *
 * * 来源：力扣（LeetCode）
 * * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 */
public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        Queue<int[]> queue = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        int[] res = new int[k];
        for (int i = 0; i < queue.size(); i++) {
            res[i] = queue.peek()[0];
        }
        return res;
    }
}
