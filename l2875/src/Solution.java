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

    public int minSizeSubarray(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
        }
        // 至多循环多少个sum可以够target + 1
        int cntCir = target / sum + 2;

        sum = 0;
        int left = 0;
        for (int right = 0; right < cntCir * n; right++) {
            sum += nums[right % n];

            while (sum > target) {
                sum -= nums[left % n];
                left++;
            }

            if(sum == target) ans = Math.min(ans, right - left + 1);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // 上述办法我选用至多n组循环可以达到target
    // 导致运行时间很长，因为滑动窗口要滑动 n * nums.length 的长度来找到最短的和
    // 1780 ms

    // 灵神思路
    //      我们不需要循环这么多轮，因为 target = k * sum + rem
    //      rem只需要两轮就能确定，所以可以使我们的循环大大减少
    // 8ms
    public int minSizeSubarrayOptim(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
        }
        // 至少需要多少轮可以做到
        int cntCir = target / sum;
        // 两轮循环只需要记录target % sum就行，后续加上cntCir * n
        int sumDes = target % sum;

        sum = 0;
        int left = 0;
        for (int right = 0; right < 2 * n; right++) {
            sum += nums[right % n];

            while (sum > sumDes) {
                sum -= nums[left % n];
                left++;
            }

            if(sum == sumDes) ans = Math.min(ans, right - left + 1);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans + cntCir * n;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minSizeSubarray(new int[]{1,2,3}, 5));
        System.out.println(solution.minSizeSubarray(new int[]{2,1,5,7,7,1,6,3}, 39));

    }
}

