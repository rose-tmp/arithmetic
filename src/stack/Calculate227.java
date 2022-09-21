package stack;

import java.util.Stack;

/**
 * @author: ZwZ
 * @date: 2022-09-20 16:11
 * @desc:
 */
public class Calculate227 {
    public int calculate(String s) {
        //对s进行预处理 把其中的' '全部删掉
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                continue;
            } else {
                sb.append(s.charAt(i));
            }
        }
        s = sb.toString();
        Stack<Integer> stack = new Stack<>();
        char preCal = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                //参与运算的数字不一定是个位数 所以要记录运算符前的值
                num = num * 10 + (c - '0');
            }
            /*当c是运算符时计算上一次的运算符
             * 但是有边界：i==s.length()-1，即遍历到最后时也要计算上一次的运算符
             * 所以这里加了一个 || i==s.length() - 1
             * */
            if (!Character.isDigit(c) || i == s.length() - 1) {
                switch (preCal) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-(num));
                        break;
                    case '*':
                        int temp = stack.pop();
                        stack.push(temp * num);
                        break;
                    default:
                        int t = stack.pop();
                        stack.push(t / num);
                }
                preCal = c;
                num = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        Calculate227 calculate227 = new Calculate227();
        System.out.println(calculate227.calculate(" 3/2 "));
    }
}
