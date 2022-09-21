package writtenExam.qunaer;

/**
 * @author: ZwZ
 * @date: 2022-09-07 14:36
 * @desc:
 */
public class Main1 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 运动员可得到的最高分
     *
     * @param energy  int整型 运动员体力值
     * @param actions int整型二维数组 二维数组i为动作号 actions[i][0]为动作i+1消耗体力,actions[i][1]为动作i+1得分
     * @return int整型
     */
    public int maxScore(int energy, int[][] actions) {
        //dp[i]:运动员有i体力,能获得的最多分
        int[] dp = new int[energy + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = actions.length - 1; j >= 0; j--) {
                //避免同一个动作做两次
                if (i == 2 * actions[j][0]) {
                    continue;
                }
                int ener = i - actions[j][0];
                if (ener >= 0) {
                    dp[i] = Math.max(actions[j][1] + dp[ener], dp[i]);
                }
            }
        }
        return dp[energy];

        /*List<int[]> list = new ArrayList<>();
        for (int i = 0; i < actions.length; i++) {
            list.add(actions[i]);
        }
        list.sort((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            } else {
                return o2[1] - o1[1];
            }
        });
        int res = 0;
        int index = 0;
        while (energy > 0 && index < list.size()) {
            int[] cur = list.get(index);
            if (energy - cur[0] >= 0) {
                res += cur[1];
                energy -= cur[0];
            }
            index++;
        }
        return res;*/
    }

    public int maxScore2(int energy, int[][] actions) {
        // write code here
        int len = actions.length;
        int dp[][] = new int[len + 1][energy];
        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= len; i++) {
            int tili = actions[i - 1][0];
            int value = actions[i - 1][1];
            for (int j = energy; j >= 0; j--) {
                dp[i][j] = dp[i - 1][j];
                if (j >= tili) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - energy] + value);
                    if (dp[i][j] > max)
                        max = dp[i][j];
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Main1 main1 = new Main1();
        main1.maxScore(10, new int[][]{{1, 1}, {2, 3}, {3, 5}, {5, 10}, {7, 9}, {8, 10}});
    }
}
