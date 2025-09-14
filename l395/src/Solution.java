import java.util.Arrays;
import java.util.HashMap;

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

    public int longestSubstring(String s, int k) {
        int n = s.length();
        int ans = 0;
        for (int i = 1; i <= 26; i++) {
            int[] cnt = new int[123];
            int count = 0;
            int res = 0;
            int left = 0;
            for (int right = 0; right < n; right++) {
                int indexR = s.charAt(right);
                cnt[indexR]++;
                if (cnt[indexR] == 1) {
                    count++;
                }
                if (cnt[indexR] == k) {
                    res++;
                }

                while (count > i) {
                    int indexL = s.charAt(left);
                    left++;
                    cnt[indexL]--;
                    if (cnt[indexL] == 0) {
                        count--;
                    }
                    if (cnt[indexL] == k - 1) {
                        res--;
                    }
                }

                if (res == i) ans = Math.max(ans, right - left + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestSubstring("ababbc", 2));
    }
}

