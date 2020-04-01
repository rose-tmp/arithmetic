package FunctionProgramming;

/**
 * @author - ZwZ
 * @date - 2020/1/21 - 20:08
 * @Description:
 */
public class Demo2 {
    public int getSum(Sumable sum, int a,int b){
        return sum.sum(a,b);
    }

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        Demo2 demo = new Demo2();

        //System.out.println( demo.getSum((first,second)->first +second,a,b) );//函数式编程

        //如果不使用函数式编程
        System.out.println(demo.getSum(new Sumable() {
            @Override
            public int sum(int a, int b) {
                return a+b;
            }
        },a,b));
    }
}
