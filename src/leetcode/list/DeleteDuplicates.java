package leetcode.list;

import leetcode.tool.ListNode;

/**
 * @author - ZwZ
 * @date - 2021/3/25 - 16:06
 * @Description:82. 删除排序链表中的重复元素 II
 * 存在一个按升序排列的链表，给你这个链表的头节点 head，
 * 请你删除链表中所有存在数字重复情况的节点，只保留原始链表中没有重复出现的数字。
 * 返回同样按升序排列的结果链表。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * <p>
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 */
public class DeleteDuplicates {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        //目前有效链表的尾部 用于当出现重复元素并将他们舍去之后，把两边的链表连起来
        ListNode end = dummyNode;
        //两个遍历指针
        ListNode pre = head;
        ListNode rear = head.next;
        while (rear != null) {
            //当数值不相等的时候三个指针一起向前走
            if (pre.val != rear.val) {
                pre = pre.next;
                rear = rear.next;
                end = end.next;
            } else {
                //寻找第一个不重复的元素
                while (rear != null && rear.val == pre.val) {
                    rear = rear.next;
                }
                //连接两个子链
                end.next = rear;
                pre = rear;
                if (rear != null) {
                    rear = rear.next;
                }
            }
        }
        return dummyNode.next;
    }
}
