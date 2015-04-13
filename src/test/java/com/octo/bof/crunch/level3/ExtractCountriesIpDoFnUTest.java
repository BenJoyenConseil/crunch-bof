package com.octo.bof.crunch.level3;

import org.apache.crunch.Pair;
import org.apache.crunch.impl.mem.emit.InMemoryEmitter;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Powered by o<+o
 */

public class ExtractCountriesIpDoFnUTest {
    @Test
    public void process_nominalCase(){
        // Given
        String ligne = "128.227.88.79\tfr";
        InMemoryEmitter<Pair<String, String>> emitter = InMemoryEmitter.create();

        // When
        new ExtractCountriesIpDoFn().process(ligne, emitter);
        List<Pair<String, String>> result = emitter.getOutput();

        // Then
        assertThat(result).contains(Pair.of("128.227.88.79", "fr"));
    }
}
