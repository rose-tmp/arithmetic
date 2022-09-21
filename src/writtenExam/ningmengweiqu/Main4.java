package writtenExam.ningmengweiqu;

import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-09-04 11:54
 * @desc:
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String x = sc.nextLine();

        String n2 = Integer.toBinaryString(n);
        int index = n2.length();
        StringBuilder res1 = new StringBuilder();
        while (index > 0) {
            int start = Math.max(index - 6, 0);
            int end = index;

            String temp = n2.substring(start, end);
            res1.insert(0, 1);
            res1.insert(0, Integer.parseInt(temp));

            index = start;
        }

        System.out.println();
    }
}
