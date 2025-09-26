import java.util.Arrays;

class Solution {
    /**
     * 二分答案 t
     * 关键在于如何枚举有效子串个数
     */
    // ()
    public int minTime(String s, int[] order, int k) {
        int n = s.length();
        if ((long)n * (n + 1) / 2 < k) return -1;
        // 创建ans数组避免因为变 * 一直调整 s
        int[] ans = new int[n];
        int left = -1;
        int right = n;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (!check(ans, mid, order, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private boolean check(int[] ans, int mid, int[] order, int k) {
        // mid的原因是
        //      我想要直接将mid填入ans中
        //      这样每次更新我只要把相应位置替换为mid
        //      就知道哪些位置此次是 *
        //      哪些位置不是
        // mid++的原因是
        //      由于mid可能为0
        //      而ans初始没有赋值也是0
        //      所以mid++做出调整
        //      不会出现0
        //      导致无法计算
        mid++;
        for (int i = 0; i < mid; i++) {
            ans[order[i]] = mid;
        }
        // 滑动窗口求数组中带 * 的个数
        int left = -1;
        int res = 0;
        for (int right = 0; right < ans.length; right++) {
            if (ans[right] == mid) {
                left = right;
            }
            res += left + 1;
            if (res >= k) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minTime("cat", new int[]{0,2,1}, 6));
    }
}