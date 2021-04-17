public class BadmintonTeam {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,4,6,6,8};
        getPlanNum(nums);
    }

    public static void getPlanNum(int[] personNums){
        int[] levelNum = getLevelNum(personNums);
        for (int j=0 ; j<levelNum.length ; j++ ){
            System.out.print(levelNum[j] + "  ");
        }


    }

    public static int[] getLevelNum (int[] personNums){
        int[] levelNum = {0,0,0,0,0,0,0,0};
        for (int i=0 ; i<personNums.length ; i++){
            levelNum[personNums[i]-1] += 1;
        }
        return levelNum;
    }


}
