package writtenExam.honor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> ans = new ArrayList<>();
        int num = sc.nextInt();
        for (int i = 2; i <= 16; i++) {
            zhuanhuan(num, i);
            if (isPal(conversion + "")) {
                ans.add(i);
            }
            conversion = "";
        }
        if (ans.size() == 0) {
            System.out.println(-1);
        } else {
            for (Integer an : ans) {
                System.out.println(an);
            }
        }
    }

    public static String[] arr = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F"};

    // 转换结果
    public static String conversion = "";

    /**
     * @param num 要转换的十进制数
     * @param sys 转换的进制位
     */
    private static void zhuanhuan(int num, int sys) {
        conversion = arr[num % sys] + conversion;

        if (num / sys == 0) return;

        zhuanhuan(num / sys, sys);
    }

    private static boolean isPal(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) == str.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}

