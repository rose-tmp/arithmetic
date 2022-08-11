package zhanlang;

import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/7/3-23:56 - 周日
 * @Description:
 */
public class HJ7_ac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double num = sc.nextDouble();
        String numStr = num + "";
        int index = 0;
        for (int i = 0; i < numStr.length(); i++) {
            if (numStr.charAt(i) == '.') {
                index = i;
                break;
            }
        }
        if (Integer.parseInt(numStr.charAt(index + 1) + "") < 5) {
            System.out.println(Integer.parseInt(numStr.substring(0, index)));
        } else {
            System.out.println(1 + Integer.parseInt(numStr.substring(0, index)));
        }
    }
}
