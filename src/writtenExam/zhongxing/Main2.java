package writtenExam.zhongxing;

import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-09-08 19:40
 * @desc:
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] sArr = str.split(" ");
        String nS = sc.nextLine();
        int[] arr = new int[sArr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }
        int n = Integer.parseInt(nS);
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            if (n >= arr[i]) {
                res++;
                n -= arr[i];
            } else {
                n = Integer.parseInt(nS) - arr[i];
                res += i*2;
                res++;
            }
        }
        System.out.println(res);
    }
}
