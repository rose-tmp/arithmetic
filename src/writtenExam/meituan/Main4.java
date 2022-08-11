package writtenExam.meituan;

import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/8/5-18:59 - 周五
 * @Description:
 */
public class Main4 {
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        dfs("", s, 0);
        System.out.println(count);
    }

    /**
     * iflytek
     */
    public static void dfs(String curS, String s, int index) {
        if (index == s.length() || curS.length() > 7) {
            return;
        }
        if (curS.equals("iflytek")) {
            count++;
            return;
        }

        for (int i = index; i < s.length(); i++) {
            //选当前位置
            dfs(curS + s.charAt(i), s, i + 1);
            //不选当前位置
            dfs(curS, s, i + 1);
        }
    }
}
