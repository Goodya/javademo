public class LongestPalindrome {
    private int len;  //记录最终长度
    private int start; // 开始字符位置

    public String getLongestStr(String s){
        if (s.length()<2){
            return s;
        }
        for (int i=0 ; i<s.length() ; i++){
            searchStr(s,i,i);
            searchStr(s,i,i+1);
        }
        return s.substring(start,start+len);
    }

    public void searchStr(String str,int s, int e){
        while (s >= 0 && e < str.length() && str.charAt(s) == str.charAt(e) ){
            s--;
            e++;
        }
        if (len < e-s-1){
            len = e-s-1;
            start = s+1;
        }
    }
}
