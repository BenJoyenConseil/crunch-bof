package com.octo.bof.crunch.level4;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;

/**
 * Powered by o<+o
 */

public class ExtractWordByArticleDoFn extends DoFn<String, Pair<String, String>> {

    public static final String SPACE = " ";
    public static final String PONCTUATION = "\\.|,";

    @Override
    public void process(String input, Emitter<Pair<String, String>> emitter) {
        if(isNotArticle(input))
            return;

        String[] splits = input.split(SPACE);
        String article = splits[0] + splits[1];
        String word;
        for(int i = 2; i < splits.length; i++){
            word = normalize(splits[i]);
            emitter.emit(Pair.of(word, article));
        }
    }

    private String normalize(String word) {
        return word
                .replaceAll(PONCTUATION, "")
                .toLowerCase();
    }

    private boolean isNotArticle(String input) {
        return (! input.startsWith("Art")) || input.isEmpty();
    }
}
