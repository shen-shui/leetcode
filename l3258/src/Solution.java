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

    public int countKConstraintSubstrings(String s, int k) {
        int n = s.length();
        char[] c = s.toCharArray();

        int ans = 0, left = 0;
        int cnt0 = 0, cnt1 = 0;

        for (int right = 0; right < n; right++) {
            // 扩展右边界
            if (c[right] == '0') {
                cnt0++;
            } else {
                cnt1++;
            }

            // 收缩左边界
            while (cnt0 > k && cnt1 > k) {
                if (c[left] == '0') {
                    cnt0--;
                } else {
                    cnt1--;
                }
                left++;
            }

            // 更新答案
            ans += right - left + 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countKConstraintSubstrings("10101", 1));
    }
}

