import java.util.Arrays;

public class DemoTwo {
    public static void main(String[] args) {
//        moveZeroes(new int[]{0,1,0,3,12});
        System.out.println(isPalindrome("0P"));
        System.out.println(Character.isLetter('O'));
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

    /**
     * 动态规划  爬楼梯  f(n) = f(n-1) + f(n-2)
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 字符串第一个唯一字符
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        if (s.length()<1){
            return -1;
        }
        if(s.length()<2){
            return 0;
        }
        int i = 0;
        while (i<s.length()){
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))){
                return i;
            }
        }
        return -1;
    }

    /**
     * 字符串第一个唯一字符 当时最快
     * @param s
     * @return
     */
    public int firstUniqChar1(String s) {
        // 字符串长度不超过26，按照原先的方式遍历
        if (s.length() <= 26) {
            int[] chars = new int[26];
            for (char c : s.toCharArray()) {
                chars[c - 'a'] += 1;
            }
            for (int i = 0; i < s.length(); ++i) {
                if (chars[s.charAt(i) - 'a'] == 1) {
                    return i;
                }
            }
        }
        //只遍历26个字母，使用indexOf函数检查字符索引
        int result = -1;
        for (char c = 'a'; c <= 'z'; ++c) {
            int pre = s.indexOf(c);
            // s包含该字符并且只出现一次
            if (pre != -1 && pre == s.lastIndexOf(c)) {
                // 取最前面的位置
                result = (result == -1 || result > pre) ? pre : result;
            }
        }
        return result;
    }

    /**
     * 有效的字母异位词
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        char[] a1 = s.toCharArray();
        char[] a2 = t.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        if (a1.equals(a2)){
            return true;
        }
        return false;
    }

    /**
     * 判断是否回文串
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        s.trim();
        int i=0 ;
        int j=s.length()-1;
        while (i<j){
            while(i<j && !Character.isLetterOrDigit(s.charAt(i))){
                i++;
            }
            while(i<j && !Character.isLetterOrDigit(s.charAt(j))){
                j--;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * 外观数列  1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 第一项是数字 1
     * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
     * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
     * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
     * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        // 递归出口
        if(n==1){
            return "1";
        }
        String s1 = countAndSay(n - 1);
        // 定义结果
        StringBuilder result = new StringBuilder();
        // 对s1遍历处理获取值
        char local = s1.charAt(0);
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            // 设定计数器 计算同一个数字出现的次数 count
            if(s1.charAt(i) == local){
                count++;
            }else {
                // 不符合，记录下
                result.append(count);
                result.append(local);
                count = 1;
                local = s1.charAt(i);
            }
        }
        result.append(count);
        result.append(local);
        return result.toString();
    }




}
