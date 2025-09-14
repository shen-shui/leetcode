import java.util.*;

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

    public int longestValidSubstring(String word, List<String> forbidden) {
        Set<String> fb = new HashSet<String>();
        fb.addAll(forbidden);

        int left = 0, ans = 0, n = word.length();
        for (int right = 0; right < n; right++) {
            for (int i = right; i >= left && i > right - 10; i--) {
                if (fb.contains(word.substring(i, right + 1))) {
                    left = i + 1;
                    break;
                }
            }
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestValidSubstring("cbaaaabc", Arrays.asList("aaa", "bc")));
    }
}

