import java.util.*;

public class Solution {
    /**
     * 红黑树
     * @param nums
     * @param indexDiff
     * @param valueDiff
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        int n = nums.length;
        TreeSet<Long> ts = new TreeSet<>();
        for (int i = 0; i < n; i++) {// 滑动窗口长度为indexDiff,只需考虑最后一个条件即可
            Long u = (long) nums[i];
            // 从ts中找到小于等于u的最大值
            Long l = ts.floor(u);
            // 从ts中找到大于等于u的最小值
            Long r = ts.ceiling(u);
            if (l != null && u - l <= valueDiff) return true;
            if (r != null && r - u <= valueDiff) return true;
            // 将当前数加到ts中,并移除下标范围不在indexDiff范围的值
            ts.add(u);
            if (i >= indexDiff) ts.remove((long) nums[i - indexDiff]);
        }
        return false;
    }

    /**
     * 桶排序
     * @param nums
     * @param indexDiff
     * @param valueDiff
     * @return
     */
    long size;
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int indexDiff, int valueDiff) {
        int n = nums.length;
        Map<Long, Long> map = new HashMap<>();
        size = valueDiff + 1;
        for (int i = 0; i < n; i++) {
            long u = nums[i];
            long idx = getIdx(u);
            // 目标桶已存在,说明前面已有[u - valueDiff, u + valueDiff]范围的数字
            if (map.containsKey(idx)) return true;
            // 检查相邻的桶
            long l = idx - 1, r = idx + 1;
            if (map.containsKey(l) && u - map.get(l) <= valueDiff) return true;
            if (map.containsKey(r) && map.get(r) - u <= valueDiff) return true;
            // 建立目标桶
            map.put(idx, u);
            // 移除下标范围不在[max(0, i - k), i)中的桶
            if (i >= indexDiff) map.remove(getIdx(nums[i - indexDiff]));
        }
        return false;
    }

    private long getIdx(long u) {
        return u >= 0 ? u / size : ((u + 1) / size) - 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.containsNearbyAlmostDuplicate(new int[]{1,2,3,1}, 3, 0));
        System.out.println(solution.containsNearbyAlmostDuplicate1(new int[]{1,2,3,1}, 3, 0));

    }
}