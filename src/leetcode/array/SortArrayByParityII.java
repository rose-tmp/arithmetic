package leetcode.array;

/**
 * @author - ZwZ
 * @date - 2020/11/12 - 10:48
 * @Description:922. 按奇偶排序数组 II
 * 给定一个非负整数数组A， A中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当A[i] 为奇数时，i也是奇数；当A[i]为偶数时， i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 * <p>
 * 示例：
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 */
public class SortArrayByParityII {
    /**
     * 当遇到下标为奇，数值为偶时，再后面找到一个下标为偶，数值为奇的与其交换
     * 反之亦然
     */
    public int[] sortArrayByParityII1(int[] A) {
        if (A.length < 2) {
            return A;
        }
        int len = A.length;
        for (int i = 0; i < len; i++) {
            //i为偶数 数值为奇数
            if (i % 2 == 0 && A[i] % 2 != 0) {
                //寻找下标为奇数 数值为偶数的位置并将数值互换
                for (int j = i + 1; j < len; j++) {
                    if (j % 2 != 0 && A[j] % 2 == 0) {
                        int temp = A[i];
                        A[i] = A[j];
                        A[j] = temp;
                        break;
                    }
                }
            }
            //i为奇数 数值为偶数
            if (i % 2 != 0 && A[i] % 2 == 0) {
                //寻找下标为偶数 数值为奇数的位置并将数值互换
                for (int j = i + 1; j < len; j++) {
                    if (j % 2 == 0 && A[j] % 2 != 0) {
                        int temp = A[i];
                        A[i] = A[j];
                        A[j] = temp;
                        break;
                    }
                }
            }
        }
        return A;
    }
}
