import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HashTableExtensivel {
    int globalDepth;
    int bucketSize;
    List<Bucket> directory;

    public HashTableExtensivel(int bucketSize) {
        this.globalDepth = 1;
        this.bucketSize = bucketSize;

        directory = new ArrayList<>();
        directory.add(new Bucket(1, bucketSize));
        directory.add(new Bucket(1, bucketSize));
    }

    private int getDirectoryIndex(int key) {
        return key & ((1 << globalDepth) - 1);
    }

    public void insert(int key) {
        int index = getDirectoryIndex(key);
        Bucket bucket = directory.get(index);

        if (bucket.insertLinear(key)) {
            return;
        }

        splitBucket(index);
        insert(key);
    }

    private void splitBucket(int index) {
        Bucket oldBucket = directory.get(index);
        int localDepth = oldBucket.localDepth;

        if (localDepth == globalDepth) {
            expandDirectory();
        }

        Bucket newBucket = new Bucket(localDepth + 1, bucketSize);
        oldBucket.localDepth++;

        int mask = (1 << oldBucket.localDepth) - 1;

        for (int i = 0; i < directory.size(); i++) {
            if (directory.get(i) == oldBucket) {
                if ((i & mask) != (index & mask)) {
                    directory.set(i, newBucket);
                }
            }
        }

        List<Integer> tempKeys = new ArrayList<>();
        for (Integer k : oldBucket.keys) {
            if (k != null) tempKeys.add(k);
        }

        Arrays.fill(oldBucket.keys, null);
        oldBucket.count = 0;

        for (int k : tempKeys) {
            insert(k);
        }
    }

    private void expandDirectory() {
        int size = directory.size();
        for (int i = 0; i < size; i++) {
            directory.add(directory.get(i));
        }
        globalDepth++;
    }

    public boolean search(int key) {
        int index = getDirectoryIndex(key);
        return directory.get(index).contains(key);
    }

    public void remove(int key) {
        int index = getDirectoryIndex(key);
        Bucket bucket = directory.get(index);

        bucket.remove(key);

        if (bucket.isEmpty()) {
            mergeBucket(index);
        }
    }

    private void mergeBucket(int index) {
        Bucket bucket = directory.get(index);

        if (bucket.localDepth <= 1) return;

        int pairIndex = index ^ (1 << (bucket.localDepth - 1));
        Bucket pairBucket = directory.get(pairIndex);

        if (pairBucket.localDepth != bucket.localDepth) return;

        for (int i = 0; i < directory.size(); i++) {
            if (directory.get(i) == bucket) {
                directory.set(i, pairBucket);
            }
        }

        pairBucket.localDepth--;
        tryShrink();
    }

    private void tryShrink() {
        if (globalDepth <= 1) return;

        for (Bucket b : directory) {
            if (b.localDepth == globalDepth) return;
        }

        globalDepth--;
        directory = new ArrayList<>(directory.subList(0, 1 << globalDepth));
    }

    public void generateDot(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("digraph HashTable {\n");
            writer.write("node [shape=record];\n\n");

            writer.write("label=\"Global Depth: " + globalDepth + "\";\n");
            writer.write("labelloc=\"t\";\n\n");

            Map<Bucket, String> bucketNames = new HashMap<>();
            int count = 0;

            for (Bucket b : directory) {
                if (!bucketNames.containsKey(b)) {
                    bucketNames.put(b, "bucket" + count++);
                }
            }

            for (Map.Entry<Bucket, String> entry : bucketNames.entrySet()) {
                Bucket b = entry.getKey();
                String name = entry.getValue();

                writer.write(name + " [label=\"LD: " + b.localDepth +
                        " | " + b.getKeysString() + "\"];\n");
            }

            writer.write("\n");

            for (int i = 0; i < directory.size(); i++) {
                String dirName = "dir" + i;

                String binary = String.format("%" + globalDepth + "s",
                        Integer.toBinaryString(i)).replace(' ', '0');

                writer.write(dirName + " [label=\"" + binary + "\"];\n");

                writer.write(dirName + " -> " +
                        bucketNames.get(directory.get(i)) + ";\n");
            }

            writer.write("}\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
