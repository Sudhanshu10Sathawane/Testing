package org.testing;

import org.junit.Test;
import static org.junit.Assert.*;

public class DSUTest {

    @Test
    public void testInitialization() {
        DSU dsu = new DSU(5);
        for (int i = 0; i < 5; i++) {
            assertEquals(i, dsu.find(i)); // Each element is its own parent initially
        }
    }

    @Test
    public void testUnionAndFind() {
        DSU dsu = new DSU(5);

        dsu.union(0, 1);
        dsu.union(1, 2);

        assertEquals(dsu.find(0), dsu.find(2)); // 0 and 2 should be in the same set
        assertNotEquals(dsu.find(0), dsu.find(3)); // 0 and 3 should not be in the same set
    }
    @Test
    public void testUnionAndFindParEqual() {
        DSU dsu = new DSU(5);

        dsu.union(0, 1);
        dsu.union(0, 2);
        assertFalse(dsu.union(1,2));
        assertEquals(dsu.find(0), dsu.find(2)); // 0 and 2 should be in the same set
        assertNotEquals(dsu.find(0), dsu.find(3)); // 0 and 3 should not be in the same set
    }
    @Test
    public void testUnionWithRankComparison() {
        DSU dsu = new DSU(10);

        dsu.union(0, 1);
        dsu.union(1, 2);
        dsu.union(2, 3);
        dsu.union(3, 4);
        dsu.union(4, 5);
        dsu.union(6, 3);

        dsu.union(0, 3);

        assertEquals(dsu.find(0), dsu.find(1));
        assertEquals(dsu.find(3), dsu.find(1));
    }
    @Test
    public void testIsConnected() {
        DSU dsu = new DSU(5);

        dsu.union(0, 1);
        dsu.union(2, 3);

        assertTrue(dsu.isConnected(0, 1));
        assertFalse(dsu.isConnected(1, 2));
        assertTrue(dsu.isConnected(2, 3));
    }

    @Test
    public void testPathCompression() {
        DSU dsu = new DSU(5);

        dsu.union(0, 1);
        dsu.union(1, 2);
        dsu.union(2, 3);

        int root = dsu.find(3);
        assertEquals(root, dsu.find(0)); // Path compression ensures all point to the root
        assertEquals(root, dsu.find(1));
        assertEquals(root, dsu.find(2));
    }

    @Test
    public void testRankHandling() {
        DSU dsu = new DSU(5);

        dsu.union(0, 1);
        dsu.union(2, 3);
        dsu.union(1, 2); // Merge two trees of equal rank

        assertEquals(dsu.find(0), dsu.find(3)); // All should belong to the same set
        assertTrue(dsu.isConnected(0, 3));
    }

    @Test
    public void testUnionWhenAlreadyConnected() {
        DSU dsu = new DSU(5);
        dsu.union(0, 1);
        dsu.union(1, 2);
        assertFalse(dsu.union(0, 2)); // Already connected
    }

    @Test
    public void testUnionAndPathCompression() {
        DSU dsu = new DSU(5);
        dsu.union(0, 1);
        dsu.union(1, 2);
        dsu.union(3, 4);
        assertTrue(dsu.isConnected(0, 2));
        assertTrue(dsu.isConnected(3, 4));
        assertFalse(dsu.isConnected(0, 4));
    }

    @Test
    public void testMultipleUnions() {
        DSU dsu = new DSU(5);
        assertTrue(dsu.union(0, 1));
        assertTrue(dsu.union(1, 2));
        assertTrue(dsu.union(2, 3));
        assertTrue(dsu.union(3, 4));
        assertTrue(dsu.isConnected(0, 4));
    }

    @Test
    public void testIsConnectedWhenNotUnioned() {
        DSU dsu = new DSU(5);
        assertFalse(dsu.isConnected(0, 1));
        assertFalse(dsu.isConnected(3, 4));
    }
}
