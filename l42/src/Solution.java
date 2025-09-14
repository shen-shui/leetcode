import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
    /*
    前后缀分解：
            定义两个数组，一个计算某个位置左边的最大值，一个计算某个位置右边的最大值
            取一个位置左右两边最大值的最小值，减去当前位置的高度，就可以得到当前格可储水量
     */
    public int trap(int[] height) {
        int n = height.length;
        int ans = 0;
        int[] left = new int[n];
        int[] right = new int[n];
        int maxHeight = height[0];
        left[0] = 0;
        for(int i = 1; i < n; i++) {
            left[i] = maxHeight;
            if (height[i] > maxHeight) {
                maxHeight = height[i];
            }
        }
//        System.out.println(Arrays.toString(left));

        right[n - 1] = 0;
        maxHeight = height[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            right[i] = maxHeight;
            if (height[i] > maxHeight) {
                maxHeight = height[i];
            }
        }
//        System.out.println(Arrays.toString(right));

        for (int i = 0; i < n; i++) {
            int currentH = Math.min(left[i], right[i]);
            if (height[i] < currentH) {
                ans += currentH - height[i];
            }
        }
        return ans;
    }

    /*
    双指针：
            前后缀的优化版本，降低了空间复杂度
            定义两个指针（一左一右），同时定义两个变量，分别记录左右指针的最大值
            比较左右指针的最大值，我们选取左右指针最大值中小的那一个，计算当前位置距离最大值的面积（即储水量）
            原因：左右指针最大值中的最小值的代表当前区域一定能存到到这个最小值的水量，因为另一个指针更大，所以不会出现溢出的情况
     */
    public int trap1(int[] height) {
        int n = height.length;
        int ans = 0;
        int left = 0, right = n - 1;
        int maxLeft = 0, maxRight = 0;
        while (left < right) {
            maxLeft = Math.max(maxLeft, height[left]);
            maxRight = Math.max(maxRight, height[right]);

            if (maxLeft > maxRight) {
                ans += maxRight - height[right];
                right--;
            } else {
                ans += maxLeft - height[left];
                left++;
            }
        }

        return ans;
    }

    /*
    单调栈：
            维护一个递减栈，当栈出现一个不是递减元素时，开始计算横向面积
            取出栈的栈顶元素，栈顶元素与当前（已经开始递增的那个元素）元素和栈顶的下一个元素的最小值 - 栈顶元素 组成 高
            当前（已经开始递增的那个元素）元素与栈顶的下一个元素之前的位置之差作为 宽，进行计算面积
            如果当前（已经开始递增的那个元素）元素不是最大元素，那么循环多次把小于此元素的栈中元素剔除计算面积，然后此元素入栈，继续上述操作


            灵神讲解：https://www.bilibili.com/video/BV1VN411J7S7/?vd_source=e78b29193429a5cd31e25c3e32ff2b5d
     */
    public int trap2(int[] height) {
        int n = height.length;
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int index = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int top = stack.peek();
                ans += (i - top - 1) * (Math.min(height[top], height[i]) - height[index]);
            }
            stack.push(i);
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.trap(new int[]{4,2,0,3,2,5}));
        System.out.println(solution.trap1(new int[]{4,2,0,3,2,5}));
        System.out.println(solution.trap2(new int[]{4,2,0,3,2,5}));
    }
}