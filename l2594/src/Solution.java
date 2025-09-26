import java.util.Arrays;

class Solution {
    /**
     * 二分答案 t
     * 简直就是数学问题
     */
    // ()
    public long repairCars(int[] ranks, int cars) {
        int n = ranks.length;
        Arrays.sort(ranks);
        // 每个人修的车要向上取整，来计算修理所需最大时间和最小时间
        int everyCar = cars / n;
        long left = (long) ranks[0] * everyCar * everyCar - 1;
        long right = (long) ranks[n - 1] * cars * cars;
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (!check(ranks, mid, cars)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private boolean check(int[] ranks, long mid, int cars) {
        int sum = 0;
        for (int rank : ranks) {
            sum += (int) Math.sqrt((double) mid / rank);
            if (sum >= cars) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.repairCars(new int[]{3,3,5,3,2}, 4));
    }
}