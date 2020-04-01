package day181128;

/**
 * @author - ZwZ
 * @date - 2018/11/28 - 21:54
 * @Description:使用java代码把十进制转化成二进制的三种方法
 */
public class DecimalToBinary {
    /**
     * 使用十进制的n除以2，把余数记下来
     * 再用商除以2
     * 依次循环直到商为0结束
     * 把余数倒着依次排列
     * 即为此二进制数*/
    public double method1(int n){
        int t = 0;//记录位数
        double bin = 0;//记录二进制数
        int r = 0;//用来存储余数
        while (n != 0){
            r = n % 2;
            n = n / 2;
            bin = bin + r*Math.pow(10,t);
            t++;
        }
        return bin;
    }
    //通过字符串对方法1进行改进
    public StringBuffer method2(int n){
        int r = 0;//用来存储余数
        StringBuffer bin = new StringBuffer();
        while (n != 0){
            r = n % 2;
            bin = bin.append(r).reverse();//追加后反转此字符串
            n = n / 2;
        }
        return bin;
    }
    //调用API函数
    public String method3(int n){
        String result = Integer.toBinaryString(n);
        return result;
    }
}
