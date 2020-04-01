package zuoPrimary.lianbiao;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2020/3/25 - 15:09
 * @Description:判断一个链表是否是回文结构
 */
class ListNode {
    int value;
    ListNode next = null;

    ListNode(int value) {
        this.value = value;
    }
}
public class IsPalindrome {
    /** 
    * @Author: ZwZ
    * @Description:借助栈 
    * @Param: [head] 
    * @return: boolean 
    * @Date: 2020/3/25-15:12
    */
    public boolean isPalindrome1(ListNode head){
        if(head == null || head.next == null)
            return true;
        else{
            Stack<ListNode> stack = new Stack<>();
            ListNode node = head;
            //将所有节点压栈
            while(node != null){
                stack.push(node);
                node = node.next;
            }
            node = head;
            while(!stack.isEmpty()){
                if(stack.pop().value == node.value){
                    node = node.next;
                }else{
                    return false;
                }
            }
            return true;
        }
    }
    /** 
    * @Author: ZwZ
    * @Description:不借助任何辅助空间 
    * @Param: [head] 
    * @return: boolean 
    * @Date: 2020/3/25-15:16
    */
    public boolean isPalindrome2(ListNode head){
        if(head == null || head.next == null)
            return true;
        ListNode slow = head;//慢指针
        ListNode quick = head;//快指针
        //保证slow指向中间的节点(偶数个节点时，指向中间两个节点的左边节点)
        while(quick.next != null && quick.next.next != null){
            slow = slow.next;
            quick = quick.next.next;
        }
        quick = slow.next;
        slow.next = null;
        ListNode temp = null;//引入第三个指针，通过三个指针完成右半部分链表的逆序
        //右半部分逆序
        while(quick != null){
            temp = quick.next;
            quick.next = slow;
            slow = quick;
            quick = temp;
        }
        quick = slow;
        slow = head;
        boolean result = true;//返回值
        while(slow != null){
            if(slow.value == quick.value){
                slow = slow.next;
                quick = quick.next;
            }else{
                result = false;
                break;
            }
        }
        //链表复位成原链表....

        return result;
    }

    public static void main(String[] args) {
        IsPalindrome isPalindrome = new IsPalindrome();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        System.out.println(isPalindrome.isPalindrome1(head));
        System.out.println(isPalindrome.isPalindrome2(head));
    }
}
