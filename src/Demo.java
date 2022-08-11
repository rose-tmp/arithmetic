import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2022/5/13-22:21 - 周五
 * @Description:
 */

class Node {
    int status;
    //children[]
    List<Node> list = new ArrayList<>();

    public Node(int status) {
        this.status = status;
    }
}

public class Demo {
    private int s = 0;

    /**
     * @return: 返回给定数组删除status=s后的数组
     */
    private List<Node> dfs2(List<Node> list) {
        List<Node> res = new ArrayList<>();
        /*
         * 递归终止条件：当这个数组为空了就返回
         * */
        if (list == null || list.size() == 0) {
            return res;
        }
        for (int i = 0; i < list.size(); i++) {
            //当前节点需要删除
            if (list.get(i).status == s) {
                res.addAll(dfs2(list.get(i).list));
            } else {
                res.add(list.get(i));
            }
        }
        return res;
    }

    /**
     * 删除当前节点下所有status的节点
     *
     * @Param: node:当前节点 status:
     * @Return:
     */
    private void dfs(Node node, int status) {
        if (node.list.size() == 0) {
            return;
        }
        int len = node.list.size();
        //状态
        int sum = 0;

        for (int i = 0; i < len; i++) {
            //当前节点需要删除
            if (node.list.get(i).status == status) {
                //将当前节点下的所有子节点赋值给父节点
                node.list.addAll(node.list.get(i).list);
                sum += node.list.get(i).list.size();
            }
            dfs(node.list.get(i), status);
            node.list.remove(i);
        }
        //遍历新加入的节点
        for (int i = len; i < len + sum; i++) {
            //当前节点需要删除
            if (node.list.get(i).status == status) {
                //将当前节点下的所有子节点赋值给父节点
                node.list.addAll(node.list.get(i).list);
                node.list.remove(i);
                sum += node.list.get(i).list.size();
            }
            dfs(node.list.get(i), status);
        }
    }

    public static void main(String[] args) {
        /*Demo demo = new Demo();
         *//*Node root = new Node(-1);
        root.list.add(new Node(0));
        root.list.add(new Node(4));
        root.list.add(new Node(5));
        root.list.add(new Node(6));
        root.list.get(0).list.add(new Node(1));
        root.list.get(0).list.add(new Node(0));
        root.list.get(0).list.add(new Node(2));
        root.list.get(0).list.add(new Node(4));
        demo.dfs(root, 0);
        for (int i = 0; i < root.list.size(); i++) {
            System.out.println(root.list.get(i).status);
        }*//*
        List<Node> list = new ArrayList<>();
        list.add(new Node(0));
        //list.add(new Node(0));
        list.get(0).list = new ArrayList<>();
        //list.get(0).list.add(new Node(2));
        list.get(0).list.add(new Node(0));
        list.get(0).list.get(0).list = new ArrayList<>();
        list.get(0).list.get(0).list.add(new Node(1));
        list.get(0).list.get(0).list.add(new Node(1));
        list.get(0).list.get(0).list.add(new Node(1));
        //list.get(0).list.add(new Node(3));
        //list.add(new Node(1));
        //list.add(new Node(3));
        //list.add(new Node(4));
        List<Node> res = demo.dfs2(list);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i).status);
        }*/


        Demo demo = new Demo();
        List<String> res = demo.restoreIpAddresses("25525511135");
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        dfs2(new ArrayList<>(), s, 0);
        return res;
    }

    public void dfs2(List<Integer> list, String s,int index) {
        if (list.size() == 4) {
            if (index == s.length()) {
                String temp = "";
                for (int i = 0; i < 4; i++) {
                    temp += (list.get(i) + ".");
                }
                temp = temp.substring(0, temp.length() - 1);
                res.add(temp);
            }
            return;
        }
        if (s.charAt(index) == '0') {
            list.add(0);
            dfs2(list, s, index + 1);
            list.remove(list.size() - 1);
        }
        int addr = 0;
        for (int i = index; i < s.length(); i++) {
            addr = addr * 10 + (s.charAt(i) - '0');
            if (addr <= 0 || addr > 0xFF) {
                break;
            }
            list.add(addr);
            dfs2(list, s, i + 1);
            list.remove(list.size() - 1);
        }
    }
}

