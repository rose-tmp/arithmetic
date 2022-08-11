
package sword2;

/**
 * @author - ZwZ
 * @date - 2022/5/11-19:11 - 周三
 * @Description:给定两个 01 字符串a和b，请计算它们的和，并以二进制字符串的形式输出。
 * 输入为 非空 字符串且只包含数字1和0。
 * <p>
 * 示例1:
 * 输入: a = "11", b = "10"
 * 输出: "101"
 * <p>
 * 示例2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/JFETK5
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddBinary {

    /**
     * 转10进制
     *//*
    public String addBinary(String a, String b) {
        int res = fuck(a) + fuck(b);
        if (res == 0) {
            return "0";
        }
        return fuck2(res);
    }

    *//**
     * @return: str对应的十进制数字
     *//*
    public int fuck(String str) {
        if (str.length() == 1 && str.charAt(0) == 0) {
            return 0;
        }
        int res = 0;
        for (int i = str.length() - 1, j = 0; i >= 0; i--, j++) {
            if (str.charAt(i) == '1') {
                res += Math.pow(2, j);
            }
        }
        return res;
    }

    *//**
     * 10 to 2
     *//*
    public String fuck2(int n) {
        StringBuilder res = new StringBuilder();
        int tmp = n;
        while (tmp > 0) {
            int f = tmp % 2;
            res.insert(0, f);
            tmp = tmp / 2;
        }
        return res.toString();
    }*/

    /**
     * 模拟运算
     */
    public String addBinary(String a, String b) {
        String res = "";
        int len_a = a.length();
        int len_b = b.length();
        int max = Math.max(len_a, len_b);
        //进位标记
        int carry = 0;
        for (int i = 0; i < max; i++) {
            int tmp;
            if (i >= len_a) {
                tmp = Integer.parseInt(String.valueOf(b.charAt(len_b - 1 - i))) + carry;
            } else if (i >= len_b) {
                tmp = Integer.parseInt(String.valueOf(a.charAt(len_a - 1 - i))) + carry;
            } else {
                tmp = Integer.parseInt(String.valueOf(a.charAt(len_a - 1 - i))) + Integer.parseInt(String.valueOf(b.charAt(len_b - 1 - i))) + carry;
            }
            if (tmp == 2) {
                res = "0" + res;
                carry = 1;
            } else {
                if (tmp <= 1) {
                    carry = 0;
                    res = tmp + res;
                } else {
                    //继续进位
                    res = tmp - 2 + res;
                    carry = 1;
                }
            }
        }
        if (carry == 1) {
            res = 1 + res;
        }
        return res;
    }
}
