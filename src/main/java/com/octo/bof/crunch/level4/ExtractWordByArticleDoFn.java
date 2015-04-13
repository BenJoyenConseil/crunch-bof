package com.octo.bof.crunch.level4;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;

/**
 * Powered by o<+o
 */

public class ExtractWordByArticleDoFn extends DoFn<String, Pair<String, String>> {
    @Override
    public void process(String input, Emitter<Pair<String, String>> emitter) {
        if(input.isEmpty()) {
            return;
        }
        if( ! input.startsWith("Art"))
            return;
        String[] splits = input.split(" ");
        String article = splits[0] + splits[1];
        String word;
        for(int i = 2; i < splits.length; i++){
            word = splits[i].replaceAll("\\.|,", "").toLowerCase();
            emitter.emit(Pair.of(word, article));
        }
    }
}
