package writtenExam.qunaer;

/**
 * @author: ZwZ
 * @date: 2022-09-07 15:08
 * @desc:
 */
public class Main3 {
    /*public String showDown(String inHand) {
        String[] inArr = inHand.split(" ");
        Map<Character, List<String>> map = new HashMap<>();
        for (int i = 0; i < inArr.length; i++) {
            List<String> list = map.getOrDefault(inArr[i].charAt(0), new ArrayList<>());
            list.add(inArr[i].substring(1));
            map.put(inArr[i].charAt(0), list);
        }

        for (Character k : map.keySet()) {
            List<String> list = map.get(k);
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals("A")) {
                    temp.add(14);
                } else if (list.get(i).equals("K")) {
                    temp.add(13);
                } else if (list.get(i).equals("Q")) {
                    temp.add(12);
                } else if (list.get(i).equals("J")) {
                    temp.add(11);
                } else {
                    temp.add(Integer.parseInt(list.get(i)));
                }
            }
            temp.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
        }
        return "";
    }

    public int getMaxLen(List<Integer> list) {
        int res = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == list.get(i - 1) + 1) {
                res++;
            }
        }
        return res;
    }*/
    /*public String showDown (String inHand) {
        inHand =inHand.replace("10","T");
        String ch[] = inHand.split(" ");
        char node[][]=new char[ch.length][2];
        for(int i=0;i<ch.length;i++){
            node[i]=ch[i].toCharArray();
        }
        int colors[] =new int[128];

        int num[] =new int[14];
        boolean color[][] =new boolean[14][128];
        for(int i=0;i<node.length;i++){
            char chr = node[i][1];
            int index = 0;
            if(chr>='2'&&chr<='9'){
                index =chr-'0';
            }
            if(chr=='T'){
                index=10;
            }
            if(chr=='A'){
                index=1;
            }
            if(chr =='J')
                index = 11;
            if(chr =='Q')
                index = 12;
            if(chr == 'K')
                index = 13;
            color[index][node[i][0]]=true;
            colors[node[i][0]]++;
            num[index]++;
        }
        //System.out.println(Arrays.toString(num));
        //皇家顺子

        if(num[1]>=0){
            for(int i=0;i<128;i++){
                if(color[1][i]){
                    boolean finish =false;
                    for(int j=10;j<=13;j++){
                        if(!color[j][i]){
                            finish =true;
                            break;
                        }
                    }
                    if(!finish){
                        return "HuangJiaTongHuaShun";
                    }
                }
            }
        }
        //同花顺
        for(int i=1;i<13;i++){
            if(i+4>13)
                break;
            if(num[i]>0){
                for(int j=0;j<128;j++){
                    if(color[i][j]){
                        boolean jud =false;
                        for(int q=1;q<5;q++){
                            if(!color[i+q][j]){
                                jud =true;
                                break;
                            }
                        }
                        if(!jud){
                            return "TongHuaShun";
                        }
                    }
                }
            }
        }
        //四条 葫芦
        int sum[] =new int[5];
        for(int i=0;i<14;i++){
            sum[num[i]]++;
        }
        if(sum[4]>=0){
            return "SiTiao";
        }
        if(sum[3]>0&&sum[2]>0){
            return "HuLu";
        }
        //tonghua
        for(int i=0;i<128;i++){
            if(colors[i]>=5){
                return "TongHua";
            }
        }


        //顺子
        for(int i=1;i<13;i++){
            if(i+4>13)
                break;
            if(num[i]>0){
                if(num[i+1]>0&&num[i+2]>0&&num[i+3]>0&&num[i+4]>0){
                    return "ShunZi";
                }
            }
        }
        if(sum[3]>0){
            return "SanTiao";
        }
        if(sum[2]>=2){
            return "LiangDui";

        }
        if(sum[2]>0)
            return "YiDui";
        return "";
    }*/
    public String shownDown(String inHead){
        inHead =inHead.replace("10","T");
        String ch[] = inHead.split(" ");
        char node[][]=new char[ch.length][2];
        for(int i=0;i<ch.length;i++){
            node[i]=ch[i].toCharArray();
        }
        //存储每个花色对应的牌的总数
        int colors[] =new int[128];
        //存储每张牌的总数,例如A K Q对应多少张
        int num[] =new int[14];
        //color[i][j]:i表示字符 j表示i这个字符所代表的花色（他所代表的花色的下标处置true）
        boolean color[][] =new boolean[14][128];
        for(int i=0;i<node.length;i++){
            char chr = node[i][1];
            int index = 0;
            if(chr>='2'&&chr<='9'){
                index =chr-'0';
            }
            if(chr=='T'){
                index=10;
            }
            if(chr=='A'){
                index=1;
            }
            if(chr =='J')
                index = 11;
            if(chr =='Q')
                index = 12;
            if(chr == 'K')
                index = 13;
            color[index][node[i][0]]=true;
            colors[node[i][0]]++;
            num[index]++;
        }
        //System.out.println(Arrays.toString(num));
        //皇家顺子

        if(num[1]>=0){
            for(int i=0;i<128;i++){
                //A的花色为i
                if(color[1][i]){
                    boolean finish =false;
                    //找10 J Q K是不是花色也为i
                    for(int j=10;j<=13;j++){
                        if(!color[j][i]){
                            finish =true;
                            break;
                        }
                    }
                    if(!finish){
                        return "HuangJiaTongHuaShun";
                    }
                }
            }
        }
        //同花顺
        for(int i=1;i<13;i++){
            if(i+4>13)
                break;
            if(num[i]>0){
                for(int j=0;j<128;j++){
                    if(color[i][j]){
                        boolean jud =false;
                        for(int q=1;q<5;q++){
                            if(!color[i+q][j]){
                                jud =true;
                                break;
                            }
                        }
                        if(!jud){
                            return "TongHuaShun";
                        }
                    }
                }
            }
        }
        //四条 葫芦
        int sum[] =new int[5];
        for(int i=0;i<14;i++){
            sum[num[i]]++;
        }
        if(sum[4]>=0){
            return "SiTiao";
        }
        if(sum[3]>0&&sum[2]>0){
            return "HuLu";
        }
        //tonghua
        for(int i=0;i<128;i++){
            if(colors[i]>=5){
                return "TongHua";
            }
        }


        //顺子
        for(int i=1;i<13;i++){
            if(i+4>13)
                break;
            if(num[i]>0){
                if(num[i+1]>0&&num[i+2]>0&&num[i+3]>0&&num[i+4]>0){
                    return "ShunZi";
                }
            }
        }
        if(sum[3]>0){
            return "SanTiao";
        }
        if(sum[2]>=2){
            return "LiangDui";

        }
        if(sum[2]>0) {
            return "YiDui";
        }
        return "GaoPai";
    }
}
