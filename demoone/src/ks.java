import java.util.Scanner;

public class ks {

    public static void main(String[] args) {//1,1,0,0,1,1,1,0,1
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        String[] chr = a.split(",");
        int[] nums = new int[chr.length];
        for (int i=0 ; i< chr.length ; i++) nums[i] = Integer.valueOf(chr[i]);
        System.out.println(cars(nums));
    }
    public static String cars(int[] chr){
        int res = 0;
        for (int i=0 ;i<chr.length-1;i++){
            if (chr[i] == 0){
                i++;
                if (i == chr.length) break;
            }
            if (chr[i] == 1){
                int j = i+1;
                if (j == chr.length) {
                    res += 1;
                    break;
                }
                while (chr[j] == 1){
                    j++;
                    if (j == chr.length)break;
                }
                res += (j-i)%3 > 0? (j-i)/3 + 1 : (j-i)/3 ;
                i = j-1;
            }
        }
        return String.valueOf(res);
    }
}
