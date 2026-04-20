public class Main {
    public static void main(String[] args) {

        // CASO 1
        HashTableExtensivel c1 = new HashTableExtensivel(2);
        c1.insert(10);
        c1.insert(20);
        c1.generateDot("caso1.dot");

        // CASO 2
        HashTableExtensivel c2 = new HashTableExtensivel(2);
        c2.insert(10);
        c2.insert(20);
        c2.insert(30);
        c2.generateDot("caso2.dot");

        // CASO 3
        HashTableExtensivel c3 = new HashTableExtensivel(2);
        c3.insert(10);
        c3.insert(20);
        c3.insert(30);
        c3.insert(40);
        c3.insert(50);
        c3.generateDot("caso3.dot");

        // CASO 4
        HashTableExtensivel c4 = new HashTableExtensivel(2);
        c4.insert(10);
        c4.insert(20);
        c4.insert(30);
        c4.remove(20);
        c4.generateDot("caso4.dot");

        // CASO 5
        HashTableExtensivel c5 = new HashTableExtensivel(2);
        c5.insert(10);
        c5.insert(20);
        c5.insert(30);
        c5.insert(40);
        c5.remove(10);
        c5.remove(30);
        c5.generateDot("caso5.dot");
    }
}