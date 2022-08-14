package writtenExam.wangyileihuo;

import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-08-14 14:44
 * @desc:
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;
        for (int i = 25; i <= n; i++) {
            String s = i + "";

            if (s.contains("25"))
                count++;
        }
        System.out.println(count);
    }
}
