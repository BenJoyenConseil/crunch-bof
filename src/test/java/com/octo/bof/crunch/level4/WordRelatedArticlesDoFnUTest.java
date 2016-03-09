package com.octo.bof.crunch.level4;

import org.apache.crunch.Pair;
import org.apache.crunch.impl.mem.emit.InMemoryEmitter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Powered by o<+o
 */

public class WordRelatedArticlesDoFnUTest {

    @Test
    public void process_nominalCase(){
        // Given
        InMemoryEmitter<Pair<String, String>> emitter = InMemoryEmitter.create();
        ArrayList<String> tmp = new ArrayList<String>();
        tmp.add("Art. 1er.");
        tmp.add("Art. 2.");
        tmp.add("Art. 5.");
        Iterable<String> articles = tmp;

        // When
        new WordRelatedArticlesDoFn().process(Pair.of("word", articles), emitter);
        List<Pair<String, String>> result = emitter.getOutput();

        // Then
        assertThat(result).contains(Pair.of("word", "Art. 1er.,Art. 2.,Art. 5.,"));
    }
}
