package writtenExam.T4399;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-08-18 20:22
 * @desc:
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String astr = sc.next();
        String bstr = sc.next();
        astr = astr.substring(1, astr.length() - 1);
        bstr = bstr.substring(1, bstr.length() - 1);
        String arrA[] = astr.split(",");
        String arrB[] = bstr.split(",");
        int a[] = new int[arrA.length];
        int b[] = new int[arrB.length];
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(arrA[i]);
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = Integer.parseInt(arrB[i]);
        }
        Arrays.sort(a);
        Arrays.sort(b);
//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.toString(b));
        int n = sc.nextInt();
        int val = sc.nextInt();
        int count = 0;
        int min = Math.min(a.length, b.length);
        for (int i = 0; i < min; i++) {
            int temp1 = a[a.length - 1 - i];
            int temp2 = b[b.length - 1 - i];

            if (n <= 0) {
                if (temp1 <= temp2) {
                    count++;
                    continue;

                }
            } else {
                if (temp2 >= temp1) {
                    count++;
                    continue;
                } else if (temp2 + val >= temp1) {
                    n--;
                    count++;
                } else {
                    continue;
                }
            }
        }
        if (count == -1)
            count = min;
        System.out.println(count);

    }
}
