package leetcode.tool;

import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2020/12/5 - 18:20
 * @Description:遍历Map的方法
 */
public class ErgodicMap {
    public void demo(Map<Integer, Integer> map) {
        //遍历map
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
