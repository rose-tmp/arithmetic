package leetcode.array;

/**
 * @author - ZwZ
 * @date - 2020/11/3 - 18:45
 * @Description:941. 有效的山脉数组
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 * A.length >= 3
 * 在 0 < i < A.length - 1 条件下，存在 i 使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * <p>
 * 示例 1：
 * 输入：[2,1]
 * 输出：false
 * <p>
 * 示例 2：
 * 输入：[3,5,5]
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：[0,3,2,1]
 * 输出：true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-mountain-array
 */
public class ValidMoucntainArray {
    public boolean validMountainArray(int[] A) {
        if (A.length < 3) {
            return false;
        }
        int i = 0;
        int len = A.length;
        //寻找山峰
        while(i < len - 1 && A[i] < A[i + 1]){
            i++;
        }
        if(i == 0 || i == len - 1){
            return false;
        }
        //判断山峰右边的元素是否满足要求
        while(i < len - 1 && A[i] > A[i + 1]){
            i++;
        }
        if(i == A.length - 1){
            return true;
        }else{
            return false;
        }
    }
}
