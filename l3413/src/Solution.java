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

    // 相当于带权的2271，需要从左往右计算一次，从右往左计算一次
    public long maximumCoins(int[][] coins, int k) {
        Arrays.sort(coins, (a, b) -> (a[0] - b[0]));
        long ans = 0;
        long cover = 0;
        int l = 0, r = 0;
        for (int[] coin : coins) {
            int tl = coin[0], tr = coin[1], c = coin[2];
            cover += (long) (tr - tl + 1) * c;
            while (coins[l][1] + k - 1 < tr) {
                cover -= (long) (coins[l][1] - coins[l][0] + 1) * coins[l][2];
                l++;
            }
            long uncover = Math.max((long) (tr - k + 1 - coins[l][0]) * coins[l][2], 0);
            ans = Math.max(ans, cover - uncover);
        }

        for (int i = 0, j = coins.length - 1; i < j; i++, j--) {
            int[] tmp = coins[i];
            coins[i] = coins[j];
            coins[j] = tmp;
        }
        cover = 0;
        for (int[] coin : coins) {
            int tl = coin[0], tr = coin[1], c = coin[2];
            cover += (long) (tr - tl + 1) * c;
            while (coins[r][0] - k + 1 > tl) {
                cover -= (long) (coins[r][1] - coins[r][0] + 1) * coins[r][2];
                r++;
            }
            long uncover = Math.max((long) (coins[r][1] - tl - k + 1) * coins[r][2], 0);
            ans = Math.max(ans, cover - uncover);
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] exp = new int[][]{{39,41,18},{3,4,16},{27,31,18},{16,19,14},{37,38,16},{11,14,17},{20,25,10},{34,35,20}};
        System.out.println(solution.maximumCoins(exp, 46));
    }
}

