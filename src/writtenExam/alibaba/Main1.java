package writtenExam.alibaba;


import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 1; i < n - 1; i++) {
            if (arr[i] == 2) {
                if (arr[i - 1] == 1 && arr[i + 1] == 3) {
                    arr[i - 1] = 2;
                    arr[i + 1] = 2;
                }
                if (arr[i - 1] == 3 && arr[i + 1] == 1) {
                    arr[i - 1] = 2;
                    arr[i + 1] = 2;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 2) {
                count++;
            }
        }
        System.out.println(count);
    }
}
