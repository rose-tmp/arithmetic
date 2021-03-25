package leetcode.list;

import leetcode.tool.ListNode;

/**
 * @author - ZwZ
 * @date - 2021/3/12 - 14:05
 * @Description:25. K 个一组翻转链表
 * <p>
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * 示例 3：
 * <p>
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 * 示例 4：
 * <p>
 * 输入：head = [1], k = 1
 * 输出：[1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseKGroup {
    /**
     * 思路：每次反转一个长度为k的子链表
     * 而反转子链表整个过程就是206. 反转链表
     *
     * 思路不难主要是的是扣边界什么的    比较考察编码的基础功底
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        //pre指向了上一个反转后的子链表的尾
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            //记录下一次需要反转的子链表的头
            ListNode nex = tail.next;
            ListNode[] reverse = reverseList(head, tail);
            //反转后的子链表的头
            head = reverse[0];
            //反转后子链表的尾
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = nex;
        }

        return hair.next;
    }

    /**
     * 反转链表
     *
     * @param head
     * @param tail
     * @return 反转后的链表的头和尾
     */
    public ListNode[] reverseList(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp;
        while (pre != tail) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return new ListNode[]{tail,head};
    }

    public static void main(String[] args) {
        ReverseKGroup group = new ReverseKGroup();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(group.reverseKGroup(head,2).val);

    }
}
