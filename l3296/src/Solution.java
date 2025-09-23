import java.util.Arrays;

class Solution {
    /**
     * 二分答案 time
     * 💩绕迷糊了 没做出来
     */
    // ()
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        Arrays.sort(workerTimes);
        int n = workerTimes.length;
        long left = (long) (Math.sqrt((double) (mountainHeight * 2) / (n * workerTimes[0])) - 1);
        long right = (long) (Math.sqrt((double) (mountainHeight * 2) / (n * workerTimes[n - 1])));
        while (left + 1 < right) {
             long mid = left + (right - left) / 2;
             if (!check(workerTimes, mid, mountainHeight)) {
                 left = mid;
             } else {
                 right = mid;
             }
        }
        return right;
    }

    private boolean check(int[] workerTimes, long t, int mountainHeight) {
        int sum = 0;
        for (int workerTime : workerTimes) {
            sum += (int) Math.sqrt((double) (2 * t) / workerTime);
            if (sum > mountainHeight) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minNumberOfSeconds(4, new int[]{2,1,1}));
    }
}