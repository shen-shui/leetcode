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

    public int takeCharacters(String s, int k) {
        int n = s.length();
        int cntA = 0, cntB = 0, cntC = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') cntA++;
            else if (c == 'b') cntB++;
            else cntC++;
        }
        if (cntA < k || cntB < k || cntC < k) return -1;

        int ans = 0;

        for (int l = 0, r = 0; r < n; r++) {
            if (s.charAt(r) == 'a') {
                cntA--;
                while (cntA < k) {
                    if (s.charAt(l) == 'a') cntA++;
                    else if (s.charAt(l) == 'b') cntB++;
                    else if (s.charAt(l) == 'c') cntC++;
                    l++;
                }
            } else if (s.charAt(r) == 'b') {
                cntB--;
                while (cntB < k) {
                    if (s.charAt(l) == 'a') cntA++;
                    else if (s.charAt(l) == 'b') cntB++;
                    else if (s.charAt(l) == 'c') cntC++;
                    l++;
                }
            } else if (s.charAt(r) == 'c') {
                cntC--;
                while (cntC < k) {
                    if (s.charAt(l) == 'a') cntA++;
                    else if (s.charAt(l) == 'b') cntB++;
                    else if (s.charAt(l) == 'c') cntC++;
                    l++;
                }
            }
            ans = Math.max(ans, r - l + 1);
        }
        return n - ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.takeCharacters("aabbccca", 2));
    }
}

