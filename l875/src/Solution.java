import java.util.Arrays;

class Solution {
    /**
     * 二分答案 time
     */
    // ()
    public int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        int n = piles.length;
        int left = piles[0] * n / h - 1;
        int right = piles[n - 1] + 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (!check(piles, mid, h)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private boolean check(int[] piles, int k, int h) {
        int sum = 0;
        if (k == 0) return false;
        for (int pile : piles) {
            sum += (pile + k - 1) / k;
            if (sum > h) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minEatingSpeed(new int[]{312884470}, 968709470));
    }
}