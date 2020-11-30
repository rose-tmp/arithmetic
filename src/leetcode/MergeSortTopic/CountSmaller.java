package leetcode.MergeSortTopic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2020/11/29 - 21:58
 * @Description:315. 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组counts。
 * 数组 counts 有该性质： counts[i] 的值是nums[i]右侧小于nums[i] 的元素的数量。
 * <p>
 * 示例：
 * 输入：nums = [5,2,6,1]
 * 输出：[2,1,1,0]
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 * <p>
 * 提示：
 * 0 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
 */
public class CountSmaller {
    /**
     * 这道题之所以使用归并排序 就是想在计算的过程中通过每个子数组在归并 并 的过程中
     * 通过已经排好的顺序做加速运算  而一下这个代码没有在归并的过程中对整个数组进行排序
     * 所以也就不存在使用从小到大的顺序做加速计算 所以这样子和暴力是没有区别的
     * */
    /*public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        int[] resArr = new int[nums.length];
        sort(nums, resArr, 0, nums.length - 1);
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            res.add(num);
        }
        return res;
    }

    private void sort(int[] arr, int[] resArr, int low, int high) {
        //递归结束条件 即范围上只有一个数
        if (low == high)
            return;
        int mid = (low + high) / 2;
        sort(arr, resArr, low, mid);//左边排好序 T(N/2)
        sort(arr, resArr, mid + 1, high);//右边排好序 T(N/2)
        merge(arr, resArr, low, mid, high);//使得左右整体排好序 T(N)
    }

    private void merge(int[] arr, int[] resArr, int low, int mid, int high) {
        int p1 = low;
        int p2 = mid + 1;
        while (p1 <= mid) {
            while (p2 <= high) {
                if (arr[p1] > arr[p2]) {
                    resArr[p1]++;
                }
                p2++;
            }
            p1++;
        }
    }*/

    /**
     * 暴力
     */
    public List<Integer> countSmaller1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
            list.add(count);
        }
        return list;
    }

    /**
     * 归并排序
     */
    public List<Integer> countSmaller2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        //记录nums对应位置上每个元素在nums这一原始数组中对应的下标值
        int[] index = new int[nums.length];
        int[] count = new int[nums.length];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }
        sort(nums,index,count,0,nums.length - 1);
        for (int i:count) {
            res.add(i);
        }
        return res;
    }

    public void sort(int[] nums, int[] index, int[] count,
                     int low, int high) {
        if (low == high) {
            return;
        }
        int mid = (low + high) / 2;
        sort(nums, index, count, low, mid);
        sort(nums, index, count, mid + 1, high);
        merge(nums, index, count, low, mid, high);
    }

    /**
     * nums:对nums数组中的元素排序后的序列存储在其中
     * index:记录nums对应位置上每个元素在nums这一原始数组中对应的下标值
     */
    public void merge(int[] nums, int[] index, int[] count,
                      int low, int mid, int high) {
        int p1 = low;
        int p2 = mid + 1;
        int k = 0;
        /*temp存储low--high之间的元素排序后的序列  用于最终更新nums数组
        indexTemp存储temp中每个元素对应在nums中的位置 用于最终更新index数组
        他们的内容同时更新，当temp中i位置新记录一个元素时，indexTemp中的i位置要同步记录这个元素在nums数组中对应的原始位置
        记录每个元素对应nums中的原始位置的原因是第146行代码在给count数组赋值时便于找到该元素在nums中的下标
        */
        int[] temp = new int[high - low + 1];
        int[] indexTemp = new int[high - low + 1];
        while (p1 <= mid && p2 <= high) {

            if (nums[p1] <= nums[p2]) {
                temp[k] = nums[p1];
                indexTemp[k] = index[p1];
                //这行代码体现了使用归并加速的本质
                count[index[p1]] += (p2 - mid - 1);
                p1++;
                k++;
            } else {
                temp[k] = nums[p2];
                indexTemp[k] = index[p2];
                p2++;
                k++;
            }
        }
        while (p1 <= mid) {
            indexTemp[k] = index[p1];
            temp[k] = nums[p1];
            count[index[p1]] += p2 - mid - 1;
            p1++;
            k++;
        }
        while (p2 <= high) {
            indexTemp[k] = index[p2];
            temp[k] = nums[p2];
            k++;
            p2++;
        }
        //将排好序的元素复制到原数组中
        for (int i = 0; i < temp.length; i++) {
            index[low] = indexTemp[i];
            nums[low] = temp[i];
            low++;
        }
    }

    public static void main(String[] args) {
        CountSmaller countSmaller = new CountSmaller();
        countSmaller.countSmaller2(new int[]{5,2,6,1});
    }
}
