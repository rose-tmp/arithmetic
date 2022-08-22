package sword_zhuanxiang.lianbiao;

import tool.Node;

/**
 * @author: ZwZ
 * @date: 2022-08-19 16:25
 * @desc:剑指 Offer II 029. 排序的循环链表
 * 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。
 * <p>
 * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
 * <p>
 * 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
 * <p>
 * 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * <p>
 * 输入：head = [3,4,1], insertVal = 2
 * 输出：[3,4,1,2]
 * 解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [], insertVal = 1
 * 输出：[1]
 * 解释：列表为空（给定的节点是 null），创建一个循环有序列表并返回这个节点。
 * 示例 3：
 * <p>
 * 输入：head = [1], insertVal = 0
 * 输出：[1,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= Number of Nodes <= 5 * 10^4
 * -10^6 <= Node.val <= 10^6
 * -10^6 <= insertVal <= 10^6
 * <p>
 * <p>
 * 注意：本题与主站 708 题相同： https://leetcode-cn.com/problems/insert-into-a-sorted-circular-linked-list/
 */
public class Insert029 {
    public Node insert(Node head, int insertVal) {
        Node temp = new Node(insertVal);
        if (head == null) {
            temp.next = temp;
            return temp;
        }
        //只有一个节点或者当前节点的next指向了自身
        if (head.next == null || head.next == head) {
            Node cur = new Node(insertVal);
            head.next = cur;
            cur.next = head;
            return head;
        }

        Node cur = head;
        Node next = cur.next;

        //寻找temp插入的位置 当while退出时,temp插入的位置就是cur和next之间
        while (next != head) {
            //在还没有遍历一圈的时候找到了插入的位置
            if (insertVal >= cur.val && insertVal <= next.val) {
                break;
            }
            //遍历到了尾连接首的地方
            if (cur.val > next.val) {
                if (insertVal >= cur.val || insertVal <= next.val) {
                    break;
                }
            }
            //继续向后遍历
            cur = cur.next;
            next = next.next;
        }
        cur.next = temp;
        temp.next = next;
        return head;
    }
}
