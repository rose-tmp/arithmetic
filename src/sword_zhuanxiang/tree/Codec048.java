package sword_zhuanxiang.tree;

import tool.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2022/8/8-16:17 - 周一
 * @Description:
 */
public class Codec048 {
    // Encodes a tree to a single string.
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

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dArr = data.split(",");
        List<String> list = new ArrayList<>(Arrays.asList(dArr));
        return deserializeFuck(list);
    }

    public TreeNode deserializeFuck(List<String> list) {
        if (list.size() == 0 || list.get(0).equals("none")) {
            list.remove(0);
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        node.left = deserializeFuck(list);
        node.right = deserializeFuck(list);
        return node;
    }
}
