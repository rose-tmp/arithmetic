package designPattern.singletonPattern;

/**
 * @author - ZwZ
 * @date - 2020/2/11 - 10:15
 * @Description:双重检查方式实现单例模式
 * 优点：可以做到线程安全并且效率较高，而且可以实现懒加载
 * 推荐使用
 */
public class Singleton5 {
    //1.私有化构造器
    private Singleton5(){}
    //2.使用valatile关键字(轻量级sync)使得instance发生改变时,立即从寄存器更新到主存,即强制从公共堆栈中取得变量值而不是在线程栈中 保证了可见性
    private static  volatile Singleton5 instance;
    public static Singleton5 getInstance(){
        if(instance == null){
            synchronized (Singleton5.class){
                if(instance == null)
                    instance = new Singleton5();
            }
        }
        return instance;
    }
}
