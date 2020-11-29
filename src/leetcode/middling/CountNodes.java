package leetcode.middling;

import leetcode.tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2020/11/24 - 11:36
 * @Description:222.完全二叉树的节点个数
 * 要求时间复杂度低于O(N)【前中后遍历的时间复杂度都是O(N)】
 *
 * 高度为h的满二叉树的节点个数：2^h - 1
 */
public class CountNodes {
    /**
     * 思路：
     * 求出该树的深度h，(一直向左走，走到底)
     * 从根节点的右子树出发向左走，走到底的高度为h2
     * 如果h2 = h,那么根节点的左子树一定是满的，直接使用满二叉树节点个数的公式求解，然后再求右子树的高度(其就是一个子问题，所以可以用递归解决)
     * 如果h2 < h,那么根节点的右子树一定是满的，直接使用满二叉树节点个数公式求解，然后递归求左子树的节点个数
     * 时间复杂度：这种办法，每一层都只遍历一个节点，一共有logN层
     * 而每次遍历某个节点的时候都会沿着左节点走到底，所以整体的时间复杂度为：O((logN)^2)
     * (和zuoPrimary.shu.CompleteBinaryTreeNodeSum思路一模一样，只是它实现方式复杂 所以算法最重要的是学思想，然后自己去实现，每个人编程的功底以及对算法运行过程理解的不同，代码的逻辑都不尽相同，要在自己的编码风格下去实现一个算法思想  才算是真的get)
     * */
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        return count(root);
    }
    public int count(TreeNode root){
        if(root == null){
            return 0;
        }
        int high = getHigh(root);
        int highR = getHigh(root.right) + 1;//加1加的是root的那个高度  为了if(highR == high)成立   当然也可以不加，然后if中修改成(highR + 1 == high)
        //此时根节点的左子树为满二叉树
        if(highR == high){
            return (1 << (high - 1)) + count(root.right);//1 << (high - 1) - 1 + 1   其中1 << (high - 1) - 1是root的左子树的节点个数 1则为root
        }
        //右子树为满二叉树
        else{
            return (1 << (highR - 1)) + count(root.left); //1 << (highR - 1) - 1 + 1
        }
    }
    /**
     * 得到树高度
     */
    public int getHigh(TreeNode root) {
        int high = 0;
        while(root != null){
            high++;
            root = root.left;
        }
        return high;
    }

    public static void main(String[] args) {
        CountNodes count = new CountNodes();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        count.countNodes(root);
    }
}
