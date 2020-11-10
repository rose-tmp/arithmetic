package leetcode.middling;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2020/11/9 - 9:31
 * @Description:973. 最接近原点的 K 个点
 * 我们有一个由平面上的点组成的列表 points。需要-从中找出 K 个距离原点 (0, 0) 最近的点。
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * <p>
 * 示例 1：
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * <p>
 * 示例 2：
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
 */
public class KClosest {
    /**
     * 思路：借助堆排序将前k个距离短的位置找出
     */
    public int[][] kClosest1(int[][] points, int K) {
        //记录每个位置距原点的距离(因为开不开根号最终的结果都是一样的，所以这里没有开根号)
        int[] distance = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            distance[i] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
        }
        //将distance中数据和下标的对应关系(也即points中的下标对应关系)存入map
        //value是一个list集合，因为对于一个距离有可能出现很多个点
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < distance.length; i++) {
            //包含则直接将下标加入已经存在的list中
            if (map.containsKey(distance[i])) {
                List<Integer> list = map.get(distance[i]);
                list.add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(distance[i], list);
            }
        }
        //建立小根堆
        List<Integer> list = heapSort(distance, K, map);
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i] = points[list.get(i)];
        }
        return res;
    }

    /**
     * 对distance数组进行堆排序 并将最终结果存储在返回集合中
     * list:存储了最终被选中的要作为kClosest方法的返回的points中对应的元素下标
     * map : 存储了距离和下标对应关系
     */
    public List<Integer> heapSort(int[] distance, int K, Map<Integer, List<Integer>> map) {
        List<Integer> list = new ArrayList<>();
        //建立小根堆
        for (int i = 0; i < distance.length; i++) {
            heapInsert(distance, i);
        }
        //构成大根堆的数组元素中最大的下标
        int heapMaxIndex = distance.length - 1;
        //用于存储某一个距离是否被处理过，如果处理过则跳过不再对其进行处理
        Map<Integer, Boolean> map2 = new HashMap<>();
        //遍历并记录堆中的前K个元素
        while (K > 0) {
            //从Map中得到存储了距离为distance[0]的元素的位置的集合
            List<Integer> temp = map.get(distance[0]);
            //之前没有处理过这个距离
            if (map2.get(distance[0]) == null) {
                //将集合中的元素下标记录在list中
                for (int i = 0; i < temp.size(); i++) {
                    list.add(temp.get(i));
                }
            }
            map2.put(distance[0], true);
            swap(distance, 0, heapMaxIndex);
            heapMaxIndex--;
            adjustDown(distance, 0, heapMaxIndex + 1);
            K--;
        }
        return list;
    }

    public void heapInsert(int[] distance, int index) {
        while (distance[index] < distance[(index - 1) / 2]) {
            swap(distance, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void adjustDown(int[] arr, int index, int heapSize) {
        int left = (index * 2) + 1;//左子节点的下标
        while (left < heapSize) {
            //当left + 1 > heapSize的时候min只能等于left
            int min = ((left + 1 < heapSize) && (arr[left] > arr[left + 1])) ? left + 1 : left;
            min = arr[min] > arr[index] ? index : min;
            if (min == index) {
                break;
            } else {
                swap(arr, min, index);
                index = min;
                left = (index * 2) + 1;
            }
        }
    }

    public void swap(int[] arr, int p1, int p2) {
        int temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }

    /**
     * 不自己建立堆
     * 而是使用JDK自带得优先队列(底层也是使用堆数据结构来实现的)完成
     */
    public int[][] kClosest2(int[][] points, int K) {
        /*
        * PriorityQueue默认的是根据自然顺序排序也就是从小到大，此时其建立的堆为小根堆
        * 如果我们想建立大根堆(这里我们要创建大根堆)，则要重写Comparator接口中的compare()让其o2 - o1
        * 可以查看PriorityQueue中siftUpUsingComparator方法得知原理(顺着offer方法去寻找此方法)
        * 这个不是什么玄学，只是在siftUpUsingComparator中判断的时候通过compare()返回值的正负写死了的
        * 顺便提一句：siftUpUsingComparator()就是在加入新元素时创建(更新)堆结构
        * */
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            //o1[0]与o2[0]表示的是距离   o[1]与o[2]表示的是此距离在points数组中对应的下标
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        //不管3721先将前K个距离加入优先队列
        for (int i = 0; i < K; i++) {
            queue.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
        }
        //更新优先队列中的元素，使其只保存距离前K小的元素
        for (int i = K; i < points.length; i++) {
            int distance = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            //当前distance小于前K小元素中最大的元素
            if (distance < queue.peek()[0]) {
                queue.poll();
                queue.offer(new int[]{distance, i});
            }
        }
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i] = points[queue.poll()[1]];
        }
        return res;
    }

    public static void main(String[] args) {
        KClosest kc = new KClosest();
        int[][] points = {{3, 3}, {5, -1}, {-2, 4}};
        kc.kClosest1(points, 2);
    }
}
