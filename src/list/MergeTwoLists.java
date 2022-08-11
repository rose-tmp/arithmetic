package list;

import tool.ListNode;

/**
 * @author - ZwZ
 * @date - 2020/9/21 - 8:00
 * @Description:合并两个有序链表 同剑指offer Merge
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head = list1.val < list2.val ? list1 : list2;
        ListNode cur1 = head == list1 ? list1.next : list1;
        ListNode cur2 = head == list2 ? list2.next : list2;
        ListNode node = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                node.next = cur1;
                cur1 = cur1.next;
            } else {
                node.next = cur2;
                cur2 = cur2.next;
            }
            node = node.next;
        }
        if (cur1 != null) {
            node.next = cur1;
        }
        if (cur2 != null) {
            node.next = cur2;
        }
        return head;
    }

    /**
     *  
     *
     * @Description: 递归
     * @return: leetcode.pool.ListNode
     * @Date: 2020/9/21-8:13
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //判空
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
