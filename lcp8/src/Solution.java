import java.util.Arrays;

class Solution {
    /**
     * 一眼  前缀和 + 二分
     */
    private record Packet(int C, int R, int H) {};

    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int n = increase.length;
        for (int i = 1; i < n; i++) {
            increase[i][0] += increase[i - 1][0];
            increase[i][1] += increase[i - 1][1];
            increase[i][2] += increase[i - 1][2];
        }
        int m = requirements.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            Packet packet = new Packet(requirements[i][0], requirements[i][1], requirements[i][2]);
            if (requirements[i][0] == 0 && requirements[i][1] == 0 && requirements[i][2] == 0){
                ans[i] = 0;
                continue;
            }
            // >= packet
            int idx = lowerBound(increase, packet);
            if (idx == n) {
                ans[i] = -1;
            } else {
                ans[i] = idx + 1;
            }
        }
        return ans;
    }

    // []
    private int lowerBound(int[][] nums, Packet packet) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Packet pack = new Packet(nums[mid][0], nums[mid][1], nums[mid][2]);
            if (comparePacket(pack, packet)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean comparePacket(Packet p1, Packet p2) {
        if (p1.C < p2.C || p1.H < p2.H || p1.R < p2.R) return true;
        else return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] increase = new int[][]{{2,8,4},{2,5,0},{10,9,8}};
        int[][] requirements = new int[][]{{2,11,3},{15,10,7},{9,17,12},{8,1,14}};
        System.out.println(Arrays.toString(solution.getTriggerTime(increase, requirements)));
    }
}