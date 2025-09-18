import java.util.ArrayList;
import java.util.List;

class Solution {

    /**
     * 二分+双指针
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        // 找到 >= x的下标
        int idx = lowerBound(arr, x);
        int left = 0, right = 0;
        if (idx != n) {
            // 如果没有x 先确保是否x右边这个数比x左边的大
            if (arr[idx] != x && idx != 0) {
                // 如果左边的更小，那么左边位双指针起点
                if (x - arr[idx - 1] <= arr[idx] - x) idx = idx - 1;
            }
            left = idx - 1;
            right = idx + 1;
            int cnt = 1;
            while (cnt < k) {
                if (left >= 0 && right < n) {
                    if (x - arr[left] <= arr[right] - x) {
                        left--;
                    } else {
                        right++;
                    }
                } else if (left < 0) {
                    right++;
                } else {
                    left--;
                }
                cnt++;
            }
        } else {
            right = n;
            left = n - k - 1;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = left + 1; i < right; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    //[]
    private int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     *  滑动窗口做法
     */
    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        int n = arr.length;
        int left = 0;
        int l = 0;
        int minSub = Integer.MAX_VALUE;
        int sum = 0;
        for (int right = 0; right < n; right++) {
            sum += Math.abs(arr[right] - x);

            if (right - left + 1 > k) {
                sum -= Math.abs(arr[left] - x);
                left++;
            }

            if (minSub > sum && right - left + 1 == k) {
                minSub = sum;
                l = left;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = l; i < l + k; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findClosestElements(new int[]{3,5,8,10}, 2, -1).toString());
    }
}