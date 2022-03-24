package dp.subsequenceAndSubstring;

/**
 * @author - ZwZ
 * @date - 2022/3/24-23:13 - 周四
 * @Description:最长公共子串 给定两个字符串str1和str2, 输出两个字符串的最长公共子串
 * 题目保证str1和str2的最长公共子串存在且唯一。
 * 数据范围：0 <= |str1|,|str2| <= 20000
 * 要求：空间复杂度 O(n^2),时间复杂度 O(n^2)
 * <p>
 * 示例1:
 * 输入:"1AB2345CD","12345EF"
 * 输出:"2345"
 * <p>
 * https://www.nowcoder.com/practice/f33f5adc55f444baa0e0ca87ad8a6aac
 */
public class LCS {
    /**
     * longest common substring
     *
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @return string字符串
     */
    public String LCS(String str1, String str2) {
        if (str1.length() == 0 || str2.length() == 0) {
            return "";
        }
        //用于记录最大子串
        int prev = 0;
        int end = 0;
        //dp[i][j]: str1[0...i]和str2[0...j]中分别以字符str1[i]和str2[j]结尾的最长子串的长度
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    //只有在大于之前记录的结果的时候才更新
                    if (dp[i][j] > end - prev) {
                        prev = i - dp[i][j];
                        end = i;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return str1.substring(prev,end);
    }
}
