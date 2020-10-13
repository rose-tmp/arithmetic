package leetcode.middling;

import leetcode.pool.ListNode;

/**
 * @author - ZwZ
 * @date - 2020/10/13 - 14:51
 * @Description:24. 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 */
public class SwapPairs {
    public ListNode swapPairs1(ListNode head) {
        //链表为空
        if(head == null) return null;
        //链表仅含一个元素
        if(head.next == null) return head;
        //链表仅含两个元素
        if(head.next.next == null){
            ListNode res = head.next;
            head.next.next = head;
            head.next = null;
            return res;
        }
        ListNode dummyHead = new ListNode(0);//哑节点
        dummyHead.next = head;
        ListNode temp = dummyHead;//每次交换temp后面的两个节点
        ListNode node = head;
        ListNode next = node.next;
        while(temp.next != null &&temp.next.next != null){
            temp.next = next;
            node.next = next.next;
            next.next = node;
            temp = node;
            node = temp.next;
            if(node != null){
                next = node.next;
            }
        }
        return dummyHead.next;
    }
    //递归版本
    public ListNode swapPairs2(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
}
