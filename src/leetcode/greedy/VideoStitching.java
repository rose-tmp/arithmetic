package leetcode.greedy;

/**
 * @author - ZwZ
 * @date - 2020/10/24 - 16:09
 * @Description:1024. 视频拼接
 * <p>
 * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
 * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束
 * 我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）
 * 返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
 * <p>
 * 示例 1：
 * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * 输出：3
 * 解释：
 * 我们选中 [0,2], [8,10], [1,9] 这三个片段。
 * 然后，按下面的方案重制比赛片段：
 * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
 * 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
 * <p>
 * 示例 2：
 * 输入：clips = [[0,1],[1,2]], T = 5
 * 输出：-1
 * 解释：
 * 我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
 * <p>
 * 示例 3：
 * 输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
 * 输出：3
 * 解释：
 * 我们选取片段 [0,4], [4,7] 和 [6,9] 。
 * <p>
 * 示例 4：
 * 输入：clips = [[0,4],[2,8]], T = 5
 * 输出：2
 * 解释：
 * 注意，你可能录制超过比赛结束时间的视频。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/video-stitching
 */
public class VideoStitching {
    /*public int videoStitching(int[][] clips, int T) {
        int res = 0;
        if (clips == null || T <= 0) {
            return -1;
        }
        int max = Integer.MIN_VALUE;
        int temp = Integer.MIN_VALUE;
        boolean flag = false;
        //找到起始位置0 为max赋初值
        for (int i = 0; i < clips.length; i++) {
            if (clips[i][0] == 0) {
                max = Math.max(max, clips[i][1]);//记录此处可以向后扩展的最远下标
            }
            if(clips[i][1] >= T){
                flag = true;
            }
        }
        int count = clips.length;
        while (count > 0) {
            for (int i = 1; i < clips.length; i++) {
                if (clips[i][0] <= max && clips[i][1] > max) {
                    temp = Math.max(temp, clips[i][1]);
                    if (temp == clips[i][1]) {
                        res++;
                    }
                    max = temp;
                }
                if (max >= T) {
                    return res + 1;
                }
            }
            count--;
        }
        return -1;
    }*/
    public int videoStitching(int[][] clips, int T) {
        if (clips == null || T <= 0) {
            return -1;
        }
        int[] maxArr = new int[T];//记录每个下标对应的最右边的下标
        int max = Integer.MIN_VALUE;
        int res = 0;
        int preMax = 0;
        for (int i = 0; i < clips.length; i++) {
            if (clips[i][0] < T) {
                maxArr[clips[i][0]] = Math.max(maxArr[clips[i][0]], clips[i][1]);
            }
        }
        //遍历存储了每个下标对应的最右边的下标的数组
        for (int i = 0; i < T; i++) {
            max = Math.max(maxArr[i], max);
            if (max == i) {
                return -1;
            }
            if (i == preMax) {
                res++;
                preMax = max;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        VideoStitching video = new VideoStitching();
        int[][] clips = new int[6][2];
        //[[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]
        clips[0][0] = 0;
        clips[0][1] = 2;
        clips[1][0] = 4;
        clips[1][1] = 6;
        clips[2][0] = 8;
        clips[2][1] = 10;
        clips[3][0] = 1;
        clips[3][1] = 9;
        clips[4][0] = 1;
        clips[4][1] = 5;
        clips[5][0] = 5;
        clips[5][1] = 9;
        System.out.println(video.videoStitching(clips, 10));
    }
}
