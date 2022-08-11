package zhanlang;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author - ZwZ
 * @date - 2022/7/5-13:28 - 周二
 * @Description:
 * 用时5'55
 */
public class HJ10_ac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Set<Character> set = new HashSet<>();
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            if (!set.contains(str.charAt(i)) && str.charAt(i) <= 127) {
                res++;
            }
            set.add(str.charAt(i));
        }
        System.out.println(res);
    }
}
