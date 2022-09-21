package writtenExam.ningmengweiqu;

import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-09-04 11:09
 * @desc:
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String p = sc.nextLine();
        System.out.println(isMatch(s,p));
    }
    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        //在MinDastance里边有介绍关于dp扩大一个位置初始化的原因
        //dp[i][j] : s[0...i-1]和p[0...j-1]是否能够匹配上
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        //s的下标从0开始
        for (int i = 0; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                //题目中有说可以保证*前边会有一个合法字符，所以不需要考虑j-2<0导致数组下标越界的情况
                if (p.charAt(j - 1) == '*') {
                    //i == 0的情况也考虑进去
                    if (dp[i][j - 2] || i == 0) {
                        dp[i][j] = dp[i][j -2];
                        continue;
                    }
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        //dp[i - 1][j]:匹配掉s[i-1]
                        //一个*可以匹配多个字符，所以这里是dp[i - 1][j]而不是dp[i-1]
                        dp[i][j] = dp[i - 1][j] | dp[i][j - 2];
                    }
                    //匹配不上，则让*和*前边的那个字符消失
                    else if (s.charAt(i - 1) != p.charAt(j - 2)) {
                        dp[i][j] = dp[i][j - 2];
                    }
                } else  {
                    //防止数组下标越界
                    if (i == 0){
                        continue;
                    }
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
