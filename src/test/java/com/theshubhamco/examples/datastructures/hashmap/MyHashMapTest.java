package com.theshubhamco.examples.datastructures.hashmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @Test
    void put_shouldInsertSingleEntry() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("one", 1);

        assertEquals(1, map.get("one"));
        assertEquals(1, map.getSize());
        assertFalse(map.isEmpty());
    }

    @Test
    void put_shouldUpdateExistingKeyWithoutIncreasingSize() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("one", 1);
        map.put("one", 100);

        assertEquals(100, map.get("one"));
        assertEquals(1, map.getSize());
    }

    @Test
    void put_shouldSupportNullKey() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put(null, 99);
        map.put("one", 1);

        assertEquals(99, map.get(null));
        assertEquals(2, map.getSize());
    }

    @Test
    void put_shouldUpdateNullKeyWithoutIncreasingSize() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put(null, 1);
        map.put(null, 2);

        assertEquals(2, map.get(null));
        assertEquals(1, map.getSize());
    }

    @Test
    void get_shouldReturnNullForNonExistentKey() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        assertNull(map.get("non-existent"));
        assertEquals(0, map.getSize());
    }

    @Test
    void get_shouldReturnInsertedValue() {
        MyHashMap<String, String> map = new MyHashMap<>();

        map.put("name", "Shubham");

        assertEquals("Shubham", map.get("name"));
    }


    @Test
    void get_shouldHandleCollisionAndReturnCorrectValueForEachKey() {
        MyHashMap<CollisionKey, String> map = new MyHashMap<>();

        CollisionKey k1 = new CollisionKey("A");
        CollisionKey k2 = new CollisionKey("B");
        CollisionKey k3 = new CollisionKey("C");

        map.put(k1, "value1");
        map.put(k2, "value2");
        map.put(k3, "value3");

        assertEquals("value1", map.get(k1));
        assertEquals("value2", map.get(k2));
        assertEquals("value3", map.get(k3));
        assertEquals(3, map.getSize());
    }

    @Test
    void put_shouldRehashAndPreserveExistingEntries() {
        MyHashMap<Integer, String> map = new MyHashMap<>();

        // threshold = 16 * 0.75 = 12
        for (int i = 1; i <= 17; i++) {
            map.put(i, "value-" + i);
        }

        for (int i = 1; i <= 17; i++) {
            assertEquals("value-" + i, map.get(i));
        }

        assertEquals(17, map.getSize());
    }

    @Test
    void remove_shouldRemoveExistingKeyFromSingleEntryBucket() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("one", 1);
        map.remove("one");

        assertNull(map.get("one"));
        assertEquals(0, map.getSize());
        assertTrue(map.isEmpty());
    }

    @Test
    void remove_shouldRemoveHeadNodeFromCollisionChain() {
        MyHashMap<CollisionKey, String> map = new MyHashMap<>();

        CollisionKey k1 = new CollisionKey("A");
        CollisionKey k2 = new CollisionKey("B");

        map.put(k1, "value1");
        map.put(k2, "value2");

        map.remove(k2); // latest inserted becomes head in current implementation

        assertNull(map.get(k2));
        assertEquals("value1", map.get(k1));
        assertEquals(1, map.getSize());
    }

    //This test case identifies a critical bug in remove method, infinite loop due to bad implementation
    @Test
    void remove_shouldWorkProperlyEvenIfKeyIsRemovedFromAnywhereCollisionChain() {
        MyHashMap<CollisionKey, String> map = new MyHashMap<>();

        CollisionKey k1 = new CollisionKey("A");
        CollisionKey k2 = new CollisionKey("B");
        CollisionKey k3 = new CollisionKey("C");

        map.put(k1, "value1");
        map.put(k2, "value2");
        map.put(k3, "value3");

        map.remove(k2); // latest inserted becomes head in current implementation

        assertNull(map.get(k2));
        assertEquals("value1", map.get(k1));
        assertEquals("value3", map.get(k3));
        assertEquals(2, map.getSize());

    }

    @Test
    void containsKey_shouldReturnTrueForExistingKey() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("one", 1);

        assertTrue(map.containsKey("one"));
    }

    @Test
    void containsKey_shouldReturnFalseForNonExistentKey() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        assertFalse(map.containsKey("non-existent"));
    }

    @Test
    void isEmpty_shouldReturnTrueForEmptyMap() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        assertTrue(map.isEmpty());
    }

    @Test
    void isEmpty_shouldReturnFalseForNonEmptyMap() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        assertFalse(map.isEmpty());
    }

    @Test
    void clear_shouldRemoveAllEntries() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put(null, 3);
        map.clear();

        assertEquals(0, map.getSize());
        assertTrue(map.isEmpty());
        assertNull(map.get("one"));
        assertNull(map.get("two"));
        assertNull(map.get(null));
    }

    @Test
    void getSize_shouldTrackInsertUpdateRemoveAndClearCorrectly() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        assertEquals(0, map.getSize());

        map.put("one", 1);
        map.put("two", 2);
        assertEquals(2, map.getSize());

        map.put("one", 100); // update only
        assertEquals(2, map.getSize());

        map.remove("two");
        assertEquals(1, map.getSize());

        map.clear();
        assertEquals(0, map.getSize());
    }

    /**
     * This test exposes a design bug in the current implementation:
     * containsKey() uses get(key) != null, so a key mapped to null is treated as absent.
     */
    @Test
    void containsKey_shouldIdeallyReturnTrueEvenWhenValueIsNull_butCurrentlyFails() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("one", null);

        // Current implementation returns false, but semantically this should be true.
        // Fixed the implementation to return true.
        assertTrue(map.containsKey("one"));
    }

    /**
     * Helper key type to force collisions.
     */
    static class CollisionKey {
        private final String value;

        CollisionKey(String value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return 42; // force same bucket
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof CollisionKey other)) return false;
            return value.equals(other.value);
        }
    }
}