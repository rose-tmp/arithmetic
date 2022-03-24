package dp;

/**
 * @author - ZwZ
 * @date - 2021/3/6 - 11:23
 * @Description:
 */
public class Demo {
    public String demo(String IPV4) {
        String[] strNums = IPV4.split(".");
        if (strNums.length != 4) {
            return "no";
        }
        for (int i = 0; i < strNums.length; i++) {
            if (strNums[i].length() == 0 || strNums[i].length() > 3) return "no";
            if (strNums[i].charAt(0) == '0' && strNums[i].length() != 1) return "no";
            for (char ch : strNums[i].toCharArray()) {
                //判断是否为数字
                if (!Character.isDigit(ch)) return "no";
            }
            if (Integer.parseInt(strNums[i]) > 255) return "no";
        }
        return "IPV4";
    }
}
