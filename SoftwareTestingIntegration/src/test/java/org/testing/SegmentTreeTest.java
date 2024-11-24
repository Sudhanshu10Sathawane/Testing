package org.testing;

import org.junit.Test;
import static org.junit.Assert.*;
public class SegmentTreeTest {

    @Test
    public void testRangeSumBasic() {
        int[] nums = {1, 2, 3, 4, 5};
        SegmentTree tree = new SegmentTree(nums);

        assertEquals(15, tree.rangeSum(0, 4));
        assertEquals(6, tree.rangeSum(0, 2));
        assertEquals(9, tree.rangeSum(3, 4));
        assertEquals(2, tree.rangeSum(1, 1));
    }

    @Test
    public void testUpdateAndRangeSum() {
        int[] nums = {2, 4, 5, 7, 8};
        SegmentTree tree = new SegmentTree(nums);

        assertEquals(26, tree.rangeSum(0, 4));
        tree.update(2, 10); // Update index 2 from 5 to 10
        assertEquals(31, tree.rangeSum(0, 4));
        assertEquals(10, tree.rangeSum(2, 2));
        assertEquals(29, tree.rangeSum(1, 4));
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        SegmentTree tree = new SegmentTree(nums);

        assertEquals(0, tree.rangeSum(0, 0));
    }

    @Test
    public void testSingleElementArray() {
        int[] nums = {42};
        SegmentTree tree = new SegmentTree(nums);

        assertEquals(42, tree.rangeSum(0, 0));
        tree.update(0, 84);
        assertEquals(84, tree.rangeSum(0, 0));
    }

    @Test
    public void testOutOfRangeQueries() {
        int[] nums = {3, 8, 7, 6, 5};
        SegmentTree tree = new SegmentTree(nums);

        assertEquals(0, tree.rangeSum(-1, -1));
        assertEquals(0, tree.rangeSum(5, 6));
        assertEquals(29, tree.rangeSum(0, 4));
        assertEquals(21, tree.rangeSum(1, 3));
    }

    @Test
    public void testUpdateMultipleTimes() {
        int[] nums = {1, 3, 5, 7, 9};
        SegmentTree tree = new SegmentTree(nums);

        assertEquals(25, tree.rangeSum(0, 4));
        tree.update(1, 10);
        tree.update(3, 15);
        assertEquals(40, tree.rangeSum(0, 4));
        assertEquals(29, tree.rangeSum(2, 4));
        assertEquals(11, tree.rangeSum(0, 1));
    }
}