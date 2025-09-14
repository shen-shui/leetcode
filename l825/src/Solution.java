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

    //  ages[x] >= ages[y]
    //  ages[x] < 2 * ages[y] - 14
    public int numFriendRequests(int[] ages) {
        int[] cnt = new int[121];
        for (int age : ages) {
            cnt[age]++;
        }

        int ans = 0;
        int ageY = 15;
        int cntWindow = 0;
        for (int ageX = 15; ageX < cnt.length; ageX++) {
            cntWindow += cnt[ageX];
            while (ageY * 2 <= ageX + 14) {
                cntWindow -= cnt[ageY];
                ageY++;
            }
            if (cntWindow > 0) { // 存在可以发送好友请求的用户
                ans += cnt[ageX] * cntWindow - cnt[ageX];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numFriendRequests(new int[]{108,115,5,24,82}));
    }
}

