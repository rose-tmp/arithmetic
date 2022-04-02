package tree.FenwickTree;

/**
 * @author - ZwZ
 * @date - 2020/11/29 - 16:36
 * @Description:树状数组或二叉索引树（英语：Binary Indexed Tree），又以其发明者命名为Fenwick树
 * 现多用于高效计算数列的前缀和,区间和 (将时间复杂度从O(N)降到O(logN))
 * 但是同样伴随的是  单点更新的复杂度从O(1)升高到了O(logN)  不过总体的时间复杂度依然是降低的
 * <p>
 * 参考视频：
 * https://www.bilibili.com/video/BV1pE41197Qj?t=495
 * https://www.bilibili.com/video/BV1Hz411v7XC?p=2
 */
public class FenwickTree {
    //维护树状数组结构的数组
    private int[] tree;
    private int len;

    /**
     * 根据数组的大小初始化
     */
    public FenwickTree(int n) {
        this.len = n;
        this.tree = new int[n + 1];
    }

    public FenwickTree(int[] nums) {
        this.len = nums.length;
        this.tree = new int[this.len + 1];
        for (int i = 0; i < this.len; i++) {
            update(i, nums[i]);
        }
    }

    /**
     * 单点更新：将下标i中的元素值加delta
     */
    public void update(int i, int delta) {
        //从下到上更新
        while (i <= this.len) {
            tree[i] += delta;
            i += lowbit(i);
        }
    }

    /**
     * 查询前缀和[1,i]
     */
    public int query(int i) {
        //从右到左查询
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= lowbit(i);
        }
        return sum;
    }
    /**
     * lowbit用来计算 非负整数x在二进制表示下最低位1及后面的0构成的数值
     *
     * */
    private int lowbit(int x) {
        return x & (-x);
    }
}
