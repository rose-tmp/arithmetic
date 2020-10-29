package leetcode.dp.CommonSubsequence;

/**
 * @author - ZwZ
 * @date - 2020/10/29 - 12:04
 * @Description:1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * 若这两个字符串没有公共子序列，则返回 0。
 * <p>
 * 示例 1:
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * <p>
 * 示例 2:
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * <p>
 * 示例 3:
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 * <p>
 * 详细讲解可以参考https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247487860&idx=1&sn=f5759ae4f22f966db8ed5a85821edd34&chksm=9bd7ef7caca0666a628fe838dee6d5da44b05eadf01fd7e87fcef813430c8e6dc3eb3c23e15f&mpshare=1&scene=1&srcid=1029egejOLkD9x33TTXxekdA&sharer_sharetime=1603937174909&sharer_shareid=60fe53036e3f365b12f8b38c78916f27&key=55250639f247efc1f9ff7879f51fc62b3e0ac6335d14635a50650bbb6c37aff461dcb3ea22235612bbedb34f296bec9e78c6754a58003ec6d1039e7c01a70bffe03e9aeb8a5e695b293ec7d79d28b3fbc670b3f4e17c89886a8099777608743ab099c36021f60c109f3e98b28b2cd2e3e9d4263c44fb164693198dbf52a23296&ascene=1&uin=MjM0MDUzMTEwOQ%3D%3D&devicetype=Windows+10+x64&version=6300002f&lang=zh_CN&exportkey=AyfCWTQ6whM%2FT2KxAOY3rnQ%3D&pass_ticket=ZU%2FjJMq%2F3dHvGR18DuVdw1AkG2HHaCijSligxbBV%2FsbvQ3zO8BmCSrqIEJzzjeQM&wx_header=0
 */
public class LongestCommonSubsequence {
    /*//暴力递归
    public int longestCommonSubsequence(String text1, String text2) {
        return dp(text1,0,text2,0);
    }
    *//**
     * 匹配text1[i......text1.length - 1]和text2[j......text2.length - 1]
     * 中的字符串
     *//*
    public int dp(String text1,int i,String text2,int j){
        //base case
        if(i == text1.length() || j == text2.length()){
            return 0;
        }
        if(text1.charAt(i) == text2.charAt(j)){
            return 1 + dp(text1,i + 1,text2,j + 1);
        }
        //由 Math.max(dp(text1,i + 1,text2,j),dp(text1,i,text2,j + 1),dp(text1,i + 1,text2,j + 1))改进而来
        //前两种情况已经包含了最后面一种
        return Math.max(dp(text1,i + 1,text2,j),dp(text1,i,text2,j + 1));
    }*/
    /**
     * 使用dp优化暴力递归
     * 暴力递归中，存在了重复计算
     * dp(text1,i + 1,text2,j + 1)包含在了dp(text1,i + 1,text2,j),dp(text1,i,text2,j + 1)里面
     */
    int[][] dpArr;
    public int longestCommonSubsequence(String text1, String text2) {
        dpArr = new int[text1.length()][text2.length()];
        for (int i = 0; i < dpArr.length; i++) {
            for (int j = 0; j < dpArr[0].length; j++) {
                dpArr[i][j] = -1;
            }
        }
        return dp(text1,0,text2,0);
    }

    public int dp(String text1, int i, String text2, int j) {
        if (i == text1.length() || j == text2.length()) {
            return 0;
        }
        if (dpArr[i][j] != -1) {
            return dpArr[i][j];
        }
        if (text1.charAt(i) == text2.charAt(j)) {
            dpArr[i][j] = 1 + dp(text1, i + 1, text2, j + 1);
        } else {
            dpArr[i][j] = Math.max(dp(text1, i + 1, text2, j), dp(text1, i, text2, j + 1));
        }
        return dpArr[i][j];
    }
}
