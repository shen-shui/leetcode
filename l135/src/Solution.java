 class Solution {
    /*
        从左往右遍历一次，只需关注左侧是否成立（只关注从左往右的上升序列，下降序列没有关注）
        从右往左遍历一次，只需关注右侧是否成立（只关注从左往右的下降序列，上升序列没有关注）
        两侧取最大值，即为题解
     */
//    public int candy(int[] ratings) {
//        int n = ratings.length;
//        int[] left = new int[n];
//        int[] right = new int[n];
//        int sum = 0;
//
//        left[0] = 1;
//        for (int i = 1; i < n; i++) {
//            if (ratings[i] > ratings[i - 1]) {
//                left[i] = left[i - 1] + 1;
//            } else {
//                left[i] = 1;
//            }
//            System.out.println(left[i]);
//        }
//
//        right[n - 1] = 1;
//        for (int i = n - 2; i >= 0; i--) {
//            if (ratings[i] > ratings[i + 1]) {
//                right[i] = right[i + 1] + 1;
//            } else {
//                right[i] = 1;
//            }
//            System.out.println(right[i]);
//        }
//
//        for (int i = 0; i < n; i++) {
//            left[i] = Math.max(left[i], right[i]);
//            sum += left[i];
//        }
//
//        return sum;
//    }

     /*
     因为从左往右没有考虑下降序列，所以需要处理两次
     若关注下降序列并记录下降序列长度，即可确定下降序列所需糖果数，不必再遍历两次
      */

     public int candy(int[] ratings) {
         int n = ratings.length;
         int sum = 1;
         int up = 0;// 上升序列长度
         int down = 0;// 下降序列长度
         int peak = 0;// 记录上升峰值
         for (int i = 1; i < n; i++) {
             if (ratings[i] > ratings[i - 1]) {
                down = 0;
                up++;
                peak = up;
                sum += 1 + up;
             } else if (ratings[i] == ratings[i - 1]) {
                 sum += 1;
                 up = 0;
                 down = 0;
                 peak = 0;
             } else {
                 up = 0;
                 down++;
                 sum += 1 + down - (peak >= down ? 1 : 0);
             }
         }
         return sum;
     }

     public static void main(String[] args) {
         System.out.println(new Solution().candy(new int[]{1, 0, 2}));
     }
}
