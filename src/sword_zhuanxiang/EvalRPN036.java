package sword_zhuanxiang;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2022/8/5-12:29 - 周五
 * @Description:
 */
public class EvalRPN036 {
    public int evalRPN(String[] tokens) {
        if (tokens.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (!("/".equals(tokens[i]) || "*".equals(tokens[i]) || "+".equals(tokens[i]) || "-".equals(tokens[i]))) {
                stack.push(Integer.parseInt(tokens[i]));
            } else {
                int i1 = stack.pop();
                int i2 = stack.pop();
                if ("/".equals(tokens[i])) {
                    stack.push((i2 / i1));
                }
                if ("*".equals(tokens[i])) {
                    stack.push(i1 * i2);
                }
                if ("-".equals(tokens[i])) {
                    stack.push(i2 - i1);
                }
                if ("+".equals(tokens[i])) {
                    stack.push(i1 + i2);
                }
            }
        }
        return stack.pop();
    }

}
