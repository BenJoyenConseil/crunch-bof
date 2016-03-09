package com.octo.bof.crunch.level3;

import com.octo.bof.crunch.level2.ExtractUrlDoFn;
import org.apache.crunch.impl.mem.emit.InMemoryEmitter;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Powered by o<+o
 */

public class ExtractIpDoFnUTest {
    @Test
    public void process_nominalCase(){
        // Given
        String ligne = "64.242.88.10 - - [07/Mar/2004:16:10:02 -0800] \"GET /mailman/listinfo/hsdivision HTTP/1.1\" 200 6291";
        InMemoryEmitter<String> emitter = InMemoryEmitter.create();

        // When
        new ExtractIpDoFn().process(ligne, emitter);
        List<String> result = emitter.getOutput();

        // Then
        assertThat(result).contains("64.242.88.10");
    }
}
