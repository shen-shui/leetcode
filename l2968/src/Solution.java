import java.util.Arrays;

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


    // 中位数贪心:在本题中，将所有元素变成中位数的代价最小
    public int maxFrequencyScore(int[] nums, long k) {
        int n = nums.length;
        Arrays.sort(nums);

        // 前缀和:快速计算出需要的操作数
        long[] s = new long[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = 0, left = 0;
        for (int right = 0; right < n; right++) {
            int mid = (right + left) / 2;
            long leftOp = (long) nums[mid] * (mid - left) - (s[mid] - s[left]);
            long rightOp = s[right + 1] - s[mid + 1] - (long) nums[mid] * (right - mid);
            while (leftOp + rightOp > k) {
                left++;
                mid = (right + left) / 2;
                leftOp = (long) nums[mid] * (mid - left) - (s[mid] - s[left]);
                rightOp = s[right + 1] - s[mid + 1] - (long) nums[mid] * (right - mid);
            }

            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    // 直接滑动窗口
    // 详见https://www.bilibili.com/video/BV1994y1A7oo/?vd_source=e78b29193429a5cd31e25c3e32ff2b5d
    // 无论如何计算都是中位数右边几个数减去中位数左边几个数
    public int maxFrequencyScore1(int[] nums, long k) {
        int n = nums.length;
        Arrays.sort(nums);

        int ans = 0, left = 0;
        long s = 0;
        for (int right = 0; right < n; right++) {
            s += nums[right] - nums[(left + right)/2];
            while (s > k) {
                s += nums[left] - nums[(left + right + 1)/2];
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxFrequencyScore(new int[]{1,2,6,4}, 3));
        System.out.println(solution.maxFrequencyScore1(new int[]{1,2,6,4}, 3));

    }
}

