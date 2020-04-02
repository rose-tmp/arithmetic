package zuoProgress;

/**
 * @author - ZwZ
 * @date - 2020/3/31 - 13:23
 * @Description:Manacher 马拉车算法
 * 给定一个字符串，求出其最长回文子串
 */
public class Manacher {
    /** 
    * @Author: ZwZ
    * @Description: 
    * @Param: [str] 
    * @return: java.lang.String 
    * @Date: 2020/4/2-13:55
    */
    public String force(String str){
        if(str == null)
            return null;
        StringBuilder string = new StringBuilder(str);
        //补充# 使得偶回文和奇回文操作一致
        for (int i = 0; i <= string.length(); i += 2) {
            string.insert(i,'#');
        }
        //求以每一个位置为中心的最大回文数
        int pre = 0;
        int end = 0;
        int
        for (int i = 0; i < string.length(); i++) {

        }
        return string.toString();
    }
    public static void main(String[] args) {
        Manacher manacher = new Manacher();
        System.out.println(manacher.force("abccba"));
    }

}
