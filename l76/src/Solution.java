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

    public String minWindow(String s, String t) {
        int ns = s.length();
        int nt = t.length();
        if (ns < nt) return "";

        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();

        int[] cntT = new int[123];
        // 记录t中出现的字母数
        int cnt = 0;
        for (char c : ct) {
            if (cntT[c] == 0) cnt++;
            cntT[c]++;
        }

        int ans = Integer.MAX_VALUE;
        String subS = "";
        int left = 0;
        for (int right = 0; right < ns; right++) {
            cntT[cs[right]]--;
            if (cntT[cs[right]] == 0) cnt--;

            while (cnt == 0) {
                // 记录答案
                if (ans > right - left + 1) {
                    ans = right - left + 1;
                    subS = s.substring(left, right + 1);
                }
                // 更新左端点
                cntT[cs[left]]++;
                if (cntT[cs[left]] == 1) cnt++;
                left++;
            }
        }
        return subS;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(solution.minWindow("a", "a"));

    }
}

