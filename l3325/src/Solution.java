import java.util.Arrays;

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


    public int numberOfSubstrings(String s, int k) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[] cnt = new int[26];

        int ans = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            cnt[c[right] - 97]++;

            while (cnt[c[right] - 97] >= k) {
                cnt[c[left++] - 97]--;
            }

            ans += left;
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numberOfSubstrings("abacb", 2));
    }
}

