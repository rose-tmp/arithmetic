package designPattern.singletonPattern;

/**
 * @author - ZwZ
 * @date - 2020/2/10 - 21:59
 * @Description:饿汉式（静态代码块）
 */
public class Singleton2 {
    //1.私有化构造器
    private Singleton2(){

    }
    //2.创建本类的静态引用并在静态代码块中领其指向对象
    private static Singleton2 instance;
    static {
        instance = new Singleton2();
    }
    //3.提供公有静态方法返回实例对象
    public static Singleton2 getInstance(){
        return instance;
    }
}
