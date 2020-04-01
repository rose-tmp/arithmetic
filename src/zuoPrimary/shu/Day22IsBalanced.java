package zuoPrimary.shu;

/**
 * @author - ZwZ
 * @date - 2020/2/6 - 11:53
 * @Description:判断二叉树是否为平衡二叉树
 */
public class Day22IsBalanced {
    public boolean IsAVL(TreeNode root) {
        return getDepth(root) != -1;
    }
    /** 
    * @Author: ZwZ
    * @Description:递归求二叉树的高度，当其不是平衡二叉树时返回-1 (递归三部曲：递归出口/递归内容/递归返回值)
     * 二叉树相关问题很多都可以用递归去解决，因为递归有一个天然的优势：每个节点都会被访问三次，(一开始一次，左子树遍历完回到这个点一次，右子树遍历完回到这个点时一次)
     * 而判断二叉树是否为平衡二叉树只需要知道三点信息：
     *  对于某一个节点，其左子树是否为平衡树   其右子树是否为平衡树  以及左右子树的高度，
     *  如果左子树或者右子树不是平衡树，那么这个树就不是平衡树，如果它们都是平衡树，那么就要看它们之间的高度差是否相差不超过1
     *
    * @Param: [root] 
    * @return: int 
    * @Date: 2020/2/6-13:31
    */
    public static int getDepth(TreeNode root){

        //递归出口
        if(root == null)
            return 0;

        //递归内容
        int leftHigh = getDepth(root.left);
        //左子树不是平衡树
        /*if(leftHigh == -1)
            return -1;*/
        int rightHigh = getDepth(root.right);
        //右子树不是平衡树 (也可以用下面的if语句去判断左右子树是否为平衡树)
        /*if(rightHigh == -1)
            return -1;*/

        //递归返回值
        if(leftHigh - rightHigh > 1 || rightHigh - leftHigh > 1 || leftHigh == -1 || rightHigh == -1)
            return -1;//不是平衡树
        else
            return (leftHigh > rightHigh ? leftHigh : rightHigh) + 1;//是平衡树，返回高度
    }
    public static void main(String[] args) {
        Day22IsBalanced isBalanced = new Day22IsBalanced();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(6);
        root.left.left.left = new TreeNode(12);
        root.right = new TreeNode(5) ;
        root.right.right = new TreeNode(9);
        root.right.right.left = new TreeNode(7);
        root.right.right.left.right = new TreeNode(4);
        System.out.println(isBalanced.IsAVL(root));
    }
}
