package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author - ZwZ
 * @date - 2020/1/21 - 16:44
 * @Description:创建Stream的方式
 *
 * Stream就相当于JDK1.8给提供的便捷方法用于处理数据
 */
public class Demo1 {
    //创建Stream的方式
    public void test1(){
        //通过Collection系列集合提供的stream()或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //通过Arrays中的静态方法stream()获取数组流
        Integer[] array = new Integer[10];
        Stream<Integer> stream2 = Arrays.stream(array);

        //通过Stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("ZwZ");

        //创建无限流
        Stream<Integer> stream4 = Stream.iterate(0,(x) -> x + 2);
    }
}
