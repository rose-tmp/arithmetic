package zuoPrimary.tanXin;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author - ZwZ
 * @date - 2020/3/28 - 14:22
 * @Description:一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目
 * 的宣讲。 给你每一个项目开始的时间和结束的时间(给你一个数
 * 组，里面 是一个个具体的项目)，你来安排宣讲的日程，要求会
 * 议室进行 的宣讲的场次最多。返回这个最多的宣讲场次。
 * 贪心策略：每一次寻找一个结束时间最早得项目去让他开宣讲会，开完之后再找一个结束时间最早得项目去开宣讲会...
 */
public class BestArrange {
    private static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    /** 
    * @Author: ZwZ
    * @Description:programs:项目 包含了项目的开始时间和结束时间  start:宣讲会开始时间 
    * @Param: [programs, start] 
    * @return: int 
    * @Date: 2020/3/28-15:17
    */
    public static int bestArrange(Program[] programs, int start) {
        Arrays.sort(programs, new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.end - o2.end;
            }
        });//按照end升序排列
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            //取出end最早并且start时间在整个宣讲会开始时间范围内的项目
            if (start <= programs[i].start) {
                result++;
                start = programs[i].end;//重复寻找start时间在整个宣讲会开始时间范围内并且end最早的项目
            }
        }
        return result;
    }

}
