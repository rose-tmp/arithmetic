package leetcode.list;

import leetcode.tool.ListNode;

/**
 * @author - ZwZ
 * @date - 2021/3/26 - 9:07
 * @Description:83. 删除排序链表中的重复元素
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 * 返回同样按升序排列的结果链表。
 * <p>
 * 示例 1：
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * <p>
 * 示例 2：
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 */
public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode rear = head.next;
        while (rear != null) {
            if (pre.val != rear.val) {
                pre = pre.next;
                rear = rear.next;
            } else {
                while (rear != null && pre.val == rear.val) {
                    rear = rear.next;
                }
                pre.next = rear;
                pre = rear;
                if (rear != null)
                    rear = rear.next;
            }
        }
        return head;
    }
}
