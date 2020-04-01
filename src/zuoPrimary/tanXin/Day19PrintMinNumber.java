package zuoPrimary.tanXin;

/**
 * @author - ZwZ
 * @date - 2020/2/2 - 14:10
 * @Description:把数组排成最小的数
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数
 * 打印能拼接出的所有数字中最小的一个
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323
 * 类似题目：给定一个字符串数组 使得数组中排成的字符串的字典序最小
 * 思想：贪心策略 数组中取出两个数字(把它们看成字符串即可)，组成最小的数字，再拿这个数字和数组中其他的数去组合，
 * 每次组合都要求得到一个最小的数字，那么最终出来的数字就是最小的
 * 贪心算法思路：
 * 1.把求解的问题分成若干个子问题
 * 2.对每一子问题求解，得到子问题的局部最优解
 * 3.把子问题的解局部最优解合成原来解问题的一个解
 * 适用的前提是：局部最优策略能导致产生全局最优解
 *
 * 本题贪心策略：
 *  每次取两个数a,b 若ab < ba 则按照ab拼
 *  这个贪心策略对不对(贪心策略对不对的证明是很复杂的，考试中不可能去证明)：
 *  ab < ba,bc < cb 一定有：ac < ca  这是为什么可以用排序的原因 这个是用数学公式推出来的，步骤复杂，左神建议写题时用比较器中的样本测，如果测出来正确，就认为是正确的，然后直接上oj平台跑
 */
public class Day19PrintMinNumber {
    /**
     * @Author: ZwZ
     * @Description:用冒泡排序实现贪心策略
     * @Param: [numbers] 
     * @return: java.lang.String 
     * @Date: 2020/2/2-18:06
     */
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) return "";
        int temp;
        for (int end = numbers.length - 1; end > 0; end--) {
            for (int j = 0; j < numbers.length - end - 1; j++) {
                double sum1 = Long.valueOf(numbers[j] + "" + numbers[j + 1]);
                double sum2 = Long.valueOf(numbers[j + 1] + "" + numbers[j]);
                if (sum1 > sum2) {
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        String str = new String("");
        for (int i = 0; i < numbers.length; i++)
            str = str + numbers[i];
        return str;
    }
    public static void main(String[] args) {
        Day19PrintMinNumber printMinNumber = new Day19PrintMinNumber();
       /* int[] numbers = {5, 8, 6, 3, 9, 2, 1, 7};
        printMinNumber.bubbleSort1(numbers);
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }*/
        int[] numbers = {3334, 3, 3333332};
        System.out.println(printMinNumber.PrintMinNumber(numbers));

    }
}
