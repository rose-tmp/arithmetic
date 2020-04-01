package designPattern.singletonPattern;

/**
 * @author - ZwZ
 * @date - 2020/2/10 - 22:10
 * @Description:线程不安全的懒汉式
 * 如果在多线程环境下，thread_1进入到 if判断语句中判断instance==null为true
 * 此时，thread_2进来执行，此时if判断语句中instance==null也是为true,然后thread_2创建了一个实例对象
 * 接下来thread_1拿到CPU之后，继续向下执行，也会创建一个实例对象
 * 从而就打破了单例模式有且只有一个实例对象的原则
 */
public class Singleton3 {
    //1.私有化构造器
    private Singleton3(){}
    private static Singleton3 instance;
    //2.提供一个静态公有方法，当使用该方法的时候才去创建实例对象
    public static Singleton3 getInstance(){
        //确保有且仅有一个实例对象
        if(instance == null)
            instance = new Singleton3();
        return instance;
    }
}
