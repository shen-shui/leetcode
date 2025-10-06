import java.util.Arrays;

class Solution {
    /**
     *  二分求最大
     *  实现很简单，但复杂度较高
     */
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length;
        int[] diff = new int[n];
        for (int i = 1; i < n; i++) {
            diff[i] = Math.max(heights[i] - heights[i - 1], 0);
        }

        int left = -1;
        int right = n;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int[] subDiff = Arrays.copyOfRange(diff, 0, mid + 1);
            Arrays.sort(subDiff);
            if (check(subDiff, bricks, ladders)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean check(int[] diff, int bricks, int ladders) {
        for (int i = 0; i < diff.length - ladders; i++) {
            bricks -= diff[i];
            if (bricks < 0) return false;
        }
        return true;
    }

    /**
     *  更简单的思路：
     *  优先使用梯子，没有梯子就去找差值最小的梯子，给他换成转
     *  未实现
     */

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.furthestBuilding(new int[]{4,2,7,6,9,14,12}, 5, 1));
    }
}