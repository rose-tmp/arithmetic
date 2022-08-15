package tree;

import tool.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author - ZwZ
 * @date - 2022/4/9-15:07 - 周六
 * @Description:剑指 Offer II 056. 二叉搜索树中两个节点之和
 * 给定一个二叉搜索树的 根节点 root 和一个整数 k , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 k 。假设二叉搜索树中节点的值均唯一。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: root = [8,6,10,5,7,9,11], k = 12
 * 输出: true
 * 解释: 节点 5 和节点 7 之和等于 12
 * 示例 2：
 * <p>
 * 输入: root = [8,6,10,5,7,9,11], k = 22
 * 输出: false
 * 解释: 不存在两个节点值之和为 22 的节点
 *  
 * <p>
 * 提示：
 * <p>
 * 二叉树的节点个数的范围是  [1, 104].
 * -104 <= Node.val <= 104
 * root 为二叉搜索树
 * -105 <= k <= 105
 *  
 * <p>
 * 注意：本题与主站 653 题相同： https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/opLdQZ
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindTarget653 {
    Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        fuck(root);
        return preOrder(root, k);
    }

    public boolean preOrder(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        //(k - root.val) != k / 2防止一个值记录两次
        if (set.contains(k - root.val) && (k - root.val) != k / 2) {
            return true;
        }
        return preOrder(root.left, k) || preOrder(root.right, k);
    }

    public void fuck(TreeNode root) {
        if (root == null) {
            return;
        }
        set.add(root.val);
        fuck(root.left);
        fuck(root.right);
    }
}