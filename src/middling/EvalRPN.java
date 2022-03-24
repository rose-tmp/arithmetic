package middling;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2021/3/16 - 20:44
 * @Description:150. 逆波兰表达式求值
 * 根据 逆波兰表示法，求表达式的值。
 * <p>
 * 有效的运算符包括+,-,*,/。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * <p>
 * <p>
 * 说明：
 * <p>
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * <p>
 * <p>
 * 示例1：
 * <p>
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: 该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * 示例2：
 * <p>
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: 该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * 示例3：
 * <p>
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 * 该算式转化为常见的中缀算术表达式为：
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation
 */
public class EvalRPN {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+")) {
                stack.push(getSum(stack, '+'));
            } else if (tokens[i].equals("/")) {
                stack.push(getSum(stack, '/'));
            } else if (tokens[i].equals("*")) {
                stack.push(getSum(stack, '*'));
            } else if (tokens[i].equals("-")) {
                stack.push(getSum(stack, '-'));
            } else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }

    public int getSum(Stack<Integer> stack, char ch) {
        int a = stack.pop();
        int b = stack.pop();
        if (ch == '+') return a + b;
        if (ch == '/') return b / a;
        if (ch == '*') return a * b;
        if (ch == '-') return b - a;
        return 0;
    }

    public static void main(String[] args) {
        EvalRPN rpn = new EvalRPN();
        System.out.println(rpn.evalRPN(new String[]{"4","13","5","/","+"}));;
    }
}
