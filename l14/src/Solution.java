public class Solution {
    public String longestCommonPrefix(String[] strs) {
        boolean flag = true;
        for (int i = 1; i <= strs[0].length(); i++) {
            String s = strs[0].substring(0, i);
            for (int j = 1; j < strs.length; j++) {
                if (!strs[j].startsWith(s)) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                return strs[0].substring(0, i - 1);
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestCommonPrefix(new String[]{"ab", "a"}));
    }
}