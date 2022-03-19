package leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2022/3/19-21:49 - 周六
 * @Description:146. LRU 缓存
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字 key 已经存在，则变更其数据值value ；
 * 如果不存在，则向缓存中插入该组key-value。如果插入操作导致关键字数量超过capacity ，
 * 则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
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
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 思路：采用hashMap + 双向链表的思想去做 （可以直接使用Java自带的双向链表 详见LRUCache146_2）
 * 实现：自己使用head和tail维护双向链表
 */
public class LRUCache146 {
    class LRUNode {
        int key;
        int value;

        public void setValue(int value) {
            this.value = value;
        }

        //双向队列中该节点的前一个节点
        LRUNode pre;
        //双向队列中该节点的后一个节点
        LRUNode last;

        public LRUNode(int value, int key) {
            this.value = value;
            this.key = key;
        }
    }

    //头节点
    LRUNode head;
    //尾节点
    LRUNode tail;
    //当前链表中共包含的节点数目
    int size;
    int capacity;
    Map<Integer, LRUNode> map;

    public LRUCache146(int capacity) {
        this.head = new LRUNode(-1, Integer.MIN_VALUE);
        this.tail = new LRUNode(-1, Integer.MAX_VALUE);
        this.map = new HashMap();
        this.capacity = capacity;
        this.size = 0;
        head.last = tail;
        tail.pre = head;
    }

    public int get(int key) {
        //将该元素提至栈顶后返回数据
        if (map.containsKey(key)) {
            LRUNode node = map.get(key);
            //将node从原始位置摘除
            node.last.pre = node.pre;
            node.pre.last = node.last;
            //将node插入到栈顶
            node.last = head.last;
            head.last.pre = node;
            head.last = node;
            node.pre = head;
            return map.get(key).value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        //包含该key 直接更新
        if (map.containsKey(key)) {
            map.get(key).setValue(value);

            LRUNode node = map.get(key);
            //todo 这部分代码在get(int key)中也出现了，所以应该单拉出来几个方法（1.删除栈中某个位置上的元素 2.删除栈底元素 3.掺入栈顶元素）以避免冗余但是懒得搞了，就这样吧
            //将node从原始位置摘除
            node.last.pre = node.pre;
            node.pre.last = node.last;
            //将node插入到栈顶
            node.last = head.last;
            head.last.pre = node;
            head.last = node;
            node.pre = head;
        } else {
            LRUNode node = new LRUNode(value, key);
            //删除
            if (size >= capacity) {
                //双向链表中删除最后一个节点（可以理解成栈底节点）
                LRUNode tmp = tail.pre;
                tail.pre = tmp.pre;
                tmp.pre.last = tail;
                //hash表中同步删除该元素
                map.remove(tmp.key);
                size -= 1;
            }
            //插入
            node.last = head.last;
            head.last.pre = node;
            head.last = node;
            node.pre = head;
            size += 1;
            map.put(key, node);
        }
    }

    public static void main(String[] args) {
        LRUCache146 lruCache = new LRUCache146(2);
        lruCache.put(2,1);
        lruCache.put(1,1);
        lruCache.put(2,3);
        lruCache.put(4,1);
        lruCache.get(1);
        lruCache.get(2);
    }
}
