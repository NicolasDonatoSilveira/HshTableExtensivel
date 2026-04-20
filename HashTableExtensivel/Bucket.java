//Gustavo R. Schwert e Nicolas D. Silveira

import java.util.Arrays;

public class Bucket {
    int localDepth;
    Integer[] keys;
    int capacity;
    int count;

    public Bucket(int depth, int capacity) {
        this.localDepth = depth;
        this.capacity = capacity;
        this.keys = new Integer[capacity];
        this.count = 0;
    }

    public boolean isFull() {
        return count >= capacity;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean insertLinear(int key) {
        if (isFull()) return false;

        int startPos = key % capacity;
        int currentPos = startPos;

        do {
            if (keys[currentPos] == null) {
                keys[currentPos] = key;
                count++;
                return true;
            }
            currentPos = (currentPos + 1) % capacity;
        } while (currentPos != startPos);

        return false;
    }

    public boolean contains(int key) {
        for (Integer k : keys) {
            if (k != null && k == key) return true;
        }
        return false;
    }

    public void remove(int key) {
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null && keys[i] == key) {
                keys[i] = null;
                count--;
                return;
            }
        }
    }

    public String getKeysString() {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (Integer k : keys) {
            if (k != null) {
                if (!first) sb.append(", ");
                sb.append(k);
                first = false;
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
