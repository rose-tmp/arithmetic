package zuoPrimary.shuzu;

import zuoPrimary.Swap;

/**
 * @author - ZwZ
 * @date - 2020/3/23 - 12:07
 * @Description:荷兰国旗问题  与一次快速排序的思想类似
 * 给定一个数组和一个数，把小于这个数的所有数放在数组的左边，等于这个数的数放在数组的中间
 * 大于这个数的数放在数组的右边
 * 思路：在数组两边分别划定两个区域，所有小于这个数的数放在数组的左边区域
 * 所有大于这个数的数放在数组右边的区域
 * 当遇到等于这个数的值时，不做任何操作，遍历指针后移继续遍历，
 * 最终遍历指针和代表右边大数区域的指针见面时，则代表划分结束
 */
public class FlagOfHolland {
    public void flagOfHolland(int[] arr,int num){
        if(arr == null || arr.length < 2)
            return;
        int left = -1;//小于num区域的左边界
        int right = arr.length;//大于num区域的右边界
        int cur = 0;//遍历指针
        //当cur和right没有相撞的时候
        while(cur < right){
            if(arr[cur] < num)
                Swap.swap(arr,++left,cur++);
            else if(arr[cur] > num)
                Swap.swap(arr,--right,cur);
            else
                cur++;
        }
    }

    public static void main(String[] args) {
        FlagOfHolland flagOfHolland = new FlagOfHolland();
        int[] arr = {25,84,21,47,15,27,68,35,20};
        flagOfHolland.flagOfHolland(arr,25);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
