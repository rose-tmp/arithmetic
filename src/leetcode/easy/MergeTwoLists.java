package leetcode.easy;

import leetcode.pool.ListNode;

/**
 * @author - ZwZ
 * @date - 2020/9/21 - 8:00
 * @Description:合并两个有序链表 同剑指offer Merge
 */
public class MergeTwoLists {
    /** 
    * @Description: 递归 
    * @return: leetcode.pool.ListNode 
    * @Date: 2020/9/21-8:13
    */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2){
        //判空
        if(list1 == null || list2 == null){
            return list1 == null ? list2 : list1;
        }else if (list1.value < list2.value){
            list1.next = mergeTwoLists(list1.next,list2);
            return list1;
        }else{
            list2.next = mergeTwoLists(list1,list2.next);
            return list2;
        }
    }
}
