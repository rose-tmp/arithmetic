package zuoPrimary.baolidigui;

/**
 * @author - ZwZ
 * @date - 2020/3/29 - 11:28
 * @Description:母牛每年生一只母牛，新出生的母牛成长三年后也能每年生一只母牛，假设所有的母牛不死
 * 求N年后，母牛的数量
 * 思路：写出来前几年之后，就会发现一个规律：F(N) = F(N-1)+ F(N-3)
 * 即：今年的牛等于上一年留到今年的牛加上三年前的牛的数量(三年前的牛到了今年之后成熟了，可以生小牛了)
 */
public class Cow {
    public static int cowNumber(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return cowNumber(n - 1) + cowNumber(n - 3);
    }
}
