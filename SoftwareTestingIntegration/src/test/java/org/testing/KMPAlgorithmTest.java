package org.testing;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class KMPAlgorithmTest {

    @Test
    public void testBasicSearch() {
        ArrayList<Integer> result = KMPAlgorithm.search("abc", "abcabcabc");
        assertEquals(3, result.size());
        assertEquals((Integer) 0, result.get(0));
        assertEquals((Integer) 3, result.get(1));
        assertEquals((Integer) 6, result.get(2));
    }

    @Test
    public void testNoMatch() {
        ArrayList<Integer> result = KMPAlgorithm.search("xyz", "abcabcabc");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleCharacterPattern() {
        ArrayList<Integer> result = KMPAlgorithm.search("a", "aaaaaa");
        assertEquals(6, result.size());
        for (int i = 0; i < 6; i++) {
            assertEquals((Integer) i, result.get(i));
        }
    }

    @Test
    public void testPartialMatch() {
        ArrayList<Integer> result = KMPAlgorithm.search("abab", "abababab");
        assertEquals(3, result.size());
        assertEquals((Integer) 0, result.get(0));
        assertEquals((Integer) 2, result.get(1));
        assertEquals((Integer) 4, result.get(2));
    }

    @Test
    public void testPatternLargerThanText() {
        ArrayList<Integer> result = KMPAlgorithm.search("abcde", "abc");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testEmptyPattern() {
        ArrayList<Integer> result = KMPAlgorithm.search("", "abcabc");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testEmptyText() {
        ArrayList<Integer> result = KMPAlgorithm.search("abc", "");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testBothEmpty() {
        ArrayList<Integer> result = KMPAlgorithm.search("", "");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCaseSensitivity() {
        ArrayList<Integer> result = KMPAlgorithm.search("abc", "aBcAbCaBc");
        assertTrue(result.isEmpty());
    }
}
