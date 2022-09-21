package writtenExam.qunaer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-09-07 19:08
 * @desc:
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nS = sc.nextLine();
        String mS = sc.nextLine();
        int n = Integer.parseInt(nS);
        int m = Integer.parseInt(mS);

        //key:猪孩子 val:猪妈
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < m; i++) {
            String str = sc.nextLine();
            String[] sArr = str.split(" ");

            int[] arr = new int[sArr.length];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = Integer.parseInt(sArr[j]);
            }

            for (int j = 1; j < arr.length; j++) {
                map.put(arr[j], arr[0]);
            }
        }

        //并查集数组
        int[] fa = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }

        for (Integer k : map.keySet()) {
            union(fa, map.get(k), k);
        }

        //要寻找的目标值
        int target1 = sc.nextInt();
        int target2 = sc.nextInt();

        if (target2 == target1) {
            System.out.println(0);
            return;
        }
        if (find(fa, target2) != find(fa, target1)) {
            System.out.println(-1);
        } else {
            int f1 = target1;
            int f2 = target2;
            int len1 = 0;
            int len2 = 0;

            while (map.containsKey(f1)) {
                f1 = map.get(f1);
                len1++;
            }
            while (map.containsKey(f2)) {
                f2 = map.get(f2);
                len2++;
            }
            System.out.println(len1 + len2);
        }
    }

    public static int find(int[] fa, int i) {
        if (fa[i] == i) {
            return i;
        }
        int f = find(fa, fa[i]);
        fa[i] = f;
        return f;
    }

    public static void union(int[] fa, int i, int j) {
        fa[find(fa,i)] = find(fa,j);
    }
}

