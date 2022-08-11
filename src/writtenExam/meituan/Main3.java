package writtenExam.meituan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/8/5-17:22 - 周五
 * @Description:小美的一个兼职是美团的一名跑腿代购员，她有 n 个订单可以接，订单编号是 1~n ，
 * 但是因为订单的时效性，他只能选择其中 m 个订单接取，精明的小美当然希望自己总的获利是最大的，
 * 已知，一份订单会提供以下信息，跑腿价格 v ，商品重量 w kg，商品每重 1kg ，代购费用要加 2 元，
 * 而一份订单可以赚到的钱是跑腿价格和重量加价之和。小美可是开兰博基尼送货的人，
 * 所以自然不会在意自己会累这种事情。请问小美应该选择哪些订单，使得自己获得的钱最多。
 * 请你按照选择的订单编号的从小到大顺序，如果存在多种方案，输出订单编号字典序较小的方案。
 * <p>
 * 格式：
 * <p>
 * 输入：
 * - 输入第一行包含两个正整数 n，m，表示订单的数量和小美可以接的订单数量。
 * - 接下来有 n 行，第 i 行表示 i-1 号订单的信息。每行有两个正整数 v 和 w  ，表示一个订单的跑腿价格和商品重量。
 * 输出：
 * - 输出包含 m 个 1~n 之间的正整数，中间用空格隔开，表示选择的订单编号。
 * 示例：
 * <p>
 * <p>
 * 输入：
 * 5 2
 * 5 10
 * 8 9
 * 1 4
 * 7 9
 * 6 10
 * 输出：2 5
 * 提示：
 * <p>
 * 1 <= n, m <= 10000
 * 1 <= v, w <= 1000
 * 请注意，本题需要自行编写「标准输入」和「标准输出」逻辑，以及自行 import/include 需要的 library。了解书写规则
 * <p>
 * 作者：美团
 * 链接：https://leetcode.cn/leetbook/read/meituan/oh1sq3/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//订单总量
        int m = sc.nextInt();//可送的订单数量
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int n1 = sc.nextInt();//跑腿价格
            int n2 = sc.nextInt();//商品
            list.add(new int[]{n1, n2, i + 1});
        }
        list.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //利润相等就根据编号排序 因为时效性，所以号小的要先送
                if ((o1[0] + o1[1] * 2) == (o2[0] + o2[1] * 2)) {
                    return o1[2] - o2[2];
                } else {
                    return (o2[0] + o2[1] * 2) - (o1[0] + o1[1] * 2);
                }
            }
        });
        /**
         * 题目上说要从小到大输出编号
         * 妈的，这也是个大坑，明明代码写出来了，但是就是A不过 因为没有排序
         * */
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            res.add(list.get(i)[2]);
        }
        res.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i) + " ");
        }
    }
}
