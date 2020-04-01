package zuoPrimary.baolidigui;

/**
 * @author - ZwZ
 * @date - 2020/3/29 - 22:12
 * @Description:给定一个数组arr（里面全是正数）和一个正数aim，如果可以任意选择arr中的数字 能不能累加得到aim，如果可以返回true否则返回false
 */
public class IsAim {
    /**
     *  
     *
     * @Author: ZwZ
     * @Description:数组中每一个位置的数都面临着两种选择：要还是不要 
     * @Param: [arr, aim,sum,i]res:遍历之前所有位置之后得到的结果 
     * @return: boolean 
     * @Date: 2020/3/29-22:15
     */
    public boolean isAim(int[] arr, int aim, int sum, int i) {
        if (i == arr.length)
            return sum == aim;
        return isAim(arr, aim, sum, i + 1) || isAim(arr, aim, sum + arr[i], i + 1);
    }
}
