package hash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2022/3/19-23:13 - 周六
 * @Description:146. LRU 缓存
 * * 请你设计并实现一个满足 LRU (最近最少使用) 缓存约束的数据结构。
 * * 实现 LRUCache 类：
 * * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * * void put(int key, int value)如果关键字 key 已经存在，则变更其数据值value ；
 * * 如果不存在，则向缓存中插入该组key-value。如果插入操作导致关键字数量超过capacity ，
 * * 则应该 逐出 最久未使用的关键字。
 * * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * * <p>
 * * 示例：
 * * <p>
 * * 输入
 * * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * * 输出
 * * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * * <p>
 * * 解释
 * * LRUCache lRUCache = new LRUCache(2);
 * * lRUCache.put(1, 1); // 缓存是 {1=1}
 * * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * * lRUCache.get(1);    // 返回 1
 * * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * * lRUCache.get(2);    // 返回 -1 (未找到)
 * * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * * lRUCache.get(1);    // 返回 -1 (未找到)
 * * lRUCache.get(3);    // 返回 3
 * * lRUCache.get(4);    // 返回 4
 * * <p>
 * * 来源：力扣（LeetCode）
 * * 链接：https://leetcode-cn.com/problems/lru-cache
 * * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 思路：采用hashMap + 双向链表的思想去做 （可以直接使用Java自带的双向链表 详见LRUCache146_2）
 * 实现：LinkedList 需要注意：LinkedList add(element)是将元素添加到链表末端
 */
public class LRUCache146_2 {
    /**
     * 创建一个node类用来存储key,value键值对是因为
     * 在put()时有可能会对栈底元素进行删除 此时要想找到map中的该元素
     * 必须拿key做定位。所以不能只存储一个value(并且只存储一个value也有冲突的风险)
     * 这里将每次插入或者更新都看成一个整体，最恰当
     * */
    class LRUNode {
        int key;
        int value;

        public void setValue(int value) {
            this.value = value;
        }

        public LRUNode(int value, int key) {
            this.value = value;
            this.key = key;
        }
    }

    //双向链表
    List<LRUNode> list;
    //当前链表中共包含的节点数目
    int size;
    int capacity;
    Map<Integer, LRUNode> map;

    public LRUCache146_2(int capacity) {
        this.map = new HashMap();
        this.capacity = capacity;
        this.size = 0;
        this.list = new LinkedList<>();
    }

    public int get(int key) {
        //将该元素提至栈顶后返回数据
        if (map.containsKey(key)) {
            LRUNode node = map.get(key);
            list.remove(node);
            list.add(node);
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
            list.remove(node);
            list.add(node);
        } else {
            LRUNode node = new LRUNode(value, key);
            //删除
            if (size >= capacity) {
                LRUNode tmp = list.remove(0);
                //hash表中同步删除该元素
                map.remove(tmp.key);
                size -= 1;
            }
            list.add(node);
            size += 1;
            map.put(key, node);
        }
    }
}
