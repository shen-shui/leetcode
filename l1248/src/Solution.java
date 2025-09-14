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


    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        // 记录 > k 个奇数的个数
        int cnt1 = 0, left1 = 0;
        // 记录 >= k 个奇数的个数
        int cnt2 = 0, left2 = 0;

        for (int right = 0; right < n; right++) {
            if (nums[right] % 2 == 1) {
                cnt1++;
                cnt2++;
            }

            while (cnt1 > k) {
                if (nums[left1++] % 2 == 1) cnt1--;
            }

            while (cnt2 >= k) {
                if (nums[left2++] % 2 == 1) cnt2--;
            }

            ans += left2 - left1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numberOfSubarrays(new int[]{1,1,2,1,1}, 3));
    }
}

