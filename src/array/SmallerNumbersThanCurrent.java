package array;

/**
 * @author - ZwZ
 * @date - 2020/10/26 - 19:55
 * @Description:1365. 有多少小于当前数字的数字
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * 以数组形式返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释：
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 * <p>
 * 示例 2：
 * 输入：nums = [6,5,4,8]
 * 输出：[2,1,0,3]
 * <p>
 * 示例 3：
 * 输入：nums = [7,7,7,7]
 * 输出：[0,0,0,0]
 * <p>
 * 提示：
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
 */
public class SmallerNumbersThanCurrent {
    /**
     * 对数组排序
     */
    public int[] smallerNumbersThanCurrent1(int[] nums) {
        if (nums == null)
            return null;
        int[] res = new int[nums.length];
        int[] cloneNums = new int[nums.length];
        //复制数组
        for (int i = 0; i < nums.length; i++) {
            cloneNums[i] = nums[i];
        }
        int temp = 0;
        //冒泡排序
        for (int end = nums.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                //交换
                if (cloneNums[i] > cloneNums[i + 1]) {
                    temp = cloneNums[i];
                    cloneNums[i] = cloneNums[i + 1];
                    cloneNums[i + 1] = temp;
                }
            }
        }
        //在cloneNums中找到nums每一个位置上对应的元素并为res赋值
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < cloneNums.length; j++) {
                if (cloneNums[j] == nums[i]) {
                    res[i] = j;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 题干中有提示0 <= nums[i] <= 100
     * 可以通过创建计数数组的方法来完成
     */
    public int[] smallerNumbersThanCurrent2(int[] nums) {
        if (nums == null) return null;
        int[] count = new int[101];
        //count记录每一个数的出现次数   且其下标天然的完成了数据排序的功能
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        /*
        * 比nums中的元素i小的数的数量也就等于count[0] + count[1] + ... + count[i - 1]
        * 为了避免双重for循环，降低时间复杂度
        * 通过一个for来完成i位置之前的元素相加的过程
        * */
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        int[] res = new int[nums.length];
        res[0] = 0;
        for (int i = 1; i < res.length; i++) {
            res[i] = count[nums[i] - 1];
        }
        return res;
    }

    public static void main(String[] args) {
        SmallerNumbersThanCurrent small = new SmallerNumbersThanCurrent();
        int[] nums = {8, 1, 2, 2, 3};
        small.smallerNumbersThanCurrent2(nums);
    }
}
