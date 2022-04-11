package tree;

import tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2022/4/11-19:21 - 周一
 * @Description:701. 二叉搜索树中的插入操作
 * 给定二叉搜索树（BST）的根节点root和要插入树中的值value，将值插入二叉搜索树。
 * 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。你可以返回任意有效的结果 。
 * <p>
 * 示例 1：
 * 输入：root = [4,2,7,1,3], val = 5
 * 输出：[4,2,7,1,3,5]
 * 解释：另一个满足题目要求可以通过的树是：
 * <p>
 * 示例 2：
 * 输入：root = [40,20,60,10,30,50,70], val = 25
 * 输出：[40,20,60,10,30,50,70,null,null,25]
 * <p>
 * 示例 3：
 * 输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * 输出：[4,2,7,1,3,5]
 * <p>
 * 提示：
 * <p>
 * 树中的节点数将在[0,104]的范围内。
 * -108<= Node.val <= 108
 * 所有值Node.val是独一无二的。
 * -108 <= val <= 108
 * 保证 val 在原始BST中不存在。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InsertIntoBST701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        return fuck(root, val);
    }

    /**
     * 向根节点为root的树中插入值为val的新节点
     *
     * @return: root
     */
    public TreeNode fuck(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.val < val) {
            root.right = fuck(root.right, val);
        } else {
            root.left = fuck(root.left, val);
        }
        return root;
    }
}
