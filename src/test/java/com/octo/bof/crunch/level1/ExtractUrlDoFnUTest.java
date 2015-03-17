package com.octo.bof.crunch.level1;


import org.apache.crunch.impl.mem.emit.InMemoryEmitter;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExtractUrlDoFnUTest {

    @Test
    public void process_nominalCase(){
        // Given
        String ligne = "64.242.88.10 - - [07/Mar/2004:16:10:02 -0800] \"GET /mailman/listinfo/hsdivision HTTP/1.1\" 200 6291";
        InMemoryEmitter<String> emitter = InMemoryEmitter.create();

        // When
        new ExtractUrlDoFn().process(ligne, emitter);
        List<String> result = emitter.getOutput();

        // Then
        assertThat(result).contains("/mailman/listinfo/hsdivision");
    }

    @Test
    public void process_whenNoUrlFound(){
        // Given
        String ligne = "80-219-148-207.dclient.hispeed.ch - - [07/Mar/2004:19:47:36 -0800] \"OPTIONS * HTTP/1.0\" 200 -";
        InMemoryEmitter<String> emitter = InMemoryEmitter.create();

        // When
        new ExtractUrlDoFn().process(ligne, emitter);
        List<String> result = emitter.getOutput();

        // Then
        assertThat(result).isEmpty();
    }
}
