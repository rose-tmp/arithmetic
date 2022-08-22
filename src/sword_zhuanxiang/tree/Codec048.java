package sword_zhuanxiang.tree;

import tool.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2022/8/8-16:17 - 周一
 * @Description:剑指 Offer II 048. 序列化与反序列化二叉树
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。
 * 这里不限定你的序列 / 反序列化算法执行逻辑，
 * 只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 * <p>
 * 输入：root = [1,2]
 * 输出：[1,2]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
 * 你并非必须采取这种方式，也可以采用其他的方法解决这个问题。
 * 树中结点数在范围 [0, 104] 内
 * -1000 <= Node.val <= 1000
 * <p>
 * <p>
 * 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 */
public class Codec048 {
    /**
     * 在二叉树 先序遍历 的过程中去记录序列化后的字符串
     * 空节点表示为none
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            sb.append("none,");
            return sb.toString();
        } else {
            sb.append(root.val + ",");
        }
        sb.append(serialize(root.left));
        sb.append(serialize(root.right));
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        String[] dArr = data.split(",");
        List<String> list = new ArrayList<>(Arrays.asList(dArr));
        return deserializeFuck(list);
    }

    /**
     * @param list 序列化的字符串
     * @return 当前序列化字符串反序列化后的根节点
     */
    public TreeNode deserializeFuck(List<String> list) {
        /**
         * 1. list.size() == 0 : 当前串为空，意味着没有节点
         * 2. list.get(0).equals("none"):当前序列化字符串的根节点为空
         * */
        if (list.size() == 0 || list.get(0).equals("none")) {
            list.remove(0);
            return null;
        }
        /*
         * 因为序列化的时候是用的先序遍历,所以list中0位置的元素一定是root
         * */
        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        root.left = deserializeFuck(list);
        root.right = deserializeFuck(list);
        return root;
    }
}
