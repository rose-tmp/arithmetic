package sort;

/**
 * @author - ZwZ
 * @date - 2020/2/18 - 19:38
 * @Description:2路归并排序 思想：递归 左边排好序，右边排好序，然后再借助辅助数组把左右整体排好序 结束
 * 可以使用左神的计算递归的时间复杂度的公式得出2路归并排序的时间复杂度是T(N) = 2T(N/2)+O(N) 得出：O(N*logN)
 * 2路归并排序是可以做到稳定
 * 举例：
 * 33567   12334 合并时，当左边和右边有相等元素的时候，先拷贝左边至arr再拷贝右边到arr
 */
public class MergeSort {
    /**
     * 递归版归并  即自顶向下的归并排序
     */
    public void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        sort(arr, 0, arr.length - 1);
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

    /**
     * 非递归版归并排序
     * 时间复杂度O(NlogN)
     * 空间复杂度O(1)
     */
    public void mergeSort2(int[] arr) {
        if (arr == null) {
            return;
        }
        // k代表每次所合并的两个子序列的大小
        // 第一次将序列中紧挨着的两个数(可以理解成两个长度为1的序列)都合并成一个有序序列
        // 第二次将由两个数组成的所有有序序列再和他们后边紧挨着的俩个数组成的序列进行合并
        // 第三次将由四个数组成的.......
        // 所以是 1,2,4,8,16...
        int k = 1;
        while (k < arr.length) {
            mergePass(arr, k, arr.length);
            k *= 2;
        }
    }

    /**
     * 将数组中相邻的有k个元素的子序列进行归并
     */
    private void mergePass(int[] arr, int k, int length) {
        int i = 0;
        //从前向后两两将长度为k的子序列合并
        //2 * k : 一个子序列的大小为k, 因为合并的过程是两两合并，所以两个序列合并的次数才加1
        // len - 2 * k + 1 则是合并的次数
        while (i < length - 2 * k + 1) {
            merge(arr, i, i + k - 1, i + 2 * k - 1);
            i += 2 * k;
        }
        //这段代码保证了，将那些“落单的”长度不足两两merge的部分和前面merge起来
        if (i < length - k) {
            merge(arr, i, i + k - 1, length - 1);
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = {1, 3, 4, 2, 5, 2, 6, 8};
        mergeSort.mergeSort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
