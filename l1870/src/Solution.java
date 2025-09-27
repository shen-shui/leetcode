import java.util.Arrays;

class Solution {
    /**
     * 二分答案 t
     */
    // ()
    public int minSpeedOnTime(int[] dist, double hour) {
        int n = dist.length;
        if (n - 1 >= hour) return -1;
        int sum = Arrays.stream(dist).sum();
        int left = (int) (sum / hour - 1);
        int right = Arrays.stream(dist).max().getAsInt() * 100 + 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (!check(dist, mid, hour)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right == Arrays.stream(dist).max().getAsInt() + 1 ? -1 : right;
    }

    private boolean check(int[] dist, int mid, double hour) {
        double t = 0;
        for (int i = 0; i < dist.length; i++) {
            if (i != dist.length - 1) {
                t += (int) ((dist[i] + mid - 1) / mid);
            } else {
                t += (double) dist[i] / mid;
            }
            if (t > hour) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minSpeedOnTime(new int[]{1,1,100000}, 2.01));
    }
}