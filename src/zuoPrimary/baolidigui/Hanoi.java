package zuoPrimary.baolidigui;

/**
 * @author - ZwZ
 * @date - 2020/3/28 - 21:48
 * @Description:汉诺塔问题，打印N层汉诺塔从最左边移动到最右边的过程
 * 汉诺塔问题：有三根杆(编号A、B、C)，在A杆自下而上、由大到小按顺序放置N个金盘，
 * 每次只能移动一个盘子，并且在移动过程中三根杆上都始终保持大盘在下，小盘在上，操作过程中盘子可以置于A、B、C任一杆上
 * 暴力递归：
 * 1.把问题转化为规模缩小了的同类子问题
 * 2.有明确的不需要继续进行递归的条件 即划分子问题的时候不可能一直无限的划分下去，要有个终止点，这个终止点也恰恰作为了递归的终止条件
 * 3.有当得到了子问题的结果之后的决策过程
 * 4.不记录每一个子问题的解
 */
public class Hanoi {
    /** 
    * @Author: ZwZ
    * @Description: 递归  分三步：
     * 1.将1--n-1个盘子从from移动到help
     * 2.将n从from移动到to
     * 3.将1--n-1从help移动到to
     * 而将1--n-1从from移动到help这个问题和1--n从from移动到to是相同的问题，前者是后者的子问题
     * 所以是可以用递归来完成的
    * @Param: [N, from, to, help] 将N个盘子从from移动到to  from to help都是棍子名字
    * @return: void 
    * @Date: 2020/3/28-22:03
    */
    public void hanoi(int N,String from,String to,String help){
        //终止条件
        if(N == 1)
            System.out.println("移动1从"+from+"到"+to);
        else {
            hanoi(N - 1,from,help,to);//将1--n-1从from移动到help落位，这时to和help就要更换位置
            System.out.println("移动"+N+"从"+from+"到"+to);
            hanoi(N-1,help,to,from);//将1--n-1从help落位到to上，而此时from就成了中间过渡的棍子
        }
    }

    public static void main(String[] args) {
        Hanoi hanoi = new Hanoi();
        hanoi.hanoi(3,"左","右","中");
    }
}
