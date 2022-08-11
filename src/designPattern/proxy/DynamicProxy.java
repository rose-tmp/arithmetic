package designPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author - ZwZ
 * @date - 2022/7/13-10:22 - 周三
 * @Description:
 */
class ProxyFactory {
    //目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //根据传入的对象利用反射机制返回一个代理对象
    public Object getInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before...");
                        //使用反射机制调用目标对象的方法
                        Object res = method.invoke(target, args);
                        System.out.println("after...");
                        return res;
                    }
                });
    }
}

public class DynamicProxy {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal dogProxy = (Animal) new ProxyFactory(dog).getInstance();
        dogProxy.eat();
    }
}
