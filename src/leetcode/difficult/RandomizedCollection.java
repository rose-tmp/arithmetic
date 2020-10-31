package leetcode.difficult;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2020/10/31 - 10:13
 * @Description:381. O(1) 时间插入、删除和获取随机元素 - 允许重复
 * <p>
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 * 注意: 允许出现重复元素。
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 * <p>
 * 示例:
 * // 初始化一个空的集合。
 * RandomizedCollection collection = new RandomizedCollection();
 * // 向集合中插入 1 。返回 true 表示集合不包含 1 。
 * collection.insert(1);
 * // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 * collection.insert(1);
 * // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 * collection.insert(2);
 * // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 * collection.getRandom();
 * // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 * collection.remove(1);
 * // getRandom 应有相同概率返回 1 和 2 。
 * collection.getRandom();
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed
 */
public class RandomizedCollection {
    private List<Integer> vals;
    private Map<Integer, Set<Integer>> indexs;
    private int index;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        vals = new ArrayList<>();
        indexs = new HashMap<>();
        index = 0;
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        vals.add(val);
        //插入下标
        if(!indexs.containsKey(val)){
            indexs.put(val,new HashSet<>());
        }
        indexs.get(val).add(index ++);
        return indexs.get(val).size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        //找到最后一个删除的下标
        if(!indexs.containsKey(val) || indexs.get(val).size() == 0) return false;
        Set<Integer> is = indexs.get(val);
        //得到一个下标
        int randomIndex = is.iterator().next();
        is.remove(randomIndex);
        //用最后一个值覆盖他
        int last = vals.get(index - 1);
        vals.set(randomIndex,last);
        //如果要删除的下标不等于最后一个元素下标，需要修改最后元素的下标映射
        if(randomIndex != index - 1){
            //修改最后元素的下标映射
            Set<Integer> set = indexs.get(last);
            set.remove(index - 1);
            set.add(randomIndex);
        }

        //删除最后一个元素
        vals.remove(--index);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        if(index <= 0) throw new RuntimeException("空");
        int i = (int) Math.floor(Math.random() * index);
        return vals.get(i);
    }
}

