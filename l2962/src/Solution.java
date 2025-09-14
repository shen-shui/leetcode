import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;

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


    public long countSubarrays(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
        int n = nums.length;

        long ans = 0;
        int left = 0, cnt = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] == max) cnt++;

            while (cnt >= k) {
                if (nums[left++] == max) cnt--;
            }

            ans += left;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countSubarrays(new int[]{1,3,2,3,3}, 2));
    }
}

