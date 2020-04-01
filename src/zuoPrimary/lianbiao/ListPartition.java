package zuoPrimary.lianbiao;

/**
 * @author - ZwZ
 * @date - 2020/3/25 - 16:09
 * @Description:将一个链表按值划分成左边小，中间相等，右边大的形式
 */
public class ListPartition {
    /**
     * @Author: ZwZ
     * @Description:借助荷兰国旗问题的解决思路， 定义一个ListNode类型的数组，在数组中按照value排序 再从前到后将节点串联 
     * @Param: [head, num] 
     * @return: void 
     * @Date: 2020/3/25-16:10
     */
    public void listPartition1(ListNode head, int num) {

    }

    /**
     * @Author: ZwZ
     * @Description:不借助辅助空间，定义6个指针分别指向小于，等于和大于num区域的链表的头和尾  然后将这三个区域串联
     * @Param: [head, num] 
     * @return: void 
     * @Date: 2020/3/25-16:12
     */
    public ListNode listPartition2(ListNode head, int num) {
        if (head == null || head.next == null)
            return head;
        ListNode node = head;
        ListNode smallHead = null;
        ListNode smallTail = null;
        ListNode equalTail = null;
        ListNode equalHead = null;
        ListNode bigHead = null;
        ListNode bigTail = null;
        while (node != null) {
            if (node.value < num) {
                if (smallHead == null) {
                    smallHead = smallTail = node;
                } else {
                    smallTail.next = node;
                    smallTail = smallTail.next;
                }
            } else if (node.value == num) {
                if (equalHead == null) {
                    equalHead = equalTail = node;
                } else {
                    equalTail.next = node;
                    equalTail = equalTail.next;
                }
            } else {
                if (bigHead == null) {
                    bigHead = bigTail = node;
                } else {
                    bigTail.next = node;
                    bigTail = bigTail.next;
                }
            }
            node = node.next;
        }
        //链表一次遍历完毕之后，将每一块“区域”的尾指针指向null 防止循环链表出现
        smallTail.next = null;
        equalTail.next = null;
        bigTail.next = null;

        //连接三块区域的节点
        if (smallTail != null) {
            if (equalHead != null) {
                smallTail.next = equalHead;
                equalTail.next = bigHead;
            }else{
                smallTail.next = bigHead;
            }
            return smallHead;
        }else{
            if(equalHead != null){
                equalTail.next = bigHead;
                return equalHead;
            }else{
                return bigHead;
            }
        }
    }

    public static void main(String[] args) {
        ListPartition listPartition = new ListPartition();
        ListNode head = new ListNode(1);
        head.next = new ListNode(9);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(3);
        ListNode head2 = listPartition.listPartition2(head,5);
        while(head2 != null){
            System.out.println(head2.value);
            head2 = head2.next;
        }
    }
}
