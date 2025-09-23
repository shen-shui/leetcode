import java.util.Arrays;

class Solution {
    /**
     * 二分答案 time
     */
    // [)
    public int shipWithinDays(int[] weights, int days) {
        int sum = Arrays.stream(weights).sum();
        int left = sum / days;
        int right = sum + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (!check(weights, mid, days)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean check(int[] weights, int w, int days) {
        int sum = 0;
        int day = 1;
        for (int weight : weights) {
            if (weight > w) return false;
            sum += weight;
            if (sum > w) {
                sum = weight;
                day++;
            }
            if (day > days) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));
    }
}