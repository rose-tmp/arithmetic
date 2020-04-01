package zuoPrimary.baolidigui;

/**
 * @author - ZwZ
 * @date - 2020/1/14 - 22:11
 * @Description:斐波那契数列 (F(1)=1，F(2)=1, F(n)=F(n-1)+F(n-2)（n>=3，n∈N*）)
 * 现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）n<=39
 */
public class Day4Fibonacci {
    /** 
    * @Author: ZwZ
    * @Description:没有优化的递归 即暴力递归
     * 存在着大量的重复计算 画出一个二叉树可知，随着n的增加，计算数量会成指数增加
     * 其时间复杂度为2^n 这种指数级别的算法，和不能用是没有什么区别的，他的复杂度太高了
    * @Param: [n] 
    * @return: int 
    * @Date: 2020/2/11-20:20
    */
    public int Fibonacci1(int n) {
        if(n <= 1)
            return n;
        else{
            return Fibonacci1(n-1) + Fibonacci1(n-2);
        }
    }
    /*优化递归  以上递归会重复计算大量的数据 例如：Fibonacci(3)等等就会在很多Fibonacci(n) n∈N*时计算过
      建立一个数组存储每一位上的数，从而减少重复计算的次数
      时间复杂度：O(n) 空间复杂度：o(n)*/
    public int Fibonacci3(int n){
        int[] arr = new int[40];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[n];
    }
    //优化存储  每次就用到了最近的两个数，所以我们可以只存储最近的两个数  时间复杂度：O(n) 空间复杂度：o(1)
    public int Fibonacci4(int n){
        if(n <= 1)
            return n;

        int sum = 0;
        int first = 0;
        int second = 1;
        for (int i = 2; i <= n; i++) {
            sum = first + second;
            first = second;
            second = sum;
        }
        /*
        int first = 0;
        int sum = 1;
        for(int i=2;i<=n;i++){
            sum = sum + first;
            first = sum - first;
        }*/
        return sum;
    }

}
