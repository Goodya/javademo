
import java.util.*;

public class DemoOne {
    public static void main(String[] args) {
//        int[] nums = new int[]{2,2,2,2,2,2,2,2,2,2,2,2,2};
//        System.out.println(fourSum(nums,8));
//        System.out.println(isValid("{[]}"));
        System.out.println(generateParenthesis(3));
    }

    /**
     * 2-9返回数字九键所有字符串组合
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        String[] str = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        for (int i=0;i<digits.length();i++){
            result = combination(result,str[digits.charAt(i)-'0'-2]);
        }
        return result;
    }

    public List<String> combination(List<String> s1,String s2){
        List<String> str = new ArrayList<>();
        for (int i=0;i<s2.length();i++){
            if (s1.isEmpty()){
                str.add(s2.substring(i,i+1));
            }else{
                for (String s:s1){
                    str.add(s+s2.substring(i,i+1));
                }
            }
        }
        return str;
    }
// 2-9返回数字九键所有字符串组合 0ms解
    public static List<String> letterCombinations1(String digits) {
        List<String > list=new ArrayList<>();//最终结果
        if(digits.length()==0)
            return list;
        String[] data = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};//2 3 4 5 6 7 8 9

        int b= 0;//指向本次的号码
        StringBuilder ans = new StringBuilder();//存临时的结果
        dfs(b,ans,data,digits,list);
        return list;
    }
    public static void dfs(int b, StringBuilder ans,String[] data,String digits,List list){
        if (b==digits.length()){
            list.add(ans.toString());
            return;
        }
        int a=digits.charAt(b)-'2';
        for (int i = 0; i < data[a].length(); i++) {//遍历a号码下的字母
            char c = data[a].charAt(i);
            ans.append(String.valueOf(c));
            dfs( b+1, ans,data,digits,list);
            ans.delete(ans.length()-1,ans.length());
        }
    }

    /**
     * 四数之和
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        if (nums.length == 4){
            if (nums[0] + nums[1] + nums[2] + nums[3] == target){
                List<Integer> list = new LinkedList<>();
                list.add(nums[0]);
                list.add(nums[1]);
                list.add(nums[2]);
                list.add(nums[3]);
                result.add(list);
            }
            return result;
        }
        for (int i=0 ; i<nums.length-3;i++){
            int j=i+1;
            if(i>0&&nums[i]==nums[i-1])
                continue;
            if(nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target)
                continue;
            if(nums[i]+nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3]<target)
                continue;
            while (j<nums.length-2){
                if(nums[i]+nums[j]+nums[j+1]+nums[j+2]>target) {
                    break;
                }
                while (j>i+1&&nums[j]==nums[j-1]&&j<nums.length-2)
                    j++;
                int k=j+1;
                int l=nums.length-1;
                int sumij = nums[i] + nums[j];
                while (k<l){
                    int sum = sumij + nums[k] + nums[l];
                    if (sum > target){
                        l--;
                    } else if (sum < target ){
                        k++;
                    }else {
                        List<Integer> list = new LinkedList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.add(nums[l]);
//                        if (!result.contains(list)){
                            result.add(list);
//                        }
                        while (k<l && nums[k+1] == nums[k]) k++;
                        while (k<l && nums[l-1] == nums[l]) l--;
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
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Stack<Character> z = new Stack<>();
        for (char cha : s.toCharArray()){
            switch (cha){
                case ')' : if (z.isEmpty() || z.peek() != '('){
                    return false;
                }
                z.pop();
                break;
                case ']' : if (z.isEmpty() || z.peek() != '['){
                    return false;
                }
                z.pop();
                break;
                case '}' : if (z.isEmpty() || z.peek() != '{'){
                    return false;
                }
                z.pop();
                break;
                default: z.push(cha);
            }
        }

        return true;
    }


    /**
     * 生成n对括号的所有情况
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        res = dfs(n, n, "",res);
            return res;
    }
    private static List<String> dfs(int left, int right, String curStr, List<String> res) {
        if (left == 0 && right == 0) { // 左右括号都不剩余了，递归终止
            res.add(curStr);
        }

        if (left > 0) { // 如果左括号还剩余的话，可以拼接左括号
            dfs(left - 1, right, curStr + "(",res);
        }
        if (right > left) { // 如果右括号剩余多于左括号剩余的话，可以拼接右括号
            dfs(left, right - 1, curStr + ")",res);
        }
        return res;
    }



    /**
     *  删除有序数组中的重复项(原地)
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 0;
        while (i<nums.length){
            while (i>0 && nums[i-1] == nums[i]){
                i++;
                if(i==nums.length){
                    return j;
                }
            }
            nums[j++] = nums[i];
            i++;
        }
        return j;
    }

    /**
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j=0 ; j<nums.length ; j++){
            if (nums[j] != val){
                nums[i++] = nums[j];
            }
        }
        return i;
    }


    /**
     * 从一个字符串中找另一个字符串的索引位置
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.length()==0){
            return 0;
        }
        if (needle.length() == haystack.length()){
            return  haystack.equals(needle) ? 0 : -1;
        }
        for (int i = 0 ; i<haystack.length() - needle.length()+1;i++ ){
            if (haystack.charAt(i) == needle.charAt(0)){
                if(needle.equals(haystack.substring(i,(i+needle.length())))){
                    return i;
                }
                continue;
            }
        }
        return -1;
    }


}
