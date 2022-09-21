package writtenExam.shunfeng;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-09-08 16:39
 * @desc:
 */
public class Main11 {
    public static Map<Integer, Character> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        String str = sc.nextLine();

        if (str == null || str.length() == 0) {
            System.out.println(0);
            return;
        }

        map.put(0, 'a');
        map.put(1, 'b');
        map.put(2, 'c');
        map.put(3, 'd');
        map.put(4, 'e');
        map.put(5, 'f');
        map.put(6, 'g');
        map.put(7, 'h');
        map.put(10, 'i');
        map.put(11, 'j');

        long res = getCombine(str);
        System.out.println(res % 1000000007);
    }

    public static long getCombine(String str) {
        if (str.length() == 0) {
            return 0;
        }
        if (str.length() == 1) {
            return 1;
        }
        char c0 = str.charAt(0);
        char c2 = 0;
        //可能和后一位元素组成i或者j
        if (c0 == '1') {
            if (str.charAt(1) == '0') {
                c2 = map.get(10);
            }
            if (str.charAt(1) == '1') {
                c2 = map.get(11);
            }
        }
        long res = 0;
        //可以截取两个位置
        if (c2 != 0) {
            long num = 0;
            if (str.substring(2).length() != 0) {
                num = getCombine(str.substring(2));
            }
            if (num == 0) {
                res++;
            } else {
                res += num;
            }
        }
        long num = getCombine(str.substring(1));
        res += num;
        return res % 1000000007;
    }
}
