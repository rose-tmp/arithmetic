package array;

/**
 * @author - ZwZ
 * @date - 2020/10/16 - 22:45
 * @Description:977. 有序数组的平方
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 * 示例 1：
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 *
 * 示例 2：
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *
 * 提示：
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 已按 非递减顺序 排序
 *
 * 进阶：
 * 请你设计时间复杂度为 O(n) 的算法解决本问题
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortedSquares {
    /**
     * 先找到数组中正负分解处，然后对两边的数平方
     * 左边的负数平方完之后顺序变成了逆序
     * 右边的正数平方完之后顺序依旧是正序
     * 然后借助MergeSort中Merge函数的思想对这两部分进行合并
     */
    public int[] sortedSquares1(int[] A) {
        int len = A.length;
        int mid = 0;
        for (int i = 0; i < len; i++) {
            if (A[i] >= 0) {
                mid = i;
                break;
            }
        }
        //将元素平方
        for (int i = 0; i < len; i++) {
            A[i] = (A[i]) * (A[i]);
        }
        //此处low传入的并不是0,而是mid - 1因为负数平方之后，是逆序的
        merge(A, mid - 1, len - 1, mid);
        return A;
    }
    //合并数组
    public void merge(int[] arr, int low, int high, int mid) {
        int[] arrTemp = new int[arr.length];//辅助数组
        int p1 = low;
        int p2 = mid;
        int i = 0;
        //将排好序的数存入临时数组
        while (p1 >= 0 && p2 <= high) {
            arrTemp[i++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1--];
        }
        while (p1 >= 0)
            arrTemp[i++] = arr[p1--];
        while (p2 <= high)
            arrTemp[i++] = arr[p2++];
        //复制
        for (int j = 0; j < arrTemp.length; j++) {
            arr[j] = arrTemp[j];
        }
    }
    /**
     * 先平方，然后使用冒泡排序进行整体排序
     * 时间复杂度为O(N^2)
     * */
    public int[] sortedSquares2(int[] A) {
        if(A == null ){
            return null;
        }
        for(int i = 0;i < A.length;i++){
            A[i] = (A[i])*(A[i]);
        }
        //冒泡排序  外层for控制每次遍历的终止点
        for (int end = A.length - 1; end > 0; end--) {
            //内层for用于比较和交换
            for (int i = 0; i < end; i++) {
                if (A[i] > A[i + 1])
                    swap(A, i, i + 1);
            }
        }
        return A;
    }
    void swap(int[] arr, int p1,int p2){
        int temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }
}
