package zhanlang;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/7/5-13:51 - 周二
 * @Description:
 * 用时14'55
 */
public class HJ14_ac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            list.add(sc.nextLine());
        }
        list.remove(0);
        String[] str = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            str[i] = list.get(i);
        }

        //冒泡
        for (int end = str.length - 1; end >= 0; end--) {
            for (int i = 0; i < end; i++) {
                if (str[i].compareTo(str[end]) > 0) {
                    String temp = str[i];
                    str[i] = str[end];
                    str[end] = temp;
                }
            }
        }
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
    }
}
