package sword_zhuanxiang.lianbiao;

import tool.ListNode;
import tool.Node;

/**
 * @author: ZwZ
 * @date: 2022-08-19 15:04
 * @desc:剑指 Offer II 024. 反转链表
 * 给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 示例 2：
 * <p>
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 * <p>
 * 输入：head = []
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 * <p>
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 * <p>
 * 注意：本题与主站 206 题相同： https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class ReverseList024 {
    /**
     * @return 反转后的链表的头节点
     */
    public Node reverseList1(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        //该行代码主要是为了找到反转后的链表的头节点
        Node newH = reverseList1(head.next);

        head.next.next = head;
        head.next = null;

        return newH;
    }

    /**
     * 迭代
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
