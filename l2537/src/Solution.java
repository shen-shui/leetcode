import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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


    public long countGood(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        long ans = 0;
        int cnt = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            int num = nums[right];
            map.merge(num, 1, Integer::sum);
            cnt += map.get(num) - 1;

            while (cnt >= k) {
                num = nums[left++];
                map.merge(num, -1, Integer::sum);
                cnt -= map.get(num);
            }

            ans += left;
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countGood(new int[]{3,1,4,3,2,2,4}, 2));
    }
}

