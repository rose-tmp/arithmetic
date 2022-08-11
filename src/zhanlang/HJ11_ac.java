package zhanlang;

import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/7/5-13:38 - 周二
 * @Description:
 * 用时2'23
 */
public class HJ11_ac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        String res = "";
        for (int i = num.length() - 1; i >= 0; i--) {
            res = res + num.charAt(i);
        }
        System.out.println(res);
    }
}
