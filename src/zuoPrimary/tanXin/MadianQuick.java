package zuoPrimary.tanXin;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author - ZwZ
 * @date - 2020/3/27 - 22:58
 * @Description:在一个给定大小为N的数据流中随时取得中位数 中位数：按顺序排列的一组数据中居于中间位置的数 (偶数个位置的话为中间两个位置上数值的平均值)
 * 思想：用一个大根堆存放N/2个小的数  小根堆存放N/个大的数
 * 当N等于偶数时，大根堆和小根堆的根节点就各自存放中位数中的其中一个
 * 当N等于基数时，用大根堆的根节点存放中位数
 */
public class MadianQuick {
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();//小根堆
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - 01;
        }
    });//大根堆

    public void add(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.offer(num);
            return;
        }
        if(num <= maxHeap.peek()){
            maxHeap.offer(num);
        }else{
            if(minHeap.isEmpty()){
                minHeap.offer(num);
                return;
            }
            if(num < minHeap.peek()){
                maxHeap.offer(num);
            }else
                minHeap.offer(num);
        }
        modifyTwoHeapsSize();

    }
    public Integer getMedian() {
        int maxHeapSize = this.maxHeap.size();
        int minHeapSize = this.minHeap.size();
        if (maxHeapSize + minHeapSize == 0) {
            return null;
        }
        Integer maxHeapHead = this.maxHeap.peek();
        Integer minHeapHead = this.minHeap.peek();
        if (((maxHeapSize + minHeapSize) & 1) == 0) {
            return (maxHeapHead + minHeapHead) / 2;
        }
        return maxHeapSize > minHeapSize ? maxHeapHead : minHeapHead;
    }
    private void modifyTwoHeapsSize() {
        if (this.maxHeap.size() == this.minHeap.size() + 2) {
            this.minHeap.add(this.maxHeap.poll());
        }
        if (this.minHeap.size() == this.maxHeap.size() + 2) {
            this.maxHeap.add(this.minHeap.poll());
        }
    }
}
