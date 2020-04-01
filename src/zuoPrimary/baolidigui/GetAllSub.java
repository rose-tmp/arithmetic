package zuoPrimary.baolidigui;

/**
 * @author - ZwZ
 * @date - 2020/3/28 - 22:31
 * @Description:打印一个字符串的所有子序列(不是子串)
 * 例如：abc
 * 打印：null a ab ac abc b bc c ...
 */
public class GetAllSub {
    /** 
    * @Author: ZwZ
    * @Description：每次来到一个位置上面临着两种选择：要此位置上的字符和不要 ,最终这个步骤画下来类似于一个满二叉树(要，不要)
     * 使用i表示来到了第几个字符
     * 使用res表示来到i这个位置之前，已经要了多少字符，即之前i - 1步留下了什么样的字符串 
     * 由于每一步走到i的位置时，都可以看做是求剩余字符串的子序列，所以这个小问题可以看做是和整个大问题相同的小问题
     *并且有递归终止条件，所以可以使用递归来完成
     *
     *
     * 递归代码关注的不应该是整个代码怎么跑的全部流程(如果想理解的话，画个栈去模拟系统栈会更好理解)
     * 而应该关注的是：大问题和子问题之间的关系，以及递归的终止条件
    * @Param: [str, i, res] 
    * @return: void 
    * @Date: 2020/3/28-22:34
    */
    public void getAllSub(char[] str,int i,String res){
        //说明此时已经走到了尽头，所以没有什么可以选择的了
        if(i == str.length){
            System.out.println(res);
            return;
        }else{
            getAllSub(str,i+1,res);//不要i位置的字符
            getAllSub(str,i+1,res+str[i]);//要i位置上的字符
        }
    }

    public static void main(String[] args) {
        GetAllSub getAllSub = new GetAllSub();
        char[] str = {'a','b','c'};
        getAllSub.getAllSub(str,0,"");
    }
}
