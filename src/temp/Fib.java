package temp;

/**
 * @author - ZwZ
 * @date - 2021/1/4 - 18:34
 * @Description:509. 斐波那契数
 * 斐波那契数，通常用F(n)表示，形成的序列称为斐波那契数列。该数列由0和1开始，后面的每一项数字都是前面两项数字的和.也就是：
 * <p>
 * F(0) = 0，F(1)= 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给你 n ，请计算 F(n) 。
 * <p>
 * 示例 1：
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * <p>
 * 示例 2：
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fibonacci-number
 */
public class Fib {
    /*public int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }*/
    public int fib(int n) {
        if(n < 0){
            return -1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i + 2];
        }
        return dp[n];
    }
}
