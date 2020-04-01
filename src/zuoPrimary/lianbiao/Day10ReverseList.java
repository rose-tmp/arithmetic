package zuoPrimary.lianbiao;

/**
 * @author - ZwZ
 * @date - 2020/1/20 - 10:27
 * @Description:反转链表 输入一个链表，反转链表后，输出新链表的表头
 */
public class Day10ReverseList {
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode node_Pre, node, node_Next;//引入三个指针
        ListNode newHead = null;
        node_Pre = null;
        node = head;
        node_Next = head.next;
        //反转链表
        while (node != null) {
            if (node.next == null)
                newHead = node;
            node.next = node_Pre;
            node_Pre = node;
            node = node_Next;
            if (node_Next != null) {
                node_Next = node_Next.next;
            } else {
                node_Next = null;
            }
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        Day10ReverseList reverseList = new Day10ReverseList();
        System.out.println(reverseList.ReverseList(head).value);
    }
}
