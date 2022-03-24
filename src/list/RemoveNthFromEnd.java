package list;

import tool.ListNode;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2020/10/18 - 8:59
 * @Description:19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 说明：
 * 给定的 n 保证是有效的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 */
public class RemoveNthFromEnd {
    /**
     *借助栈结构
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if(head == null || n < 0){
            return head;
        }
        ListNode right = null;//指向当前节点的后一个节点
        ListNode node = head;
        Stack<ListNode> stack = new Stack();
        //入栈
        while(node != null){
            stack.push(node);
            node = node.next;
        }
        while(n-- > 0){
            right = node;//第一次进入while时，right = node = null
            node = stack.pop();
        }
        if(!stack.isEmpty()){
            stack.peek().next = right;
            return head;
        }else{
            return head.next;
        }
    }
    /**
     * 1.计算链表长度len
     * 2.第len - n + 1位置上的节点就是需要删除的节点
     * 3.通过三个指针对其删除
     * */
    public ListNode removeNthFromEnd2(ListNode head, int n) {

        return null;
    }
    /**
     * 只遍历一次链表
     * fast slow，fast先比跑出去n个节点，然后slow再开始跑...
     * */
    public ListNode removeNthFromEnd3(ListNode head, int n) {

        return null;
    }
}
