package dp;

/**
 * @author - ZwZ
 * @date - 2022/7/20-9:55 - 周三
 * @Description:
 */
public class TranslateNum {
    public int translateNum(int num) {
        if (num < 0) {
            return 0;
        }
        if (num < 10) {
            return 1;
        }
        int n = 0;
        String str = num + "";
        n = str.length();
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        if (Integer.parseInt(str.substring(0, 2)) < 26) {
            dp[2] = 2;
        }else {
            dp[2] = 1;
        }
        for (int i = 3; i < dp.length; i++) {
            if (Integer.parseInt(str.substring(i - 2, i)) < 26 && str.charAt(i-2) != '0') {
                dp[i] += dp[i - 2] + dp[i - 1];
            } else {
                dp[i] += dp[i - 1];
            }
        }
        return dp[n];
    }
}
