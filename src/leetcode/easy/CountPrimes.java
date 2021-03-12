package leetcode.easy;

import java.util.Arrays;

/**
 * @author - ZwZ
 * @date - 2020/12/3 - 9:04
 * @Description:204. 计数质数
 * 统计所有小于非负整数n的质数的数量。
 * <p>
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：n = 1
 * 输出：0
 * <p>
 * 提示：
 * 0 <= n <= 5 * 106
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-primes
 */
public class CountPrimes {
    /**
     * 纯暴力枚举
     */
    public int countPrimes1(int n) {
        if (n < 1) {
            return 0;
        }
        int ans = 0;
        for (int i = 2; i < n; i++) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }

    /**
     * 时间复杂度O(N)
     */
    private boolean isPrime(int x) {
        for (int i = 2; i < x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 优化暴力
     * O(sprt(N))
     */
    public int countPrimes2(int n) {
        if (n < 1) {
            return 0;
        }
        int ans = 0;
        for (int i = 2; i < n; i++) {
            ans += isPrime2(i) ? 1 : 0;
        }
        return ans;
    }

    /**
     * isPrime()中将[2,x-1]全部遍历
     * 但是以12为例：
     * 12 = 2 * 6
     * 12 = 3 * 4
     * 12 = sprt(12) * sprt(12)
     * 12 = 4 * 3
     * 12 = 6 * 2
     * 可见  其实遍历到12的平方根之后后面的数值就没有必要再去遍历了
     */
    private boolean isPrime2(int x) {
        for (int i = 2; i * i < x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 埃氏筛
     * 出发点：如果 xx 是质数，那么大于 xx 倍数 2x,3x,3x,… 一定不是质数
     */
    public int countPrimes3(int n) {
        //对每个位置进行标记  1代表为质数   0代表不为质数
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        /*
         * 因为从2开始遍历  遍历到后面的每一个位置的时候  如果这个位置上是1  那么他一定是质数
         * 因为如果他不是质数的话，他肯定是由比他小的两个数相乘得到   而比他小的数先被遍历到
         * 所以如果他不是质数，在遍历比他小的那些数的时候肯定会把他这一位置置为0
         * */
        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 1) {
                ans += 1;
                //
                if ((long) i * i < n) {
                    //从i^2这一位置开始将i的倍数位置上赋值为0 因为他们肯定不是质数
                    //j之所以从i*i开始修改isPrime数组中的值 是因为2*j,3*j,4*j......(j - 1)*j在遍历到2,3,4......j - 1的时候就已经把他们置为0了
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 暴力
     *
     * @param n
     * @return
     */
    public int demo(int n) {
        int ans = 0;
        for (int i = 2; i < n; i++) {
            int temp = 0;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    temp++;
                }
            }
            if (temp == 0) {
                ans++;
            }
        }
        return ans;
    }
}
