import java.util.*;

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


    public int countCompleteSubarrays(int[] nums) {
        int n = nums.length;
        int count = (int)Arrays.stream(nums).distinct().count();

        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            map.merge(nums[right], 1, Integer::sum);

            while (map.size() == count) {
                int num = nums[left++];
                map.merge(num, -1, Integer::sum);
                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }

            ans += left;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countCompleteSubarrays(new int[]{1,3,1,2,2}));
    }
}

