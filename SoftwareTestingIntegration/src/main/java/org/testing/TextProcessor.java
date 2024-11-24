package org.testing;

import java.util.ArrayList;
import java.util.List;

class TextProcessor {

    public static List<List<Integer>> findPatternInMultipleTexts(String pattern, List<String> texts) {
        List<List<Integer>> result = new ArrayList<>();

        for (String text : texts) {
            ArrayList<Integer> indices = KMPAlgorithm.search(pattern, text);
            result.add(indices);
        }

        return result;
    }
}
