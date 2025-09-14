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

    public String longestNiceSubstring(String s) {
        int n = s.length();
        int ans = 0;
        String s1 = "";
        for (int i = 1; i <= 26; i++) {
            int left = 0;
            int[] cntX = new int[26];
            int[] cntx = new int[26];
            int count = 0;
            int res = 0;
            for (int right = 0; right < n; right++) {
                int indexR = 0;
                if (s.charAt(right) >= 97) {
                    indexR = s.charAt(right) - 97;
                    cntX[indexR]++;
                    if (cntX[indexR] == 1) {
                        if (cntx[indexR] > 0) {
                            res++;
                        }
                        count++;
                    }
                } else {
                    indexR = s.charAt(right) - 65;
                    cntx[indexR]++;
                    if (cntx[indexR] == 1) {
                        if (cntX[indexR] > 0) {
                            res++;
                        }
                        count++;
                    }
                }
                while (count > 2 * i) {
                    int indexL = 0;
                    if (s.charAt(left) >= 97) {
                        indexL = s.charAt(left) - 97;
                        cntX[indexL]--;
                        if (cntX[indexL] == 0) {
                            if (cntx[indexL] > 0) {
                                res--;
                            }
                            count--;
                        }
                    } else {
                        indexL = s.charAt(left) - 65;
                        cntx[indexL]--;
                        if (cntx[indexL] == 0) {
                            if (cntX[indexL] > 0) {
                                res--;
                            }
                            count--;
                        }
                    }
                    left++;
                }
                if (res == i) {
                    if (ans < right - left + 1) {
                        s1 = s.substring(left, right + 1);
                    }
                    ans = Math.max(ans, right - left + 1);
                }
            }
        }
        return s1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestNiceSubstring("dDzeE"));
    }
}

