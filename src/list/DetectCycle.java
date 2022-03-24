package list;

import tool.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2020/10/10 - 23:39
 * @Description:142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 */
public class DetectCycle {
    public class Solution {
        //使用快慢指针  leetcode官方解答中需要明白a+(n+1)b+nc=2(a+b)这个式子成立的原因是：慢指针进环之后绝对跑不完一圈就会被追上
        public ListNode detectCycle1(ListNode head) {
            if(head == null) return null;
            ListNode slow = head;
            ListNode fast = head;
            while(fast != null){
                if(fast.next == null || fast.next.next == null){
                    return new ListNode(-1);
                }
                fast = fast.next.next;
                slow = slow.next;
                if(slow == fast){
                    ListNode temp = head;
                    while(temp != slow){
                        temp = temp.next;
                        slow = slow.next;
                    }
                    return temp;
                }
            }
            return new ListNode(-1);
        }
        //使用hash结构
        public ListNode detectCycle2(ListNode head) {
            if(head == null){
                return null;
            }
            ListNode node = head;
            Map<ListNode,Integer> map = new HashMap();
            while(node != null){
                if(map.containsKey(node)){
                    return node;
                }else{
                    map.put(node,1);
                }
                node = node.next;
            }
            return null;
        }
    }
}
