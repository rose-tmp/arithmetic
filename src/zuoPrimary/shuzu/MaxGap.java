package zuoPrimary.shuzu;

/**
 * @author - ZwZ
 * @date - 2020/3/23 - 21:05
 * @Description:给定一个数组 求排序之后相邻两个数的差值的最大值，要求时间复杂度是O(N)
 * 且不能使用基于比较的排序算法
 * 不基于比较的排序算法：桶排序(基数排序/计数排序都是桶排序思想的落地实现）
 * 思想：
 * 定义length+1个桶，将每个数均匀的存放在这些桶里 举例：数组中存储了N个数，最小值是0,最大值是99，那么定义N+1个桶，0-9存储在第一个桶，10-19存储在第二个桶...
 * 必定有一个桶是空的
 */
public class MaxGap {
    public int getMaxGap(int[] arr){
        if(arr == null || arr.length < 2)
            return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            min = arr[i] < min ? arr[i] : min;
            max = arr[i] > max ? arr[i] : max;
        }
        if(min == max)
            return 0;
        //必定会留出来一个或者多个空桶，以确保最大的差值不可能出现在同一个桶中两个数之间
        boolean[] hasNum = new boolean[arr.length + 1];//每个桶中是否加过元素
        int[] mins = new int[arr.length + 1];//每个桶中的最小值
        int[] maxs = new int[arr.length + 1];//每个桶中的最大值
        int index = 0;
        //为桶中对应数据赋值
        for (int i = 0; i < arr.length; i++) {
            index = ((arr[i] - min)*arr.length)/(max - min);//桶下标 （不明白为什么）
            mins[index] = hasNum[index] ? Math.min(mins[index],arr[i]) : arr[i];
            maxs[index] = hasNum[index] ? Math.max(maxs[index],arr[i]) : arr[i];
            hasNum[index] = true;
        }
        int result = 0;//最大的差值
        int lastmax = maxs[0];//上一个桶的最大值
        for (int i = 0; i < mins.length; i++) {
            if(hasNum[i]){
                result = Math.max(result,mins[i] - lastmax);
                lastmax = maxs[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MaxGap maxGap = new MaxGap();
        int[] arr = {1,10,4,2,8};
        System.out.println(maxGap.getMaxGap(arr));
    }
}
