import java.util.Arrays;

class Solution {
    /**
     * 二分答案 radius
     * 二重for循环 可以思考使用 双指针 来优化
     */
    // ()
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int left = -1;
        int right = (int) (1e9 + 1);
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (!check(houses, heaters, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private boolean check(int[] houses, int[] heaters, int radius) {
        // 复杂度 O(m * n)
        // 超时
//        for (int i = 0; i < houses.length; i++) {
//            int distance = Integer.MAX_VALUE;
//            for (int j = 0; j < heaters.length; j++) {
//                distance = Math.min(distance, Math.abs(heaters[j] - houses[i]));
//            }
//            if (distance > radius) return false;
//        }
//        return true;

        // 双指针
        // 时间复杂度 O(m)
        int indexHo = 0;
        int indexHe = 0;
        while (indexHo < houses.length) {
            int house = houses[indexHo];
            int heater = heaters[indexHe];
            if (house >= heater - radius && house <= heater + radius) {
                indexHo++;
            } else {
                indexHe++;
            }
            if (indexHe == heaters.length) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findRadius(new int[]{1,5}, new int[]{2}));
    }
}