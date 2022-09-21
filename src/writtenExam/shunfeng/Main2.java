package writtenExam.shunfeng;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-09-07 17:40
 * @desc:
 */
public class Main2 {
    static int res = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < n; i++) {
            queue.offer(sc.nextInt());
        }
        List<Integer> list = new ArrayList<>();

        while (true) {
            for (int i = 0; i < m; i++) {
                list.add(queue.poll());
            }
            list.sort((o1, o2) -> o2 - o1);
            if (list.get(list.size() - 1) > 0) {
                res++;
            } else {
                break;
            }
            for (int i = 0; i < list.size(); i++) {
                queue.offer(list.get(i) - 1);
            }
            list.clear();
        }
        System.out.println(res);
    }

}
