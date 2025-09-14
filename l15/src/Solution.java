import java.util.*;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        if (n < 3) {
            return null;
        }

        for (int i = 0; i < n; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = n - 1;
            while (left < right) {
                if (nums[left] + nums[i] + nums[right] == 0) {
                    res.add(addList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (nums[left] + nums[i] + nums[right] <= 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return res;
    }

    private List<Integer> addList(int i, int j, int k) {
        List<Integer> list = new ArrayList<>();
        list.add(i);
        list.add(j);
        list.add(k);
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.threeSum(new int[]{2,-3,0,-2,-5,-5,-4,1,2,-2,2,0,2,-4,5,5,-10});
        for (List<Integer> list : lists) {
            for (int i : list) {
                System.out.println(i);
            }
        }
    }
}