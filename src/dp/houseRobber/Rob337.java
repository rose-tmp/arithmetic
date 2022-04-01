package dp.houseRobber;

import tool.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2022/4/1 7:55 下午
 * @Description:337. 打家劫舍 III
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为root。
 * 除了root之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * 给定二叉树的root。返回在不触动警报的情况下，小偷能够盗取的最高金额。
 * <p>
 * <p>
 * 示例 1:
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释:小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 * <p>
 * 示例 2:
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释:小偷一晚能够盗取的最高金额 4 + 5 = 9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Rob337 {
    //key:节点 value:以该节点为root节点 如果偷这家，所能获取的最高金额
    Map<TreeNode, Integer> map_fuck = new HashMap<>();
    //key:节点 value:以该节点为root节点 如果不偷这家，所能获取的最高金额
    Map<TreeNode, Integer> map_nofuck = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return Math.max(map_nofuck.get(root), map_fuck.get(root));
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        dfs(root.right);
        //类似递归式后序遍历 最后的时候再给map赋值 就可以做到自底向下赋值
        map_fuck.put(root, root.val + map_nofuck.getOrDefault(root.left, 0) + map_nofuck.getOrDefault(root.right, 0));
        map_nofuck.put(root, Math.max(map_fuck.getOrDefault(root.left, 0),map_nofuck.getOrDefault(root.left,0))
                + Math.max(map_fuck.getOrDefault(root.right, 0),map_nofuck.getOrDefault(root.right, 0)));
    }
}
