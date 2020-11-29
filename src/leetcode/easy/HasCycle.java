package leetcode.easy;

import leetcode.tool.ListNode;

/**
 * @author - ZwZ
 * @date - 2020/10/9 - 22:22
 * @Description:
 * 给定一个链表，判断链表中是否有环
 *
 */
public class HasCycle {
    /**
     * 快慢指针思想:
     * 如果链表中有环的话，快指针先进环，然后慢指针必然在某个时间会和其相遇，即快指针套了慢指针N圈
     */
    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }
        ListNode fast;
        if(head.next != null){
            fast = head.next;
        }else{
            //单节点但是是一个环
            if(head.next == head){
                return true;
            }else{
                return false;
            }
        }
        ListNode slow = head;
        while(slow != fast){
            if(fast.next == null || fast.next.next == null){
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }
}
