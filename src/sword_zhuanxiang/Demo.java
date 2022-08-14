package sword_zhuanxiang;

import tool.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/8/4-20:47 - 周四
 * @Description:
 */
public class Demo {

    public boolean isBalance(TreeNode root) {

        boolean[] isBalance = new boolean[1];
        isBalance[0] = true;

        getHeight(root, isBalance);

        return isBalance[0];

    }

    public int getHeight(TreeNode root, boolean[] isBalance) {
        if (root == null) return 0;

        int leftHeight = getHeight(root.left, isBalance);
        if (!isBalance[0]) return -1; // 左子树不平衡，直接返回，不用计算当前节点的高度了
        int rightHeight = getHeight(root.right, isBalance);
        if (!isBalance[0]) return -1; // 右子树不平衡，直接返回，不用计算当前节点的高度了

        if (Math.abs(leftHeight - rightHeight) > 1) {
            isBalance[0] = false;
            return -1;
        }

        // 只有左右子树平衡了，才返回高度
        return 1 + Math.max(leftHeight, rightHeight);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

    }

    class MyCalendar {

        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;

        public MyCalendar() {

        }

        public boolean book(int start, int end) {
            //初次book
            if (minVal == Integer.MAX_VALUE && maxVal == Integer.MIN_VALUE) {
                minVal = start;
                maxVal = end;
                return true;
            }
            if ((end < minVal && start < minVal) || (start >= maxVal && end >= start)) {

                return true;
            }
            return false;
        }
    }

    public static boolean isValid(String username) {
        //首字母不是大写字母
        if (!((username.charAt(0) >= 'A' && username.charAt(0) <= 'Z') || (username.charAt(0) >= 'a' && username.charAt(0) <= 'z'))) {
            return false;
        }
        boolean flag = false;

        for (int i = 1; i < username.length(); i++) {
            boolean isChar = false, isNum = false;
            //是字母
            if (username.charAt(i) >= 'A' && username.charAt(i) <= 'Z' ||
                    username.charAt(i) >= 'a' && username.charAt(i) <= 'z') {
                isChar = true;
            }
            //是数字
            if (username.charAt(i) >= '0' && username.charAt(i) <= '9') {
                isNum = true;
            }
            //既不是字母也不是数字
            if (!isChar && !isNum) {
                return false;
            }
            if (isNum) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Character> map = new HashMap<>();
        //外星人字典序和中国人字典序，字符对应关系
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), (char) (i + 'a'));
        }
        String[] wordsHuman = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            char[] c = new char[words[i].length()];
            for (int j = 0; j < words[i].length(); j++) {
                c[j] = map.get(words[i].charAt(j));
            }
            String str = new String(c);
            wordsHuman[i] = str;
        }
        for (int i = 0, j = 1; i < wordsHuman.length - 1; i++, j++) {
            if (wordsHuman[i].compareTo(wordsHuman[j]) > 0) {
                return false;
            }
        }
        return true;
    }


    /*public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node h = new Node(insertVal);
            h.next = h;
            return h;
        }
        if (head.next == head) {
            Node next = new Node(insertVal);
            head.next = next;
            next.next = head;
            return head;
        }
        Node cur = head;
        Node next = head.next;
        while (next != head) {
            if ((cur.val <= insertVal && insertVal <= next.val)) {
                Node temp = new Node(insertVal);
                cur.next = temp;
                temp.next = next;
                return head;
            } else if (cur.val >= next.val && cur.val <= insertVal) {
                Node temp = new Node(insertVal);
                cur.next = temp;
                temp.next = next;
                return head;
            } else {
                cur = cur.next;
                next = next.next;
            }
        }
        Node temp = new Node(insertVal);
        cur.next = temp;
        temp.next = next;
        return head;
    }*/
}
