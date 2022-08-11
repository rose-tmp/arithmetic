package sword_zhuanxiang.tree;

import tool.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author - ZwZ
 * @date - 2022/8/9-14:34 - 周二
 * @Description:
 */
public class FindTarget056 {
    /**
     *借助hash
     */
    Set<Integer> set = new HashSet<>();

    public boolean findTarget2(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return findTarget2(root.left, k) || findTarget2(root.right, k);
    }

    /**
     * @return: 以root为根节点的树中是否存在两个和为k的节点
     * 这样做不能通过全部实例，对于最终结果出现在一个节点的两个子节点上面的情况，是无法覆盖到的
     * 例如：[8,6,10,5,7,9,11] 12 应该返回true,该方法返回false 因为5,7都是6的子节点
     */
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        boolean res = false;
        res |= isExist(root.left, k - root.val);
        res |= isExist(root.right, k - root.val);

        res |= findTarget(root.left, k);
        res |= findTarget(root.right, k);

        return res;
    }

    /***
     * @return: 以root为根节点的树中是否存在值为val的结点
     */
    private boolean isExist(TreeNode root, int val) {
        if (root == null) {
            return false;
        }
        if (root.val == val) {
            return true;
        }
        return isExist(root.left, val) || isExist(root.right, val);
    }
}
