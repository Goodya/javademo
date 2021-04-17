public class BuyOranges {

    public static void main(String[] args) {
        System.out.println(getMinNumber(20, 7, 3));
    }

    /**
     * 获取最小袋子数
     *
     * @param orangeNum 购买橘子数
     * @param packNum1  袋子打包数量1
     * @param packNum2  袋子打包数量2
     * @return Integer
     */
    public static Integer getMinNumber(Integer orangeNum, Integer packNum1, Integer packNum2) {
        if (orangeNum <= 0 || packNum1 <= 0 || packNum2 <= 0) {
            return -1;
        }
        Integer largeNum = Integer.max(packNum1, packNum2); //大的打包数量
        Integer smallNum = Integer.min(packNum1, packNum2); //小的打包数量
        Integer remainder = orangeNum % largeNum; //余数
        Integer minNum = getMinNum(smallNum, largeNum, remainder, orangeNum);
        if (remainder == 0) { // 购买总数整除大打包数情况
            return orangeNum / largeNum;
        } else if (remainder % smallNum == 0) { // 余数为小打包数的倍数的情况
            return (orangeNum / largeNum) + (remainder / smallNum);
        } else if (minNum != null && orangeNum > minNum) { //通过计算可以得出最小袋子数情况
            return (orangeNum / largeNum) - (minNum / largeNum) + (minNum + remainder) / smallNum;
        } else if (orangeNum % smallNum == 0) { // 购买数整除小打包数情况
            return orangeNum / smallNum;
        }
        return -1;
    }

    public static Integer getMinNum(Integer smallNum, Integer largeNum, Integer remainder, Integer orangeNum) {// 获取余数情况的最小可整分配数量
        for (int i = 1; i < orangeNum / largeNum; i++) {
            if ((i * (largeNum - smallNum) + remainder) % smallNum == 0) {
                return i * largeNum;
            }
        }
        return null;
    }
}
