package zuoPrimary.shu;

/**
 * @author - ZwZ
 * @date - 2020/3/25 - 21:57
 * @Description:判断是否为二叉搜索树
 */
public class IsBST {

    /** 
    * @Author: ZwZ
    * @Description:在中序遍历的基础上 定义有一个pre指向上次遍历的节点的value
     * 在中序遍历中打印节点的地方，判断node.value是否大于pre.value
     * 如果每一次都是，则是平衡二叉树。反之则不是 
    * @Param: [node] 
    * @return: boolean 
    * @Date: 2020/3/25-21:58
    */
    public boolean isBST(TreeNode node){

        return true;
    }
}
