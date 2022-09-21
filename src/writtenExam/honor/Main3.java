package writtenExam.honor;
import java.util.*;

public class Main3 {
    //存储已经部署了的redis的编号
    static Set<Integer> redisList = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char cmd = str.charAt(0);
        if (cmd == '1') {
            char c1 = str.charAt(8);
            char c2 = str.charAt(9);
            int rNum = (c1 - '0') * 10 + (c2 - '0');
            System.out.println(getNum(rNum));
        } else if (cmd == '2') {
            String token = str.substring(2);
            //节点序号
            int id = getId(token);
            //根据节点序号得到服务器序号
            int sum = 0;
            int i = 0;
            for (i = 0; i < 20; i++) {
                sum += i * 50;
                if (sum > id) {
                    break;
                }
            }
            /*if (redisList.contains(i)) {
                System.out.println(id);
            }*/
            System.out.println(id);
        } else if (cmd == '3') {

        } else if (cmd == '4') {
            char c1 = str.charAt(8);
            char c2 = str.charAt(9);
            int rNum = (c1 - '0') * 10 + (c2 - '0');
            redisList.add(rNum);
            System.out.println(getNum(rNum));
        } else if (cmd == '5') {

        }
    }

    /**
     * @param rNum redis服务器编号
     * @return 节点序号
     */
    private static int getNum(int rNum) {
        return 50 * (rNum - 1);
    }

    /**
     *
     */
    private static int getId(String token) {
        int sum = 0;
        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);
            sum += c;
        }
        return sum % 999;
    }
}

