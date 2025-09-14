import java.util.*;

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

    /**
     * 右端点一直留在每一段的右边界上，停留在中间的话只会 <= 停留在右边界上的覆盖数量
     *
     */
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, (a, b) -> a[0] - b[0]);
        int ans = 0;
        int cover = 0;
        int l = 0;
        for (int[] tile : tiles) {
            int tl = tile[0];
            int tr = tile[1];
            cover += tr - tl + 1;

            int carpetLeft = tr - carpetLen + 1;// 左端点位置
            while (tiles[l][1] < carpetLeft) { // tiles[l]完全不在窗口内
                cover -= tiles[l][1] - tiles[l][0] + 1;
                l++;
            }

            // tiles[l]在窗口内
            int uncover = Math.max(carpetLeft - tiles[l][0], 0);
            ans = Math.max(ans, cover - uncover);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumWhiteTiles(new int[][]{
                {1, 5},
                {10, 11},
                {12, 18},
                {20, 25},
                {30, 32}
        }, 10));
    }
}

