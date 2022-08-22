package sword_zhuanxiang.lianbiao;

import tool.ListNode;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2022/8/4-19:06 - 周四
 * @Description:剑指 Offer II 026. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln-1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * 输入: head = [1,2,3,4]
 * 输出: [1,4,2,3]
 * 示例 2:
 * <p>
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,5,2,4,3]
 * <p>
 * 提示：
 * <p>
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/LGjMqU
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReorderList026 {
    /**
     * 时间复杂度O(N)
     * 空间复杂度O(N)
     * */
    public void reorderList1(ListNode head) {
        if (head == null) {
            return;
        }
        java.util.Stack<ListNode> stack = new Stack<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node);
            node = node.next;
        }
        node = head;
        ListNode top = stack.pop();
        while (node.next != top && node != top) {
            ListNode temp = node.next;
            stack.peek().next = null;
            node.next = top;
            top.next = temp;

            top = stack.pop();
            node = temp;
        }
    }
    /**
     * 1. 找中点
     * 2. 反转后续链表
     * 3. 合并
     * 时间复杂度O(N)
     * 空间复杂度O(1)
     * */
    public void reorderList2(ListNode head) {

    }
}
