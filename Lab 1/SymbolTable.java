
/**
 * This class represents a symbol table, or hash table, with a very
 * simple hash function. Observe that you are not supposed to change
 * hash function. Ensure that you use linear probing as your method of
 * collision handling.
 *
 * @author Magnus Nielsen, Tommy Färnqvist ...
 */
public class SymbolTable {
    private static final int INIT_CAPACITY = 7;

    /* Number of key-value pairs in the symbol table */
    private int size;
    /* Size of linear probing table */
    private int maxSize;
    /* The keys */
    private String[] keys;
    /* The values */
    private Character[] vals;

    /**
     * Create an empty hash table - use 7 as default size
     */
    public SymbolTable() {
        this(INIT_CAPACITY);
    }

    /**
     * Create linear probing hash table of given capacity
     */
    public SymbolTable(int capacity) {
        size = 0;
        maxSize = capacity;
        keys = new String[maxSize];
        vals = new Character[maxSize];
    }

    /**
     * Return the number of key-value pairs in the symbol table
     */
    public int size() {
        return size;
    }

    /**
     * Is the symbol table empty?
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Does a key-value pair with the given key exist in the symbol table?
     */
    public boolean contains(String key) {
        return get(key) != null;
    }

    /**
     * Hash function for keys - returns value between 0 and maxSize-1
     */
    public int hash(String key) {
        int i;
        int v = 0;

        for (i = 0; i < key.length(); i++) {
            v += key.charAt(i);
        }
        return v % maxSize;
    }

    /**
     * Insert the key-value pair into the symbol table.
     * TODO: implement the put method.
     */
    public void put(String key, Character val) {

        if (size == maxSize || key == null) {
            return;
        }

        if (val == null) {
            delete(key);
            return;
        }

        int i = hash(key);

        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
            i = (i + 1) % maxSize;
        }

        if (keys[i] == null) {
            keys[i] = key;
            vals[i] = val;
            size++;
            return;
        }
    }

    /**
     * Return the value associated with the given key, null if no such
     * value.
     * TODO: implement the get method.
     */
    public Character get(String key) {

        if (key == null) {
            return null;
        }

        int i = hash(key);
        int stop = i;

        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                return vals[i];
            }

            i = (i + 1) % maxSize;

            if (i == stop) {
                return null;
            }
        }

        return null;
    }

    /**
     * Delete the key (and associated value) from the symbol table.
     * TODO: implement the delete method.
     */
    public void delete(String key) {
        // TODO: delete() for hash tables should have amortized time
        // complexity O(1), but this implementation is O(N)

        // Fixed without creating a new table and rehashing to the original table using
        // tombstones.

        if (key == null) {
            return;
        }

        int i = hash(key);

        if (contains(key)) {

            while (!keys[i].equals(key)) {
                i = (i + 1) % maxSize;
            }

            keys[i] = null;
            vals[i] = null;
            size--;

            int c = (i + 1) % maxSize;

            while (keys[c] != null) {

                String tmpKey = keys[c];
                Character tmpVals = vals[c];

                keys[c] = null;
                vals[c] = null;

                size--;
                put(tmpKey, tmpVals);

                c = (c + 1) % maxSize;
            }
        }
        return;
    }

    /**
     * Print the contents of the symbol table.
     */
    public void dump() {
        String str = "";

        for (int i = 0; i < maxSize; i++) {
            str = str + i + ". " + vals[i];
            if (keys[i] != null) {
                str = str + " " + keys[i] + " (";
                str = str + hash(keys[i]) + ")";
            } else {
                str = str + " -";
            }
            System.out.println(str);
            str = "";
        }
    }

    // TODO: not complementary work, but test programs should not be
    // placed inside the work product. when delivering the class to the
    // user, the user wants only what was specified. hand in separate
    // test programs in the future.
    public void test() {

        put("the", 'a');
        put("het", 'b');
        put("teh", 'c');
        put("hte", 'd');
        put("fusk", 'e');
        put("hej", 'f');
        dump();

        System.out.println();
        System.out.println("Hämtar värdet av keys hej, the, hte och fusk");
        System.out.println(get("hej") + " - förväntat f");
        System.out.println(get("the") + " - förväntat a");
        System.out.println(get("hte") + " - förväntat d");
        System.out.println(get("fusk") + " - förväntat e");

        System.out.println();
        System.out.println("Testar att hämta en identifierare som inte finns i listan, bör resultera i null");
        System.out.println(get("lapp"));

        System.out.println();
        System.out.println(
                "Sätter in identifierare 'the' som redan finns i tabellen tillsammans med värde p, värdet bör ändra från det gamla till p");
        put("the", 'p');
        dump();

        System.out.println();

        System.out.println(
                "Sätter in identifieraren 'fusk' tillsammans med null som värde, bör resultera i att värdet raderas från tabellen och tabellen hashas om");

        put("fusk", null);
        dump();

        System.out.println();
        System.out.println("Fyller tabellen.");
        put("fusk", 't');
        put("paj", 'r');
        dump();

        System.out.println();
        System.out.println("Testar att sätta in ett värde i en full tabell, inget bör hända");
        put("sol", 'h');
        dump();

        System.out.println();
        System.out.println("Testar att sätta in ett värde med key null, inget bör hända");
        put(null, 'd');
        dump();

        System.out.println();
        System.out.println("Testar att kalla på delete med null key, inget bör hända");
        delete(null);
        dump();

        System.out.println();
        System.out.println("Tar bort het, teh och hte");
        delete("het");
        delete("teh");
        delete("hte");
        dump();

        System.out.println();
        System.out.println("Tar bort paj, fusk och the");
        delete("paj");
        delete("fusk");
        delete("the");
        dump();

    }
}
