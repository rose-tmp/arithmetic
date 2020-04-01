package zuoPrimary.shuzu;

import zuoPrimary.Swap;

/**
 * @author - ZwZ
 * @date - 2020/3/23 - 11:48
 * @Description:给定一个数组和一个数，将小于等于这个数的所有数放在数组的左边，大于这个数的数放在数组的右边
 * 思路：划定一个左边的范围，遍历数组
 * 当遍历到的数字小于等于num，与左边范围的下一个数字进行交换，然后左边范围+1
 * 当遍历到的数字大于num,不交换，指针前移
 */
public class ArrAndNum {
    public void arrAndNum(int[] arr,int num){
        if(arr == null || arr.length < 2 || num == Integer.MAX_VALUE)
            return;
        int left = -1;//left左边以及left处的数值都是小于等于num
        for (int cur = 0; cur < arr.length; cur++) {
            if(arr[cur] <= num){
                Swap.swap(arr,left+1,cur);
                left++;
            }
        }
    }

    public static void main(String[] args) {
        ArrAndNum arrAndNum = new ArrAndNum();
        int[] arr = {1,3,6,7,8,4,2};
        arrAndNum.arrAndNum(arr,4);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
