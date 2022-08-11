package designPattern.prototypePattern;

import java.io.*;

/**
 * @author - ZwZ
 * @date - 2020/2/12 - 16:57
 * @Description:通过流的方式（序列化和反序列化）进行深拷贝
 *
 * 要想实现对象的序列化，必须使得此对象实现Serializable
 * 因为实现了此接口之后对象会被编码，内容包括：类名，对象字段名和数值等等
 * 对象序列化:将内存中的对象写入磁盘文件-------ObjectOutputStream
 * 对象反序列化:将磁盘文件中的对象加载入内存---ObjectInputStream
 */
public class SheepDeepCopy2 implements Serializable,Cloneable {
    private String name;
    private Friend friend;

    @Override
    protected Object clone() {
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            //序列化
            oos = new ObjectOutputStream(bos);//创建写入指定位置：字节输出流所对应内存的对象输出流
            oos.writeObject(this);//将当前对象写入对象输出流所对应的字节输出流所对应的内存
            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());//toByteArray()创建一个新分配的大小等于当前输出流的byte[]并且将其内容复制到该数组中 从而确保接下来使用ObjectInputStream反序列化后的对象是一个新的对象
            ois = new ObjectInputStream(bis);//创建从指定InputStream 即bis对象读取信息的对象输入流
            SheepDeepCopy2 copy = (SheepDeepCopy2) ois.readObject();
            return copy;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            //关闭流
            try {
                bis.close();
                ois.close();
                bos.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
