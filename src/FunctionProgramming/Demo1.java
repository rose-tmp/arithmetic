package FunctionProgramming;

/**
 * @author - ZwZ
 * @date - 2020/1/21 - 20:01
 * @Description:
 */
public class Demo1 {
    public static void show(MyFunctionInterface mF){
        mF.method();
    }
    public static void main(String[] args) {
        //show(() -> System.out.println("lambda执行了...."));

        MyFunctionInterface fI = new MyFunctionInterface() {
            @Override
            public void method() {
                System.out.println("刚刚lambda一行代码完成的事情，我要这么多行");
            }
        };
        show(fI);
    }
}

