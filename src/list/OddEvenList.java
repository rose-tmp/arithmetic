package list;

import tool.ListNode;

/**
 * @author - ZwZ
 * @date - 2020/11/13 - 18:50
 * @Description:328. 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * <p>
 * 示例 2:
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * <p>
 * 说明:
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 */
public class OddEvenList {
    /***
     * 双指针
     * 遍历一次链表将奇数下标的节点和偶数下标的节点分别连成两个子链表
     * 最后将两个子链表连接起来
     */
    public ListNode oddEvenList(ListNode head) {
        //如果链表的长度小于等于2 直接返回
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        //指向偶数下标节点的指针
        ListNode evenNode = head;
        //指向奇数下标节点的指针
        ListNode oddNode = head.next;
        ListNode oddHead = oddNode;
        //遍历链表
        while (evenNode.next != null && oddNode.next != null) {
            evenNode.next = oddNode.next;
            evenNode = oddNode.next;

            oddNode.next = evenNode.next;
            oddNode = evenNode.next;
        }
        //这个代码是考虑了具体的两种情况：链表长度为奇数和偶数  之后加上去的
        if (oddNode != null) {
            oddNode.next = null;
        }
        //连接偶数子链表和奇数子链表
        evenNode.next = oddHead;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        OddEvenList oddEvenList = new OddEvenList();
        oddEvenList.oddEvenList(head);
    }
}
