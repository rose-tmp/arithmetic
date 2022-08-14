package writtenExam.meituan2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        if (n < 3) {
            return;
        }
        combine(list,3);
        List<List<Integer>> ans = new ArrayList<>();
        for (List<Integer> re : res) {
            if ((list.get(re.get(0) - 1) + list.get(re.get(2) - 1)) == 3 * list.get(re.get(1) - 1)) {
                ans.add(re);
            }
        }
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i).get(0) + " " + ans.get(i).get(1) + " " + ans.get(i).get(2));
        }
    }
    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> combine(List<Integer> list, int k) {
        dfs(new ArrayList<>(), list, k, 0);
        return res;
    }

    /**
     * @Param: index 当前遍历到的位置(只取当前位置之后的数与其形成一个组合 从而避免出现[1,2]和[2,1]都加入res的情况)
     */
    public static void dfs(List<Integer> list,List<Integer> list2, int k, int index) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));

        }
        for (int i = index; i <= list2.size(); i++) {
                list.add(i);
                dfs(list,list2, k, i + 1);
                list.remove(list.size() - 1);
        }
    }

    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        if (n < 3) {
            return;
        }
        List<List<Integer>> res = new ArrayList<>();
        //i为p2
        for (int i = 1; i < list.size() - 1; i++) {
            int p1 = 0;
            int p3 = list.size() - 1;
            while (p1 < i && p3 > i) {
                if ((list.get(p1) + list.get(p3)) == 3 * list.get(i)) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(p1);
                    temp.add(i);
                    temp.add(p3);
                    res.add(temp);
                }
            }
        }

    }*/
}
