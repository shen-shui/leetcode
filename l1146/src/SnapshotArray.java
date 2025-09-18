import java.util.*;

class SnapshotArray {
    private int count = 0;
    private Map<Integer, List<int[]>> map = new HashMap<>();

    public SnapshotArray(int length) {}

    public void set(int index, int val) {
        map.computeIfAbsent(index, k -> new ArrayList<>()).add(new int[]{count, val});
    }

    public int snap() {
        return count++;
    }

    public int get(int index, int snap_id) {
        if (!map.containsKey(index)) return 0;
        List<int[]> inner = map.get(index);
        int idx = lowerBound(inner, snap_id + 1) - 1;
        return idx < 0 ? 0 : inner.get(idx)[1];
    }

    // ()
    private int lowerBound(List<int[]> nums, int target) {
        int left = -1;
        int right = nums.size();
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid)[0] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        SnapshotArray sa = new SnapshotArray(3);
        sa.set(0, 5);
        sa.snap();
        sa.set(0, 6);
        System.out.println(sa.get(0, 0));
    }
}