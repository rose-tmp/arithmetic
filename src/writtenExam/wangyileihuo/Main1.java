package writtenExam.wangyileihuo;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-08-14 14:37
 * @desc:
 */
public class Main1 {

    //[[1,3],[3,0,1],[2],[0]]
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //[[2],[3],[1],[]]
        String str = sc.nextLine();
        str = str.substring(1, str.length() - 1);
        StringBuilder sb = new StringBuilder();

        List<List<Integer>> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '[') {
                continue;
            }
            else if (c == ',') {
                if (sb.length() > 0)
                    l2.add(Integer.parseInt(sb.toString()));
                sb = new StringBuilder();
            } else if (c == ']') {
                if (sb.length() > 0) {
                    l2.add(Integer.parseInt(sb.toString()));
                    sb = new StringBuilder();
                }
                l1.add(new ArrayList<>(l2));
                l2 = new ArrayList<>();
            } else {
                sb.append(c);
            }
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        boolean[] flag = new boolean[l1.size()];
        flag[0] = true;
        while (!queue.isEmpty()) {
            int val = queue.poll();
            flag[val] = true;
            List<Integer> listtemp = l1.get(val);
            for (int num : listtemp) {
                if (!flag[num]) {
                    queue.add(num);
                    flag[num] = true;
                }
            }
        }
        for (int i = 0; i < flag.length; i++) {
            if (!flag[i]) {
                System.out.println(false);
                return;
            }
        }
        System.out.println(true);
    }
}
