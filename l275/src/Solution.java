class Solution {

    /**
     *  一个动态的二分算法
     *  每次都需要对比 citations[mid] 和 n - mid + 1
     *  也就是说二分的target是不断变化的，但是还是二分一样的远离，如果 citations[mid] < n - mid + 1 那么左边不可能再有答案了，和二分同一思想
     *
     *
     *  理解了循环不变量：
     *              我们要看再循环中谁一直保持着满足要求（是 left 还是 right），一个一直保持满足要求，一个一直保持不满足要求，这就是循环不变量
     *              由循环不变量我们可以很容易得到 最终返回的答案一定是那个一直满足要求的值
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0;
        // 这道题二分的是答案，所以开区间的右端点是 n + 1
        int right = n + 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (citations[n - mid] >= mid) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.hIndex(new int[]{0}));
    }
}