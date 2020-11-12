package leetcode.dp;

/**
 * @author - ZwZ
 * @date - 2020/11/11 - 19:51
 * @Description:514. 自由之路
 * 电子游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
 * 给定一个字符串ring，表示刻在外环上的编码；给定另一个字符串key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 * 最初，ring的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使key的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完key中的所有字符。
 * 旋转ring拼出 key 字符key[i]的阶段中：
 * 您可以将ring顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串ring的一个字符与 12:00 方向对齐，并且这个字符必须等于字符key[i] 。
 * 如果字符key[i]已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作1 步。按完之后，您可以开始拼写key的下一个字符（下一阶段）, 直至完成所有拼写。
 * <p>
 * 示例：
 * 输入: ring = "godding", key = "gd"
 * 输出: 4
 * 解释:
 * 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
 * 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 * 当然, 我们还需要1步进行拼写。
 * 因此最终的输出是 4。
 * 提示：
 * ring 和key的字符串长度取值范围均为1至100；
 * 两个字符串中都只有小写字符，并且均可能存在重复字符；
 * 字符串key一定可以由字符串 ring旋转拼出。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/freedom-trail
 */

public class FindRotateSteps {
    /*class Node {
        public char ch;
        public Node pre;
        public Node next;

        public Node(Node pre, Node next, char ch) {
            this.ch = ch;
            this.pre = pre;
            this.next = next;
        }
    }

    *//**
     * 创建双循环链表的方式解决
     *//*
    public int findRotateSteps(String ring, String key) {
        if (ring.length() == 0 || ring == null) {
            return 0;
        }
        //链表头节点
        Node head = new Node(null, null, ring.charAt(0));
        Node pre = head;
        //创建双循环链表
        for (int i = 1; i < ring.length(); i++) {
            //创建当前节点
            Node temp = new Node(pre, null, ring.charAt(i));
            pre.next = temp;
            temp.pre = pre;
            pre = temp;
        }
        //连接首尾节点  此时pre指向了尾节点
        pre.next = head;
        head.pre = pre;

        //key所匹配的当前字符的下标
        int index = 0;
        //ring中处在12点位置的字符的下标
        Node node12 = head;
        //从当前节点向后遍历找到与key中字符匹配的元素时所走步数
        int nextNum = 0;
        //从当前节点向前遍历找到与key中字符匹配的元素时所走步数
        int preNum = 0;
        //遍历过程中所用节点
        Node node;
        //记录总步数
        int res = 0;
        boolean flag = false;
        while (index < key.length()) {
            //遍历过程中所用节点
            node = node12;
            //记录向后走匹配上所需步数
            while (node.ch != key.charAt(index)) {
                node = node.next;
                nextNum++;
            }
            //题目中说拼写即匹配时按下中心按钮的操作也算是一步
            nextNum++;
            //向后匹配成功时的节点
            Node nextEnd = node;
            //还原node位置
            node = node12;
            //记录向前走匹配上所需步数
            while (node.ch != key.charAt(index)) {
                node = node.pre;
                preNum++;
            }
            //题目中说拼写即匹配时按下中心按钮的操作也算是一步
            preNum++;
            //修改指向12点位置的ring中的元素
            if (preNum <= nextNum) {
                node12 = node;
                res += preNum;
            } else {
                node12 = nextEnd;
                res += nextNum;
            }
            index++;
        }
        return res;
    }*/
    public int findRotateSteps(String ring, String key) {
        return 0;
    }
}
