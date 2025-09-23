import java.util.Arrays;

class Solution {
    /**
     * 二分答案 time
     */
    // []
    public long minimumTime(int[] time, int totalTrips) {
        Arrays.sort(time);
        long left = 1;
        long right = (long) totalTrips * time[0];
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (!check(time, mid, totalTrips)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int[] times, long t, int totalTrips) {
        long sum = 0;
        for (int time : times) {
            sum += t / time;
            if (sum >= totalTrips) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minimumTime(new int[]{1,2,3}, 5));
    }
}