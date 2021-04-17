import com.sun.deploy.util.StringUtils;


import java.util.*;

public class Main {
    public static void main(String[] args) {
//        char c = 'a';
//        System.out.println((int) c);
//        System.out.println(getMaxStr("abcabcbbabcdefg"));
//        System.out.println(lengthOfLongestSubstring("abcade"));
//        Trie trie = new Trie();
//        trie.insert("appel");
//        System.out.println(trie.search("app"));
//        System.out.println(trie.startsWith("app"));
//        trie.insert("app");
//        System.out.println(trie.search("app"));
//        System.out.println(reverse(1534236469));
//        System.out.println(isPalindrome(11));
//        int[] nums = {-1, 0, 1, 2, -1, -4};
//        System.out.println(threeSum(nums));
        int[] nums = {1, 2, 4, 8, 16, 32, 64, 128};
        System.out.println(threeSumClosest(nums, 82));
    }


    /**
     * 获取无重复字符的最长子串
     *
     * @param s 字符串
     * @return int
     */
    public static int getMaxStr(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int res = 0;
        char[] strs = s.toCharArray();
        for (int i = 0; i < strs.length - 1; i++) {
            for (int j = i + 1; j < strs.length + 1; j++) {
                String temp = s.substring(i, j);
                if (isRepeat(temp)) {
                    break;
                } else {
                    res = Integer.max(res, temp.length());
                }
            }
        }
        return res;
    }

    /**
     * 判断字符串是否重复
     *
     * @param str
     * @return
     */
    public static Boolean isRepeat(String str) {
        char[] strs = str.toCharArray();
        for (char e : strs) {
            if (str.indexOf(e) != str.lastIndexOf(e)) {
                return true; // 重复返回true
            } else {
                return false;
            }
        }
        return true;
    }


    public static int lengthOfLongestSubstring(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }

    /**
     * 反转数字
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        String str = String.valueOf(x);
        Boolean flag = true;
        long result = 0;
        String res = "";
        if (x > 0) {
            for (int i = str.length() - 1; i >= 0; i--) {
                if (flag) {
                    if (str.charAt(i) == '0') {
                        continue;
                    } else {
                        flag = false;
                    }
                }
                res += str.charAt(i);
            }
            result = Long.valueOf(res);
        } else if (x < 0) {
            for (int i = str.length() - 1; i > 0; i--) {
                if (flag) {
                    if (str.charAt(i) == '0') {
                        continue;
                    } else {
                        flag = false;
                    }
                }
                res += str.charAt(i);
            }
            result = -Long.valueOf(res);
        }

        return (int) result == result ? (int) result : 0;
    }


    /**
     * 取字符串中的数字
     *
     * @param s
     * @return
     */
    public static int myAtoi1(String s) {
        s = s.trim();
        if (s.length() < 1) return 0;
        char[] c = s.toCharArray();
        long res = 0;
        if (c[0] == '-' || c[0] == '+' || (c[0] >= '0' && c[0] <= '9')) {
            for (int i = 0; i < c.length; i++) {
                if ((c[i] == '-' || c[i] == '+') && i == 0) continue;
                if (c[i] >= '0' && c[i] <= '9') {
                    res = (c[i] - '0') + res * 10;
                } else break;
                if (-res <= Integer.MIN_VALUE && c[0] == '-') return Integer.MIN_VALUE;
                if (res >= Integer.MAX_VALUE && c[0] != '-') return Integer.MAX_VALUE;
            }
        } else return 0;
        return c[0] == '-' ? (int) -res : (int) res;
    }

    /**
     * 是否回文数
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int numberOfDigits = 0;
        int n = x;
        while (n > 0) {
            n = n / 10;
            numberOfDigits += 1;
        }
        for (int i = 1; i <= (numberOfDigits % 2 == 0 ? numberOfDigits / 2 : numberOfDigits / 2 + 1); i++) {
            if (x % intPow(10, i) != reverseNum(x / intPow(10, numberOfDigits - i))) {
                return false;
            }
        }

        return true;
    }

    //10的幂运算
    public static int intPow(int x, int y) {
        for (int i = 1; i < y; i++) {
            x = x * 10;
        }
        return x;
    }

    //翻转数字
    public static int reverseNum(int x) {
        int y = 0;
        while (x > 0) {
            y = x % 10 + y * 10;
            x = x / 10;
        }
        return y;
    }

    // 翻转判断是否相等判断回文
    public static Boolean isPalindrome222(int x) {
        if (x < 0) return false;
        int rem = 0, y = 0;
        int quo = x;
        while (quo != 0) {
            rem = quo % 10;
            y = y * 10 + rem;
            quo = quo / 10;
        }
        return y == x;
    }

    //判断是否回文数
    public boolean isPalindrome666(int x) {
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;
        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        return x == reversed || x == reversed / 10;
    }


    /**
     * 判断x轴和y轴盛水最多的面积    （超时了！）
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        if (height.length == 2) {
            return Math.min(height[0], height[1]);
        }
        int res = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int h = Math.min(height[i], height[j]);
                res = Math.max(res, h * (j - i));
            }
        }
        return res;
    }

    public static int maxArea1(int[] height) {
        if (height.length == 2) {
            return Math.min(height[0], height[1]);
        }
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            int h = Math.min(height[i], height[j]);
            res = Math.max((j - i) * h, res);
            if (height[i] < height[j]) i++;
            else j--;
        }
        return res;
    }

    //数字转罗马数字
    public static String intToRoman(int num) {
        int[] v = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] reps = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        String res = "";
        for (int i = 0; i < reps.length; i++) {
            while (num >= v[i]) {
                res += reps[i];
                num -= v[i];
            }
        }
        return res;
    }

    //罗马数字转数字
    public static int romanToInt(String s) {
        int res = 0;
        s += "I";
        for (int i = 0; i < s.length() - 1; i++) {
            switch (s.charAt(i)) {
                case 'I':
                    if (s.charAt(i) == s.charAt(i + 1)) {
                        res += 1;
                    } else {
                        res -= 1;
                    }
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'X':
                    if (s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'C') {
                        res -= 10;
                    } else {
                        res += 10;
                    }
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'C':
                    if (s.charAt(i + 1) == 'D' || s.charAt(i + 1) == 'M') {
                        res -= 100;
                    } else {
                        res += 100;
                    }
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'M':
                    res += 1000;
                    break;
                default:
                    res += 0;
            }
        }
        return res;
    }

    // 4ms 解法 罗马数字变数字
    public int romanToInt1(String s) {
        // 当前位小于下一位，右-左；否则右+左。总之，+右+/-左
        // 永远和前一位做比较,对前一位加或减
        int sum = 0;
        int pre = turnNum(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int current = turnNum(s.charAt(i));
            if (current > pre) {
                sum = sum - pre;
            } else {
                sum = sum + pre;
            }
            pre = current;
        }
        sum = sum + pre;
        return sum;

    }

    private int turnNum(char c) {
        if (c == 'I') {
            return 1;
        }
        if (c == 'V') {
            return 5;
        }
        if (c == 'X') {
            return 10;
        }
        if (c == 'L') {
            return 50;
        }
        if (c == 'C') {
            return 100;
        }
        if (c == 'D') {
            return 500;
        }
        if (c == 'M') {
            return 1000;
        }
        return 0;

    }

    public static String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        StringBuilder res = new StringBuilder();
        int len = strs[0].length();
        for (int i = 0; i < len; i++) {
            if (strs[0].charAt(i) == strs[strs.length - 1].charAt(i)) {
                res.append(strs[0].charAt(i));
            } else {
                return res.toString();
            }
        }
        return res.toString();
    }

    // 三个整数和为0
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int sum = -nums[i];
            int l = i + 1;   //左指针
            int r = nums.length - 1;   //右指针
            while (l < r) {
                if (nums[l] + nums[r] > sum) {
                    r--;
                } else if (nums[l] + nums[r] < sum) {
                    l++;
                } else {
                    List<Integer> res = new ArrayList<>();
                    res.add(nums[i]);
                    res.add(nums[l]);
                    res.add(nums[r]);
                    result.add(res);
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                }
            }
        }
        return result;
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[0] + nums[1] + nums[2] >= target) {
            return nums[0] + nums[1] + nums[2];
        }
        if (nums[n - 1] + nums[n - 2] + nums[n - 3] <= target) {
            return nums[n - 1] + nums[n - 2] + nums[n - 3];
        }
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n - 2; i++) {
            int numi = nums[i];
            for (int j = i + 1; j < n - 1; j++) {
                int k = j + 1;
                if (numi + nums[j] + nums[k] > target) {
                    while (numi + nums[j] + nums[k] > target) {
                        k--;
                        if (k == j) {
                            k++;
                            break;
                        }
                    }
                    int res = Math.abs(result - target) < Math.abs(numi + nums[j] + nums[k] - target) ? result : numi + nums[j] + nums[k];
                    result = Math.abs(result - target) <= Math.abs(res - target) ? result : res;
                } else if (numi + nums[j] + nums[k] < target) {
                    while (numi + nums[j] + nums[k] < target) {
                        j++;
                        if (k == j) {
                            j--;
                            break;
                        }
                    }
                    int res = Math.abs(result - target) < Math.abs(numi + nums[j] + nums[k] - target) ? result : numi + nums[j] + nums[k];
                    result = Math.abs(result - target) <= Math.abs(res - target) ? result : res;
                } else {
                    return target;
                }
            }
        }
        return result;
    }


}
