package designPattern.singletonPattern;

/**
 * @author - ZwZ
 * @date - 2020/2/11 - 17:16
 * @Description:关于内部类的Demo
 * 内部类可以访问外部类的成员包括私有成员
 * 但是，静态内部类只能访问外部类的静态成员 包括静态变量和静态方法
 * 成员内部类即可以访问外部类的静态成员也可以访问外部类的普通成员
 */
public class Demo {
    //成员变量
    private String name = "abc";
    private static int id = 110;
    //成员方法
    public void demoMethod1(){
        System.out.println("外部类的成员方法");
        //局部内部类  只可以在相对应的成员方法中使用
        class inDemo{
            public void inDemoMethod(){
                System.out.println("局部内部类的成员方法");
            }
        }

    }
    //静态成员方法
    public static void demoMethod2(){
        System.out.println("外部类的静态成员方法");
    }
    //成员内部类
    class InDemo{
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
    public static void main(String[] args) throws ClassNotFoundException {
        //实例化成员内部类（要通过外部类的对象去造对象）
        Demo.InDemo inDemo = new Demo().new InDemo();
        //实例化静态内部类
        Demo.InDemo2 inDemo2 = new InDemo2();
    }
}
