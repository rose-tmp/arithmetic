package day181207;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - zwz
 * @date - 18-12-7-下午7:42
 * @Description:剑指offer面试题3/题目一
 */
public class FindNumber {
    public int[] find1(int data[]) {
        int num;
        int top = 0;
        int[] reslut = new int[data.length / 2];
        for (int i = 0; i < data.length; i++) {
            num = data[i];
            for (int j = i + 1; j < data.length; j++) {
                if (num == data[j]) {
                    reslut[top] = num;
                    top++;
                }
            }
        }
        return reslut;
    }

    public int find2(int[] data) {
        //判断数组是否为空
        if (data.length == 0) {
            return 0;
        }
        //判断数组中数据是否在要求范围
        for (int i = 0; i < data.length; i++) {
            if (data[i] > data.length - 1) {
                return 0;
            }
        }
        //判断重复元素
        for (int i = 0; i < data.length; i++) {
            while (data[i] != i) {
                if (data[data[i]] == data[i]) {
                    return data[i];
                }
                int n = data[data[i]];
                data[data[i]] = data[i];
                data[i] = n;
            }
        }
        //没有找到重复元素
        return 0;
    }
    //使用哈希表
    public int find3(int[] data) {
        //判断数组是否为空
        if (data.length == 0) {
            return 0;
        }
        //判断数组中数据是否在要求范围
        for (int i = 0; i < data.length; i++) {
            if (data[i] > data.length - 1) {
                return 0;
            }
        }
        //判断重复元素
        Map map = new HashMap();
        for (int i = 0; i < data.length; i++) {
            if(map.containsValue(data[i])){
                return data[i];
            }else{
                map.put(i,data[i]);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] data = new int[]{1, 5, 2, 4, 3, 5};
        FindNumber findNumber = new FindNumber();
        /*int[] result = findNumber.find1(data);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }*/
        System.out.println(findNumber.find3(data));
    }
}
