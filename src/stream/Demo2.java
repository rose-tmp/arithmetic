package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author - ZwZ
 * @date - 2020/1/21 - 17:09
 * @Description:Stream的中间操作
 */
public class Demo2 {
    //filter()
    public void filterDemo(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Stream stream = list.stream().filter(i -> i<5);//筛选集合中小于5的元素
        //终止操作，没有终止操作只有中间操作的话，由于Stream不存储数据，所以没有任何作用。只有在结果要是用的时候一次性执行出全部内容（惰性求值）
        stream.forEach(System.out::println);
    }
    //limit()
    public void limitDemo(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Stream stream = list.stream().filter(i -> i<5).limit(2);//筛选集合中小于5的元素并限制只筛选出2条元素
        //stream.forEach(System.out::println);//使用方法引用的方式输出  函数式接口是Lambda的基础，而方法引用是Lambda的孪生兄弟 就是只要可以使用Lambda的地方都可以使用方法引用，没有使用Lambda表达式就不能使用方法引用
        stream.forEach(x -> System.out.println(x));//与上一句等价也是JDK1.8的语法糖 泛式：类名::方法名
    }
    //map() 将Map()中接收的函数作为参数，该函数会被应用到每一个元素中，并将其映射成新元素
    public void mapDemo(){
        List<String> list = Arrays.asList("a","b","c");
        //list.stream().map(String::toUpperCase).forEach(x -> System.out.println(x));
        list.stream().map(str -> str.toUpperCase()).forEach(x -> System.out.println(x));
    }
    public static void main(String[] args) {
        Demo2 demo = new Demo2();
        demo.mapDemo();
    }

}
