package leetcode.middling;

import leetcode.tool.ListNode;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2020/10/20 - 20:13
 * @Description:143. 重排链表
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * <p>
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 */
public class ReorderList {
    public void reorderList1(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        Stack<ListNode> stack = new Stack();
        ListNode node = head;
        ListNode top;
        ListNode temp;
        //入栈
        while (node != null) {
            stack.push(node);
            node = node.next;
        }
        node = head;
        while (!stack.isEmpty()) {
            top = stack.pop();
            if (node != null) {
                temp = node.next;//临时记录链表中node节点的下一个节点
                node.next = top;
                node = temp;
                if (top != node) {
                    top.next = node;
                } else {
                    node.next = null;//新链表的尾节点指向null  防止产生环形链表
                    break;
                }
            }
        }
    }

    /**
     * 找到链表的中间位置，然后将链表后部分反转
     * 然后再将前后合并
     */
    public void reorderList2(ListNode head) {
        if (head == null) {
            return;
        }
        //寻找链表的中间位置
        ListNode slow = head;
        ListNode fast = head;
        /*
        快指针一次走两步，慢指针一次走一步，快指针走到最后的时候，慢指针刚好走到中间
        */
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;//将第一部分链表的尾节点指向null 防止最终有环存在
        //反转链表的后半部分
        ListNode nodeRear = reverseList(head2);
        ListNode nodePre = head;
        ListNode tempPre;
        ListNode tempRear;
        //合并两个链表
        while (nodePre != null && nodeRear != null) {
            tempPre = nodePre.next;
            nodePre.next = nodeRear;
            tempRear = nodeRear.next;
            nodeRear.next = tempPre;

            nodePre = tempPre;
            nodeRear = tempRear;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public static void main(String[] args) {
        ReorderList reorder = new ReorderList();
        ListNode head = new ListNode(1);
        ListNode l2 = new ListNode(2);
        head.next = l2;
        ListNode l3 = new ListNode(3);
        l2.next = l3;
        ListNode l4 = new ListNode(4);
        l3.next = l4;
        reorder.reorderList2(head);
    }
}
