import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * 二分答案 t
     * 屎山 测了好几次把特殊数据排除了做出来了
     */
    // ()
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        int left = n + sum - 1;
        int right = changeIndices.length + 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (!check(changeIndices, nums, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right == changeIndices.length + 1 ? -1 : right;
    }

    private boolean check(int[] changeIndices, int[] nums, int mid) {
        int n = nums.length;
        int[] flag = new int[n];
        int len = 0;
        int cnt = mid;
        for (int i = mid - 1; i >= 0; i--) {
            int index = changeIndices[i];
            if (flag[index - 1] == 1 && len == 1 && nums[index - 1] == 0) {
                cnt--;
            }
            if (nums[index - 1] <= Math.min(cnt - 1, i) && flag[index - 1] != 1) {
                flag[index - 1] = 1;
                len++;
                cnt -= (nums[index - 1] + 1);

                if (cnt < 0) return false;
            }
            if (len == n) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.earliestSecondToMarkIndices(new int[]{3,0,5,2,0,2}, new int[]{3,3,1,5,6,2,4,2,4,4,4,1,3,6,5,1,5,5,1,2,5}));
    }
}