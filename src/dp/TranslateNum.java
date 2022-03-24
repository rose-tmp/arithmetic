package dp;

/**
 * @author - ZwZ
 * @date - 2022/3/12 4:47 下午
 * @Description:剑指 Offer 46. 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * 示例 1:
 * <p>
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * <p>
 *                         12258
 *                     1/        \12
 *                    2258       258
 *                  2/   \22    /   \
 *                258    58    .....
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TranslateNum {
    public int translateNum(int num) {
        if (num < 0) {
            return 0;
        }
        String str = Integer.toString(num);
        //dp[i] S(0,i-1)有多少种翻译方法
        // 因为后边要用到dp[i - 2]所以dp和s之间的位置对应关系错开了一位
        int[] dp = new int[str.length() + 1];
        //初始化
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            //&& Integer.parseInt(str.charAt(i - 2) + "") != 0是官方题解中num=506时候计算错误然后添加的边界
            //即考虑str.charAt(i - 2) == 0的情况
            if (Integer.parseInt((str.charAt(i - 2) + "" + str.charAt(i - 1))) < 26 && Integer.parseInt(str.charAt(i - 2) + "") != 0) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[str.length()];
    }
}
