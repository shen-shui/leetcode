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

    /**
     *  滑动窗口 + 动态规划
     */
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        if (k * 2 + 1 >= prizePositions[n - 1] - prizePositions[0]) return n;
        int ans = 0, left = 0;
        int[] mx = new int[n + 1];
        for (int right = 0; right < n; right++) {
            while (prizePositions[right] - prizePositions[left] > k) {
                left++;
            }
            // 正常滑动窗口更新答案应该是 ans = Math.max(ans, right -left + 1)
            // 因为本题为两条线段，所以在滑动窗口的左端点开始移动时，我们也要开始计算左端点左侧的第一条线段的最大值也就是mx[left]
            ans = Math.max(ans, mx[left] + right -left + 1);
            // 动态规划
            // 更新左端点第一条线段的最大值
            // 由于right left在一直移动符合题目中小于k的标准，所以对于每个right我们可以实时获取到他左侧的最大值
            // 并且不会出现left位于某个位置的中间， 比如 [1 1 1 2] left只会位于第一个1或者第一个2
            // 由于滑动窗口已经获取了左端点，所以动态规划就要抛弃右端点，也就是使用下标+1，这样计算最大值的时候就是<=index-1
            mx[right + 1] = Math.max(mx[right], right -left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximizeWin(new int[]{1,1,2,2,3,3,5}, 2));
    }
}

