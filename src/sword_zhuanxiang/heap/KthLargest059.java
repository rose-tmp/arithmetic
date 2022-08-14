package sword_zhuanxiang.heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author - ZwZ
 * @date - 2022/8/4-20:47 - 周四
 * @Description:剑指 Offer II 059. 数据流的第 K 大数值
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * <p>
 * 请实现 KthLargest 类：
 * <p>
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 * <p>
 * 解释：
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= 104
 * 0 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * 最多调用 add 方法 104 次
 * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 * <p>
 * <p>
 * 注意：本题与主站 703 题相同： https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/
 */
public class KthLargest059 {
    /**
     * java原生优先队列
     * 也可以自己创建一个小根堆
     */
    int k;
    Queue<Integer> smallHeap;//小根堆

    public KthLargest059(int k, int[] nums) {
        this.k = k;
        this.smallHeap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            smallHeap.offer(nums[i]);
        }
    }

    public int add(int val) {
        smallHeap.offer(val);
        while (smallHeap.size() > k) {
            smallHeap.poll();
        }
        return smallHeap.peek();
    }
}
