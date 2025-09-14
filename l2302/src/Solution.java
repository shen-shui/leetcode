public class Solution {
    // 不定长滑动窗口
//    //外层循环扩展右边界，内层循环扩展左边界
//    for (int l = 0, r = 0 ; r < n ; r++) {
//        //当前考虑的元素
//        while (l <= r && check()) {//区间[left,right]不符合题意
//            //扩展左边界
//        }
//        //区间[left,right]符合题意，统计相关信息
//    }

    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;

        long ans = 0, count = 0;
        int left = 0;
        // 前缀和数组
        // 因为每次增加都会把除当前位置以外的num重新添加一遍  前缀和数组更方便添加
        long[] prefix = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefix[i] = prefix[i-1] + nums[i-1];
        }

        for (int right = 0; right < n; right++) {
            // count += 当前数字 * 数组长度 + 之前的数组之和
            count += (long) nums[right] * (right - left + 1) + prefix[right] - prefix[left];

            while (count >= k) {
                // count -= 最先进来的数组 * 数组长度 - 后面的数组之和
                count -= (long) nums[left] * (right - left + 1) + prefix[right + 1] - prefix[left + 1];
                left++;
            }

            ans += right - left + 1;
        }
        return ans;
    }

    public long countSubarraysOptim(int[] nums, long k) {
        int n = nums.length;

        long ans = 0, count = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            count += nums[right];

            while (count * (right - left + 1) >= k) {
                count -= nums[left++];
            }

            ans += right - left + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countSubarrays(new int[]{2,1,4,3,5}, 10));
        System.out.println(solution.countSubarraysOptim(new int[]{2,1,4,3,5}, 10));

    }
}

