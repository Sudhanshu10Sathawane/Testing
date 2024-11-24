package org.testing;

import org.junit.Test;
import static org.junit.Assert.*;

public class TrieTest {

    @Test
    public void testInsertAndSearch() {
        Trie trie = new Trie();
        trie.insert("apple");
        assertTrue(trie.search("apple"));
        assertFalse(trie.search("app"));
    }

    @Test
    public void testStartsWith() {
        Trie trie = new Trie();
        trie.insert("apple");
        assertTrue(trie.startsWith("app"));
        assertFalse(trie.startsWith("apl"));
    }

    @Test
    public void testEmptyTrie() {
        Trie trie = new Trie();
        assertFalse(trie.search("apple"));
        assertFalse(trie.startsWith("app"));
    }

    @Test
    public void testInsertMultipleWords() {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("appl");
        assertTrue(trie.search("apple"));
        assertTrue(trie.search("app"));
        assertTrue(trie.search("appl"));
    }
}
