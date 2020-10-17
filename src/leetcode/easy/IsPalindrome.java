package leetcode.easy;

import leetcode.pool.ListNode;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2020/10/17 - 11:26
 * @Description:234. 回文链表
 * 请判断一个链表是否为回文链表
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 */
public class IsPalindrome {
    class Solution {
        //借助栈
        public boolean isPalindrome1(ListNode head) {
            Stack<ListNode> stack = new Stack();
            ListNode node = head;
            //入栈
            while (node != null) {
                stack.push(node);
                node = node.next;
            }
            while (head != null) {
                if (head.value != stack.pop().value) {
                    return false;
                }
                head = head.next;
            }
            return true;
        }

        /**
         * 利用栈进行操作的话，入栈整个链表但是出栈比较的时候是不需要全部比较的，只需要比较一半
         * 所以可以对isPalindrome1进行优化
         * 空间复杂度为O(N)
         * 时间复杂度为O(N)
         */
        public boolean isPalindrome2(ListNode head) {
            if (head == null) return true;
            Stack<ListNode> stack = new Stack();
            ListNode node = head;
            int len = 0;
            //入栈
            while (node != null) {
                stack.push(node);
                node = node.next;
                len++;
            }
            len = len >> 1;//len = len / 2;
            while (len-- >= 0) {
                if (head.value != stack.pop().value) {
                    return false;
                }
                head = head.next;
            }
            return true;
        }

        /**
         *将链表后半段反转后依次比较
         * 时间复杂度O(N)
         * 空间复杂度O(1)
         */
        public boolean isPalindrome3(ListNode head) {
            if (head == null) return true;
            ListNode fast = head, slow = head;
            //通过快慢指针找到中点
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            //如果fast不为空，说明链表的长度是奇数个
            if (fast != null) {
                slow = slow.next;
            }
            //反转后半部分链表
            slow = reverse(slow);
            fast = head;
            while (slow != null) {
                //然后比较，判断节点值是否相等
                if (fast.value != slow.value)
                    return false;
                fast = fast.next;
                slow = slow.next;
            }
            return true;
        }
        //反转链表
        public ListNode reverse(ListNode head) {
            ListNode prev = null;
            while (head != null) {
                ListNode next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            return prev;
        }
        ListNode temp;
        /**
         * 此方法是从反向输出单链表中找到的思路：
         * public void printListNode(ListNode head){
         *    if(head == null) return;
         *    printListNode(head.next);
         *    System.out.println(head.val);
         * }
         * 在本题目中我们也可以利用递归的方式将指针一直推到链表的最后一个节点处
         * 然后递归每返回依次就相当于指针逆向移动一次
         * 这时，拿头上的节点与其作比较，如果值相等，则继续，否则返回false
         */
        public boolean isPalindrome4(ListNode head) {
            temp = head;
            return palindrome(head);
        }
        public boolean palindrome(ListNode head){
            boolean res = false;
            if(head == null){
                return true;
            }
            res = palindrome(head.next) && head.value == temp.value;
            //temp扮演的角色是从头部出发的指针 当一次递归返回执行到这一行代码的时候，temp就要向后移动一次
            temp = temp.next;
            return res;
        }
    }
}
