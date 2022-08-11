package interview;

import tool.ListNode;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/7/27-19:09 - 周三
 * @Description:
 */
public class Demo2 {
    public void reorderList(ListNode head) {

    }
    public void quickSort(int[] ans, int low, int high) {

    }

    public int[] partition(int[] ans, int low, int high) {
        int left = low - 1, right = high + 1, cur = low;
        int target = ans[low + (high - low)/2];
        while (cur < right) {
            if (ans[cur]  == target) {
                cur++;
            }
            if (ans[cur] < target) {
                int temp = ans[left + 1];
                ans[left + 1] = ans[cur];
                ans[cur] = temp;
                cur++;
            }
            if (ans[cur] > target) {
                int temp = ans[right-1];
                ans[right-1] = ans[cur];
                ans[cur] = temp;
            }
        }
        return new int[]{left,right};
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> s1 = new Stack();
        Stack<ListNode> s2 = new Stack();
        Stack<ListNode> sR = new Stack<>();
        ListNode cur = l1;
        while (cur != null) {
            s1.push(cur);
            cur = cur.next;
        }
        cur = l2;
        while (cur != null) {
            s2.push(cur);
            cur = cur.next;
        }
        int temp = 0;
        while (!s1.empty() && !s2.empty()) {
            ListNode n1 = s1.pop();
            ListNode n2 = s2.pop();
            int s = n1.val + n2.val + temp;
            temp = 0;
            if (s >= 10) {
                temp = s / 10;
                s %= 10;
            }
            sR.push(new ListNode(s));
        }
        ListNode res = sR.pop();
        ListNode curNode = res;
        while (!sR.isEmpty()) {
            ListNode tp = sR.pop();
            curNode.next = tp;
            curNode = tp;
        }
        while (!s1.isEmpty()) {
            ListNode top = s1.pop();
            int s = top.val + temp;
            temp = 0;
            if (s >= 10) {
                temp = s / 10;
                s %= 10;
            }
            ListNode node = new ListNode(s);
            node.next = res;
            top.next = res;
            return l1;
        }
        if (!s2.isEmpty()) {
            ListNode top = s2.pop();
            top.next = res;
            return l2;
        }
        return res;
    }

    public int countSubstrings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += getCount(s, i, i);
            res += getCount(s, i, i + 1);
        }
        return res;
    }

    /**
     * 计算以某个字符或者某两个字符为回文串中心时，所有回文串的长度
     */
    public int getCount(String s, int start, int end) {
        int count = 0;
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            count++;
            start--;
            end++;
        }
        return count;
    }

    /**
     * 动态规划
     * 思路是错的
     */
    /*public int countSubstrings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int res = 0;
        //dp[i]:以s[i]结尾的字符串的回文串的长度
        int[] dp = new int[s.length()];
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            if ((i - dp[i - 1]) > 0 && s.charAt(i - dp[i - 1]) == s.charAt(i)) {
                dp[i] = dp[i - 1] + 2;
            }
            res += dp[i];
        }
        return res;
    }*/
    public boolean isPalindrome(String s) {
        if (s.length() == 0) {
            return true;
        }
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            //数字
            if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
                newStr.append(s.charAt(i));
            }
            //大写字母
            if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
                newStr.append(s.charAt(i));
            }
            //小写字母
            if (s.charAt(i) >= 97 && s.charAt(i) <= 122) {
                newStr.append(s.charAt(i));
            }
        }
        return isVaild(newStr);
    }

    public boolean isVaild(StringBuilder sb) {
        int p1 = 0;
        int p2 = sb.length() - 1;
        while (p1 < p2) {
            if (Character.toLowerCase(sb.charAt(p1)) != Character.toLowerCase(sb.charAt(p2))) {
                return false;
            }
            p1++;
            p2--;
        }
        return true;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int right = 1;
        int max = 1;
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        for (int left = 0; left < s.length(); left++) {
            //左括号右移
            if (left != 0) {
                set.remove(s.charAt(left - 1));
            }
            while (right < s.length() && !set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }

    public int pivotIndex(int[] nums) {
        int sumL = 0, sumR = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum - nums[0] == 0) {
            return 0;
        }
        sumR = sum - nums[0];
        for (int i = 1; i < nums.length; i++) {
            sumL += nums[i - 1];
            sumR -= nums[i];
            if (sumL == sumR) {
                return i;
            }
        }
        return -1;
    }

    static class Side {
        int a;
        int b;
        long len;

        public Side(int a, int b, long len) {
            this.a = a;
            this.b = b;
            this.len = len;
        }
    }

    static int path[];

    public static int searchFather(int num) {
        if (path[num] < 0) {
            return num;
        } else
            return path[num] = searchFather(path[num]);
    }

    public static void union(int a, int b) {
        int fa = searchFather(a);
        int fb = searchFather(b);
        if (fa == fb)
            return;
        if (path[fa] < path[fb]) {//a>b
            path[fa] += path[fb];
            path[fb] = fa;
        } else {
            path[fb] += path[fa];
            path[fa] = fb;
        }
    }

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        demo2.isPalindrome("A man, a plan, a canal: Panama");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//数量
        path = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            path[i] = -1;
        }
        int paths = sc.nextInt();//可能
        int must = sc.nextInt();//必须
        long map[][] = new long[n + 1][n + 1];
        for (int i = 0; i < paths; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long val = sc.nextLong();
            map[a][b] = val;
            map[b][a] = val;
        }
        Queue<Side> queue = new PriorityQueue<>((o1, o2) -> (int) (o1.len - o2.len));
        boolean jud[] = new boolean[n];//n个节点
        long count = 0;//结果
        boolean judmap[][] = new boolean[n + 1][n + 1];
        for (int i = 0; i < must; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (judmap[a][b])
                continue;
            judmap[a][b] = true;
            judmap[b][a] = true;
            count += map[a][b];//值
            union(a, b);
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                if (map[i][j] > 0 && !judmap[i][j]) {
                    queue.add(new Side(i, j, map[i][j]));
                }
            }
        }
        while (!queue.isEmpty()) {
            Side side = queue.poll();

            int a = side.a;
            int b = side.b;

            int fa = searchFather(a);
            int fb = searchFather(b);
            if (fa != fb) {
                union(a, b);
                count += side.len;
            }
        }
        int father = searchFather(1);
        for (int i = 2; i <= n; i++) {
            int fa = searchFather(i);
            if (fa != father) {
                count = -1;
                break;
            }
        }
        System.out.println(count);

    }

    List<List<Integer>> res = new ArrayList<>();

    public void dfs(List<Integer> list, List<Integer> nums, List<Integer> row, List<Integer> col, List<Integer> diag, int index) {
        if (list.size() == 9) {
            List<Integer> rowSum = new ArrayList<>();
            List<Integer> colSum = new ArrayList<>();
            //行列
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int sumRow = 1;
                    sumRow *= list.get(i + j);
                    rowSum.add(sumRow);
                }
                for (int j = 0; j < 2; j++) {
                    int sumCol = 1;
                    sumCol *= list.get(i);
                    sumCol *= list.get(i + 3);
                    colSum.add(sumCol);
                }
            }
            //判断
            if (isSame(rowSum) && isSame(colSum) && (list.get(0) * list.get(8)) == list.get(2) * list.get(6)) {
                res.add(list);
            }

        }
        /*int sumRow = 1;
        int sumCol = 1;
        int sumDiag = 1;
        for (int i = 0; i < row.size(); i++) {
            sumRow *= row.get(i);
        }
        for (int i = 0; i < col.size(); i++) {
            sumCol *= col.get(i);
        }
        for (int i = 0; i < diag.size(); i++) {
            sumDiag *= diag.get(i);
        }
        if (row.size() == 3 && col.size() == 3 && diag.size() == 3 && sumRow == sumCol && sumRow == sumDiag) {
            res.add(list);
        }*/
        for (int i = index; i < nums.size(); i++) {
            list.add(nums.get(i));
            if ((index + 1) % 3 == 0) {
                //row.add()
            }
        }
    }

    public boolean isSame(List<Integer> list) {
        int l0 = list.get(0);
        int l1 = list.get(1);
        int l2 = list.get(2);
        if (l0 == l1 && l0 == l2) {
            return true;
        }
        return false;
    }
    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '+' || str.charAt(i) == '-') {
                res += 1;
            } else if (str.charAt(i) == '*') {
                res += 2;
            } else if (str.charAt(i) == '/') {
                res += 4;
            }
        }
        System.out.println(res);
    }*/
}
