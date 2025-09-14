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

    public int equalSubstring(String S, String T, int maxCost) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int n = s.length;
        int cnt = 0;
        int res = 0;
        for (int l = 0, r = 0; r < n; r++) {
            int abs = Math.abs(s[r] - t[r]);
            cnt += abs;
            while (cnt > maxCost) {
                cnt -= Math.abs(s[l] - t[l++]);
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.equalSubstring("abcd", "cdef", 3));
    }
}