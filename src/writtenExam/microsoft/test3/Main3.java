package writtenExam.microsoft.test3;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-08-19 19:50
 * @desc:
 */
public class Main3 {
    public int solution(int[] A, int[] B) {
        // write your code in Java 8 (Java SE 8)
        int n = A.length + 1;
        List<Integer> list[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        int count[] = new int[n];
        for (int i = 0; i < n - 1; i++) {
            list[A[i]].add(B[i]);
            list[B[i]].add(A[i]);
        }
        for (int i = 0; i < n; i++) {
            count[i] = 1;
        }
        List<Integer> l2 = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        boolean flag[] = new boolean[n];
        flag[0] = true;
        while (!queue.isEmpty()) {
            int num = queue.poll();
            l2.add(num);
            flag[num] = true;
            for (int i = 0; i < list[num].size(); i++) {
                int team = list[num].get(i);
                if (!flag[team]) {
                    queue.add(team);
                }
            }
        }
        count[0] = 0;
        boolean flag2[] = new boolean[n];
        for (int i = l2.size() - 1; i >= 0; i--) {
            int num = l2.get(i);
            flag2[num] = true;
            for (int q = 0; q < list[num].size(); q++) {
                int n2 = list[num].get(q);
                if (!flag2[n2]) {
                    count[n2] += count[num];
                }
            }
        }
        int va = 0;
        for (int i = 1; i < n; i++) {
            va += (count[i] + 4) / 5;
        }
        return va;
    }
}
