import java.util.*;

// 灵神题解
class Router {
    private record Packet(int source, int destination, int timestamp) {}
    private record Pair(List<Integer> timestamps, int head) {}

    private final int memoryLimit;
    // 队列 用于先进先出元素
    private final Queue<Packet> packetQ = new ArrayDeque<>();
    // 集合 用于判重
    private final Set<Packet> packetSet = new HashSet<>();
    // Map destination -> Pair的映射 二分时可以少一些元素
    private final Map<Integer, Pair> destToTimestamps = new HashMap<>();

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        Packet packet = new Packet(source, destination, timestamp);
        // 集合add返回1表示不在集合中
        if (!packetSet.add(packet)) { // 表示该packet在集合中
            return false;
        }

        if (packetQ.size() == memoryLimit) { // 太多了
            // 直接调用该方法弹出去一个
            forwardPacket();
        }
        packetQ.add(packet);
        destToTimestamps.computeIfAbsent(destination, k -> new Pair(new ArrayList<>(), 0)).timestamps.add(timestamp);
        return true;
    }

    public int[] forwardPacket() {
        if (packetQ.isEmpty()) {
            return new int[]{};
        }
        Packet packet = packetQ.poll();
        packetSet.remove(packet);
        // head这个变量就是位了模拟一个队列，相当于Pair中存入的是一个队列
        destToTimestamps.compute(packet.destination, (k, p) -> new Pair(p.timestamps, p.head + 1));
        return new int[]{packet.source, packet.destination, packet.timestamp};
    }

    public int getCount(int destination, int startTime, int endTime) {
        Pair p = destToTimestamps.get(destination);
        if (p == null) return 0;
        int left = lowerBound(p.timestamps, startTime, p.head - 1);
        int right = lowerBound(p.timestamps, endTime + 1, p.head - 1);
        return right - left;
    }

    // ()
    private int lowerBound(List<Integer> nums, int target, int left) {
        int right = nums.size();
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }
}