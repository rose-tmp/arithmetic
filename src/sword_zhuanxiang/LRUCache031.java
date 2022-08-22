package sword_zhuanxiang;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/8/4-22:52 - 周四
 * @Description:剑指 Offer II 031. 最近最少使用缓存
 * 运用所掌握的数据结构，设计和实现一个  LRU (Least Recently Used，最近最少使用) 缓存机制 。
 *
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *
 * 示例：
 *
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *
 * 提示：
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 105
 * 最多调用 2 * 105 次 get 和 put
 *
 *
 * 进阶：是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 注意：本题与主站 146 题相同：https://leetcode-cn.com/problems/lru-cache/
 *
 * 通过次数17,569提交次数
 */
public class LRUCache031 {
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

    public LRUCache031(int capacity) {
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
        LRUCache031 cache = new LRUCache031(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
    }
}
