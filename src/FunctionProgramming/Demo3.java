package FunctionProgramming;

/**
 * @author - ZwZ
 * @date - 2020/1/21 - 20:25
 * @Description:方法引用实例
 */
public class Demo3 {
    public static void main(String[] args) {
        Demo3 demo = new Demo3();
        Print print = new Print();
        demo.printUpperCase("nba",print::printUpper);//使用方法引用来代替Lambda表达式进行函数式编程
        demo.printUpperCase("cba",str -> print.printUpper(str));
    }
    public void printUpperCase(String str ,PrintString pS){
        pS.print(str);
    }
}
