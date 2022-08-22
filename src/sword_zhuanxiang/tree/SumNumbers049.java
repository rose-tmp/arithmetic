package sword_zhuanxiang.tree;

import tool.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2022/8/8-17:19 - 周一
 * @Description:剑指 Offer II 049. 从根节点到叶节点的路径数字之和
 * 给定一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * 叶节点 是指没有子节点的节点。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [1, 1000] 内
 * 0 <= Node.val <= 9
 * 树的深度不超过 10
 * <p>
 * 注意：本题与主站 129 题相同： https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
 */
public class SumNumbers049 {
    int res = 0;

    /**
     * 每个节点都有俩个选择:向它的左边走;向它的右边走
     * 所以可以用dfs
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, new ArrayList<>());
        return res;
    }

    /**
     * @param list 记录遍历过程中走过的节点
     * @param root 当前遍历到的节点
     */
    public void dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        /**
         * root为叶子节点
         * */
        if (root.left == null && root.right == null) {
            int curSum = 0;
            for (Integer i : list) {
                curSum = (curSum * 10) + i;
            }
            curSum = (curSum * 10) + root.val;
            res += curSum;
            return;
        }
        list.add(root.val);
        dfs(root.left, list);
        dfs(root.right, list);
        //左右都遍历完了之后返回到了这里,此时需要将root删除 然后继续向上返回
        list.remove(list.size() - 1);
    }
}
