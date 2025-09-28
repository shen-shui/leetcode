import java.util.Arrays;
import java.util.Collections;

class Solution {

    /**
     *  二分求最大
     *  感觉有点像由果索因
     *          看到题目首先想的是应该给多少块糖合适，但是二分的方式是我给你多少颗糖，你看这糖够分吗？不够的话就不行
     *          (只能用于有序，我给你2块糖行 -> 给你1块也行  必须单调)
     */
    public int maximumCandies(int[] candies, long k) {
        // 使用流 API 进行倒序排序
        int[] sorted = Arrays.stream(candies)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        long sum = Arrays.stream(sorted).asLongStream().sum();
        if (sum < k) return 0;
        int left = -1;
        int right = sorted[0] + 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check(sorted, mid, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean check(int[] sorted, int mid, long k) {
        if (mid == 0) return true;
        long sum = 0;
        for (int candy : sorted) {
            int person = candy / mid;
            sum += person;
            if (person == 0) break;
        }
        return sum >= k;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumCandies(new int[]{2,5}, 11));
    }
}