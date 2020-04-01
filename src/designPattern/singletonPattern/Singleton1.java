package designPattern.singletonPattern;

/**
 * @author - ZwZ
 * @date - 2020/2/10 - 21:46
 * @Description:饿汉式(静态变量)
 * 缺点：没有实现懒加载，可能造成内存浪费
 * 优点：在类加载时就完成了实例化，多线程环境下可以使用
 */
public class Singleton1 {
    //1.私有化构造器
    private Singleton1(){

    }
    //2.本类内部创建对象实例  这里也要定义成static  因为在静态方法中无法使用非静态的元素
    private final static Singleton1 instance = new Singleton1();
    //3.提供一个公有的静态方法,返回实例对象 已经私有化构造器，外部不能new对象，所以只能将此方法设置成静态
    public static Singleton1 getInstance(){
        return instance;
    }
}
