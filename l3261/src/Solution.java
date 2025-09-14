import java.util.Arrays;
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

    // 优化后仍然超时   不知道 前缀和 和 二分算法 起到了什么作用
    public long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
        int n = s.length();
        char[] c = s.toCharArray();
        int nq = queries.length;
        int[] leftSum = new int[n];
        long[] ans = new long[nq];

        int left = 0, cnt0 = 0, cnt1 = 0;
        for (int right = 0; right < n; right++) {
            if (c[right] == '0') cnt0++;
            else cnt1++;

            while (!(cnt0 <= k || cnt1 <= k)) {
                if (c[left] == '0') cnt0--;
                else cnt1--;
                left++;
            }

            leftSum[right] = left;
        }

        for (int i = 0; i < nq; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            // j是给定的右端点，来寻找左端点
            for (int j = start; j <= end; j++) {
                // 如果右端点j对应的左端点 >= 给定的左端点
//                if (leftSum[j] >= start) {
//                    ans[i] += j - leftSum[j] + 1;
//                } else {
//                    ans[i] += j - start + 1;
//                }
                // 可以合并
                ans[i] += j - Math.max(leftSum[j], start) + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] queries = new int[][]{
                {0,6}
        };
        System.out.println(Arrays.toString(solution.countKConstraintSubstrings("0001111", 2, queries)));
    }
}

