import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RangeFreqQuery {
    private Map<Integer, List<Integer>> map = new HashMap<>();

    public RangeFreqQuery(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }


    public int query(int left, int right, int value) {
        List<Integer> list = map.get(value);
        if (list == null) return 0;
        int l = lowerBound(list, left);
        int r = lowerBound(list, right + 1);
        return r - l;
    }

    //[)
    private int lowerBound(List<Integer> nums, int target) {
        int left = 0;
        int right = nums.size();

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        RangeFreqQuery rangeFreqQuery = new RangeFreqQuery(new int[]{1, 1, 1, 2, 2});
        System.out.println(rangeFreqQuery.query(3, 3, 2));
    }
}
