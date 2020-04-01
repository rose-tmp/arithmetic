package nestedClass;

/**
 * @author - ZwZ
 * @date - 2020/2/11 - 17:16
 * @Description:关于内部类的Demo
 * 内部类可以访问外部类的成员包括私有成员
 * 但是，静态内部类只能访问外部类的静态成员 包括静态变量和静态方法
 * 成员内部类即可以访问外部类的静态成员也可以访问外部类的普通成员
 *
 * 外部类中访问内部类的成员必须创建内部类的对象
 */
public class Demo {
    //成员变量
    private String name = "abc";
    private static int id = 110;
    //成员方法
    public void demoMethod1(){
        /*此变量因为在局部内部类中使用，所以编译器会自动加上final关键字修饰
        * 因为new InDemo()后此对象存储在堆中，而局部变量存储在Java栈中demoMethod1()对应的方法栈帧中
        * 当该方法执行完毕之后，栈帧就会出栈，此时局部变量就会消失
        * 但是堆内存中的InDemo类的那个对象则是交给JVM的垃圾回收机制去处理
        * 假如说在栈帧出栈，局部变量消失之后，对象并没有消失，那么str就意味着已经不存在，编译器就会认为这是错误的
        * 加上final关键字之后，str就变成了常量
        * inDemoMethod()中的输出语句编译的时候就不再是："局部内部类的成员方法"+str
        * 而是"局部内部类的成员方法"+ "abc"
        * */
        String str = "abc";
        System.out.println("外部类的成员方法");
        //局部内部类  只可以在相对应的成员方法中使用
        class InDemo{
            public void inDemoMethod(){
                System.out.println("局部内部类的成员方法" + str);//在局部内部类中使用到了局部变量
            }
        }
        InDemo inDemo = new InDemo();
        inDemo.inDemoMethod();
        //在外部类中访问内部类必须创建对象
        InDemo1 demo = new InDemo1();
        demo.method1();
    }
    //静态成员方法
    public static void demoMethod2(){
        System.out.println("外部类的静态成员方法");
    }
    //成员内部类
    class InDemo1{
        public void method1(){
            demoMethod1();
            System.out.println(id + "成员内部类的方法");
        }
    }
    //静态内部类
    static class InDemo2{
        public void method1(){
            demoMethod2();
            System.out.println(id + "静态内部类的方法");
        }
    }
    public static void main(String[] args) {
        //实例化成员内部类（要通过外部类的对象去造对象）
        Demo.InDemo1 inDemo = new Demo().new InDemo1();
        //实例化静态内部类
        Demo.InDemo2 inDemo2 = new Demo.InDemo2();
    }
}
