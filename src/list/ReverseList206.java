package list;

import tool.ListNode;
import tool.Node;

/**
 * @author - ZwZ
 * @date - 2021/3/12 - 14:24
 * @Description:206. 反转链表
 * 反转一个单链表。
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 */
public class ReverseList206 {
    /**
     * 迭代
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * @return 反转后当前链表的新头节点
     */
    public Node reverseList20220721(Node head) {
        if (head.next == null) {
            return head;
        }
        Node node = reverseList20220721(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    /**
     * 递归
     * 大致思路：https://leetcode-cn.com/problems/reverse-linked-list/solution/fan-zhuan-lian-biao-by-leetcode-solution-d1k2/
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 反转以head为头的链表
     */
    public ListNode reverseList3(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            head.next.next = head;
            ListNode tmp = head.next;
            head.next = null;
            return tmp;
        }
        //创建哑节点
        ListNode dummyNode = new ListNode(-1);
        ListNode res = fuck(head, dummyNode);
        head.next = null;
        return res;
    }


    /**
     * @return: 返回反转后的链表的头
     */
    public ListNode fuck(ListNode head, ListNode preHead) {
        if (head.next == null) {
            head.next = preHead;
            return head;
        }
        if (head.next.next == null) {
            ListNode tmp = head.next;
            head.next.next = head;
            head.next = preHead;
            return tmp;
        }
        //记录待反转链表的头
        ListNode node = head.next.next;
        ListNode tmp = head.next;
        head.next.next = head;
        head.next = preHead;
        return fuck(node, tmp);
    }
}
