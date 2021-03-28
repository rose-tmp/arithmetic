package leetcode.list;

import leetcode.tool.ListNode;

/**
 * @author - ZwZ
 * @date - 2021/3/28 - 14:16
 * @Description:61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动k个位置。
 * 即将链表看成一个环状，然后旋转K下然后之前的Head位置指向的位置
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * <p>
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-list
 */
public class RotateRight {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int len = 0;
        ListNode slow = head;
        ListNode fast = head;
        ListNode newHead;
        //计算链表长度
        while (slow != null) {
            len++;
            slow = slow.next;
        }
        //旋转链表的长度的倍数的话，链表总结构是不变的
        if (k % len == 0) {
            return head;
        }
        slow = head;
        //让fast 先走k%len步
        for (int i = 0; i < k % len; i++) {

            if (fast != null) {
                fast = fast.next;
            }
        }
        //slow  fast一起向后走 寻找新链表的头
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        newHead = slow.next;
        slow.next = null;
        fast.next = head;
        return newHead;
    }
}
