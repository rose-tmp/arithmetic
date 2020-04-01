package zuoPrimary.shuzu;

/**
 * @author - ZwZ
 * @date - 2020/3/22 - 23:01
 * @Description:小和问题 (左神讲算法)
 * 在一个数组中，每一个数左边比当前数小得数累加起来，叫做这个数组得小和，求一个数组得小和
 * 例如：[1,3,4,2,5]
 * 小和为：1+1+3+1+3+4+2 = 16
 * 思想：借助归并排序(循环遍历会出现很多重复的步骤，而归并排序中，每个小块是排好序的，省去了无用的步骤)
 * 可以看作是归并排序的应用
 */
public class GetSmallSum {
    int count = 0;//数组的小和

    public int getSmallSum(int[] arr) {
        if (arr == null || arr.length < 2)
            return count;
        mergeSort(arr, 0, arr.length - 1);
        return count;
    }

    private void mergeSort(int[] arr, int low, int high) {
        //递归终止条件
        if (low == high)
            return;
        int mid = (low + high) /2;
        mergeSort(arr, low, mid);//左边排序
        mergeSort(arr, mid + 1, high);//右边排序
        mergeGetSmallSum(arr, low, mid, high);//将左右两边整体排序
    }

    private void mergeGetSmallSum(int[] arr, int low, int mid, int high) {
        int[] arrTemp = new int[high - low + 1];
        int p1 = low;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= high) {
            count += arr[p1] < arr[p2] ? (high - p2 + 1) * arr[p1] : 0;
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

    public static void main(String[] args) {
        GetSmallSum getSmallSum = new GetSmallSum();
        int[] arr = {1, 3, 4, 2, 5};
        System.out.println(getSmallSum.getSmallSum(arr));
    }
}
