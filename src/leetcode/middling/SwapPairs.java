package leetcode.middling;

import leetcode.pool.ListNode;

/**
 * @author - ZwZ
 * @date - 2020/10/13 - 14:51
 * @Description:24. 两两交换链表中的节点
 * <p>
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
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
        if (head == null) return null;
        //链表仅含一个元素
        if (head.next == null) return head;
        //链表仅含两个元素
        if (head.next.next == null) {
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
        while (temp.next != null && temp.next.next != null) {
            temp.next = next;
            node.next = next.next;
            next.next = node;
            temp = node;
            node = temp.next;
            if (node != null) {
                next = node.next;
            }
        }
        return dummyHead.next;
    }

    public ListNode swapPairs2(ListNode head) {
        //链表至少有2个节点才能交换，否则就不要交换
        if (head == null || head.next == null)
            return head;
        //这里的first，second，third可以参照图中的标注
        ListNode first = head;
        ListNode second;
        ListNode third;
        //这个是交换链表之后的尾结点，他的next要指向新交换的链表
        ListNode preLast = null;
        //这个只赋值一次，它是要返回的新链表的头结点
        ListNode newHead = head.next;
        //如果能交换就继续操作
        while (first != null && first.next != null) {
            //给second，third赋值
            second = first.next;
            third = second.next;
            //first和second这两个节点交换
            first.next = third;
            second.next = first;
            //这个时候second就是交换之后新链表的头结点，
            //如果preLast不为空，说明前面还有交换完成的链表
            //，要让preLast的next指向新链表的头结点
            if (preLast != null) {
                preLast.next = second;
            }
            //因为first和second交换之后，first就变成新链表
            //的尾结点了，把它保存在preLast中
            preLast = first;
            //前两个交换了，然后从第3个在继续操作
            first = third;
        }
        //返回新链表
        return newHead;
    }

    //递归版本
    public ListNode swapPairs3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = head.next;
        head.next = swapPairs2(newHead.next);
        newHead.next = head;
        return newHead;
    }
}
