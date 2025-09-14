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

    public int longestNiceSubarray(int[] nums) {
        int ans = 0;
        int or = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            // 优雅子数组指的是所有数字的二进制中某一位只有一个数字可以为1，剩下数字必须为0
            // 进行与操作，如果这两个数二进制中某一位都为1，那么结果>0，也就不符合要求
            while ((or & nums[right]) > 0) {
                // 异或操作，相同为假，相反为真
                // 用或操作加入的元素中只有一个元素某个位置会是1，其他位置都是0
                // 用异或操作来去除left元素，若某位置为1，那么or该位置就变成0，若某位置为0，不会导致or变化
                or ^= nums[left++]; // 从or中去掉集合nums[left];
            }
            // 用或操作来加入新元素，或操作如果有一个是1，那么接下来还是1，不会被0给或成0
            or |= nums[right];
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestNiceSubarray(new int[]{1,3,8,48,10}));
    }
}

