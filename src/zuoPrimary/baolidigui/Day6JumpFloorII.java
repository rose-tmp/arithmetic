package zuoPrimary.baolidigui;

/**
 * @author - ZwZ
 * @date - 2020/1/15 - 10:43
 * @Description:变态跳台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法
 *
 */
public class Day6JumpFloorII {
    /*f(n)=f(n-1)+f(n-2)+...+f(1)
    f(n-1)=f(n-2)+...f(1)
    得:f(n)=2*f(n-1)*/
    public int JumpFloorII(int target) {
        if(target <= 2)
            return target;
        int[] arr = new int[target+1];//标志数组
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 3; i < arr.length; i++) {
            arr[i] = 2*arr[i-1];
        }
        return arr[target];
    }
}
