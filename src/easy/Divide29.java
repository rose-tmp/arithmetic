package easy;

/**
 * @author - ZwZ
 * @date - 2022/4/23-19:06 - 周六
 * @Description:剑指 Offer II 001. 整数除法
 * 给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%'。
 * 注意：
 * <p>
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8以及truncate(-2.7335) = -2
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,2^31−1]。本题中，如果除法结果溢出，则返回 231− 1
 * <p>
 * 示例 1：
 * <p>
 * 输入：a = 15, b = 2
 * 输出：7
 * 解释：15/2 = truncate(7.5) = 7
 * 示例 2：
 * <p>
 * 输入：a = 7, b = -3
 * 输出：-2
 * 解释：7/-3 = truncate(-2.33333..) = -2
 * 示例 3：
 * <p>
 * 输入：a = 0, b = 1
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：a = 1, b = 1
 * 输出：1
 * <p>
 * 提示:
 * <p>
 * -231<= a, b <= 231- 1
 * b != 0
 * <p>
 * <p>
 * 注意：本题与主站 29题相同：https://leetcode-cn.com/problems/divide-two-integers/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xoh6Oh
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Divide29 {
    /**
     * 减法代替除
     */
    public int divide1(int a, int b) {
        //溢出
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        if (a == Integer.MIN_VALUE && b == 1) {
            return Integer.MIN_VALUE;
        }
        if (b == Integer.MIN_VALUE && a == Integer.MIN_VALUE) {
            return 1;
        }
        if (b == Integer.MIN_VALUE) {
            return 0;
        }
        boolean flag = false;
        int res = 0;
        //最终结果需要转换符号
        if (a >= 0 && b < 0 || a <= 0 && b > 0) {
            flag = true;
        }
        //统一为负数(正数的话可能越界)
        b = b > 0 ? -b : b;
        a = a > 0 ? -a : a;
        while (a <= b) {
            a = a - b;
            res++;
        }
        return flag ? -res : res;
    }

    /**
     * 对于上述过程优化
     * 思路：在divide1中每次a减去的是一个单位的b
     */
    public int divide2(int a, int b) {
        //溢出
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        if (a == Integer.MIN_VALUE && b == 1) {
            return Integer.MIN_VALUE;
        }
        if (b == Integer.MIN_VALUE && a == Integer.MIN_VALUE) {
            return 1;
        }
        if (b == Integer.MIN_VALUE) {
            return 0;
        }
        boolean flag = false;
        int res = 0;
        //最终结果需要转换符号
        if (a >= 0 && b < 0 || a <= 0 && b > 0) {
            flag = true;
        }
        //统一为负数(正数的话可能越界)
        b = b > 0 ? -b : b;
        a = a > 0 ? -a : a;
        res = fuck(a, b);
        return flag ? -res : res;
    }

    /**
     * @Param: a 被除数
     * @Param: b 除数
     * @return: a/b的结果
     */
    public int fuck(int a, int b) {
        int res = 0;
        int tmp = b;
        while (a <= b) {
            int k = 1;
            while (a <= tmp) {
                a = a - tmp;
                k += k;
                //防止125行越界
                if (-tmp > Integer.MAX_VALUE / 2) {
                    break;
                }
                tmp = tmp + tmp;
            }
            tmp = b;
            res += (k - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        Divide29 divide29 = new Divide29();
        int fuck = divide29.divide2(-1658356636, -1639504005);
        System.out.println(fuck);
    }
}
