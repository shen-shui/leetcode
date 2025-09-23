import java.util.Arrays;

class Solution {

    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        int len = (n + 3) / 4;
        boolean flag = n % 4 > 0;
        for (int i = 0; i < n; i+=len) {
            int start = lowerBound(arr, arr[i]);
            int end = lowerBound(arr, arr[i] + 1) - 1;
            if (flag) {
                if (end - start + 1 >= len) {
                    return arr[start];
                }
            } else {
                if (end - start + 1 > len) {
                    return arr[start];
                }
            }
        }
        return 0;
    }

    // [)
    private int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findSpecialInteger(new int[]{1,2,2,6,6,6,6,7,10}));
    }
}