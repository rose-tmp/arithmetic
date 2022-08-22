package sword_zhuanxiang.stack;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2022/8/8-10:37 - 周一
 * @Description:剑指 Offer II 038. 每日温度
 * 请根据每日 气温 列表 temperatures ，重新生成一个列表，要求其对应位置的输出为：
 * 要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 示例 1:
 * <p>
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 * <p>
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 * <p>
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 * <p>
 * <p>
 * 注意：本题与主站 739 题相同： https://leetcode-cn.com/problems/daily-temperatures/
 */
public class DailyTemperatures038 {
    /**
     * 单调栈 官方思路
     * 栈中维护一个从栈底到栈顶单调递减的序列
     * */
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 0) {
            return new int[]{};
        }
        int index = 0;
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];
        while (index < temperatures.length) {
            if (stack.isEmpty()) {
                stack.push(index);
            } else {
                while (!stack.isEmpty() && temperatures[index] > temperatures[stack.peek()]) {
                    int top = stack.pop();
                    res[top] = index - top;
                }
                stack.push(index);
            }
            index++;
        }
        return res;
    }
}
