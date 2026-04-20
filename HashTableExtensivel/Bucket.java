import java.util.*;

public class Bucket {
    int localDepth;
    List<Integer> keys;
    int capacity;

    public Bucket(int depth, int capacity) {
        this.localDepth = depth;
        this.capacity = capacity;
        this.keys = new ArrayList<>();
    }

    public boolean isFull() {
        return keys.size() >= capacity;
    }

    public boolean isEmpty() {
        return keys.isEmpty();
    }
}