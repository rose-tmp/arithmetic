package writtenExam.microsoft.test3;

/**
 * @author: ZwZ
 * @date: 2022-08-19 20:08
 * @desc:
 */
public class Demo {
    static int countSubstrings(String s) {
        int count=0;
        int length = s.length();//获取字符串的长度
        boolean[][] dp = new boolean[length][length];
        for(int i=length-1;i>=0;i--) {
            for(int j=i;j<length;j++) {
                if(s.charAt(i) != s.charAt(j)) {//首先得是保证两端相等
                    continue;
                }
                dp[i][j] = (j-i <= 2 || dp[i+1][j-1]);//r
                if(dp[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
