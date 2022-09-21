package writtenExam.alibaba;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        String str = sc.next();
        char chr[] = str.toCharArray();
        int count =0;
        for(int i=1;i<chr.length;i++){
            if(chr[i]!=chr[i-1]){
                count++;
            }
        }
        System.out.println(count-2);
    }
}