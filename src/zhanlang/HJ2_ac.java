package zhanlang;

import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/7/3-21:13 - 周日
 * @Description:
 */
public class HJ2_ac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String temp = sc.nextLine();
        String c = sc.nextLine();

        temp = temp.toLowerCase();
        c = c.toLowerCase();

        int ans = 0;
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) == c.charAt(0)) {
                ans++;
            }
        }
        System.out.print(ans);
    }
}
