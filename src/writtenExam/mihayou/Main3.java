package writtenExam.mihayou;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2022/8/8-17:30 - 周一
 * @Description:米哈游，给树结点染色的问题，一开始没想出来，后来下来弄明白了。 问题：
 * 树（其实是无向图），的节点涂色，只能涂R,G,B三色。同时非叶子结点两旁一定有与它自己不同的两种颜色，而叶子结点则不需要考虑这个问题。
 * <p>
 * 比如，一个非叶子节点是R，跟它相连的至少有两个节点（我们假设有四个节点跟它相连），也就意味着，跟它相连的不仅有G，还有B（或许还有R，但是至少要满足G,和B才能谈别的）。
 * <p>
 * 输入，N，节点总数  接下来n-1行，为n-1条边，但是保证不会成环，而且是连通图。
 */
public class Main3 {
    public static void main(String[] args) {
        int n = 5;
        // int[][] adjcent = new int[n-1][2];
        int[][] adjcent = {{1, 3}, {3, 5}, {5, 4}, {2, 3}};
        List<List<Integer>> edgs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edgs.add(new ArrayList<>());
        }
        int[] color = new int[n]; // 用二进制来进行计算,这个用来记录，自己以及周围 节点已经选的颜色。
        char[] colorChar = new char[n];
        // 构建bfs
        int[] inde = new int[n];
        for (int i = 0; i < adjcent.length; i++) {
            int u = adjcent[i][0];
            int v = adjcent[i][1];
            edgs.get(u - 1).add(v - 1);
            edgs.get(v - 1).add(u - 1);
            inde[u - 1]++; //
            inde[v - 1]++;
        }
        // 因为是无向图，所以从哪一点出发都没问题，我们设置从0出发
        colorChar[0] = 'R';
        color[0] = 4;// 100
        LinkedList<Integer> queue = new LinkedList();
        queue.offer(0);
        boolean[] visited = new boolean[n];
        while (!queue.isEmpty()) {
            int len = queue.size();
            while (len-- > 0) {
                int cur = queue.poll();
                visited[cur] = true;
                List<Integer> edg = edgs.get(cur);
                for (int next : edg) {
                    if (visited[next]) {
                        continue;
                    }
                    colorChar[next] = find(color, cur, colorChar[cur]);
                    // 初始化color[next]，color[next]一开始只与它自己选了什么和它的上一个节点有关。
                    color[next] = check(colorChar[next]) | check(colorChar[cur]);
                    queue.offer(next);
                }
            }
        }
        for (char co : colorChar) {
            System.out.println(co);
        }
    }

    public static char find(int[] color, int index, char before) { // 这个before是来知道到底前一个是什么东西的
        int nxchoose = color[index] ^ 7; //与7异或，就知道要找哪一个了
        char chooseChar = 'R';
        int choice = 0;
        // 其实基本上只有这个地方能用到before，因为周围RGB颜色都没有，或者都选光的时候，特别是选光了的时候才需要去查看before是什么。
        if (nxchoose == 0 || nxchoose == 7) {
            // 那三个颜色都可以选；
            if (before != 'R') {
                chooseChar = 'R';
                choice = 4;
            } else if (before != 'G') {
                chooseChar = 'G';
                choice = 2;
            } else {
                chooseChar = 'B';
                choice = 1;
            }
        }
        if (nxchoose == 1) {
            chooseChar = 'B';
            choice = 1;
        }
        if (nxchoose == 2) {
            chooseChar = 'G';
            choice = 2;
        }
        if (nxchoose == 3) {
            if (before != 'G') {
                chooseChar = 'G';
                choice = 2;
            } else {
                chooseChar = 'B';
                choice = 1;
            }
        }
        if (nxchoose == 4) {
            if (before != 'R') {
                chooseChar = 'R';
                choice = 4;
            }
        }
        if (nxchoose == 5) {
            if (before != 'R') {
                chooseChar = 'R';
                choice = 4;
            } else {
                chooseChar = 'B';
                choice = 1;
            }
        }
        if (nxchoose == 6) {
            if (before != 'R') {
                chooseChar = 'R';
                choice = 4;
            } else {
                chooseChar = 'G';
                choice = 2;
            }
        }
        color[index] = color[index] | choice;  // 更新cur的color状态。 因为是添加的关系，所以这里使用的是或。
        return chooseChar;
    }

    public static int check(char clorChar) {
        switch (clorChar) {
            case 'R':
                return 4;
            case 'G':
                return 2;
            default:
                return 1;
        }
    }
}
