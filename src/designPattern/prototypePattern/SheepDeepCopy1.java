package designPattern.prototypePattern;

/**
 * @author - ZwZ
 * @date - 2020/2/12 - 16:20
 * @Description:深拷贝 将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的
 * 简单来说，就是深复制进行了完全彻底的复制
 * 方式1：重写clone()实现深拷贝    即将引用数据类型单独处理
 */
public class SheepDeepCopy1 implements Cloneable {
    private String name;
    private Friend friend;

    @Override
    protected SheepDeepCopy1 clone() throws CloneNotSupportedException {
        SheepDeepCopy1 copySheep = (SheepDeepCopy1) super.clone();//完成对基本数据类型和String类型的克隆
        //对引用数据类型单独处理  即再克隆一份
        copySheep.friend = friend.clone();
        return copySheep;
    }
}
