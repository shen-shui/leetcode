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

    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int ans = 0;
        int cnt = 0;
        for (int l = 0, r = 0; r < fruits.length; r++) {
            cnt += fruits[r][1];
            while (l <= r && Math.min(Math.abs(startPos - fruits[l][0]), Math.abs(startPos - fruits[r][0])) + fruits[r][0] - fruits[l][0] > k) {
                cnt -= fruits[l][1];
                l++;
            }
            ans = Math.max(ans, cnt);
        }
//        cnt = 0;
//        for (int l = 0, r = 0; r < fruits.length; r++) {
//            cnt += fruits[r][1];
//            while (l <= r && Math.abs(startPos - fruits[r][0]) + fruits[r][0] - fruits[l][0] > k) {
//                cnt -= fruits[l][1];
//                l++;
//            }
//            ans = Math.max(ans, cnt);
//        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxTotalFruits(new int[][]{
                {2, 8},
                {4, 3},
                {8, 6},
//                {0, 9},
//                {4, 1},
//                {5, 7},
//                {6, 2},
//                {7, 4},
//                {10, 9}
        }, 5, 4));
    }
}

