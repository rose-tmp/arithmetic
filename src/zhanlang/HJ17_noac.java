package zhanlang;

import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/7/5-18:56 - 周二
 * @Description:https://www.nowcoder.com/practice/119bcca3befb405fbe58abe9c532eb29?tpId=37&tags=&title=&difficulty=0&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
 * 用时：28'15
 * 没有ac的原因：理解错了题目的意思 以为“合法坐标为A(或者D或者W或者S) + 数字（两位以内）”表示数字一定是两位数
 * 所以在判断是否有效的时候一开始直接拿str.length != 3作为判断条件
 * 其实"A8"这种也是合法的，即向左移动8个位置
 *
 * 所以要认真读题，如果以后用例一直通不过的话，就好好再审一遍题，看看是不是理解错了题干
 */
public class HJ17_noac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] sArr = str.split(";");
        int x = 0;
        int y = 0;
        for (int i = 0; i < sArr.length; i++) {
            if (isVaild(sArr[i])) {
                int f = Integer.parseInt(sArr[i].substring(1));
                if ((sArr[i].charAt(0) + "").equals("A")) {
                    x -= f;
                }
                if ((sArr[i].charAt(0) + "").equals("D")) {
                    x += f;
                }
                if ((sArr[i].charAt(0) + "").equals("W")) {
                    y += f;
                }
                if ((sArr[i].charAt((0)) + "").equals("S")) {
                    y -= f;
                }
            }
        }
        System.out.print(x);
        System.out.print(",");
        System.out.println(y);
    }

    public static boolean isVaild(String str) {
        if (str.length() == 0 || str.length() > 3) {
            return false;
        }
        if (str.charAt(0) != 'A' && str.charAt(0) != 'D' && str.charAt(0) != 'W' && str.charAt(0) != 'S') {
            return false;
        }
        try {
            Integer.parseInt(str.substring(1));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
