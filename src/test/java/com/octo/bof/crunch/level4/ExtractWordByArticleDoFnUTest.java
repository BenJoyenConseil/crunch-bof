package com.octo.bof.crunch.level4;

import com.octo.bof.crunch.level3.ExtractCountriesIpDoFn;
import org.apache.crunch.Pair;
import org.apache.crunch.impl.mem.emit.InMemoryEmitter;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Powered by o<+o
 */

public class ExtractWordByArticleDoFnUTest {
    @Test
    public void process_nominalCase(){
        // Given
        String ligne = "Art. 1er. Les hommes naissent et demeurent libres et égaux en droits. Les distinctions sociales ne peuvent être fondées que sur l'utilité commune.";
        InMemoryEmitter<Pair<String, String>> emitter = InMemoryEmitter.create();

        // When
        new ExtractWordByArticleDoFn().process(ligne, emitter);
        List<Pair<String, String>> result = emitter.getOutput();

        // Then
        assertThat(result).contains(Pair.of("hommes", "Art.1er."));
        assertThat(result).contains(Pair.of("naissent", "Art.1er."));
        assertThat(result).contains(Pair.of("libres", "Art.1er."));
        assertThat(result).contains(Pair.of("égaux", "Art.1er."));
    }

    @Test
    public void process_shouldEmitWordWithoutDot(){
        // Given
        String ligne = "Art. 1er. droits. commune.";
        InMemoryEmitter<Pair<String, String>> emitter = InMemoryEmitter.create();

        // When
        new ExtractWordByArticleDoFn().process(ligne, emitter);
        List<Pair<String, String>> result = emitter.getOutput();

        // Then
        assertThat(result).contains(Pair.of("droits", "Art.1er."));
        assertThat(result).contains(Pair.of("commune", "Art.1er."));
    }

    @Test
    public void process_shouldEmitWordWithoutComma(){
        // Given
        String ligne = "Art. 1er. droits,";
        InMemoryEmitter<Pair<String, String>> emitter = InMemoryEmitter.create();

        // When
        new ExtractWordByArticleDoFn().process(ligne, emitter);
        List<Pair<String, String>> result = emitter.getOutput();

        // Then
        assertThat(result).contains(Pair.of("droits", "Art.1er."));
    }

    @Test
    public void process_shouldNotEmitWord_whenLineDoesNotStartWith_Art(){
        // Given
        String ligne = "Les hommes naissent et demeurent libres et égaux en droits. Les distinctions sociales ne peuvent être fondées que sur l'utilité commune.";
        InMemoryEmitter<Pair<String, String>> emitter = InMemoryEmitter.create();

        // When
        new ExtractWordByArticleDoFn().process(ligne, emitter);
        List<Pair<String, String>> result = emitter.getOutput();

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    public void process_shouldNotEmitWord_whenLineIsEmpty(){
        // Given
        String ligne = "";
        InMemoryEmitter<Pair<String, String>> emitter = InMemoryEmitter.create();

        // When
        new ExtractWordByArticleDoFn().process(ligne, emitter);
        List<Pair<String, String>> result = emitter.getOutput();

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    public void process_shouldEmitInLowerCase(){
        // Given
        String ligne = "Art. 1er. Droits,";
        InMemoryEmitter<Pair<String, String>> emitter = InMemoryEmitter.create();

        // When
        new ExtractWordByArticleDoFn().process(ligne, emitter);
        List<Pair<String, String>> result = emitter.getOutput();

        // Then
        assertThat(result).contains(Pair.of("droits", "Art.1er."));
    }
}
