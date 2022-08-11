package zhanlang;

import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/7/5-14:16 - 周二
 * @Description:https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4?tpId=37&tags=&title=&difficulty=0&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37 用时：48'09
 * 没有ac的原因：也看出来了这是一个01背包问题
 * 但是看错题目了，以为买一个附件就要买掉它所属的的主件和属于该主件的所有附件，买一个主件就要买掉它的所有附件
 * 其实不是，其实是买附件必须买它从属的主件，买主键可以选择不买附件，或者买n个附件
 */
public class HJ16_noac {
    static class Goods {
        int price;//价格
        int height;//满意度
        boolean flag;//true:为主件 false:不是主件

        int index1 = -1;//附件编号
        int index2 = -1;//附件编号
    }
    /**
     * 对于主件有以下状态：
     * 1. 只买主件
     * 2. 买主件+1个附件
     * 3. 买主件+2个附件
     * 4. 不买该主件
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int all = sc.nextInt();//钱的总数
        int num = sc.nextInt();//物品的数量(附件也算)
        Goods[] gArr = new Goods[num];
        for (int i = 0; i < gArr.length; i++) {
            gArr[i] = new Goods();
        }
        //i表示每个物品的id
        for (int i = 0; i < num; i++) {
            int p = sc.nextInt();//价格
            int h = sc.nextInt();//重要度
            int index = sc.nextInt();
            Goods temp = gArr[i];
            temp.price = p;
            temp.height = p * h;

            //主件
            if (index == 0) {
                temp.flag = true;
            } else {//附件
                //该附件对应的主件的第一个位置上还没有记录其他附件
                if (gArr[index - 1].index1 == -1) {
                    gArr[index - 1].index1 = i;
                } else {
                    gArr[index - 1].index2 = i;
                }
            }
            gArr[i] = temp;
        }
        //dp[i][j]:只有前i个商品(包含第i-1个)，有j元钱的时候，所能拿到的最大满意度
        int[][] dp = new int[num + 1][all + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j];
                //附件 ->直接dp[i][j] = dp[i - 1][j]; 因为如果它的主件在前i-1个商品中，那么在此之前就已经做了计算
                //如果它的主件没有在前i-1个商品中，则它一定不能选上
                if (!gArr[i - 1].flag) {
                    continue;
                }
                if (j - gArr[i - 1].price >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - gArr[i - 1].price] + gArr[i - 1].height);
                }
                if (gArr[i - 1].index1 != -1 && j - gArr[i - 1].price - gArr[gArr[i - 1].index1].price >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - gArr[i - 1].price - gArr[gArr[i - 1].index1].price] + gArr[i - 1].height + gArr[gArr[i - 1].index1].height);
                }
                if (gArr[i - 1].index2 != -1 && j - gArr[i - 1].price - gArr[gArr[i - 1].index2].price >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - gArr[i - 1].price - gArr[gArr[i - 1].index2].price] + gArr[i - 1].height + gArr[gArr[i - 1].index2].height);
                }
                if (gArr[i - 1].index1 != -1 && gArr[i - 1].index2 != -1 && j - gArr[i - 1].price - gArr[gArr[i - 1].index1].price - gArr[gArr[i - 1].index2].price >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - gArr[i - 1].price - gArr[gArr[i - 1].index1].price - gArr[gArr[i - 1].index2].price] + gArr[i - 1].height + gArr[gArr[i - 1].index1].height + gArr[gArr[i - 1].index2].height);
                }
            }
        }
        System.out.println(dp[num][all]);
    }
}
