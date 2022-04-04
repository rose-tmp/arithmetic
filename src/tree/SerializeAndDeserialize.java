package tree;

import tool.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2022/4/4-20:50 - 周一
 * @Description:剑指 Offer II 048. 序列化与反序列化二叉树
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 * <p>
 * 输入：root = [1,2]
 * 输出：[1,2]
 * <p>
 * 提示：
 * <p>
 * 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，也可以采用其他的方法解决这个问题。
 * 树中结点数在范围 [0, 104] 内
 * -1000 <= Node.val <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/h54YBf
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/h54YBf
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SerializeAndDeserialize {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        fuck1(root, sb);
        return sb.toString();
    }

    //得到以root为根节点的树的序列化列表
    public void fuck1(TreeNode root, StringBuilder str) {
        if (root == null) {
            str.append("none,");
        } else {
            str.append(root.val).append(",");
            fuck1(root.left, str);
            fuck1(root.right, str);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> list = new ArrayList<>();
        String[] strArr = data.split(",");
        for (int i = 0; i < strArr.length; i++) {
            list.add(strArr[i]);
        }
        return fuck2(list);
    }

    //反序列化List<String>为序列的,,,
    public TreeNode fuck2(List<String> list) {
        if (list.get(0).equals("none")) {
            list.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        root.left = fuck2(list);
        root.right = fuck2(list);
        return root;
    }

    public static void main(String[] args) {
        SerializeAndDeserialize sd = new SerializeAndDeserialize();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println(sd.serialize(root));
        //System.out.println(sd.deserialize(sd.serialize(root)));
    }
}
