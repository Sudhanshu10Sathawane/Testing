package org.testing;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TextProcessorTest {

    @Test
    public void testFindPatternInMultipleTexts() {
        String pattern = "abc";
        List<String> texts = Arrays.asList(
                "abcabcabc",
                "aabcdaabcabc",
                "no match here",
                ""
        );

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0, 3, 6), // Matches in the first text
                Arrays.asList(1, 6, 9),    // Matches in the second text
                Arrays.asList(),        // No match in the third text
                Arrays.asList()         // No match in the empty text
        );

        List<List<Integer>> result = TextProcessor.findPatternInMultipleTexts(pattern, texts);

        assertEquals(expected, result);
    }
}
