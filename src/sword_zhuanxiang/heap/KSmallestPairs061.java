package sword_zhuanxiang.heap;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-08-14 10:46
 * @desc:剑指 Offer II 061. 和最小的 k 个数对
 * 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * <p>
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * <p>
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 示例 2:
 * <p>
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 示例 3:
 * <p>
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums1.length, nums2.length <= 104
 * -109 <= nums1[i], nums2[i] <= 109
 * nums1, nums2 均为升序排列
 * 1 <= k <= 1000
 * <p>
 * <p>
 * 注意：本题与主站 373 题相同：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/
 */
public class KSmallestPairs061 {
    /**
     * kSmallestPairs1中没有利用到两个数组中元素都是有序的这一特征
     * 思路:因为两个数组中的元素都是有序的，所以没有必要把所有的组合都找出来然后在放入堆中去寻找前k个
     * 至于怎么利用有序的这一条件去进行的优化，可以看一下leetcode题解->
     * 大思路就是：[i,j]如果是第n个小的元素，那么第n+1小的元素就是[i+1,j]或者[i,j+1]
     * 那么我们就把这两个数放进堆中，堆自动调整好之后就可以把小根堆的堆头取出来放在结果里
     * 而官方给的题解难就难在避免重复元素的方式上->没看懂为什么这样可以避免重复
     * <p>
     * 避免重复还有另一种方式：set
     * 具体可以看一下：https://leetcode.cn/problems/qn8gGX/solution/yi-bu-bu-si-kao-chu-bfs-you-xian-dui-lie-smel/
     */
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        Queue<int[]> smallHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (nums1[o1[0]] + nums2[o1[1]]) - (nums1[o2[0]] + nums2[o2[1]]);
            }
        });
        //先把其中一个数组中的首元素和另外一个数组中的所有元素构成的组合加入优先队列
        for (int i = 0; i < Math.min(nums2.length, k); i++) {
            smallHeap.offer(new int[]{0, i});
        }

        List<List<Integer>> ans = new ArrayList<>();
        //官方题解
        while (k-- > 0 && !smallHeap.isEmpty()) {
            int[] cur = smallHeap.poll();
            List<Integer> l = new ArrayList<>();
            l.add(nums1[cur[0]]);
            l.add(nums2[cur[1]]);
            ans.add(l);
            /*
            * 首先因为nums1和nums2都是有序的,所以[0,0]所对应的元素(即nums1[0],nums2[0])一定是最小的元素
            * 因为[i,j]如果是第n个小的元素，那么第n+1小的元素就是[i+1,j]或者[i,j+1]
            * 所以第二小的元素只可能出现在[0,1]或者[1,0]这两个位置
            * 又因为一开始的时候在67行已经把[0,i]加进了堆中
            * 所以这里每次遍历到[i,j]的时候只需要将[i+1,j]加入到堆中
            * 直到找到了k个组合，就会跳出while.这样比起kSmallestPairs1时间复杂度少很多
            * */
            if (cur[0] + 1 < nums1.length) {
                smallHeap.offer(new int[]{cur[0] + 1, cur[1]});
            }
        }
        return ans;
    }

    /**
     * 思路:把所有的组合都找出来然后再通过堆去寻找前k个
     * 寻找所有组合的过程时间复杂度是o(N^2)
     * <p>
     * 当然也可以在list中加入每个数组中前k个元素所形成的集合，因为前k个结果一定出现在这些
     * 元素所构成的集合里，时间复杂度为o(k^2) 这种办法复杂度依旧是平方级别
     */
    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        List<int[]> list = new ArrayList<>();//arr[0]:num1;arr[1]:num2
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                list.add(new int[]{nums1[i], nums2[j]});
            }
        }
        //创建大根堆 arr[0]:num1+num2; arr[1]:num1; arr[2]:num2
        Queue<int[]> bigHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        for (int[] i : list) {
            bigHeap.offer(new int[]{i[0] + i[1], i[0], i[1]});
            //把大根堆的首元素出来，那么遍历一次结束后剩下的k个元素就是结果,即最小的k个
            if (bigHeap.size() > k) {
                bigHeap.poll();
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int[] arr : bigHeap) {
            List<Integer> temp = new ArrayList<>();
            temp.add(arr[1]);
            temp.add(arr[2]);
            res.add(temp);
        }
        return res;
    }
}
