package day181122;

/**
 * //                       .::::.
 * //                     .::::::::.
 * //                    :::::::::::
 * //                 ..:::::::::::'
 * //              '::::::::::::'
 * //                .::::::::::
 * //           '::::::::::::::..
 * //                ..::::::::::::.
 * //              ``::::::::::::::::
 * //               ::::``:::::::::'        .:::.
 * //              ::::'   ':::::'       .::::::::.
 * //            .::::'      ::::     .:::::::'::::.
 * //           .:::'       :::::  .:::::::::' ':::::.
 * //          .::'        :::::.:::::::::'      ':::::.
 * //         .::'         ::::::::::::::'         ``::::.
 * //     ...:::           ::::::::::::'              ``::.
 * //    ```` '            ':::::::::'                  ::::..
 * //                       '.:::::'                    ':'````..
 *
 * @author - ZwZ呀咿呀咿哟
 * @date - 2018/11/22 - 16:04
 * @Description:判断一个数是不是回文数 与day181121整数算法有些类似
 * https://leetcode-cn.com/problems/palindrome-number/solution/
 */

public class Question1 {
    //未考虑int类型数值反转之后越界的情况
    public boolean isPalindrome1(int x) {
        //存储x数值，用于与m的比较
        int x0 = x;
        int m = 0;
        if (x < 0 || (x%10 == 0 && x != 0)) {
            return false;
        } else {
            while (x != 0) {
                m = m * 10 + x % 10;
                x = x / 10;
            }
            //0也是回文数
            if (m == x0 || x0 == 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    //考虑越界情况
    public boolean isPalindrome2(int x) {
        if (x < 0 || (x%10 == 0 && x != 0)) {
            return false;
        }
        int revertedNumber = 0;
        while(x >  revertedNumber){
            revertedNumber = revertedNumber * 10 + x%10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber/10;
    }

    public static void main(String[] args) {
        Question1 question1 = new Question1();
        System.out.println(question1.isPalindrome2(2147483647));
    }
}
