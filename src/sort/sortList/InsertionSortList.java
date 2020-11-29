package sort.sortList;

import leetcode.tool.ListNode;

/**
 * @author - ZwZ
 * @date - 2020/11/20 - 15:35
 * @Description:147. 对链表进行插入排序
 */
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyNode = new ListNode(Integer.MIN_VALUE);//哑节点 便于将元素插入到头部
        dummyNode.next = head;
        ListNode lastNode = head;//有序节点中的最后一个节点
        ListNode node = head.next;
        while (node != null) {
            //直接插入到有序序列的最后一个位置
            if (lastNode.value <= node.value) {
                lastNode = lastNode.next;
            }
            //从有序序列的头部开始遍历 直到找到node的位置
            else {
                ListNode temp = dummyNode;
                while (temp.next.value <= node.value) {
                    temp = temp.next;
                }
                lastNode.next = node.next;
                node.next = temp.next;
                temp.next = node;
            }
            node = lastNode.next;
        }
        return dummyNode.next;
    }
}
