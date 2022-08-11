import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/7/3-19:46 - 周日
 * @Description:
 */
public class Main2 {
    public static void main(String[] args) {
        String res1 = "Yes";
        String res2 = "No";
        List<String> res = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int num = Integer.parseInt(in.nextLine());
        List<List<Integer>> list = new ArrayList<>();

        while (num > 0) {
            num--;
            String[] temp = in.nextLine().split(" ");
            List<Integer> l = new ArrayList<>();
            for (String s : temp) {
                System.out.println(s);
                l.add(Integer.parseInt(s));
            }
            list.add(l);
        }

        //排序
        for (int i = 0; i < list.size(); i++) {
            list.get(i).sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
        }
        for (int i = 0; i < list.size(); i++) {
            List<Integer> l0 = list.get(i);
            if (num1(l0)) {
                res.add(res1);
            } else {
                res.add(res2);
            }
        }
        for (String r : res) {
            System.out.println(r);
        }
    }

    public static boolean num1(List<Integer> list) {
        int n = list.size();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int left = j + 1, right = n - 1, k = j;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (list.get(mid) < list.get(i) + list.get(j)) {
                        k = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                ans += k - j;
            }
        }
        if (ans == 2) {
            return true;
        } else {
            return false;
        }
    }

}
