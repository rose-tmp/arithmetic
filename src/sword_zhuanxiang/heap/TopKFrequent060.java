package sword_zhuanxiang.heap;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-08-14 10:20
 * @desc:剑指 Offer II 060. 出现频率最高的 k 个数字
 * 给定一个整数数组 nums 和一个整数 k ，请返回其中出现频率前 k 高的元素。可以按 任意顺序 返回答案。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * 进阶：所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 * <p>
 * 注意：本题与主站 347 题相同：https://leetcode-cn.com/problems/top-k-frequent-elements/
 */
public class TopKFrequent060 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = getCount(nums);
        //arr[0]:某元素 arr[1]:某元素出现的数量
        Queue<int[]> smallHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for (int key : map.keySet()) {
            int count = map.get(key);
            smallHeap.offer(new int[]{key, count});
            if (smallHeap.size() > k) {
                smallHeap.poll();
            }
        }
        int[] res = new int[smallHeap.size()];
        int index = 0;
        for (int[] arr : smallHeap) {
            res[index] = arr[0];
            index++;
        }
        return res;
    }

    /**
     * @return 返回每个元素出现的次数
     * key:元素值;value:出现次数
     */
    public Map<Integer, Integer> getCount(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, 1 + map.getOrDefault(num, 0));
        }
        return map;
    }
}
