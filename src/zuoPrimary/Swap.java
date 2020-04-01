package zuoPrimary;

/**
 * @author - ZwZ
 * @date - 2020/3/23 - 11:55
 * @Description:交换数组中的两个下标对应的数值
 */
public class Swap {
    public static void swap(int[] arr, int p1,int p2){
        int temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }
}
