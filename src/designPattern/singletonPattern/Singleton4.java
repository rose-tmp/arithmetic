package designPattern.singletonPattern;

/**
 * @author - ZwZ
 * @date - 2020/2/10 - 22:18
 * @Description:线程安全的懒汉式
 * 缺点：效率低
 */
public class Singleton4 {
    //1.私有化构造器
    private Singleton4(){}
    private static Singleton4 instance;
    //2.提供一个静态公有方法，当使用该方法的时候才去创建实例对象 并且上锁
    public synchronized static Singleton4 getInstance(){
        if(instance == null)
            instance = new Singleton4();
        return instance;
    }

}
