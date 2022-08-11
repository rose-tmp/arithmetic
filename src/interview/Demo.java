package interview;

import tool.ListNode;
import tool.Node;
import tool.TreeNode;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/7/18-22:23 - 周一
 * @Description: 20220725字节二面前把之前做过的题目又做了一遍，全在这里
 */
public class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();//[[2,3],[4,5],[6,7],[8,9],[1,10]]
        demo.merge(new int[][]{{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}});
    }

    public void mergeSort(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(nums, low, mid);
        sort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    public void merge(int[] nums, int low, int mid, int high) {
        int p1 = low;
        int p2 = mid;
        int[] tempArr = new int[high - low + 1];
        int i = 0;
        while (p1 < mid && p2 < nums.length) {
            if (nums[p1] <= nums[p2]) {
                tempArr[i] = nums[p1];
                p1++;
                i++;
            } else {
                tempArr[i] = nums[p2];
                p2++;
                i++;
            }
        }
        if (p1 == mid) {
            for (int j = p2; j < nums.length; j++) {
                tempArr[i] = nums[j];
                i++;
            }
        }
        if (p2 == nums.length) {
            for (int j = p1; j < nums.length; j++) {
                tempArr[i] = nums[j];
                i++;
            }
        }
        //复制
        for (int j = 0; j < tempArr.length; j++) {
            nums[low++] = tempArr[j];
        }
    }

    /**
     * @param low  当前数组需要进行快速排序的区域的第一个位置(包含下标low)
     * @param high 当前数组需要进行快速排序的区域的最后一个位置(包含下标high)
     */
    public void quickSort(int[] nums, int low, int high) {
        if (nums.length < 2) {
            return;
        }
        int[] targetPos = partation(nums, low, high);
        quickSort(nums, low, targetPos[0]);
        quickSort(nums, targetPos[1], high);
    }

    /**
     * 确定nums[low,high]上中间位置位置的那个元素的最终位置
     *
     * @return arr[0]:第一个该元素的位置下标 arr[1]:最后一个该元素的位置下标
     * 因为这个元素可能出现重复，所以就使用arr对其左右边界下标进行记录
     */
    public int[] partation(int[] nums, int low, int high) {
        int target = nums[low + (high - low) / 2];
        //初始化 因为一开始还没有进行排序所以左右边界(包含当前位置)都在当前边界的基础上-1
        int left = low - 1;//nums[0,left]都是比target小的(包含left)
        int right = high + 1;//nums[right,nums.length-1]都是比target大的(包含right)
        int cur = low;
        /**
         * 当前指针撞上有边界的时候停止
         * 此时就形成了[0...left]中都是<target的元素
         * [left+1,right-1]中都是==target的元素
         * [right...]都是>target的元素的局面
         * */
        while (cur < right) {
            if (nums[cur] == target) {
                cur++;
            }
            if (nums[cur] < target) {
                int temp = nums[left + 1];
                nums[cur] = temp;
                nums[left + 1] = nums[cur];
                left++;
            }
            if (nums[cur] > target) {
                int temp = nums[right - 1];
                nums[right - 1] = nums[cur];
                nums[cur] = temp;
            }
        }
        return new int[]{left, right};
    }

    public void bubbleSort1(int[] nums) {
        for (int end = nums.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (nums[i] > nums[i + 1]) {
                    int temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                }
            }
        }
    }

    public void bubbleSort2(int[] nums) {
        for (int end = nums.length - 1; end >= 0; end--) {
            boolean flag = false;
            for (int i = 0; i < end; i++) {
                if (nums[i] > nums[i + 1]) {
                    int temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                return;
            }
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length == 0) {
            return ans;
        }
        Arrays.sort(nums);
        int p1 = 0;
        while (nums[p1] <= 0) {
            int p2 = p1 + 1;
            int p3 = nums.length - 1;
            while (p2 < p3) {
                if (nums[p1] + nums[p2] + nums[p3] < 0) {
                    p2++;
                } else if (nums[p1] + nums[p2] + nums[p3] > 0) {
                    p3--;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[p1]);
                    list.add(nums[p2]);
                    list.add(nums[p3]);
                    ans.add(list);
                    while (p2 < p3 && nums[p2] == nums[p2 + 1]) {
                        p2++;
                    }
                    while (p2 < p3 && nums[p3] == nums[p3 - 1]) {
                        p3--;
                    }
                    p2++;
                    p3--;
                }
            }
            while (p1 < nums.length - 1 && nums[p1] == nums[p1 + 1]) {
                p1++;
            }
            p1++;
        }
        return ans;
    }

    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        int index = 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });
        while (index < intervals.length) {
            int left = intervals[index][0];
            int right = intervals[index][1];
            while (index + 1 < intervals.length && intervals[index][1] >= intervals[index + 1][0]) {
                left = Math.min(Math.min(intervals[index][0], intervals[index + 1][0]), left);
                right = Math.max(right, Math.max(intervals[index][1], intervals[index + 1][1]));
                index++;
            }
            list.add(new int[]{left, right});
            index++;
        }
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }

    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> ans = new ArrayList<>();
        int num = 1;
        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                num++;
            }
            if (i == s.length() - 1 || s.charAt(i) != s.charAt(i + 1)) {
                if (num >= 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i - num + 1);
                    list.add(i + 1);
                    ans.add(list);
                }
                num = 1;
            }
        }
        return ans;
    }

    //[[1,2],[3,5],[6,7],[8,10],[12,16]] [4,8]
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int pos1 = 0;
        int pos2 = 0;
        int index = 0;
        List<int[]> ans = new ArrayList<>();
        while (index < intervals.length && intervals[index][1] < newInterval[0]) {
            ans.add(intervals[index]);
            index++;
        }
        pos1 = newInterval[0];
        pos2 = newInterval[1];
        while (index < intervals.length && intervals[index][0] <= newInterval[1]) {
            pos1 = Math.min(intervals[index][0], pos1);
            pos2 = Math.max(intervals[index][1], pos2);
            index++;
        }
        ans.add(new int[]{pos1, pos2});
        while (index < intervals.length) {
            ans.add(intervals[index++]);
        }
        int[][] ansArr = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            ansArr[i] = ans.get(i);
        }
        return ansArr;
    }

    public boolean canFormArray(int[] arr, int[][] pieces) {
        for (int i = 0; i < arr.length; ) {
            boolean flag = false;
            for (int j = 0; j < pieces.length; j++) {
                if (arr[i] == pieces[j][0]) {
                    for (int k = 0; k < pieces[j].length; k++) {
                        if (arr[i] != pieces[j][k]) {
                            return false;
                        }
                        i++;
                    }
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    public List<List<String>> solveNQueens(int n) {
        dfs8(new boolean[n][n], new HashSet<>(), new HashSet<>(), new HashSet<>(), 0, n);
        return ans8;
    }

    List<List<String>> ans8 = new ArrayList<>();

    public void dfs8(boolean[][] flag, Set<Integer> d1, Set<Integer> d2, Set<Integer> col, int row, int n) {
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (flag[i][j]) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                list.add(sb.toString());
            }
            ans8.add(list);
        }
        //列
        for (int i = 0; i < n; i++) {
            if (d1.contains(i - row) || d2.contains(i + row) || col.contains(i)) {
                continue;
            }
            flag[row][i] = true;
            d1.add(i - row);
            d2.add(i + row);
            col.add(i);
            dfs8(flag, d1, d2, col, row + 1, n);
            flag[row][i] = false;
            d1.remove(i - row);
            d2.remove(i + row);
            col.remove(i);
        }
    }

    List<String> wordRes = new ArrayList<>();

    public List<String> wordBreak2(String s, List<String> wordDict) {
        dfs7(new ArrayList<>(), s, wordDict, 0);
        return wordRes;
    }

    public void dfs7(List<String> list, String s, List<String> wordDict, int index) {
        if (index == s.length()) {
            StringBuilder sb = new StringBuilder();
            for (String value : list) {
                sb.append(value);
                sb.append(" ");
            }
            wordRes.add(sb.substring(0, sb.length() - 1));
            return;
        }
        if (index > s.length()) {
            return;
        }
        for (int i = index; i <= s.length(); i++) {
            String cur = s.substring(index, i);
            if (wordDict.contains(cur)) {
                list.add(cur);
                dfs7(list, s, wordDict, i);
                list.remove(list.size() - 1);
            }
        }
    }


    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs6(new ArrayList<>(), nums, 0);
        return ans;
    }

    public void dfs6(List<Integer> list, int[] nums, int index) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[index]);
        //选
        dfs6(list, nums, index + 1);
        list.remove(list.size() - 1);
        //不选
        dfs6(list, nums, index + 1);
    }

    List<String> fuckL = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        int i = 0;
        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                break;
            }
        }
        if (i == s.length()) {
            fuckL.add("0.0.0.0");
            return fuckL;
        }
        dfs(s, new ArrayList<>(), 0);
        return fuckL;
    }

    public void dfs(String s, List<Integer> cur, int index) {
        if (cur.size() == 4 && index == s.length()) {
            String temp = cur.get(0) + "." + cur.get(1) + "." + cur.get(2) + "." + cur.get(3);
            fuckL.add(temp);
        }
        if (index >= s.length()) {
            return;
        }
        for (int i = index + 1; i <= s.length(); i++) {
            String str = s.substring(index, i);
            if (str.length() > 3) {
                break;
            }
            if (Integer.parseInt(str) >= 0 && Integer.parseInt(str) <= 255) {
                if (str.charAt(0) == '0') {
                    if (str.length() == 1) {
                        cur.add(0);
                        dfs(s, cur, i);
                        cur.remove(cur.size() - 1);
                    } else {
                        break;
                    }
                }
                cur.add(Integer.parseInt(str));
                dfs(s, cur, i);
                cur.remove(cur.size() - 1);
            }
        }
    }

    public List<TreeNode> generateTrees(int n) {
        return getTree(1, n);
    }

    /**
     * @return [i, j]中所能构成的所有二叉树的头节点
     */
    public List<TreeNode> getTree(int i, int j) {
        List<TreeNode> res = new ArrayList<>();
        if (i > j) {
            res.add(null);
            return res;
        }
        //只有一个节点
        if (i == j) {
            res.add(new TreeNode(i));
            return res;
        }
        for (int head = i; head <= j; head++) {
            List<TreeNode> left = getTree(i, head - 1);
            List<TreeNode> right = getTree(head + 1, j);
            for (int k = 0; k < left.size(); k++) {
                for (int l = 0; l < right.size(); l++) {
                    TreeNode root = new TreeNode(head);
                    root.left = left.get(k);
                    root.right = right.get(l);
                    res.add(root);
                }
            }
        }
        return res;
    }

    List<String> res1 = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dfs(new char[n * 2], 0);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < res1.size(); i++) {
            if (isVaild(res1.get(i))) {
                list.add(res1.get(i));
            }
        }
        return list;
    }

    public void dfs(char[] s, int len) {
        if (s.length == len) {
            res1.add(new String(s));
            return;
        }
        s[len] = '(';
        dfs(s, len + 1);
        s[len] = ')';
        dfs(s, len + 1);
    }

    public boolean isVaild(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public boolean exist(char[][] board, String word) {
        boolean[][] flag = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, flag, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return board中以board[i][j]为出发点，匹配word[index...]是否成功
     */
    public boolean dfs(char[][] board, boolean[][] flag, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return false;
        }
        boolean res = false;
        if (board[i][j] == word.charAt(index) && !flag[i][j]) {
            flag[i][j] = true;
            res = dfs(board, flag, word, i + 1, j, index + 1) ||
                    dfs(board, flag, word, i - 1, j, index + 1) ||
                    dfs(board, flag, word, i, j + 1, index + 1) ||
                    dfs(board, flag, word, i, j - 1, index + 1);
            flag[i][j] = false;
        }
        return res;
    }

    List<List<Integer>> ans2 = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs5(n, k, 1, new ArrayList<>());
        return ans2;
    }

    public void dfs5(int n, int k, int index, List<Integer> list) {
        if (list.size() == k) {
            ans2.add(new ArrayList<>(list));
        }
        if (index > n || list.size() > k) {
            return;
        }
        for (int i = index; i <= n; i++) {
            list.add(i);
            dfs5(n, k, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        df4(candidates, target, 0, new ArrayList<>());
        return ans;
    }

    public void df4(int[] candidates, int target, int index, List<Integer> list) {
        int sum = 0;
        for (Integer integer : list) {
            sum += integer;
        }
        if (index > candidates.length || sum > target) {
            return;
        }
        if (sum == target) {
            ans.add(new ArrayList<>(list));
        }
        for (int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            df4(candidates, target, index + 1, list);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs3(candidates, target, new ArrayList<>(), 0);
        return ans;
    }

    public void dfs3(int[] candidates, int target, List<Integer> list, int index) {
        int sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        if (index > candidates.length || sum > target) {
            return;
        }
        if (sum == target) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            dfs3(candidates, target, list, i);
            list.remove(list.size() - 1);
        }
    }

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = getMid(head);
        ListNode tail = revese(mid);

        ListNode node1 = head;
        ListNode node2 = tail;
        while (node1.next != null && node2 != null) {
            ListNode n1Temp = node1.next;
            ListNode n2Temp = node2.next;
            node1.next = node2;
            node2.next = n1Temp;
            node2 = n2Temp;
            node1 = n1Temp;
        }
    }

    public ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode res = slow.next;
        slow.next = null;
        return res;
    }

    /**
     * @return 反转后链表的头节点
     */
    public ListNode revese(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode h = revese(head.next);
        head.next.next = head;
        head.next = null;
        return h;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        cur.next = head;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                ListNode next = cur.next;
                while (next.val == next.next.val) {
                    next = next.next;
                }
                cur.next = next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            while (next != null && next.val == cur.val) {
                next = next.next;
            }
            cur.next = next;
            cur = next;
        }
        return head;
    }


    public int[] findDiagonalOrder(int[][] mat) {
        if (mat.length == 0) {
            return new int[0];
        }
        int rowCount = mat.length;
        int colCount = mat[0].length;
        int x = 0, y = 1;
        boolean flag = true;//true:向下遍历  false:向上遍历
        List<Integer> res = new ArrayList<>();
        res.add(mat[0][0]);
        //直接从[0,1]开始向下遍历 使用for控制遍历次数
        for (int i = 0; i < rowCount + colCount - 2; i++) {
            if (flag) {
                while (x < rowCount && y > 0) {
                    res.add(mat[x++][y--]);
                }
                //修改x,y的值 为接下来向上遍历做准备
                if (x == rowCount - 1) {
                    y++;
                }
                if (y == 0) {
                    x++;
                }
            } else {
                while (x > 0 && y < colCount) {
                    res.add(mat[x--][y++]);
                }
                if (x == 0) {
                    y++;
                }
                if (y == colCount - 1) {
                    x++;
                }
            }
            //反转方向 进行第二次循环
            flag = !flag;
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    public int longestPalindromeSubseq(String s) {
        if (s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int ans = 1;
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }
        for (int j = 1; j < dp.length; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = j - i + 1;
                    } else {
                        dp[i][j] = 2 + dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        int start = 0;
        int end = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
        }
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[j][i] = false;
                } else {
                    if (i - j < 3) {
                        dp[j][i] = true;
                    } else {
                        dp[j][i] = dp[j + 1][i - 1];
                    }
                }
                if (dp[j][i] && (i - j) > (end - start)) {
                    start = j;
                    end = i;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    public int longestValidParentheses(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < dp.length; i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
            } else {
                if (s.charAt(i - 1) == '(') {
                    if (i - 2 >= 0) {
                        dp[i] = dp[i - 2] + 2;
                    } else {
                        dp[i] = 2;
                    }
                } else {
                    if ((i - dp[i - 1] - 1) >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] += dp[i - 1] + 2;
                        if (i - dp[i - 1] - 2 > 0) {
                            dp[i] += dp[i - dp[i - 1] - 2];
                        }
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public boolean canPartition(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        int sum = 0;
        int max = 0;
        //计算数组元素总和和最大值
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        //此时数组中元素不可能分成两份总和相等的子数组 target = sum/2
        if (sum % 2 != 0 || max > sum / 2) {
            return false;
        }
        boolean[][] dp = new boolean[nums.length][sum / 2 + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (j - nums[i] >= 0) {
                    dp[i][j] = dp[i - 1][j - nums[i]] | dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length - 1][sum / 2];
    }

    public int findTargetSumWays(int[] nums, int target) {
        dfs(0, 0, nums, target);
        return res;
    }

    int res = 0;

    public void dfs(int index, int sum, int[] nums, int target) {
        if (sum == target && index == nums.length) {
            res++;
        }
        if (index == nums.length) {
            return;
        }
        dfs(index + 1, sum + nums[index], nums, target);
        dfs(index + 1, sum - nums[index], nums, target);
    }

    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        for (int i = 1; i < dp[0].length; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    for (int k = 1; k < j; k++) {
                        if (dp[i][k]) {
                            dp[i][j] = true;
                        }
                    }
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public int longestMountain(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < left.length; i++) {
            if (left[i] > 0 && right[i] > 0) {
                ans = Math.max(ans, right[i] + left[i] + 1);
            }
        }
        return ans;
    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if (nums[i] + dp[i - 1] > nums[i]) {
                dp[i] = nums[i] + dp[i - 1];
            } else {
                dp[i] = nums[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public Node fuck2(Node head) {
        return dfs(head);
    }

    /**
     * @return 反转后当前链表的新头节点
     */
    public Node dfs(Node head) {
        if (head.next == null) {
            return head;
        }
        Node node = dfs(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public Node fuck(Node node) {
        if (node == null) {
            return null;
        }
        Node dummyNode = new Node(-1);
        dummyNode.next = node;
        Node pre = dummyNode;
        Node cur = node;
        Node next = cur.next;
        while (next != null) {
            cur.next = pre;
            pre = cur;
            cur = next;
            next = next.next;
        }
        cur.next = pre;
        return cur;
    }

    //[10,9,2,5,3,7,101,18]
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        int[] dp = new int[envelopes.length];
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            //按照宽度升序排列
            return o1[0] - o2[0];
        });
        Arrays.fill(dp, 1);
        int ans = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    //text1 = "abcde", text2 = "ace"
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    public int minimumDeleteSum(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = s1.charAt(i - 1) + dp[i - 1][0];
        }
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = s2.charAt(i - 1) + dp[0][i - 1];
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public int numTrees(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return false;
        }
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j + 1))) {
                    dp[i] = true;
                }
            }
        }
        return dp[dp.length - 1];
    }

    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0 || amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] >= dp.length) {
                continue;
            }
            dp[coins[i]] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - j < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i - j], dp[i]);
            }
        }
        return dp[amount];
    }

    public int maxProfit(int[] prices, int fee) {
        if (prices.length <= 1) {
            return 0;
        }
        int min = prices[0];
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res = Math.max(prices[i] - min - fee, res);
            min = Math.min(min, prices[i]);
        }
        return res;
    }

    public int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int translateNum(int num) {
        if (num < 0) {
            return 0;
        }
        if (num < 10) {
            return 1;
        }
        int n = 0;
        String str = num + "";
        n = str.length();
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        if (Integer.parseInt(str.substring(0, 2)) < 26) {
            dp[2] = 2;
        } else {
            dp[2] = 1;
        }
        for (int i = 3; i < dp.length; i++) {
            if (Integer.parseInt(str.substring(i - 2, i)) < 26 && str.charAt(i - 2) != '0') {
                dp[i] += dp[i - 2] + dp[i - 1];
            } else {
                dp[i] += dp[i - 1];
            }
        }
        return dp[n];
    }
}
