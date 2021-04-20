
import java.util.*;

public class DemoOne {
    public static void main(String[] args) {
//        int[] nums = new int[]{2,2,2,2,2,2,2,2,2,2,2,2,2};
//        System.out.println(fourSum(nums,8));
//        System.out.println(isValid("{[]}"));
//        System.out.println(generateParenthesis(3));
//        nextPermutation(new int[]{2, 3, 1});
        System.out.println(longestValidParentheses2("(()(((()"));
    }

    /**
     * 2-9返回数字九键所有字符串组合
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        String[] str = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        for (int i = 0; i < digits.length(); i++) {
            result = combination(result, str[digits.charAt(i) - '0' - 2]);
        }
        return result;
    }

    public List<String> combination(List<String> s1, String s2) {
        List<String> str = new ArrayList<>();
        for (int i = 0; i < s2.length(); i++) {
            if (s1.isEmpty()) {
                str.add(s2.substring(i, i + 1));
            } else {
                for (String s : s1) {
                    str.add(s + s2.substring(i, i + 1));
                }
            }
        }
        return str;
    }

    // 2-9返回数字九键所有字符串组合 0ms解
    public static List<String> letterCombinations1(String digits) {
        List<String> list = new ArrayList<>();//最终结果
        if (digits.length() == 0)
            return list;
        String[] data = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};//2 3 4 5 6 7 8 9

        int b = 0;//指向本次的号码
        StringBuilder ans = new StringBuilder();//存临时的结果
        dfs(b, ans, data, digits, list);
        return list;
    }

    public static void dfs(int b, StringBuilder ans, String[] data, String digits, List list) {
        if (b == digits.length()) {
            list.add(ans.toString());
            return;
        }
        int a = digits.charAt(b) - '2';
        for (int i = 0; i < data[a].length(); i++) {//遍历a号码下的字母
            char c = data[a].charAt(i);
            ans.append(String.valueOf(c));
            dfs(b + 1, ans, data, digits, list);
            ans.delete(ans.length() - 1, ans.length());
        }
    }

    /**
     * 四数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        if (nums.length == 4) {
            if (nums[0] + nums[1] + nums[2] + nums[3] == target) {
                List<Integer> list = new LinkedList<>();
                list.add(nums[0]);
                list.add(nums[1]);
                list.add(nums[2]);
                list.add(nums[3]);
                result.add(list);
            }
            return result;
        }
        for (int i = 0; i < nums.length - 3; i++) {
            int j = i + 1;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target)
                continue;
            if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target)
                continue;
            while (j < nums.length - 2) {
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                while (j > i + 1 && nums[j] == nums[j - 1] && j < nums.length - 2)
                    j++;
                int k = j + 1;
                int l = nums.length - 1;
                int sumij = nums[i] + nums[j];
                while (k < l) {
                    int sum = sumij + nums[k] + nums[l];
                    if (sum > target) {
                        l--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        List<Integer> list = new LinkedList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.add(nums[l]);
//                        if (!result.contains(list)){
                        result.add(list);
//                        }
                        while (k < l && nums[k + 1] == nums[k]) k++;
                        while (k < l && nums[l - 1] == nums[l]) l--;
                        k++;
                        l--;
                    }
                }
                j++;
            }
        }
        return result;
    }

    /**
     * 判断括号是否有效
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Stack<Character> z = new Stack<>();
        for (char cha : s.toCharArray()) {
            switch (cha) {
                case ')':
                    if (z.isEmpty() || z.peek() != '(') {
                        return false;
                    }
                    z.pop();
                    break;
                case ']':
                    if (z.isEmpty() || z.peek() != '[') {
                        return false;
                    }
                    z.pop();
                    break;
                case '}':
                    if (z.isEmpty() || z.peek() != '{') {
                        return false;
                    }
                    z.pop();
                    break;
                default:
                    z.push(cha);
            }
        }

        return true;
    }


    /**
     * 生成n对括号的所有情况
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        res = dfs(n, n, "", res);
        return res;
    }

    private static List<String> dfs(int left, int right, String curStr, List<String> res) {
        if (left == 0 && right == 0) { // 左右括号都不剩余了，递归终止
            res.add(curStr);
        }

        if (left > 0) { // 如果左括号还剩余的话，可以拼接左括号
            dfs(left - 1, right, curStr + "(", res);
        }
        if (right > left) { // 如果右括号剩余多于左括号剩余的话，可以拼接右括号
            dfs(left, right - 1, curStr + ")", res);
        }
        return res;
    }


    /**
     * 删除有序数组中的重复项(原地)
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 0;
        while (i < nums.length) {
            while (i > 0 && nums[i - 1] == nums[i]) {
                i++;
                if (i == nums.length) {
                    return j;
                }
            }
            nums[j++] = nums[i];
            i++;
        }
        return j;
    }

    /**
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
        }
        return i;
    }


    /**
     * 从一个字符串中找另一个字符串的索引位置
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        if (needle.length() == haystack.length()) {
            return haystack.equals(needle) ? 0 : -1;
        }
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                if (needle.equals(haystack.substring(i, (i + needle.length())))) {
                    return i;
                }
                continue;
            }
        }
        return -1;
    }

    /**
     * 将给定数字序列重新排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * 必须 原地 修改，只允许使用额外常数空间。
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        int indexi = -1;
        for (int i = len - 1; i >= 1; i--) {
            if (nums[i] > nums[i - 1]) {
                indexi = i - 1;
                break;
            }
        }
        if (indexi >= 0) {
            for (int i = len - 1; i >= indexi + 1; i--) {
                if (nums[i] > nums[indexi]) {
                    //swap nums[i] and nums[indexi]
                    int num = nums[i];
                    nums[i] = nums[indexi];
                    nums[indexi] = num;
                    break;
                }
            }
        }
        Arrays.sort(nums, indexi + 1, len);
    }

    /**
     * 最长连续有效括号 超时......
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        if (s.length() < 2) {
            return 0;
        }
        int maxNum = 0; //最大括号数量
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == ')') {
                i++;
                if (i == s.length()) {
                    break;
                }
            }
            int res = 0; //临时括号数量
            int result = 0;
            Stack<Character> z = new Stack<>();
            for (int j = i; j < s.length(); j++) {
                switch (s.charAt(j)) {
                    case ')':
                        if (z.isEmpty()) {
                            maxNum = Math.max(maxNum, result);
                            result = 0;
                            res = 0;
                            break;
                        } else {
                            z.pop();
                            if (z.isEmpty()) {
                                res += 2;
                                result = result + res;
                                res = 0;
                                break;
                            }
                            res += 2;
                            break;
                        }
                    default:
                        z.push(s.charAt(j));
                }
            }
            maxNum = Math.max(maxNum, result);

        }
        return maxNum;
    }

    /**
     * 最长连续有效括号
     * @param s
     * @return
     */
    public static int longestValidParentheses1(String s) {
        char[] chars = s.toCharArray();
        return Math.max(calc(chars, 0, 1, chars.length, '('), calc(chars, chars.length -1, -1, -1, ')'));
    }

    private static int calc(char[] chars , int i ,  int flag,int end, char cTem){
        int max = 0, sum = 0, currLen = 0,validLen = 0;
        for (;i != end; i += flag) {
            sum += (chars[i] == cTem ? 1 : -1);
            currLen ++;
            if(sum < 0){
                max = max > validLen ? max : validLen;
                sum = 0;
                currLen = 0;
                validLen = 0;
            }else if(sum == 0){
                validLen = currLen;
            }
        }
        return max > validLen ? max : validLen;
    }

    /**
     * 最长连续有效括号 哨兵
     * @param s
     * @return
     */
    public static int longestValidParentheses2(String s) {
        List<Integer> unmatchedBrackets;
        int i, size, validLength, longest;

        if (s == null || s.isEmpty()) {
            return 0;
        } // if (s == null || s.isEmpty())

        longest = 0;
        unmatchedBrackets = new ArrayList<>();
        unmatchedBrackets.add(-1); // 哨兵
        size = 1;
        for (i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case '(':
                    // 遇左括号压栈
                    unmatchedBrackets.add(i);
                    ++size;
                    break; // case '('

                case ')':
                    // 遇右括号弹栈
                    --size;
                    unmatchedBrackets.remove(size);
                    if (unmatchedBrackets.isEmpty()) {
                        // 如果哨兵弹出说明当前右括号未配对左括号
                        // 当前的右括号压栈成为新的哨兵
                        unmatchedBrackets.add(i);
                        ++size;
                    } // if (unmatchedBrackets.isEmpty())
                    else {
                        // 当前右括号成功配对了一个左括号时
                        // 计算当前右括号到上一个入栈字符的距离（有效子串的长度）
                        // 并按需刷新最长有效长度
                        validLength = i - unmatchedBrackets.get(size - 1);
                        if (longest < validLength) {
                            longest = validLength;
                        } // if (longest < validLength)
                    } // else
            } // switch (s.charAt(i))
        } // for (i = 0; i < s.length(); ++i)

        return longest;
    } // longestValidParentheses(String)


}
