package designPattern.prototypePattern;

/**
 * @author - ZwZ
 * @date - 2020/2/12 - 15:55
 * @Description:
 */
public class Demo {
    public void getSheep() throws CloneNotSupportedException {
        SheepShallowCopy sheepShallowCopy = new SheepShallowCopy();
        Friend friend = new Friend();
        friend.setName("wolf");
        sheepShallowCopy.setAge(21);
        sheepShallowCopy.setColor("红色");
        sheepShallowCopy.setName("sb");
        sheepShallowCopy.setFriend(friend);
        SheepShallowCopy sheepShallowCopyClone = sheepShallowCopy.clone();
        sheepShallowCopyClone.setName("2b");//不会影响到sheep原型的值
        sheepShallowCopyClone.getFriend().setName("sheepSister");//会影响到sheepClone中的Friend属性
        System.out.println(sheepShallowCopy.hashCode() +"---hashCode---" + sheepShallowCopyClone.hashCode());
        System.out.println(sheepShallowCopy.getName() + "----name---" + sheepShallowCopyClone.getName());
        System.out.println(sheepShallowCopy.getFriend().getName()+"---friend---" + sheepShallowCopyClone.getFriend().getName());

    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Demo demo = new Demo();
        demo.getSheep();
    }
}
