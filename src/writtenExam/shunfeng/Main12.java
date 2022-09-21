package writtenExam.shunfeng;

import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-09-08 17:08
 * @desc:https://blog.csdn.net/weixin_38328444/article/details/115621433
 */
public class Main12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String str = sc.nextLine();

        if (str.length() == 0) {
            System.out.println(0);
            return;
        }
        int maxSum = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '1') {
                sum++;
            }
            if (str.charAt(i) == '0') {
                sum--;
            }
            maxSum = Math.max(maxSum, sum);
            //之前的子串对结果的贡献为负,所以从头开始计数
            sum = Math.max(sum, 0);
        }

        System.out.println(maxSum);
    }
    /**
     * 前缀和的方式也超时 只能通过45%
     * */
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String str = sc.nextLine();

        if (str.length() == 0) {
            System.out.println(0);
            return;
        }

        //prefixSum0[i]:[0...i) 0的个数是多少 即str的下标i之前有多少0
        int[] prefixSum0 = new int[n + 1];
        //prefixSum1[i]:[0...i) 1的个数是多少 即str的下标i之前有多少1
        int[] prefixSum1 = new int[n + 1];

        if (str.charAt(0) == '0') {
            prefixSum0[1] = 1;
        } else {
            prefixSum1[1] = 1;
        }

        for (int i = 1; i < n + 1; i++) {
            if (str.charAt(i - 1) == '0') {
                prefixSum0[i] = prefixSum0[i - 1] + 1;
                prefixSum1[i] = prefixSum1[i - 1];
            } else {
                prefixSum1[i] = prefixSum1[i - 1] + 1;
                prefixSum0[i] = prefixSum0[i - 1];
            }
        }
        int res = 0;
        //暴力所有子串
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                //*String temp = str.substring(i, j + 1);
                //res = Math.max(getSum(temp), res);
                int sum0 = prefixSum0[j + 1] - prefixSum0[i];
                int sum1 = prefixSum1[j + 1] - prefixSum1[i];
                res = Math.max(res, sum1 - sum0);
            }
        }

        System.out.println(res);
    }

    /*public static int getSum(String str) {
        int sum0 = 0;
        int sum1 = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                sum0++;
            } else {
                sum1++;
            }
        }
        return sum1 - sum0;
    }*/
}
