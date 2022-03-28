package dp;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author - ZwZ
 * @date - 2021/3/6 - 11:23
 * @Description:
 */
public class Demo {
    public static void main(String[] args) {
        //消息队列
        SyncStack ss = new SyncStack();
        Producer p = new Producer(ss);//新建一个生产者
        Consume c = new Consume(ss);//新建一个消费者

        Thread tp = new Thread(p);//新建一个生产者线程

        Thread tc1 = new Thread(c);//新建一个消费者线程1
        Thread tc2 = new Thread(c);//新建一个消费者线程2
        Thread tc3 = new Thread(c);//新建一个消费者线程3

        tp.start();//启动生产者线程
        tc1.start();//启动消费者线程1
        tc2.start();//启动消费者线程2
        tc3.start();//启动消费者线程3
    }

    //产品
    class product {
        int id;

        product(int id) {
            this.id = id;
        }

        public String toString() {
            return "steamBread:" + id;
        }
    }
}

//存储结构
class SyncStack {
    int index = 0;
    Demo.product[] stb = new Demo.product[100];

    //入栈
    public synchronized void push(Demo.product sb) {
        while (index == stb.length) {//栈满
            try {
                this.wait();//让当前线程等待
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.notify();//唤醒在此对象监视器上等待的单个线程，即消费者线程
        stb[index] = sb;
        this.index++;
    }

    //出栈
    public synchronized Demo.product pop() {
        while (index == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        this.index--;//push第n个之后，this.index++，使栈顶为n+1，故return之前要减一
        return stb[index];
    }
}

//生产者类，实现了Runnable接口，以便于构造生产者线程
class Producer implements Runnable {
    SyncStack ss = null;

    Producer(SyncStack ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, 10);// 设定指定的时间10毫秒
        try {
            Thread.sleep(2);//睡眠2秒
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

//消费者类，实现了Runnable接口，以便于构造消费者线程
class Consume implements Runnable {
    SyncStack ss = null;

    public Consume(SyncStack ss) {
        super();
        this.ss = ss;
    }

    @Override
    public void run() {
        Demo.product stb = ss.pop();
        System.out.println("消费了" + stb);
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
