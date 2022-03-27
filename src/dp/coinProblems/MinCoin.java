package dp.coinProblems;

/**
 * @author - ZwZ
 * @date - 2020/9/23 - 13:11
 * @Description:给你 k 种⾯值的硬币，⾯值分别为 c1, c2 ... ck ，每种硬
 * 币的数量⽆限，再给⼀个总⾦额 amount ，问你最少需要⼏枚硬币凑出这个
 * ⾦额，如果不可能凑出，算法返回 -1
 * <p>
 * 来源：https://labuladong.gitbook.io/algo/di-ling-zhang-bi-du-xi-lie/dong-tai-gui-hua-xiang-jie-jin-jie
 */
public class MinCoin {
    /**
     * 暴力递归  每个位置上的硬币面临着两种选择：留还是不留
     * minCoin(amount) = min{minCoin(amount - coin) + 1,minCoin(amount)}
     * 画出递归树，可以看出有很多重复计算
     *
     * @param amount
     * @param coin
     * @return
     */
    public int minCoin1(int amount, int[] coin) {
        //base case
        if (amount == 0)
            return 0;
        else if (amount < 0)
            return -1;
        else {
            int res = Integer.MAX_VALUE;
            for (int i = 0; i < coin.length; i++) {
                int subProblem = minCoin1(amount - coin[i], coin);
                if (subProblem == -1)
                    continue;
                res = Math.min(res, subProblem + 1);
            }
            return res != Integer.MAX_VALUE ? res : -1;
        }
    }

    /**
     * dp优化
     *
     * @param amount
     * @param coins
     * @return
     */
    public int minCoin2(int amount, int[] coins) {
        //base case
        if (amount <= 0)
            return 0;
        int[] dpArr = new int[amount + 1];
        //赋初值 dpArr[0] == 0
        for (int i = 1; i < dpArr.length; i++) {
            dpArr[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < dpArr.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                //子问题无解
                if (i - coins[j] < 0)
                    continue;
                dpArr[i] = Math.min(dpArr[i], dpArr[i - coins[j]] + 1);
            }
        }
        return dpArr[amount] != Integer.MAX_VALUE ? dpArr[amount] : -1;
    }

    public static void main(String[] args) {
        MinCoin coin = new MinCoin();
        int[] coins = {1, 3, 5};
        System.out.println(coin.minCoin1(11, coins));
    }
}
