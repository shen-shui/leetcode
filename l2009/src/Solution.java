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


    /**
     * 思路来自灵神题解
     * 我们只需要把数组去重之后进行滑动窗口
     * 因为要求所有数字必须连续且互不相同
     * 滑动窗口固定长度为 n1 - 1
     * 如果nums[r] - nums[l]的值比 n1 - 1 大的话，就说明不是连续的
     * 我们拿到去重数组的最大包含的值，剩下的值就是要改变的值
     * 这样就可以简单的拿到ans
     */
    public int minOperations(int[] nums) {
        int n1 = nums.length;
        Arrays.sort(nums);
        int n2 = removeDuplicates(nums);

        int ans = 0;
        for (int l = 0, r = 0; r < n2; r++) {
            while (nums[r] - nums[l] > n1 - 1) {
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return n1 - ans;
    }

    /**
     * 高手原地去重
     */
    private int removeDuplicates(int[] nums) {
        int n = nums.length;
        int i = 0, j = 1;
        while (j < n) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
            j++;
        }
        return i + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minOperations(new int[]{4, 2, 5, 3}));
    }
}

