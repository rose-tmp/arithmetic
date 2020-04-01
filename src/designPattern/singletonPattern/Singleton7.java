package designPattern.singletonPattern;

/**
 * @author - ZwZ
 * @date - 2020/2/11 - 14:10
 * @Description:通过枚举完成单例模式
 * 优点：避免了多线程同步问题，而且可以防止反序列化重新创建新的对象(即前几种方法都是可以通过反射机制打破单例模式)
 *
 * 推荐使用
 */
public enum Singleton7 {
    INSTANCE;
    //定义属性和方法这一块和类没有什么区别
    public String str;
    public void method1(){}
    public void method2(){}
}
