package middling;

/**
 * @author - ZwZ
 * @date - 2020/11/18 - 15:50
 * @Description:134. 加油站
 * 在一条环路上有N个加油站，其中第i个加油站有汽油gas[i]升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1个加油站需要消耗汽油cost[i]升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * <p>
 * 说明:
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * <p>
 * 示例1:
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * <p>
 * 示例 2:
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 * 输出: -1
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gas-station
 */
public class CanCompleteCircuit {
    /**
     * 寻找gas[i] > cost[i]的位置
     * 然后去试探是否可以返回到出发点
     * 如果可以则其为最终返回结果
     * 如果不可以则继续向后寻找满足gas[i] > cost[i]的位置 继续试探
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] >= cost[i]) {
                //起始位置
                int start = i;
                int count = gas[start] - cost[start];//汽车邮箱中剩余的油量
                int temp;
                if(start != gas.length - 1){
                    temp = start + 1;
                }else{
                    temp = 0;
                }
                while(temp != start){
                    count += gas[temp] - cost[temp];//将车从i开到i + 1位置后油箱内的油量
                    //证明此时车从i开不到i + 1位置 要继续寻找下一个开始点
                    if(count < 0){
                        break;
                    }
                    if(temp == gas.length - 1){
                        temp = 0;
                    }else{
                        temp++;
                    }
                }
                if(temp == start){
                    return start;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        CanCompleteCircuit can = new CanCompleteCircuit();
        int[] gas = new int[]{5,1,2,3,4};
        int[] cost = new int[]{4,4,1,5,1};
        System.out.println(can.canCompleteCircuit(gas,cost));;
    }
}
