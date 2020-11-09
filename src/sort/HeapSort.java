package sort;

import zuoPrimary.Swap;

/**
 * @author - ZwZ
 * @date - 2020/2/18 - 22:51
 * @Description:堆排序 堆排序做不到稳定
 * 举例：4 4 4 5 5中，5向上跑的时候，和第二个4交换位置，那么第二个4就已经跑到了第三个4得后面
 */
public class HeapSort {
    public void heapSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        /*建立大根堆
        依次将arr[i]加入堆中
        此过程的复杂度：O(log1+log2+log3...) 其收敛于O(N)
         */
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int heapSize = arr.length;//堆大小
        Swap.swap(arr, 0, --heapSize);//根节点和最后面的叶子结点换位，并且将堆的长度--，表示最后一个节点已经不再满足堆结构
        //通过不断地swap()和向下调整使得整个堆所在得数组变得有序
        while (heapSize > 0) {
            adjustDown(arr, 0, heapSize);//根节点变小了，要向下调整
            Swap.swap(arr, 0, --heapSize);
        }
    }

    /**
     * @Author: ZwZ
     * @Description: 向上调整  即adjustUp
     * 将arr中下标为index的元素加入大根堆
     * 从而由arr中下标0...index形成一个大根堆
     * @Param: [arr, index] 
     * @return: void 
     * @Date: 2020/3/23-16:39
     */
    private void heapInsert(int[] arr, int index) {
        /*加入的元素比父节点的元素大
        * 此while考虑到了index == 0的情况，在此时并不会发生数组下标越界
        * index == 0时,(index - 1)/2 == 0
        * */
        while (arr[index] > arr[(index - 1) / 2]) {
            Swap.swap(arr, index, (index - 1) / 2);
            //指向父节点 继续判断父节点与父父节点之间关系  当不满足while时停止 即如果这个数特别大，则一定要把它一口气送到堆的根节点为止
            index = (index - 1) / 2;
        }
    }

    /**
     *  
     *
     * @Author: ZwZ
     * @Description:向下调整
     * 当某一处元素变小时，要向下调整 即将此元素与其左右子节点中的最大的一个交换
     * 然后再将index指向交换了的位置继续向下重复此过程
     * @Param: [arr, index, heapSize] [待调整的数组，变小了的元素的数组下标，堆大小]
     * @return: void 
     * @Date: 2020/3/23-17:16
     */
    private void adjustDown(int[] arr, int index, int heapSize) {
        int left = (index * 2) + 1;//左侧子节点
        while (left < heapSize) {
            //左右子节点中最大值的下标
            int max = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            //父节点和子节点中的最大值的下标
            max = arr[max] < arr[index] ? index : max;
            //父节点虽然变小，但仍然比子节点都大
            if (max == index)
                break;
            Swap.swap(arr, max, index);
            index = max;//向下调整
            left = (index * 2) + 1;
        }
    }

    public static void main(String[] args) {
        HeapSort sort = new HeapSort();
        int[] arr = {8, 7, 6, 5, 4, 3, 2, 1};
        sort.heapSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
