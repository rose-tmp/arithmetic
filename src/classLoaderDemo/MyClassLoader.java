package classLoaderDemo;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author - ZwZ
 * @date - 2020/2/13 - 19:53
 * @Description:自己实现类加载器 按照ClassLoader类中的文档中写的Demo的流程去实现用户自定义类加载
 */
public class MyClassLoader extends ClassLoader {
    private final static String fileSuffixExt = ".class";//文件后缀
    private String classLoaderName;
    private String loadPath;

    public void setLoadPath(String loadPath) {
        this.loadPath = loadPath;
    }

    //指定当前类加载器的父类加载器
    public MyClassLoader(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    public MyClassLoader(String classLoaderName) {
        super();//使用appClassLoader作为此类加载器的父类加载器
        this.classLoaderName = classLoaderName;
    }

    /**
     * @Author: ZwZ
     * @Description:将文件变为byte数组
     * @Param: [name]  类的二进制名称
     * @return: byte[] 
     * @Date: 2020/2/13-20:53
     */
    private byte[] loadClassData(String name) {
        byte[] data = null;
        ByteOutputStream bos = null;//字节输出流
        InputStream is = null;
        try {
            name = name.replace(".", "\\");//将路径中的\\替换为.
            String fileName = loadPath + name + fileSuffixExt;
            File file = new File(fileName);
            is = new FileInputStream(file);//文件输入流
            bos = new ByteOutputStream();
            int ch;
            while (-1 != (ch = is.read())) {
                bos.write(ch);
            }
            data = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bos)
                    bos.close();
                if (null != is)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public Class findClass(String name) {
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);//defineClass属于本地方法 最终将我们得到的class文件所对应的二进制流写入到JVM内存的方法区中
    }
}
