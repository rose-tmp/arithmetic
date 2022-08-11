package designPattern.proxy;

/**
 * @author - ZwZ
 * @date - 2022/7/13-10:08 - 周三
 * @Description:
 */
interface Animal {
    void eat();
}

class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("eat...");
    }
}

//Dog的代理类
class DogProxy implements Animal {
    private Dog dog;

    //创建代理对象时传递进来要代理的对象
    public DogProxy(Dog dog) {
        this.dog = dog;
    }

    @Override
    public void eat() {
        //对代理对象做增强
        System.out.println("before eat...");
        dog.eat();
        //对代理对象做增强
        System.out.println("after eat...");
    }
}

public class StaticProxy {
    public static void main(String[] args) {
        DogProxy proxy = new DogProxy(new Dog());
        proxy.eat();
    }
}
