package leetcode.difficult;

/**
 * @author - ZwZ
 * @date - 2020/11/28 - 14:20
 * @Description:493. 翻转对
 * 给定一个数组nums，如果i < j且nums[i] > 2*nums[j]我们就将(i, j)称作一个重要翻转对。
 * 你需要返回给定数组中的重要翻转对的数量。
 * <p>
 * 示例 1:
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * <p>
 * 注意:
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-pairs
 * <p>
 * 相似题目：《剑指Offer》InversePairs
 */
public class ReversePairs {
    /**
     * 暴力
     */
    public int reversePairs1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((long) nums[i] > (long) 2 * nums[j]) {
                    total++;
                }
            }
        }
        return total;
    }

    /**
     * 归并排序思想  在归并排序算法的基础上只需要加入：
     * //先寻找重要翻转对
     * while (p1 <= mid) {
     *     //int转long 防止 arr[p2] * 2之后越界(超过int所能代表的最大或者最小值)
     *     while (p2 <= high && (long) arr[p1] > ((long)arr[p2] * 2)) {
     *         p2++;
     *     }
     *     total += p2 - mid - 1;
     *     p1++;
     * }
     * //指针复位 开始归并排序
     * p1 = low;
     * p2 = mid + 1;
     */
    int total = 0;

    public int reversePairs2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        sort(nums,0,nums.length - 1);
        return total;
    }

    private void sort(int[] arr, int low, int high) {
        //递归结束条件 即范围上只有一个数
        if (low == high)
            return;
        int mid = (low + high) / 2;
        sort(arr, low, mid);//左边排好序 T(N/2)
        sort(arr, mid + 1, high);//右边排好序 T(N/2)
        merge(arr, low, mid, high);//使得左右整体排好序 T(N)
    }

    private void merge(int[] arr, int low, int mid, int high) {
        int[] arrTemp = new int[high - low + 1];//辅助数组
        int p1 = low;
        int p2 = mid + 1;
        int i = 0;
        //先寻找重要翻转对
        while (p1 <= mid) {
            //int转long 防止 arr[p2] * 2之后越界(超过int所能代表的最大或者最小值)
            while (p2 <= high && (long) arr[p1] > ((long)arr[p2] * 2)) {
                p2++;
            }
            total += p2 - mid - 1;
            p1++;
        }
        //指针复位 开始归并排序
        p1 = low;
        p2 = mid + 1;
        //将排好序的数存入临时数组
        while (p1 <= mid && p2 <= high) {
            arrTemp[i++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= mid)
            arrTemp[i++] = arr[p1++];
        while (p2 <= high)
            arrTemp[i++] = arr[p2++];
        //复制
        for (int j = 0; j < arrTemp.length; j++) {
            arr[low++] = arrTemp[j];
        }
    }
}
