package sword_zhuanxiang.huaDongChuangKou;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/8/9-15:25 - 周二
 * @Description:剑指 Offer II 057. 值和下标之差都在给定的范围内
 * 给你一个整数数组 nums 和两个整数 k 和 t 。
 * 请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，
 * 同时又满足 abs(i - j) <= k 。
 * 如果存在则返回 true，不存在返回 false。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 104
 * 0 <= t <= 231 - 1
 * <p>
 * <p>
 * 注意：本题与主站 220 题相同：
 * https://leetcode-cn.com/problems/contains-duplicate-iii/
 */
public class ContainsNearbyAlmostDuplicate057 {
    /**
     * 暴力
     * 拉跨方法，面试官必不可能满意
     */
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (Math.abs(nums[i] - nums[j]) <= t && Math.abs(i - j) <= k) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 滑动窗口
     * 思路:维护一个大小为k的滑动窗口，窗口不停的往右挪
     * 每向右挪一个位置，对于加进来的这个元素x要判断一下窗口中所包含的元素中
     * 是否存在在[x-t,x+t]这个区间中的元素。如果存在就返回true
     * <p>
     * 如果每次判断是否存在...时都需要遍历一遍滑动窗口中的k个元素，那么时间复杂度O(kN)
     * 相较于暴力中的O(N^2)，时间复杂度是降低的
     */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if (nums.length == 0) {
            return false;
        }
        //滑动窗口
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (isExist(list, nums[i], t)) {
                return true;
            }
            list.add((long) nums[i]);
            //滑动窗口右边界向前滑动一位，左边窗口边界也要向前滑动一位
            if (i >= k) {
                list.remove(0);
            }
        }
        return false;
    }

    /**
     * containsNearbyAlmostDuplicate2中的滑动窗口是用list集合维护的
     * 所以时间复杂度高，过不了leetcode所有示例
     * 在containsNearbyAlmostDuplicate3中改成TreeSet 即有序集合
     */
    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        if (nums.length == 0) {
            return false;
        }
        //滑动窗口
        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            //>=(nums[i] - t)的所有元素中最小的元素
            Long ceil = set.ceiling((long) nums[i] - (long) t);
            //比(nums[i]-t)大的元素中最小的元素如果都不满足这个if的话,其他的肯定也不满足
            if (ceil != null && ceil <= ((long) nums[i] + (long) t)) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);//把左边界右移
            }
        }
        return false;
    }

    /**
     * 除了滑动窗口这种思路之外，还可以借助桶排序的思想去维护一个滑动窗口中的所有元素
     * 桶的分布:[...[-(t+1),-1],[0,t],[t+1,2t],[2t+1,3t]...[(n-1)t+1,nt]] -->每个桶的大小为t+1
     * nums[]中的每个元素都存放到对应的桶中:
     * 1. 该桶中已经存在数据，那么直接返回true (因为同一个桶中的两个元素之间的差值一定在t之内)
     * 2. 该桶中不存在数据，那么比较相邻的两个桶中存放的值是否满足差值在t以内的条件
     * 满足则返回true;不满足则返回false
     * (比较的过程是不复杂的，因为每个桶中虽然理论上是可以存放一个范围内的所有数,但是在该题目中
     * 它只会存储一个数,因为当一个桶中有两个数的时候就直接返回了true)
     * <p>
     * 桶的实现: 每个桶的大小为t+1,则nums[]中的每个元素都可以表示为:(t+1)*id + x (0<=x<=t)
     * 其中id表示桶的编号
     * 将id作为map中的key,桶中所有元素所构成的集合作为map的value，则形成了桶排序的基础数据结构
     * 但是因为在这个题目中,每个位置只有机会存储一个元素,所以就没必要使用Map<Integer,int[]>
     * 而是使用Map<Integer,Integer>即可
     */
    public boolean containsNearbyAlmostDuplicate4(int[] nums, int k, int t) {
        if (nums.length == 0) {
            return false;
        }
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long bucketId = getBucketId(nums[i], t);
            if (map.containsKey(bucketId)) {
                return true;
            }
            if (map.containsKey(bucketId - 1) && Math.abs(nums[i] - map.get(bucketId - 1)) < (t + 1)) {
                return true;
            }
            if (map.containsKey(bucketId + 1) && Math.abs(nums[i] - map.get(bucketId + 1)) < (t + 1)) {
                return true;
            }
            map.put(bucketId, (long) nums[i]);
            if (i >= k) {
                //维护的桶的数据结构中存的也是一个滑动窗口大小数据范围的数据,所以要对数据进行和滑动窗口中一样的删除操作
                map.remove(getBucketId(nums[i - k], t ));
            }
        }
        return false;
    }

    /**
     * @return: num对应的桶id
     */
    private long getBucketId(long num, long t) {
        long size = t + 1;//每个桶的大小
        //n >= 0 : ID = n / (t + 1)
        if (num >= 0) {
            return num / size;
        }
        //n < 0 : ID = (n + 1) / (t + 1) - 1
        return (num + 1) / size - 1;
    }

    /**
     * @return: list中是否存在[x-t,x+t]区间中的元素
     */
    private boolean isExist(List<Long> list, long x, int t) {
        for (Long i : list) {
            if (i >= (x - t) && i <= (x + t)) {
                return true;
            }
        }
        return false;
    }
}

