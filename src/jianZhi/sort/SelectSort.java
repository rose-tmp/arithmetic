package jianZhi.sort;

import zuoPrimary.Swap;

/**
 * @author - ZwZ
 * @date - 2020/3/22 - 15:03
 * @Description:选择排序
 * (由于时间复杂度高，这种排序算法几乎没有人使用在工程中)
 * 0-----n-1选出最小值放在0位置
 * 1-----n-1选出最小值放在1位置
 * 2-----n-1选出最小值放在2位置
 * ....
 * 时间复杂度和数据本身状况无关，恒为：O(n^2)
 *
 * 无论如何都做不到稳定 举例：5 5 5 5 4 0 2
 */
public class SelectSort {
    public void selectSort(int[] arr){
        if(arr == null || arr.length < 2)
            return;
        for (int start = 0; start < arr.length - 1; start++) {
            int min = start;
            //找最小值的下标
            for (int i = start + 1; i < arr.length; i++) {
                min = arr[i] < arr[min] ? i : min;
            }
            //交换
            Swap.swap(arr, start, min);
        }
    }
}
