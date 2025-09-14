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

    public int balancedString(String s) {
        int n = s.length();
        int balance = n / 4;
        int cntBal = 0;
        int maxBal = 0;
        int ans = Integer.MAX_VALUE;
        // 创建一个存储QWER的数组,利用ascii码表，不用复杂的计算该给哪个字符加数量
        int[] cnt = new int[88];
        for (char c : s.toCharArray()) {
            cnt[c]++;
        }
        // 记录已经balance的数量
        for (int i = 69; i < 88; i++) {
            if (cnt[i] >= balance) {
                maxBal++;
                if (cnt[i] == balance) cntBal++;
            }
        }
        if (maxBal == cntBal) return 0;

        int left = 0;
        for (int right = 0; right < n; right++) {
            int indexR = s.charAt(right);
            cnt[indexR]--;
            if (cnt[indexR] == balance) cntBal++;

            while (cntBal == maxBal) {
                ans = Math.min(ans, right - left + 1);
                int indexL = s.charAt(left);
                cnt[indexL]++;
                if (cnt[indexL] == balance + 1) {
                    cntBal--;
                }
                left++;
            }
        }

        // 灵神循环:
        //      只需要判断是否QWER均 <= balance
        //      如果均满足
        //      那一定是全部 == balance
        //      得到一个答案后移动左端点
        //      直到某一个字母 > balance
        //      开始寻找下一个子串
//        for (int right = 0; right < n; right++) { // 枚举子串右端点
//            cnt[s[right]]--;
//            while (cnt['Q'] <= m && cnt['W'] <= m && cnt['E'] <= m && cnt['R'] <= m) {
//                ans = Math.min(ans, right - left + 1);
//                cnt[s[left]]++; // 缩小子串
//                left++;
//            }
//        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.balancedString("WQWRQQQW"));
    }
}

