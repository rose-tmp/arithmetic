package writtenExam.microsoft.test3;

/**
 * @author: ZwZ
 * @date: 2022-08-19 19:48
 * @desc:
 */
public class Main2 {
    public String solution(String S) {
        // write your code in Java 8 (Java SE 8)
        int  count[] =new int[128];
        char chars[] = S.toCharArray();
        for(char ch:chars){
            count[ch]++;
        }
        StringBuilder pre = new StringBuilder();
        StringBuilder last =new StringBuilder();
        for(int i=127;i>=0;i--){
            int num = count[i]/2;
            for(int j=0;j<num;j++){
                pre.append((char)i);
                last.insert(0,(char) i);
            }
        }
        for(int i=127;i>=0;i--){
            if(count[i]%2==1) {
                pre.append((char) (i));
                break;
            }
        }

        pre.append(last);
        while (pre.length()>1&&pre.charAt(0)=='0'){
            pre =new StringBuilder(pre.substring(1,pre.length()-1));
        }
        if(pre.length()==0)
            pre=new StringBuilder("0");
        return pre.toString();
    }
}