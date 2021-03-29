package leetcode.dp;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2021/3/24 - 14:14
 * @Description:32. 最长有效括号
 * 给你一个只包含 '('和 ')'的字符串，找出最长有效（格式正确且连续）
 * 括号子串的长度。
 * <p>
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * <p>
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * <p>
 * 示例 3：
 * 输入：s = ""
 * 输出：0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 */
public class LongestValidParentheses {
    /**
     * 动态规划
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        //dp[i]:在s[0...i]中以字符s[i]结尾的最长有效括号的长度
        int[] dp = new int[s.length()];
        dp[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
            } else {
                //如果前一个字符可以与之匹配，那么就是在上上个字符的基础上+2
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }
                //前一个字符是)
                if (dp[i - 1] > 0) {
                    if (i - dp[i - 1] - 1 >= 0
                            && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = 2 + dp[i - 1];
                        if (i - dp[i - 1] - 2 > 0) {
                            dp[i] += dp[i - dp[i - 1] - 2];
                        }
                    }
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 栈
     * ()(())
     *
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        /*if (s == null || s.length() == 0) return 0;
        //栈中始终记录当前没有被匹配上的(的下标
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    stack.pop();
                    if(!stack.isEmpty()){
                    }else{
                        stack.push(i);
                    }
                }
                res = Math.max(res, i - stack.peek());
            }
        }
        return res;*/
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        /*栈底始终存储从左向右看，目前为止最后一个没有匹配成功的)
        栈底这个是非常有用的，比如在()(())例子中，如果在栈底不加一个第一次匹配没有成功的位置记录
        的话 那么我们刚好遍历到最后一个位置匹配完，然后res取当前有效字符和res的最大值，那么就是Math.max(2,4)
        这就会导致结果的错误
        用栈底去记录从左向右看，目前为止最后一个没有匹配成功的)的位置的话就会有一个好处就是，最后遍历到
        最后一个位置的时候，其下标-栈底这个位置元素的下标，就代表了整个的有效字符的长度*/
        stack.push(-1);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                /*stack为空的时候，说明栈底中之前的)已经pop掉了
                而栈底代表的是从左向右看，目前为止最后一个没有匹配成功的)
                所以此时要更新栈底的元素

                关于为什么一开始栈中压入一个-1也可以不想这么复杂
                就直接：因为这里判空之后要把i压栈，为了使得"边界情况向常规化靠近"
                所以就先压入一个-1,然后就可以进入for循环统一执行
                */
                if (stack.isEmpty()) {
                    stack.push(i);
                }
                res = Math.max(res, i - stack.peek());
            }
        }
        return res;

    }
}
