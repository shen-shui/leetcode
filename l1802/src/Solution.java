import java.util.Arrays;

class Solution {
    /**
     *  二分求最大
     */
    public int maxValue(int n, int index, int maxSum) {
        int left = 0;
        int right = maxSum - n + 2;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check(n, mid, index, maxSum)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean check(int n, int mid, int index, int maxSum) {
        long sum = 0;
        // 向前推，不包含mid
        // 末项 mid - 1
        // 首项 index + 1 >= mid  -> 1
        //      index + 1 < mid -> mid - index
        if (index - mid + 1 >= 0) {
            sum += (long) mid * (mid - 1) / 2 + index - mid + 1;
        } else {
            sum += (2L * mid - index - 1) * index / 2;
        }
        if (sum > maxSum) return false;
        // 向后推
        // 首项 mid
        // 末项
        if (n - index - mid >= 0) {
            sum += (long) mid * (mid + 1) / 2 + n - index - mid;
        } else {
            sum += (2L * mid - n + index + 1) * (n - index) / 2;
        }
        if (sum > maxSum) return false;
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxValue(685453290, 293811406, 689728311));
    }
}