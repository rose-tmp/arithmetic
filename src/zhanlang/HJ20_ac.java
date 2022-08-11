package zhanlang;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/7/5-20:02 - 周二
 * @Description:
 */
public class HJ20_ac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            list.add(sc.nextLine());
        }
        for (String s : list) {
            if (isVaild(s)) {
                System.out.println("OK");
            } else {
                System.out.println("NG");
            }
        }
    }

    static boolean isVaild(String str) {
        //长度要超过8位
        if (str.length() <= 8) {
            return false;
        }
        //包括大小写字母.数字.其它符号,以上四种至少三种
        boolean[] flag = new boolean[4];
        for (int i = 0; i < str.length(); i++) {
            //大写字母
            if (str.charAt(i) >= 65 && str.charAt(i) <= 90) {
                flag[0] = true;
            }
            //小写字母
            else if (str.charAt(i) >= 97 && str.charAt(i) <= 122) {
                flag[1] = true;
            }
            //数字
            else if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                flag[2] = true;
            } else {
                flag[3] = true;
            }
        }
        int num = 0;
        for (int i = 0; i < 4; i++) {
            if (flag[i]) {
                num++;
            }
        }
        if (num < 3) {
            return false;
        }

        //不能有长度大于2的包含公共元素的子串重复
        for (int i = 0; i < str.length() - 3; i++) {
            if (str.substring(i + 3).contains(str.substring(i, i + 3))) {
                return false;
            }
        }
        return true;
    }
}
