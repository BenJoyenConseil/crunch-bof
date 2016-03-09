package com.octo.bof.crunch.level4;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;

import java.util.Iterator;

/**
 * Powered by o<+o
 */

public class WordRelatedArticlesDoFn extends DoFn<Pair<String, Iterable<String>>, Pair<String, String>> {

    @Override
    public void process(Pair<String, Iterable<String>> input, Emitter<Pair<String, String>> emitter) {
        String word = input.first();
        StringBuilder articles = new StringBuilder();
        Iterator<String> iterator = input.second().iterator();

        while (iterator.hasNext())
            articles.append(iterator.next())
                    .append(",");

        emitter.emit(Pair.of(word, articles.toString()));
    }
}
