package zuoProgress;

/**
 * @author - ZwZ
 * @date - 2020/3/30 - 14:58
 * @Description:KMP算法：解决子串包含问题 如果str1中包含子串str2返回str2在str1中一开始出现时的下标即开始位置
 * 如果不存在返回-1
 * <p>
 * 暴力解决:每次两个指针分别指向两个字符串的开头，匹配上则str2的指针++，匹配不上则str2的指针回到0位置
 * 然后str1的指针++ 这种解决方式，相当于把str1的每一个位置上的字符都遍历了一遍
 * KMP算法就通过定义一个next数组存储每一个字符串的最长前缀（不能包含最后面那个位置 即要计算的位置的前一个位置）和最长后缀(不能包含第一个位置的值)的值，来避免了这一点
 * 至于为什么可以避免，可以用数学上的反证法
 */
public class KMP {
    public int getIndexOf(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < str2.length()) {
            return -1;
        }
        char[] str1Array = str1.toCharArray();
        char[] str2Array = str2.toCharArray();
        int p1 = 0;//str1的指针
        int p2 = 0;//str2的指针
        int[] next = getNextArray(str2Array);//存储str2每一位置的最长前缀和最长后缀的数组
        while (p1 < str1Array.length && p2 < str2Array.length) {
            //匹配上
            if (str1Array[p1] == str2Array[p2]) {
                p1++;
                p2++;
            } else if (next[p2] == -1) {
                p1++;//p2指针已经回到了0位置但是依旧匹配不上
            } else {//KMP算法的体现 即当匹配不上的时候，不是在str1的下一个位置重复匹配的过程，而是通过str2的匹配不上的当前字符的最长前缀和最长后缀值挑选出来一个位置匹配，因为通过反证法可以知道在选出来的这个位置之前一定不可能存在字符串可以匹配上str2
                p2 = next[p2];
            }
        }
        return p2 == str2Array.length ? p1 - p2 : -1;//当p2全部走完str2的时候说明匹配成功
    }

    private int[] getNextArray(char[] str2Array) {
        if(str2Array.length == 1)
            return new int[] {-1};//规定0位置的最长前后缀是-1
        int[] next = new int[str2Array.length];
        next[0] = -1;
        next[1] = 0;
        int p1 = 2;//指向所求最长前后缀的字符的下标
        int p2 = 0;//跳到的位置 即当前下标所指位置的前一个字符的最长前缀后面一个位置
        while(p1 < next.length){
            if(next[p1 - 1] == next[p2])
                next[p1++] = ++p2;//0--p2一共有p2+1个值 所以p1所在位置的最长前缀是p2+1
            else if(p2 > 0)//第一种情况不满足且还可以向前跳
                p2 = next[p2];
            else//已经跳到了第一个位置
                next[p1++] = 0;
        }
        return next;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP();
        String str1 = "a,b,c,d,e";
        String str2 = "b,c";
        System.out.println(kmp.getIndexOf(str1,str2));
    }
}
