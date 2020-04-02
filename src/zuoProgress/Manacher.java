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
     * @Description:暴力遍历 时间复杂度：O(N^2) 当字符串每个位置的值都相同时
     * @Param: [str] 
     * @return: java.lang.String 
     * @Date: 2020/4/2-13:55
     */
    public String force(String str) {
        if (str == null || str.length() == 1)
            return str;
        StringBuilder string = new StringBuilder(str);
        //补充# 使得偶回文和奇回文操作一致
        for (int i = 0; i <= string.length(); i += 2) {
            string.insert(i, '#');
        }
        //求以每一个位置为中心的最大回文数
        int pre = 0;
        int end = 0;
        int[] palindrome = new int[string.length()];
        palindrome[0] = 0;
        palindrome[string.length() - 1] = 0;
        //初始化回文数组
        for (int i = 1; i < string.length() - 1; i++) {
            pre = i - 1;
            end = i + 1;
            palindrome[i] = 1;//自身
            while (pre >= 0 && end <= string.length() - 1 && string.charAt(pre) == string.charAt(end)) {
                palindrome[i] += 2;
                pre--;
                end++;
            }
        }
        //寻找最大回文字串
        int max = 0;
        int center = 0;
        for (int i = 0; i < palindrome.length; i++) {
            if (palindrome[i] > max) {
                max = palindrome[i];
                center = i;
            }
        }
        String res = string.substring(center - (max - 1) / 2, center + (max - 1) / 2 + 1);
        return res.replaceAll("#", "");
    }
    /** 
    * @Author: ZwZ
    * @Description:马拉车算法
     * 返回最大回文串长度
     *添加#以及使用回文数组存储每一个位置的最大回文串长度，这些和暴力求解相同
     * 只是，求每一个位置的最大回文串长度的方法不同，不再是依次遍历 而是利用前面已经计算出来的结果对后面的结算进行加速
     * 当指针来到i位置，要求i位置的回文半径有多大时，三种情况：
     * 1.i不在最右回文半径中------->此时暴力向左右扩
     * 2.i在最右回文半径中:
     *      2.1 i关于回文中心c的对称点i`的回文范围完全在c的回文范围中，此时i的回文半径等于i`的回文半径 O(1)
     *      2.2 i`的回文范围已经超过了c的回文范围，此时i的回文半径就是R - i  O(1)
     *      2.3 i`的回文范围刚好落在了c的回文范围的左边界上，此时i的回文半径不能直接得出来，要向后试，试出来
     * 时间复杂度:O(N^2) 
    * @Param: [str] 
    * @return: java.lang.String 
    * @Date: 2020/4/2-14:45
    */
    public int manacher(String str) {
        if (str == null || str.length() == 1)
            return 0;
        StringBuilder stringBuilder = new StringBuilder(str);
        //补充# 使得偶回文和奇回文操作一致
        for (int i = 0; i <= stringBuilder.length(); i += 2) {
            stringBuilder.insert(i, '#');
        }
        int[] manacherArr = new int[stringBuilder.length()];//存储每一个位置的回文半径(和暴力解法中的数组一样概念)
        manacherArr[0] = 0;
        manacherArr[stringBuilder.length() - 1] = 0;
        int center = -1;//取得最右回文半径时此时的回文中心
        int right = -1;//最右回文半径 即所有已经计算出的回文半径中的最大值
        int max = Integer.MIN_VALUE;//记录当前最大    回文半径
        for (int i = 1; i < stringBuilder.length() - 1; i++) {
            //2.1 AND 2.2 当right <= i得时候将要暴扩，此时manacher[i] = 1即将自己算入到半径中去
            manacherArr[i] = right > i ? Math.min(manacherArr[2*center - i],right - i) : 1;
            //暴力扩(2.1 AND 2.2 暴扩的时候肯定不满足内层得if) i表示中心
            while(i + manacherArr[i] < stringBuilder.length() && i - manacherArr[i] > -1){
                if(stringBuilder.charAt(i + manacherArr[i])== stringBuilder.charAt(i - manacherArr[i]))
                    manacherArr[i]++;
                else
                    break;
            }
            //扩出得区域大于原区域 此时更新中心和右边界
            if(i + manacherArr[i] > right){
                right = i + manacherArr[i];
                center = i;
            }
            max = Math.max(max,manacherArr[i]);
        }
        return max - 1;//由于manacherArr[]记录得是半径，而因为源字符串加入了#，所以只需要减1而不需要/2
    }

    public static void main(String[] args) {
        Manacher manacher = new Manacher();
        System.out.println(manacher.manacher("abccbafds"));
    }

}
