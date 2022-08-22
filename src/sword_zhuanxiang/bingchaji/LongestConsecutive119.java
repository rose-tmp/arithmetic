package sword_zhuanxiang.bingchaji;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ZwZ
 * @date: 2022-08-18 11:43
 * @desc:剑指 Offer II 119. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * <p>
 * 进阶：可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 * <p>
 * <p>
 * 注意：本题与主站 128 题相同： https://leetcode-cn.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutive119 {
    /**
     * 并查集
     * 把节点i挂在节点i-1的集合中，遍历完整个nums后
     * 返回最大的集合的size即可
     *
     * 时间复杂度：o(N)。详见UnionFind.java中对于使用了路径压缩进行优化了的并查集的时间复杂度
     */
    public int longestConsecutive(int[] nums) {
        //node1:某节点 node2:此节点所在集合的最上方节点
        Map<Integer, Integer> fatherMap = new HashMap<>();
        //val:该节点所在集合的大小
        Map<Integer, Integer> sizeMap = new HashMap<>();

        //初始化并查集
        for (int i = 0; i < nums.length; i++) {
            fatherMap.put(nums[i], nums[i]);
            sizeMap.put(nums[i], 1);
        }

        //对每个节点所在的集合进行union操作
        for (int i = 0; i < nums.length; i++) {
            if (fatherMap.containsKey(nums[i] - 1)) {
                union(fatherMap, sizeMap, nums[i] - 1, nums[i]);
            }
        }

        //寻找size最大的集合
        int ans = 0;
        for (Integer k : sizeMap.keySet()) {
            ans = Math.max(ans, sizeMap.get(k));
        }
        return ans;
    }

    /**
     * @return 节点i所在集合中的“老大”
     */
    private int find(Map<Integer, Integer> fMap, Integer i) {
        int f = fMap.get(i);
        if (f != i) {
            f = find(fMap, f);
        }
        fMap.put(i, f);
        return f;
    }

    /**
     * 合并两个节点所在的两个集合
     */
    private void union(Map<Integer, Integer> fMap, Map<Integer, Integer> sMap, int i, int j) {
        int fI = find(fMap, i);//i所在集合中的”老大“
        int fJ = find(fMap, j);//j所在集合中的”老大“
        if (fI != fJ) {
            int iSize = sMap.get(fI);
            int jSize = sMap.get(fJ);
            if (iSize <= jSize) {
                fMap.put(fI, fJ);
                sMap.put(fJ, iSize + jSize);
            } else {
                fMap.put(fJ, fI);
                sMap.put(fI, iSize + jSize);
            }
        }
    }
}
