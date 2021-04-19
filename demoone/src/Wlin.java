import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Wlin {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        int b = in.nextInt();
        String[] strs = a.split(",");
        ArrayList<Integer> idx = new ArrayList<>();
        int[] strs1 = new int[2*strs.length];
        for (int i=0 ; i<strs1.length-1 ; i++){
            strs1[i] = 0;
        }
        int j=0;
        for (int i=0 ; i<strs.length ; i++){
            if (strs[i].contains("-")){
                String[] str = strs[i].split("-");
                strs1[i+j] = Integer.valueOf( str[0]);
                idx.add(Integer.valueOf( str[0]));
                strs1[i+j+1] = Integer.valueOf(str[1]);
                j++;
            }else {
                strs1[i+j] = Integer.valueOf(strs[i]);
            }
        }
        Arrays.sort(strs1);
        deleteNum(strs1,idx,b);

    }

    public static void deleteNum(int[] sortStr, ArrayList idxStr,int num){
        int i=0;
        Boolean zd = false;
        int idx = -1;
        while (sortStr[i] <= num){
            i++;
        }
        while ( i< sortStr.length){
            if (num == sortStr[i]){
               idx = i; break;
            }if(sortStr[i]>=num){
                if (idxStr.contains(sortStr[i-1])){
                    idx = i-1;
                    break;
                }
            }
            i++;
        }
        if (idx == -1){
            for (int j=0 ; j<sortStr.length; j++){
                if (idxStr.contains(sortStr[j])){
                    System.out.print(sortStr[j] + "-" + sortStr[j+1]);
                    j++;
                }else {
                    System.out.print(sortStr[j]);

                }
            }
        }else {
            for (int j=0 ; j<sortStr.length; j++){
                if (j != idx){
                    if (idxStr.contains(sortStr[j])){
                        System.out.print(sortStr[j] + "-" + sortStr[j+1]);
                        j++;
                    }else {
                        System.out.print(sortStr[j]);

                    }
                } else {
                    if (sortStr[j] == num && (num+1) != sortStr[j+1]){
                        System.out.print((num + 1) + "-" + sortStr[j+1]);
                    }else if (sortStr[j] == num && num+1 == sortStr[j+1]){
                        System.out.print((num + 1) +"," + sortStr[j+1]);
                    }else if (sortStr[j+1] != num && sortStr[j+1] != (num-1) ){
                        System.out.print(sortStr[j] + "-" + (num));
                        System.out.print((num + 1) + "-" + sortStr[j+1]);
                    }
                }

            }
        }



    }


}
