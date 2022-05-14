public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key +" " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray; // or Object[]
    private int M = 11; // default number of chains
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[M];
    }

    public MyHashTable(int M) {
        size = 0;
        chainArray = new HashNode[M];
        this.M = M;
    }

    private int hash(K key){
        return key.hashCode() % size;
    }

    public void put(K key, V value){
        int hash = hash(key);
        if (chainArray[hash] == null)
            chainArray[hash] = new HashNode<K, V>(key, value);
        else {
            HashNode<K, V> entry = chainArray[hash];
            while (entry.next != null && !entry.key.equals(key))
                entry = entry.next;
            if (entry.key.equals(key))
                entry.value = value;
            else
                entry.next = new HashNode<K, V>(key, value);
        }
    }

    public V get(K key){
        int hash = hash(key);
        if (chainArray[hash] == null)
            return null;
        else {
            HashNode<K, V> entry = chainArray[hash];
            while (entry.next != null && !entry.key.equals(key))
                entry = entry.next;

            if (entry.key.equals(key))
                return entry.value;
            else
                return null;
        }
    }

    public V remove(K key){
        // Apply hash function to find index for given key
        int bucketIndex = hash(key);

        // Get head of chain
        HashNode<K, V> head = chainArray[bucketIndex];

        // Search for key in its chain
        HashNode<K, V> prev = null;
        while (head != null)
        {
            // If Key found
            if (head.key.equals(key))
                break;

            // Else keep moving in chain
            prev = head;
            head = head.next;
        }

        // If key was not there
        if (head == null)
            return null;

        // Reduce size
        size--;

        // Remove key
        if (prev != null)
            prev.next = head.next;
        else
            chainArray[bucketIndex] = head.next;

        return head.value;
    }

    public boolean contains(V value){
        return getKey(value) != null;
    }

    public K getKey(V value){
        HashNode<K, V>[] table = this.chainArray;
        int i = table.length;

        while(i-- > 0) {
            for(HashNode<K, V> element = table[i]; element != null; element = element.next) {
                if (element.value.equals(value)) {
                    return element.key;
                }
            }
        }

        return null;
    }
}
