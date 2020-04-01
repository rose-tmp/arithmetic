package classLoaderDemo;

/**
 * @author - ZwZ
 * @date - 2020/2/13 - 21:08
 * @Description:
 */
public class Demo {
    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader("classLoader1");
        classLoader.setLoadPath("D:\\IntelliJ IDEA cunchu\\arithmetic\\out\\production\\arithmetic\\jianZhi\\");
        Class<?> clazz = classLoader.findClass("ListNode");
        System.out.println(clazz);
    }
}
