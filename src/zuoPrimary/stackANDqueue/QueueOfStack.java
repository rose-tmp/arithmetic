package zuoPrimary.stackANDqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author - ZwZ
 * @date - 2020/3/24 - 15:24
 * @Description:使用两个队列模拟一个栈
 */
public class QueueOfStack {
    private Queue<Integer> dataQueue = new LinkedList();
    private Queue<Integer> helpQueue = new LinkedList();
    //入栈
    public void push(int n){
        dataQueue.offer(n);
    }
    //出栈
    public int pop(){
        if(dataQueue.isEmpty())
            throw new ArrayIndexOutOfBoundsException("越界");
        //除去最后一个数据，其他数据转至另一个队列
        while(dataQueue.size() > 1){
            helpQueue.add(dataQueue.poll());
        }
        int result = dataQueue.poll();
        //两个队列身份交换
        Queue<Integer> temp = dataQueue;
        dataQueue = helpQueue;
        helpQueue = temp;
        return result;
    }
}
