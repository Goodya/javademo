import java.util.Arrays;

public class DemoTwo {
    public static void main(String[] args) {
//        moveZeroes(new int[]{0,1,0,3,12});
        System.out.println('0' - '0');
    }


    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                res[k++] = nums1[i];
                i++;
                j++;
            }
        }
        return Arrays.copyOf(res, k);

//        int []temp=new int[1000]; // 0ms解法
//        int []res=new int[Math.max(nums1.length,nums2.length)] ;
//        for(int i=0;i<nums1.length ;i++){
//            temp[nums1[i]]+=1;
//        }
//        int index=0;
//        for(int i=0;i<nums2.length ;i++){
//            if(temp[nums2[i]]>0){
//                res[index++]=nums2[i];
//                temp[nums2[i]]--;
//            }
//        }
//        return Arrays.copyOfRange(res,0,index);
    }

    /**
     * 数组中的数加一
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int i = digits.length - 1;
        while (digits[i] == 9) {
            i--;
            if (i < 0) {
                int[] result = new int[digits.length + 1];
                result[0] = 1;
                return result;
            }
        }
        int[] result = Arrays.copyOf(digits, digits.length);
        result[i] = result[i++] + 1;
        while (i < digits.length) {
            result[i] = 0;
            i++;
        }
        return result;
    }

    /**
     * 0移动到后面 原数组操作
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int i = 0;
        int j = 1;
        while (i<nums.length){
            if (nums[i] == 0){
                j = i;
                while (j<nums.length){
                    if (j<nums.length-1 && nums[j] == 0){
                        j++;
                    }else {
                        nums[i] = nums[j];
                        nums[j] = 0;
                        break;
                    }
                }
            }
            i++;
        }

//        if (nums == null || nums.length <= 1) {
//            return ;
//        }
//
//        int zeroCounter = 0;
//        int index = 0;
//
//        for (int i = 0; i <= nums.length - 1; i++) {
//            // 不等于0，则交换元素位置
//            if (nums[i] != 0) {
//                int temp = nums[i];
//                nums[i] = nums[index];
//                nums[index] = temp;
//                index++;
//            }
//        }

    }

    /**
     * 判断有效数独
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int len = board.length;
        int[][] line = new int[len][len];
        int[][] column = new int[len][len];
        int[][] block = new int[len][len];
        for (int i=0 ; i<len ; i++){
            for (int j=0 ; j<len ; j++){
                if (board[i][j] == '.'){
                    continue;
                }
                int num = board[i][j] - '0' -1;
                int k = i/3*3 + j/3;
                if (line[i][num] == 1 || column[j][num] == 1 || block[k][num] == 1 ){
                    return false;
                }
                line[i][num] = 1;
                column[j][num] = 1;
                block[k][num] = 1;
            }
        }
        return true;
    }

    private int[] memo;
    /**
     * 返回和为目标值的组合的总数
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
//        if(target == 0){  // 简单递归 会超时
//            return 1;
//        }
//        int res = 0;
//        for(int num : nums){
//            if(target >= num){
//                res += combinationSum4(nums,target-num);
//            }
//        }
//        return res;

        memo = new int[target + 1];
        Arrays.fill(memo, -1);
        memo[0] = 1;
        return search(nums, target);
    }

    //记忆化搜索 返回和为目标值的组合的总数
    private int search(int[] nums, int target) {
        if (memo[target] != -1) {
            return memo[target];
        }
        int res = 0;
        for (int num : nums) {
            if (target >= num) {
                res += search(nums, target - num);
            }
        }
        memo[target] = res;
        return res;
    }

    /**
     * 动态规划   返回和为目标值的组合的总数
     */
    public int combinationSum4d(int[] nums, int target) {
        int[] memo = new int[target + 1];
        memo[0] = 1;
        for (int i = 0; i < target; i++) {
            for (int num : nums) {
                if (i + num <= target) {
                    memo[i + num] += memo[i];
                }
            }
        }
        return memo[target];
    }


}
