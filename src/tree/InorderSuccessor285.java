package tree;

import tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2022/4/8-23:40 - 周五
 * @Description:剑指 Offer II 053. 二叉搜索树中的中序后继
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 * 节点p的后继是值比p.val大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。
 * <p>
 * 示例 1：
 * 输入：root = [2,1,3], p = 1
 * 输出：2
 * 解释：这里 1 的中序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
 * <p>
 * 示例2：
 * 输入：root = [5,3,6,2,4,null,null,1], p = 6
 * 输出：null
 * 解释：因为给出的节点没有中序后继，所以答案就返回 null 了。
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [1, 104] 内。
 * -105 <= Node.val <= 105
 * 树中各节点的值均保证唯一。
 * <p>
 * 注意：本题与主站 285题相同：https://leetcode-cn.com/problems/inorder-successor-in-bst/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/P5rCT8
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InorderSuccessor285 {
    /**
     * 遍历树，若当前遍历节点的值大于目标节点
     * 则他有可能是目标节点的中序后继，至于是不是还要看当前节点是不是有左孩子
     * 若当前遍历节点的值小于目标节点
     * 则它绝对不是目标节点的中序后继，所以要右走遍历更大值的节点
     * */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        TreeNode node = root;
        TreeNode res = null;
        while (node != null) {
            if (node.val > p.val) {
                res = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return res;
    }
    /**
     * (该思路没通过)
     * 思路:在二叉搜索树中某节点的中序后继节点就是他的右子节点为根节点的子树上的最左节点
     * 当其右子节点为空且其为父节点的左子节点时,则中序后继节点为其父节点
     * 当其右子节点为空且其为父节点的右子节点时，则其中序后继节点为其父节点的父节点
     */
    /*public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (root == p) {
            return null;
        }
        java.util.Stack<TreeNode> stack = getStack(root, p);
        stack.pop();
        boolean flag = false;
        //p为父节点的右孩子
        if (stack.peek().right == p) {
            flag = true;
        }
        return fuck(p, flag, stack);
    }
    *//**
     * 得到从根节点开始，到当前节点的路径
     * *//*
    public java.util.Stack<TreeNode> getStack(TreeNode root, TreeNode node) {
        java.util.Stack<TreeNode> stack = new java.util.Stack<>();
        TreeNode n = root;
        while (!stack.isEmpty() || n != null) {
            while (n != null) {
                stack.push(n);
                if (n == node) {
                    return stack;
                }
                n = n.left;
            }
            n = stack.pop();
            //如果把根节点弹出了，要再加进去
            if (n == root) {
                stack.push(n);
            }
            n = n.right;
        }
        return stack;
    }

    *//**
     * 寻找某节点的中序后继节点
     *
     * @param flag  是否为其父节点的右子节点
     * @param stack 存储从整个树的根节点出发，遍历到当前节点root为止，所经过的节点:方便寻找其父节点和父节点的父节点
     *//*
    public TreeNode fuck(TreeNode root, boolean flag, java.util.Stack<TreeNode> stack) {
        if (root == null) {
            return null;
        }
        if (root.right != null) {
            return getLeft(root.right);
        } else {
            //父节点
            TreeNode node = stack.pop();
            //其右子节点为空且其为父节点的右子节点
            if (flag) {
                if (stack.isEmpty()) {
                    return null;
                }
                return stack.peek();
            } else {
                return node;
            }
        }

    }

    */

    /***
     * @return: 某节点为根节点的树中的左子树上的最左节点
     *//*
    public TreeNode getLeft(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root;
        }
        return getLeft(root.left);
    }*/
    public static void main(String[] args) {
        InorderSuccessor285 inorderSuccessor285 = new InorderSuccessor285();
        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(inorderSuccessor285.inorderSuccessor(root, root).val);
        ;
    }
}
