package zuoPrimary.shu;

/**
 * @author - ZwZ
 * @date - 2020/3/25 - 22:46
 * @Description:完全二叉树结点个数 要求时间复杂度低于O(N)【所以不能用遍历的方式】
 * 高度为h的满二叉树的节点个数：2^h - 1
 * 思路：
 * 求出该树的深度h，(一直向左走，走到底)
 * 从该节点的右子树出发向左走，走到底的高度为h2
 * 如果h2 = h,那么该节点的左子树一定是满的，直接使用满二叉树节点个数的公式求解，然后再求右子树的高度(其就是一个子问题，所以可以用递归解决)
 * 如果h2 < h,那么该节点的右子树一定是满的，直接使用满二叉树节点个数公式求解，然后递归求左子树的节点个数
 *
 * 时间复杂度：这种办法，每一层都只遍历一个节点，一共有logN层
 * 而每次遍历某个节点的时候都会沿着左节点走到底，所以整体的时间复杂度为：O((logN)^2)
 */
public class CompleteBinaryTreeNodeSum {
    /**
     *  
     *
     * @Author: ZwZ
     * @Description:  
     * @Param: [node] 
     * @return: int 
     * @Date: 2020/3/26-11:30
     */
    public int nodeSum(TreeNode node) {
        if (node == null)
            return 0;
        return bs(node,1,getHigh(node,1));
    }

    /**
     *  
     *
     * @Author: ZwZ
     * @Description:level：当前节点所在的层数，high : 整棵树的高度(始终不变)
     * @Param: [node, level, high] 
     * @return: int 以node为根节点的子树一共有多少节点
     * @Date: 2020/3/26-11:42
     */
    private int bs(TreeNode node, int level, int high) {
        if (level == high)
            return 1;
        //右子树高度与左子树相等
        if (getHigh(node.right, level + 1) == high)
            return (1 << (high - level)) + bs(node.right, level + 1, high);
        else
            return (1 << (high - level - 1)) + bs(node.left, level + 1, high);
    }

    /**
     *  
     *
     * @Author: ZwZ
     * @Description:求以node为根节点的树的整体高度(相对于整棵树的根节点) level：node当前高度 
     * @Param: [node, level] 
     * @return: int 
     * @Date: 2020/3/26-11:51
     */
    private int getHigh(TreeNode node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        CompleteBinaryTreeNodeSum sum = new CompleteBinaryTreeNodeSum();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(6);
        root.left.left.left = new TreeNode(12);
        root.right = new TreeNode(5) ;
        root.right.right = new TreeNode(9);
        root.right.right.left = new TreeNode(7);
        root.right.right.left.right = new TreeNode(4);
        System.out.println(sum.nodeSum(root));
    }
}
