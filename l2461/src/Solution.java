import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        long sum = 0, res = 0;
        for (int i = 0; i < n; i++) {
            // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
            int num = nums[i];
            sum += num;
            map.merge(num, 1, Integer::sum);
            if (i + 1 < k) continue;

            // 更新：更新答案。一般是更新最大值/最小值。
            if (map.size() == k) res = Math.max(sum, res);

            // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。
            num = nums[i + 1 - k];
            sum -= num;
            if (map.get(num) == 1) {
                map.remove(num);
            } else {
                map.put(num, map.get(num)-1);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumSubarraySum(new int[]{1,5,4,2,9,9,9}, 3));
    }
}