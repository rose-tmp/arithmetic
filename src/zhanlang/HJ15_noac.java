package zhanlang;

import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/7/5-14:07 - 周二
 * @Description:https://www.nowcoder.com/practice/440f16e490a0404786865e99c6ad91c9?tpId=37&tags=&title=&difficulty=0&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
 * 用时7'48
 */
public class HJ15_noac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int res = 0;
        while (N != 0) {
            if ((N & 1) == 1) {
                res++;
            }
            N = N >> 1;
        }
        System.out.println(res);
    }
}
