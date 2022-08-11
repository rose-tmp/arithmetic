package writtenExam.meituan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/8/5-15:01 - 周五
 * @Description:小美是美团仓库的管理员，她会根据单据的要求按顺序取出仓库中的货物，每取出一件货物后会把剩余货物重新堆放，使得自己方便查找。已知货物入库的时候是按顺序堆放在一起的。如果小美取出其中一件货物，则会把货物所在的一堆物品以取出的货物为界分成两堆，这样可以保证货物局部的顺序不变。 已知货物最初是按 1~n 的顺序堆放的，每件货物的重量为 w[i] ,小美会根据单据依次不放回的取出货物。请问根据上述操作，小美每取出一件货物之后，重量和最大的一堆货物重量是多少？
 * <p>
 * 格式：
 * <p>
 * <p>
 * 输入：
 * - 输入第一行包含一个正整数 n ，表示货物的数量。
 * - 输入第二行包含 n 个正整数，表示 1~n 号货物的重量 w[i] 。
 * - 输入第三行有 n 个数，表示小美按顺序取出的货物的编号，也就是一个 1~n 的全排列。
 * 输出：
 * - 输出包含 n 行，每行一个整数，表示每取出一件货物以后，对于重量和最大的一堆货物，其重量和为多少。
 * 示例：
 * <p>
 * <p>
 * 输入：
 * 5
 * 3 2 4 4 5
 * 4 3 5 2 1
 * 输出：
 * 9
 * 5
 * 5
 * 3
 * 0
 * 解释：
 * 原本的状态是 {{3,2,4,4,5}} ，取出 4 号货物后，得到 {{3,2,4},{5}} ，第一堆货物的和是 9 ，然后取出 3 号货物得到 {{3,2}{5}} ，此时第一堆和第二堆的和都是 5 ，以此类推。
 * 提示：
 * <p>
 * 1 <= n <= 50000
 * 1 <= w[i] <= 100
 * 请注意，本题需要自行编写「标准输入」和「标准输出」逻辑，以及自行 import/include 需要的 library。了解书写规则
 * <p>
 * Java
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 作者：美团
 * 链接：https://leetcode.cn/leetbook/read/meituan/oh4ykh/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//货物数量
        List<Integer> list = new ArrayList<>();//货物重量
        boolean[] delFlag = new boolean[n];
        List<Integer> index = new ArrayList<>();//所取货物的编号
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        for (int i = 0; i < n; i++) {
            index.add(sc.nextInt());
        }
        for (int i = 0; i < index.size(); i++) {
            int j = index.get(i) - 1;
            delFlag[j] = true;
            int leftSum = 0;
            if (j - 1 >= 0) {
                leftSum = getSum(list, delFlag, 0, j - 1);
            }
            int rightSum = 0;
            if (j + 1 < list.size()) {
                rightSum = getSum(list, delFlag, j + 1, list.size() - 1);
            }
            System.out.println(Math.max(leftSum, rightSum));
        }
    }

    /**
     * [start,end]的和
     */
    public static int getSum(List<Integer> list, boolean[] flag, int start, int end) {
        int res = 0;
        for (int i = start; i < end - start + 1; i++) {
            //已经被删除
            if (flag[i]) {
                continue;
            }
            res += list.get(i);
        }
        return res;
    }
}
