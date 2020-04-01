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
