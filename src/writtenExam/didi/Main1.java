package writtenExam.didi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-09-04 19:29
 * @desc:
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        //前缀和数组
        double[] perfixArr = new double[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            perfixArr[i] = sum;
        }
        int res = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            double avg = perfixArr[i] / (i + 1);
            double temp = ((double) arr[i]) / k;
            if (temp <= avg) {
                res = i + 1;
                break;
            }
        }
        System.out.println(res);
    }
}
