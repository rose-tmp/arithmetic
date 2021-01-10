package leetcode.dp.dpOnStringAndArray;

/**
 * @author - ZwZ
 * @date - 2020/12/6 - 15:13
 * @Description:72. 编辑距离 [2019腾讯笔试]
 * 给定两个字符串s1和s2，计算出将s1转换成s2所使用的最少操作数。你可以对一个字符串进行如下三种操作︰
 * 1.插入一个字符
 * 2.删除一个字符
 * 3.替换一个字符
 * 示例1:
 * 输入: s1 = "horse", s2 = "ros"输出:3
 * 解释:
 * horse -> rorse(将'h′替换为'r')
 * rorse -> rose(删除'r')
 * rose -> ros(删除'e')
 * <p>
 * 示例2:
 * 输入:s1 = "intention", s2 = "execution"输出:5
 * 解释:
 * intention -> inention(删除't')
 * inention -> enention(将'i′替换为'e' )
 * enention -> exention(将'n'替换为'x')
 * exention -> exection(将'n’替换为'c')
 * exection -> execution(插入'u')
 */
public class Distance {
    /**
     * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484731&idx=3&sn=aa642cbf670feee73e20428775dff0b5&chksm=9bd7fb33aca0722568ab71ead8d23e3a9422515800f0587ff7c6ef93ad45b91b9e9920d8728e&scene=21#wechat_redirect
     * 暴力递归
     */
    public int minDistance(String s1, String s2) {
        if (s2 == null || s1 == null) {
            return -1;
        }
        return soultion(s1.length() - 1, s2.length() - 1, s1, s2);
    }

    /**
     * 暴力递归的方法中存在着大量的重复计算 即重叠子问题
     * 我们可以使用 带备忘录的递归 或者是 动态规划 对其进行优化
     */
    public int minDistanceDp(String s1, String s2) {
        return dp(s1, s2);
    }

    private int dp(String s1, String s2) {
        //dp[i - 1][j - 1] s1[0...i]转换成s2[0...j]所用的最小操作数
        /*没有使用dp[i][j]表示s1[0...i]转换成s2[0...j]所用的最小操作数
        是因为这样才可以翻译出暴力递归的逻辑  即当j = -1时 最小操作数为s1的长度
        当i = -1时 最小操作数为s2的长度*/
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        //初始化
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    /**
     * 将字符串s1[0...i]转换成s2[0...j]所用的最小操作数
     * i 作为指针遍历s1
     * j 作为指针遍历s2
     */
    private int soultion(int i, int j, String s1, String s2) {
        /*
         * 此时s1先遍历完成
         * 那么s2剩余的字符只能添加到s1上
         * 所以返回s2剩余的字符的长度[0...i长度是i+1]  即为剩余的操作数
         * */
        if (i == -1) {
            return j + 1;
        }
        /*
         * 此时s2先遍历完成
         * 那么s1剩余的字符只能全部删除  即返回s1剩余的字符的长度
         * */
        if (j == -1) {
            return i + 1;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return soultion(i - 1, j - 1, s1, s2);
        } else {
            //每个位置上可以执行三种操作  删除 替换 插入   返回其中操作数最小的那一个
            return Math.min(soultion(i - 1, j, s1, s2),
                    Math.min(soultion(i, j - 1, s1, s2), soultion(i - 1, j - 1, s1, s2))) + 1;
        }
    }

    public static void main(String[] args) {
        Distance distance = new Distance();
        String s1 = "horse";
        String s2 = "ros";
        String s3 = "intention";
        String s4 = "execution";
        System.out.println(distance.minDistance(s1,s2));
        System.out.println(distance.minDistanceDp(s1,s2));
        System.out.println(distance.minDistance(s3,s4));
        System.out.println(distance.minDistanceDp(s3,s4));
    }
}