package day181123;

/**
 * //                       .::::.
 * //                     .::::::::.
 * //                    :::::::::::
 * //                 ..:::::::::::'
 * //              '::::::::::::'
 * //                .::::::::::
 * //           '::::::::::::::..
 * //                ..::::::::::::.
 * //              ``::::::::::::::::
 * //               ::::``:::::::::'        .:::.
 * //              ::::'   ':::::'       .::::::::.
 * //            .::::'      ::::     .:::::::'::::.
 * //           .:::'       :::::  .:::::::::' ':::::.
 * //          .::'        :::::.:::::::::'      ':::::.
 * //         .::'         ::::::::::::::'         ``::::.
 * //     ...:::           ::::::::::::'              ``::.
 * //    ```` '            ':::::::::'                  ::::..
 * //                       '.:::::'                    ':'````..
 *
 * @author - ZwZ呀咿呀咿哟
 * @date - 2018/11/23 - 15:15
 * @Description:买卖股票的最佳时机
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/
 */
public class Questions2 {
    //暴力法
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i; j < prices.length; j++) {
                if (prices[j] - prices[i] > max) {
                    max = prices[j] - prices[i];
                }
            }
        }
        return max;
    }

    //一次遍历法
    public int maxProfit2(int[] prices) {
        int min = Integer.MAX_VALUE;//最低买价
        int max = 0;//最大利润
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > max) {
                max = prices[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Questions2 questions2 = new Questions2();
        int[] string = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(questions2.maxProfit(string));
        System.out.println(questions2.maxProfit2(string));
    }
}
