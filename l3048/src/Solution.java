import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * 二分答案 t
     * 屎山 测了好几次把特殊数据排除了做出来了 check()
     */
    // ()
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        int left = n + sum - 1;
        int right = changeIndices.length + 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (!checkOpt(changeIndices, nums, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right == changeIndices.length + 1 ? -1 : right;
    }

    private boolean check(int[] changeIndices, int[] nums, int mid) {
        int n = nums.length;
        int[] flag = new int[n];
        int len = 0;
        int cnt = mid;
        for (int i = mid - 1; i >= 0; i--) {
            int index = changeIndices[i];
            if (flag[index - 1] == 1 && len == 1 && nums[index - 1] == 0) {
                cnt--;
            }
            if (nums[index - 1] <= Math.min(cnt - 1, i) && flag[index - 1] != 1) {
                flag[index - 1] = 1;
                len++;
                cnt -= (nums[index - 1] + 1);

                if (cnt < 0) return false;
            }
            if (len == n) return true;
        }
        return false;
    }

    private boolean checkOpt(int[] changeIndices, int[] nums, int mid) {
        // 记录可以用来减num的时间
        int cnt = 0;
        // 记录已经标记了多少个，总共需要标记 nums.length 个
        // 还可以优化，直接初始化pos数组全部为-1，如果某一个位置更新完最后位置还是-1，就说明当前时间无法标记该位置，直接返回false
        int flag = 0;
        // 使用pos记录在mid时间下，最后一次出现某下标的下标，最后一次出现意味着必须要进行标记，否则会失败
        int[] pos = new int[nums.length + 1];
        int len = Math.min(mid, changeIndices.length);
        for (int i = 0; i < len; i++) {
            pos[changeIndices[i]] = i;
        }

        for (int i = 0; i < len; i++) {
            if (i == pos[changeIndices[i]]) {
                flag++;
                cnt -= nums[changeIndices[i] - 1];
                if (cnt < 0) return false;
                if (flag == nums.length) return true;
            } else {
                cnt++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.earliestSecondToMarkIndices(new int[]{2,2,0}, new int[]{2,2,2,2,3,2,2,1}));
    }
}