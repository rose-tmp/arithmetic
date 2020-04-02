package zuoPrimary;

import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2020/3/24 - 20:50
 * @Description:
 */
public class InOut {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //题目中要求几行输入，就定义几个String变量 如果是整型 使用Inteager.parseInt(String str)转
        String first = "";
        String second = "";
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                first = scanner.nextLine();
            }
            if (i == 1) {
                second = scanner.nextLine();
            }
        }
        System.out.println(first);
        System.out.println(second);
    }
}
