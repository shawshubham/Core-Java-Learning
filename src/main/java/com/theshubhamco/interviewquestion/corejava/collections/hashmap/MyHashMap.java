package com.theshubhamco.interviewquestion.corejava.collections.hashmap;

public class MyHashMap<K, V> {

    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry<K, V>[] buckets;
    private int capacity = 16;
    private int size = 0;
    private float loadFactor = 0.75f;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new Entry[capacity];
    }

    private int getIndex(K key) {
        int hash = key == null ? 0 : key.hashCode();
        return hash & (capacity - 1); // basic logic: hash % capacity;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Entry<K,V> head = buckets[index];

        Entry<K,V> current = head;
        // Updating the existing key with the new value
        while (current != null) {
            if ((current.key == null && key == null) || (current.key != null && current.key.equals(key))) {
                current.value = value; // Update existing key
                return;
            }
            current = current.next;
        }

        // Creating a new entry with key and value and inserting it at the beginning of the bucket
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = head; // Insert at the beginning of the bucket
        buckets[index] = newEntry;
        size++;

        if (size > getThreshold()) {
            resizeAndRehash();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        Entry<K,V> current = buckets[index];

        while (current != null) {
            if ((current.key == null && key == null) || (current.key != null && current.key.equals(key))) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    public void remove(K key) {
        int index = getIndex(key);
        Entry<K,V> current = buckets[index];
        Entry<K,V> prev = null;

        while (current != null) {
            if ((current.key == null && key == null) ||
                    (current.key != null && current.key.equals(key))) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if ((current.key == null && key == null) ||
                    (current.key != null && current.key.equals(key))) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private void resizeAndRehash() {
        Entry<K, V>[] oldBuckets = buckets;
        capacity *= 2;
        buckets = new Entry[capacity];

        for (Entry<K, V> head : oldBuckets) {
            Entry<K, V> current = head;

            while (current != null) {
                Entry<K, V> next = current.next;

                int newIndex = getIndex(current.key);
                current.next = buckets[newIndex];
                buckets[newIndex] = current;

                current = next;
            }
        }
    }

    private int getThreshold() {
        return (int) (capacity * loadFactor);
    }

    public int getSize() {
        return size;
    }
}
