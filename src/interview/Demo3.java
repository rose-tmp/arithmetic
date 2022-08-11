package interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2022/8/4-17:30 - 周四
 * @Description:
 */
public class Demo3 {
    static String res = "00:00:00";//输出结果

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = Integer.parseInt(sc.nextLine());//行数
        List<String> list = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            String s = sc.nextLine();
            list.add(s);
        }
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            //入栈
            if (list.get(i).charAt(0) == 'i') {
                stack.push(list.get(i).substring(3));
            } else {
                if (stack.isEmpty()) {
                    break;
                }
                String in = stack.pop();
                String timeCur = getTime(in, list.get(i).substring(4));
                add(timeCur);
            }
        }
        if (res.charAt(1) == ':') {
            System.out.println("0" + res);
        } else {
            System.out.println(res);
        }
    }

    public static void add(String timeCur) {
        String[] curArr = timeCur.split(":");
        String[] resArr = res.split(":");
        if (Integer.parseInt(curArr[2]) + Integer.parseInt(resArr[2]) > 60) {
            resArr[2] = ((Integer.parseInt(curArr[2]) + Integer.parseInt(resArr[2])) - 60) + "";
            resArr[1] = (Integer.parseInt(resArr[1]) + 1) + "";
        } else {
            resArr[2] = "" + (Integer.parseInt(curArr[2]) + Integer.parseInt(resArr[2]));
        }

        if (Integer.parseInt(curArr[1]) + Integer.parseInt(resArr[1]) > 60) {
            resArr[1] = (60) + "";
            resArr[0] = (Integer.parseInt(resArr[1]) + 1) + "";
        } else {
            resArr[1] = "" + (Integer.parseInt(curArr[1]) + Integer.parseInt(resArr[1]));
        }

        resArr[0] = (Integer.parseInt(resArr[0]) + Integer.parseInt(curArr[0])) + "";
        res = resArr[0] + ":" + resArr[1] + ":" + resArr[2];
    }

    //得到本次时间间隔
    public static String getTime(String in, String out) {
        String[] inArr = in.split(":");
        String[] outArr = out.split(":");
        String resHour = "";
        String resMin = "";
        String resMill = "";

        int outMill = Integer.parseInt(outArr[2]);
        int inMill = Integer.parseInt(inArr[2]);
        if (outMill > inMill) {
            resMill = "" + (outMill - inMill);
        } else {
            resMill = "" + (outMill + 60 - inMill);
            outArr[1] = "" + (Integer.parseInt(outArr[1]) - 1);
        }

        int outMin = Integer.parseInt(outArr[1]);
        int inMin = Integer.parseInt(inArr[1]);
        if (outMin > inMin) {
            resMin = "" + (outMin - inMin);
        } else {
            resMin = "" + (outMin + 60 - inMin);
            outArr[0] = "" + (Integer.parseInt(outArr[0]) - 1);
        }

        int outHour = 0;
        if (outArr[0].charAt(0) == 0) {
            outHour = Integer.parseInt(outArr[0].substring(1));
        } else {
            outHour = Integer.parseInt(outArr[0]);
        }
        int inHour = 0;
        if (inArr[0].charAt(0) == 0) {
            inHour = Integer.parseInt(inArr[0].substring(1));
        } else {
            inHour = Integer.parseInt(inArr[0]);
        }
        resHour = "" + (outHour - inHour);

        return resHour + ":" + resMin + ":" + resMill;
    }
}
