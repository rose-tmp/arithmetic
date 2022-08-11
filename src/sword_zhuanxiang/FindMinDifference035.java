package sword_zhuanxiang;

import java.util.Collections;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2022/8/5-11:50 - 周五
 * @Description:
 */
public class FindMinDifference035 {
    /**
     * 排序后，最小时间间隔就只会出现在相邻的两个时间点中或者首尾时间点中
     * */
    public int findMinDifference(List<String> timePoints) {
        //如果时间出现的次数超过了1440，就一定有两个相同的时间点
        if (timePoints.size() > 1440) {
            return 0;
        }
        Collections.sort(timePoints);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < timePoints.size() - 1; i++) {
            res = Math.min(res, getMin(timePoints.get(i), timePoints.get(i + 1)));
        }
        //首尾时间差 防止出现["23:59","00:00"]
        res = Math.min(res, getMin(timePoints.get(0), timePoints.get(timePoints.size() - 1)));
        return res;
    }

    //t1代表的时间已经比t2小
    public int getMin(String t1, String t2) {
        int m1 = (((t1.charAt(0) - '0') * 10 + (t1.charAt(1) - '0')) * 60 +
                ((t1.charAt(3) - '0') * 10 + t1.charAt(4) - '0'));
        int m2 = (((t2.charAt(0) - '0') * 10 + (t2.charAt(1) - '0')) * 60 +
                ((t2.charAt(3) - '0') * 10 + t2.charAt(4) - '0'));
        int temp = m1 + 1440;
        return Math.min(m2 - m1, temp - m2);
    }
}
