package lambda;

/**
 * @author - ZwZ
 * @date - 2020/1/17 - 14:53
 * @Description:
 */
interface Action{
    void eat();
//   void sleep(int time);
}
class Child implements Action{

    @Override
    public void eat() {

    }
}
public class demo1 {
    //匿名内部类
    public void demo(){
        Action child = new Action() {
            @Override
            public void eat() {
                System.out.println("eat milk");
            }
            /*@Override
            public void sleep(int time) {
                System.out.println("睡"+time+"小时");
            }*/
        };
    }
    //Lambda表达式 (需要接口的支持，而且接口中的抽象方法只能为一个，即其需要函数式接口的支持)
    public void demo2(){
        Action child = () -> System.out.println("eat milk");
        child.eat();
    }

    public static void main(String[] args) {
        demo1 demo = new demo1();
        demo.demo2();
    }
}
