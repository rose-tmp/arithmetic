package designPattern.singletonPattern;

/**
 * @author - ZwZ
 * @date - 2020/2/11 - 13:42
 * @Description:通过内部类实现单例模式
 * 内部类和静态内部类在外部类加载的时候不会被加载
 * 只有在使用的时候才被加载 且加载类的过程可以确保线程之间的互斥
 * 即实现了懒加载又保证了线程安全，且只有第一次静态内部类加载的时候才会有线程之间的互斥也保证了效率
 *
 * 推荐使用
 */
public class Singleton6 {
    //1.私有化构造器
    private Singleton6(){}
    //2.创建静态内部类 作为实例化对象工厂
    private static class singletonFactory{
        private static final Singleton6 INSTANCE = new Singleton6();
    }

    public static Singleton6 getInstance(){
        return singletonFactory.INSTANCE;
    }
}
