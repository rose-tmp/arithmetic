package day181122;

import java.util.HashMap;
import java.util.Stack;

/**
 * //                       .::::.
 * //                     .::::::::.
 * //                    :::::::::::
 * //                 ..:::::::::::'
 * //              '::::::::::::'
 * //                .::::::::::
 * //           '::::::::::::::..
 * //                ..::::::::::::.
 * //              ``::::::::::::::::
 * //               ::::``:::::::::'        .:::.
 * //              ::::'   ':::::'       .::::::::.
 * //            .::::'      ::::     .:::::::'::::.
 * //           .:::'       :::::  .:::::::::' ':::::.
 * //          .::'        :::::.:::::::::'      ':::::.
 * //         .::'         ::::::::::::::'         ``::::.
 * //     ...:::           ::::::::::::'              ``::.
 * //    ```` '            ':::::::::'                  ::::..
 * //                       '.:::::'                    ':'````..
 *
 * @author - ZwZ呀咿呀咿哟
 * @date - 2018/11/22 - 16:30
 * @Description:有效括号
 * https://leetcode-cn.com/problems/valid-parentheses/solution/
 * 使用栈和网站起初给的特定算法的根本区别在于：特定算法用一个left值存储括号的状态
 * 故只能存储一种括号的状态
 * 但是栈可以存储多种括号的状态
 */
public class Question2 {
    private HashMap<Character,Character> map;
    public Question2(){
        this.map = new HashMap();
        this.map.put(')','(');
        this.map.put('}','{');
        this.map.put(']','[');
    }
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            if(this.map.containsKey(c)){
                char topElement = stack.empty() ? '#' : stack.pop();

                if(topElement != this.map.get(c)){
                    return false;
                }
            }else{
                stack.push(c);
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        Question2 question2 = new Question2();
        System.out.println(question2.isValid("{[[]{}]}()()"));
    }
}
