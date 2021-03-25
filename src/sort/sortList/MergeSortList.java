package sort.sortList;

import leetcode.tool.ListNode;

/**
 * @author - ZwZ
 * @date - 2020/11/21 - 9:03
 * @Description: 148. 排序链表
 * 给你链表的头结点head，请将其按升序排列并返回排序后的链表
 * 要求时间复杂度为O(nlogn)  空间复杂度为常数级
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeSortList {
    /**
     * 使用(自顶向下的)归并排序 即递归方式的归并排序  对链表进行排序
     * 时间复杂度  O(NlogN)
     * 空间复杂度  O(NlogN)
     */
    public ListNode mergeSortList(ListNode head) {
        return sort(head);
    }

    /**
     * head: 需要排序的链表的头
     * tail: 需要排序的链表的尾
     */
    public ListNode sort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMidNode(head);
        ListNode rightHead = mid.next;
        mid.next = null;
        ListNode list1 = sort(head);
        ListNode list2 = sort(rightHead);
        ListNode res = merge(list1, list2);
        return res;
    }

    /**
     * 寻找一个链表的中点
     */
    private ListNode findMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode node = dummyNode;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                node.next = head1;
                head1 = head1.next;
            } else {
                node.next = head2;
                head2 = head2.next;
            }
            node = node.next;
        }
        if (head1 == null) {
            node.next = head2;
        }
        if (head2 == null) {
            node.next = head1;
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        MergeSortList sortList = new MergeSortList();
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        ListNode temp = sortList.mergeSortList(head);
        System.out.println(temp.val);
    }
}
