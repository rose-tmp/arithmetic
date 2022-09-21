package writtenExam.wande;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-09-11 18:40
 * @desc:
 */
public class Main1 {
    static class TreeNode {
        int realVal;//该节点的节点值
        int score;//该节点的得分,即节点值+节点所在层数
        TreeNode left;
        TreeNode right;

        public TreeNode(int realVal, int score) {
            this.score = score;
            this.realVal = realVal;
        }
    }

    /**
     * 思路:
     * 1. 通过输入构建二叉树,其中二叉树每个节点记录自身节点值和该节点得分
     * 2. 依次从每个二叉树中的每个节点出发去寻找该子树上满足条件的路径并将其记录在res结果集合中
     * 3. 遍历res结果集合,将最终正确答案以给定格式输出至控制台
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int target = Integer.parseInt(sc.nextLine());
        String str = sc.nextLine();
        String[] sArr = str.split(" ");

        if (sArr.length == 0) {
            return;
        }

        int index = 1;//构建二叉树的过程中,所遍历到的节点在输入元素数组中的下标
        int level = 1;//当前遍历节点所处层数
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(sArr[0]), Integer.parseInt(sArr[0]));
        queue.add(root);

        /*
         * 根据输出构造二叉树
         * 其中每个节点的val为节点得分,即节点值+节点所在层
         * */
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                TreeNode l = null, r = null;
                //左节点初始化
                if (index != sArr.length) {
                    if (!sArr[index].equals("null")) {
                        int val = Integer.parseInt(sArr[index]);
                        l = new TreeNode(val, level + val);
                        queue.add(l);
                    }
                    index++;
                }
                //右节点初始化
                if (index != sArr.length) {
                    if (!sArr[index].equals("null")) {
                        int val = Integer.parseInt(sArr[index]);
                        r = new TreeNode(val, level + val);
                        queue.add(r);
                    }
                    index++;
                }

                cur.left = l;
                cur.right = r;
            }
            //层数自增
            level++;
        }

        //依次从每个二叉树中的每个节点出发去寻找该子树上满足条件的路径
        getRes(root, target);

        //遍历res结果集合,将最终正确答案以给定格式输出至控制台
        for (int i = 0; i < res.size(); i++) {
            List<TreeNode> cur = res.get(i);
            for (int j = 0; j < cur.size(); j++) {
                System.out.print(cur.get(j).realVal + " ");
            }
            System.out.println();
        }
    }

    /**
     * 从二叉树中每个节点出发，开始寻找满足条件的路径
     */
    private static void getRes(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        dfs(new ArrayList<>(), root, target);
        if (root.left != null) {
            dfs(new ArrayList<>(), root.left, target);
        }
        if (root.right != null) {
            dfs(new ArrayList<>(), root.right, target);
        }
    }

    private static List<List<TreeNode>> res = new ArrayList<>();

    /**
     * 以root为根节点出发，寻找该树中节点和为target的路径
     * 找到满足条件的路径后将其加入到最终结果res中
     */
    private static void dfs(List<TreeNode> list, TreeNode root, int target) {
        if (root == null) {
            return;
        }
        int sum = 0;
        for (TreeNode node : list) {
            sum += node.score;
        }
        //寻找到一条路径
        if (sum + root.score == target) {
            list.add(root);
            res.add(new ArrayList<>(list));
            return;
        }
        list.add(root);
        dfs(list, root.left, target);
        dfs(list, root.right, target);
        list.remove(list.size() - 1);
    }
}
