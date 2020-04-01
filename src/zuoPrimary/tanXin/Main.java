package zuoPrimary.tanXin;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2020/3/28 - 10:38
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[256];
        int k = 0;
        for (int i = 0; i < 2; i++) {
            String line = scanner.nextLine();
            if(i == 0) {
                String arrStr = line;
                String[] str = arrStr.split(" ");
                for (int j = 0; j < str.length; j++) {
                    arr[j] = Integer.parseInt(str[j]);
                }
                continue;
            }
            if(i == 1) {
                k = Integer.parseInt(line);
                continue;
            }
        }
        PriorityQueue minHeap = new PriorityQueue();
        //加入小根堆
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != 0)
                minHeap.offer(arr[i]);
        }
        //输出
        for (int i = 0; i < k; i++) {
            System.out.print(minHeap.poll()+",");
        }
    }
}
