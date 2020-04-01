package day181123;

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
 * @date - 2018/11/23 - 13:36
 * @Description:棒球比赛（用到栈） 栈中存储每次的得分情况
 * https://leetcode-cn.com/problems/baseball-game/solution/
 */
public class Question1 {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for(String op : ops){
            if(op.equals("+")){
                int top = stack.pop();
                int newtop = top + stack.peek();
                stack.push(top);
                stack.push(newtop);
            }else if(op.equals("D")){
                stack.push(2 * stack.peek());
            }else if(op.equals("C")){
                stack.pop();
            }else{
                stack.push(Integer.valueOf(op));
            }
        }
        int num = 0;
        for(int score : stack){
            num = num + score;
        }
        return num;
    }
    public static void main(String[] args) {
        Question1 question1 = new Question1();
        String[] strings = new String[]{"5","2","C","D","+"};
        System.out.println(question1.calPoints(strings));

    }
}
