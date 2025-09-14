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

    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        int ans = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums.get(i), key -> new ArrayList<>()).add(i);
        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> idx = entry.getValue();
            int cnt = 0;
            for (int l = 0, r = 0; r < idx.size(); r++) {
                cnt++;
                while(idx.get(r) - idx.get(l) > k + cnt - 1) {
                    l++;
                    cnt--;
                }
                ans = Math.max(ans, r - l + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestEqualSubarray(Arrays.asList(1,3,2,3,1,3), 3));
    }
}

