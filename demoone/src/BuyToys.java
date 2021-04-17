public class BuyToys {
    public static void main(String[] args) {
        getGroupsNum(5,250);
    }

    /**
     *
     * @param n 钱的张数
     * @param m 玩具价格
     */
    public static void getGroupsNum(Integer n ,Integer m){
        int[]  moneys = {1,5,10,50};//钱的面额
        int resultNum = 0;//组合数量
        for (int i = 0 ; i<=n ; i++){
            int[] result = {0,0,0,0}; // 此数组表示对应位置面额的张数
            result[0] = i;
            for (int j = 0 ; j<=n-i ; j++){
                result[1] = j;
                for (int k = 0 ; k<=n-i-j ; k++){
                    result[2] = k;
                    for (int l = 0 ; l<=n-i-j-k ; l++){
                        result[3] = l;
                        resultNum += lookAllGroups(result,moneys,m,n);
                    }
                }
            }
        }
        System.out.println(resultNum);
    }

    public static int lookAllGroups(int[] result,int[] moneys,int m,int n){
        if (result[0]+result[1]+result[2]+result[3] == n && moneys[0]*result[0]
                + moneys[1] * result[1] + moneys[2] * result[2] + moneys[3] * result[3] >= m){//总张数等于n 并且总钱数大于价格返回1
            return 1;
        }
        return 0;
    }
}
