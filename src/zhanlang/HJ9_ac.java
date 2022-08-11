package zhanlang;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author - ZwZ
 * @date - 2022/7/5-12:59 - 周二
 * @Description:
 */
public class HJ9_ac {
    //9876673
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String numStr = num + "";
        String res = "";
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < numStr.length(); i++) {
            set.add(numStr.charAt(i));
        }

        for (int i = numStr.length() - 1; i >= 0; i--) {
            if (set.contains(numStr.charAt(i))) {
                res = res + numStr.charAt(i);
                set.remove(numStr.charAt(i));
            }
        }
        System.out.println(res);
    }
}
