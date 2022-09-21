package writtenExam.shunfeng;

import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-09-07 17:13
 * @desc:
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        for (int i = 4; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3] + 1;
        }
        System.out.println(dp[n] % 10000007);
    }


}
