package designPattern.prototypePattern;

/**
 * @author - ZwZ
 * @date - 2020/2/12 - 15:43
 * @Description:原型模式
 * 浅拷贝：将一个对象复制后，基本数据类型的变量都会重新创建，
 * 而引用类型，如某个数组或者某个类对象，则是指将该成员变量的引用值(内存地址)复制一份给新的对象
 */
public class SheepShallowCopy implements Cloneable {
    private String name;
    private int age;
    private String color;
    private Friend friend;
    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    //使用默认的克隆方法去克隆实例
    @Override
    protected SheepShallowCopy clone() throws CloneNotSupportedException {
        return (SheepShallowCopy)super.clone();
    }
}
