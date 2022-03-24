package list;

import tool.ListNode;

/**
 * @author - ZwZ
 * @date - 2020/10/9 - 22:22
 * @Description:141. 环形链表
 * 给定一个链表，判断链表中是否有环
 */
public class HasCycle {
    /**
     * 快慢指针思想:
     * 如果链表中有环的话，快指针先进环，然后慢指针必然在某个时间会和其相遇，即快指针套了慢指针N圈
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fast;
        if (head.next != null) {
            fast = head.next;
        } else {
            return false;
        }
        ListNode slow = head;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
