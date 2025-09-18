import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TimeMap {
    private Map<String, List<String[]>> map = new HashMap<>();

    public TimeMap() {}

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(new String[]{String.valueOf(timestamp), value});
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        List<String[]> inner = map.get(key);
        inner.sort((a, b) -> (Integer.compare(Integer.parseInt(a[0]), Integer.parseInt(b[0]))));
        int idx = lowerBound(inner, timestamp + 1) - 1;
        return idx < 0 ? "" : inner.get(idx)[1];
    }

    // []
    private int lowerBound(List<String[]> nums, int timestamp) {
        int left = 0;
        int right = nums.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (Integer.parseInt(nums.get(mid)[0]) < timestamp) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("vrobykydll", "hwliiq", 2);
        timeMap.set("vrobykydll", "kcvcjzzwx", 11);
        System.out.println(timeMap.get("vrobykydll", 12));
    }

}