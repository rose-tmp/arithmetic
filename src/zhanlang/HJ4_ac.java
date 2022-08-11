package zhanlang;

import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/7/3-21:47 - 周日
 * @Description:
 */
public class HJ4_ac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        //能被8整除的第一个小于等于str.length的数
        int num = str.length() - str.length() % 8;
        for (int i = 0; i < num; i += 8) {
            if (i + 8 > str.length()) {
                break;
            } else {
                System.out.println(str.substring(i, i + 8));
            }
        }
        String s = "";
        if (num < str.length()) {
            for (int i = num; i < str.length(); i++) {
                s = s + str.charAt(i);
            }
            for (int i = s.length(); i < 8; i++) {
                s = s + "0";
            }
            System.out.println(s);
        }
    }
}
