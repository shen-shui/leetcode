public class Solution {
    // 不定长滑动窗口
//    //外层循环扩展右边界，内层循环扩展左边界
//    for (int l = 0, r = 0 ; r < n ; r++) {
//        //当前考虑的元素
//        while (l <= r && check()) {//区间[left,right]不符合题意
//            //扩展左边界
//        }
//        //区间[left,right]符合题意，统计相关信息
//    }

    public int longestSemiRepetitiveSubstring(String s) {
        int n = s.length();
        int res = 0;
        int index = -1; // 记录上一个半子字符串的下标
        boolean flag = false; // 记录是否有过半重复子字符串
        for (int l = 0, r = 0; r < n; r++) {
            if (r != 0 && s.charAt(r - 1) == s.charAt(r)) {
                if (index != -1) {
                    l = index;
                }
                index = r;
            }
            res = Math.max(r - l + 1, res);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestSemiRepetitiveSubstring("1111111"));
    }
}