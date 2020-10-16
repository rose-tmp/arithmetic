package leetcode.easy;

/**
 * @author - ZwZ
 * @date - 2020/10/16 - 22:45
 * @Description:977. 有序数组的平方
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
