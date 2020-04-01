package zuoPrimary.hash;

import java.util.HashMap;

/**
 * @author - ZwZ
 * @date - 2020/3/26 - 15:01
 * @Description: 设计一个RandomPool结构，在此结构上有如下三种功能：
 * insert(key):将某个key加入到该结构，做到不重复加入
 * delete(key):将原来在结构中的某个key移除
 * getRandom():等概率随机返回结构中的任何一个key
 * 要求三个方法时间复杂度都是O(1)
 * 思想：此结构与hash表很像，但是如果用一张hash表去存储(key , null)的话，getRandom做不到O(1)
 * 因为当数据量比较小的时候，hash表上很多地方是空的
 */
public class RandomPool {
    public HashMap<String, Integer> map2;
    public HashMap<Integer, String> map1;
    public int size;

    public RandomPool() {
        this.map1 = new HashMap<>();
        this.map2 = new HashMap<>();
        this.size = 0;
    }

    public void insert(String str) {
        map1.put(size, str);
        map2.put(str, size++);
    }

    public String getRandom() {
        if (size == 0)
            return null;
        int index = (int) Math.random() * size;
        return map1.get(index);
    }

    /**
     *  
     *
     * @Author: ZwZ
     * @Description:因为存在getRandom，而为了防止delete后的hash表出现空洞 即某个下标上的数据为空 从而影响getRandom的效率 所以delete时，要将下标最后一个的数据向前补充删除的位置上的数据
     * @Param: [str] 
     * @return: void 
     * @Date: 2020/3/26-15:19
     */
    public void delete(String str) {


    }
}
