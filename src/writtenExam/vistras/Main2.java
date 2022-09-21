package writtenExam.vistras;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZwZ
 * @date: 2022-09-15 14:58
 * @desc:
 */
public class Main2 {
    /**
     * @return 所有完全对称日
     */
    public List<String> getPalDay() {
        List<String> res = new ArrayList<>();
        for (int mouth = 1; mouth <= 12; mouth++) {
            int days = getDay(mouth);
            for (int day = 1; day <= days; day++) {
                StringBuilder sb = new StringBuilder();
                //将月份加入结果中
                if (mouth < 10) {
                    sb.append(0).append(mouth);
                } else {
                    sb.append(mouth);
                }
                //将日期加入结果中
                if (day < 10) {
                    sb.append(0).append(day);
                } else {
                    sb.append(day);
                }
                //将"月份+日期"反转
                StringBuilder temp = sb.reverse();
                temp.append(sb);

                //加入结果中
                res.add(temp.toString());
            }
        }

        return res;
    }

    /**
     * @param mouth 给定月份
     * @return 当前月份共有多少天
     */
    private int getDay(int mouth) {
        if (mouth == 1 || mouth == 3 || mouth == 5 || mouth == 7 || mouth == 8 || mouth == 10 || mouth == 12) {
            return 31;
        } else if (mouth == 4 || mouth == 6 || mouth == 9 || mouth == 11) {
            return 30;
        } else {
            //2月
            return 28;
        }
    }

    /**
     * @return 当前年份是否是闰年 true:是 false:不是
     */
    /*private boolean isRunYear(String year) {
        if (Integer.parseInt(year) % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }*/
}
