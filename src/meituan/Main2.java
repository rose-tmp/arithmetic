package meituan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
/**
 * @author - ZwZ
 * @date - 2020/3/12 - 20:07
 * @Description:
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = 0;//道具数量
        int count = 0;//背包容量
        int[] height = null ;//道具重量
        int[] money = null;//道具价值
        HashMap<Integer,Integer> map = new HashMap();
        for (int i = 0; i < 4; i++) {
            String line = scanner.nextLine();
            if(i == 0) {
                number = Integer.parseInt(line);
                continue;
            }
            if(i == 1) {
                count = Integer.parseInt(line);
                continue;
            }
            if(i == 2){
                String[] heightTemp = line.split(" ");
                height = new int[heightTemp.length];
                for (int j = 0; j < heightTemp.length; j++) {
                    height[j] = Integer.parseInt(heightTemp[j]);
                }
                continue;
            }
            if(i == 3){
                String[] moneyTemp = line.split(" ");
                money = new int[moneyTemp.length];
                for (int j = 0; j < moneyTemp.length; j++) {
                    money[j] = Integer.parseInt(moneyTemp[j]);
                }
                continue;
            }
        }
        if(number <= 0 || height.length != money.length)
            return;
        for (int i = 0; i < height.length; i++) {
            map.put(money[i],height[i]);
        }
        Arrays.sort(money);
        int sumHeight = 0;
        int moneyHeight = 0;
        for (int i = map.size() - 1; i >= 0; i--) {
            if(sumHeight < count){
                sumHeight = sumHeight + map.get(money[i]);
                moneyHeight += money[i];
            }
        }
        System.out.println(moneyHeight);
    }
}
