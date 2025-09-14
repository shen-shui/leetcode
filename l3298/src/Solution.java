import java.util.HashMap;
import java.util.Map;

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


    public long validSubstringCount(String word1, String word2) {
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();

        // 记录c2有多少种字母
        int count = 0;
        int[] cnt = new int[26];
        for (char c : c2) {
            cnt[c - 97]++;
            if (cnt[c - 97] == 1) count++;
        }

        long ans = 0;
        int left = 0;
        for (int right = 0; right < c1.length; right++) {
            char c = c1[right];
            cnt[c - 97]--;
            if (cnt[c - 97] == 0) count--;

            while (count == 0) {
                c = c1[left++];
                cnt[c - 97]++;
                if (cnt[c - 97] == 1) count++;
            }

            ans += left;
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.validSubstringCount("bcca", "abc"));
    }
}

