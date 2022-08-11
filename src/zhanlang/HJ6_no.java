package zhanlang;

import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/7/3-22:46 - 周日
 * @Description:https://www.nowcoder.com/practice/196534628ca6490ebce2e336b47b3607?tpId=37&tags=&title=&difficulty=0&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
 * 描述
 * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）
 *
 * 数据范围： 1 \le n \le 2 \times 10^{9} + 14 \1≤n≤2×10
 * 9
 *  +14
 * 输入描述：
 * 输入一个整数
 *
 * 输出描述：
 * 按照从小到大的顺序输出它的所有质数的因子，以空格隔开。
 *
 * 示例1
 * 输入：
 * 180
 * 复制
 * 输出：
 * 2 2 3 3 5
 * 复制
 */
public class HJ6_no {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long num = scanner.nextLong();
        long k = (long) Math.sqrt(num);

        for (long i = 2; i <= k; ++i) {
            while (num % i == 0) {
                System.out.print(i + " ");
                num /= i;
            }
        }
        System.out.println(num == 1 ? "" : num + " ");
    }


    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        double num = Math.sqrt(t);
        int f = get(1);
        while (t != 1) {
            while (t % f == 0) {
                t = t / f;
                System.out.print(f + " ");
            }
            f = get(f);
            if (f > num) {
                break;
            }
        }
        if (f > num && t != 1) {
            System.out.println(t);
        }
    }

    //得到大于num的第一个质数
    public static int get(int n) {
        int ans;
        for (int i = n + 1; ; i++) {
            if (isFuck(i)) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    public static boolean isFuck(int n) {
        if (n <= 2) {
            return true;
        }
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                sum++;
            }
        }
        if (sum == 1) {
            return true;
        } else {
            return false;
        }
    }
}

