package zhanlang;

import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/7/3-22:15 - 周日
 * @Description:
 */
public class HJ5_ac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int res = 0;
        int j = 0;
        for (int i = str.length() - 1; i >= 2; i--) {
            res += get(str.charAt(i)) * get2(j);
            j++;
        }
        System.out.println(res);
    }
    public static int get2(int j) {
        int res = 1;
        for (int i = 0; i < j; i++) {
            res *= 16;
        }
        return res;
    }
    public static int get(char s) {
        if (s == 'A') {
            return 10;
        } else if (s == 'B') {
            return 11;
        } else if (s == 'C') {
            return 12;
        } else if (s == 'D') {
            return 13;
        } else if (s == 'E') {
            return 14;
        } else if (s == 'F') {
            return 15;
        }
        return Integer.parseInt(s + "");
    }
}
