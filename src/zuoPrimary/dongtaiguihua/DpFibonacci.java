package zuoPrimary.dongtaiguihua;

/**
 * @author - ZwZ
 * @date - 2020/3/29 - 21:18
 * @Description:使用动态规划对斐波那契数列问题进行优化
 */
public class DpFibonacci {
    /** 
    * @Author: ZwZ
    * @Description:暴力递归 
    * @Param: [n] 
    * @return: int 
    * @Date: 2020/3/29-21:34
    */
    public int fibonacci(int n){
        if(n <= 1)
            return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    /** 
    * @Author: ZwZ
    * @Description:使用动态规划优化 
    * @Param: [n] 
    * @return: int 
    * @Date: 2020/3/29-21:34
    */
    public int dpFibonacci(int n){
        int[] dp = new int[n];
        //根据递归终止条件为dp表赋值
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i -1] + dp[i - 2];
        }
        return dp[n];
    }
}
