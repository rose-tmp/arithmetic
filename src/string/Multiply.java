package string;

/**
 * @author - ZwZ
 * @date - 2021/3/20 - 12:05
 * @Description:43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数num1和num2，返回num1和num2的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 */
public class Multiply {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        for (int i = num1.length() - 1; i >= 0; i--) {
            int temp = 0;
            StringBuilder sb = new StringBuilder();
            for (int m = num1.length() - 1; m >= i; m--) {
                sb.append(0);
            }
            for (int j = num2.length() - 1; j >= 0; j--) {
                //进位
                temp = ((int) num2.charAt(j) * (int) num1.charAt(i) + temp) / 10;
                int temp2 = ((int) num2.charAt(j) * (int) num1.charAt(i) + temp) % 10;
                sb.append(temp2);
            }
            res = addTwoString(res.toString(), sb.reverse().toString());
        }
        return res.toString();
    }

    public StringBuilder addTwoString(String nums1, String nums2) {
        int index1 = nums1.length() - 1;
        int index2 = nums2.length() - 1;
        int temp = 0;
        StringBuilder res = new StringBuilder();
        while (index1 >= 0 && index2 >= 0) {
            res.append(((int) nums1.charAt(index1) + (int) nums2.charAt(index2) + temp) / 10);
            temp = ((int) nums1.charAt(index1) + (int) nums2.charAt(index2)) % 10;
        }
        if (index1 < 0) {
            while (index2 >= 0) {
                res.append((nums2.charAt(index2) + temp) / 10);
                temp = (nums2.charAt(index2) + temp) % 10;
                index2--;
            }
        }
        if (index2 < 0) {
            while (index1 >= 0) {
                res.append((nums1.charAt(index1) + temp) / 10);
                temp = (nums1.charAt(index1) + temp) % 10;
                index1--;
            }
        }
        return res;
    }

    public String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        //结果
        String ans = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int add = 0;
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + add;
                curr.append(product % 10);
                add = product / 10;
            }
            if (add != 0) {
                curr.append(add % 10);
            }
            ans = addStrings(ans, curr.reverse().toString());
        }
        return ans;
    }

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }
}
