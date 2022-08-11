package sword_zhuanxiang;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/8/4-22:52 - 周四
 * @Description:
 */
public class LRUCache {
    /**
     * map + 自行模拟的双向链表
     */
    class DLinkedNode {
        DLinkedNode prev;
        DLinkedNode next;
        int val;
        int key;

        public DLinkedNode(int val) {
            this.val = val;
        }
    }

    Map<Integer, DLinkedNode> map; //key: 元素的key value:该key对应的双向链表中的节点
    DLinkedNode head, tail;//越靠前代表最近刚使用过
    int capacity;//容量

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        head = new DLinkedNode(-1);//哑节点
        tail = new DLinkedNode(-1);//哑节点
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 1. 存在:将队列中当前Node提到队头
     * 2. 不存在: return -1
     */
    public int get(int key) {
        if (map.containsKey(key)) {
            DLinkedNode node = map.get(key);
            toHead(node);
            return node.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            //更新值
            DLinkedNode node = map.get(key);
            node.val = value;
            map.put(key, node);
            //提到队头
            toHead(node);
        } else {
            //满了
            if (map.size() == capacity) {
                //删除尾部
                int k = delTail();
                map.remove(k);

                //加入新节点
                DLinkedNode n = new DLinkedNode(value);
                n.key = key;
                map.put(key, n);
                addHead(n);
            } else {
                DLinkedNode n = new DLinkedNode(value);
                n.key = key;
                addHead(n);
                map.put(key, n);
            }
        }
    }

    /**
     * 添加新头节点
     */
    public void addHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * 将node提到双向链表的最前头
     */
    public void toHead(DLinkedNode node) {
        /*node.prev.next = node.next;
        node.next.prev = node.prev;

        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;*/

        delNode(node);
        addHead(node);
    }

    /**
     * 删除双向链表中的尾
     *
     * @return key
     */
    public int delTail() {
        DLinkedNode node = tail.prev;
        delNode(node);

        return node.key;
    }

    /**
     * 删除某节点
     */
    public int delNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        return node.key;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
    }
}
