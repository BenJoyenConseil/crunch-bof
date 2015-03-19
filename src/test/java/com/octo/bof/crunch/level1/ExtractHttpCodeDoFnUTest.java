package com.octo.bof.crunch.level1;


import com.octo.bof.crunch.level2.ExtractUrlDoFn;
import org.apache.crunch.impl.mem.emit.InMemoryEmitter;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExtractHttpCodeDoFnUTest{


    @Test
    public void process_nominalCase(){
        // Given
        String ligne = "64.242.88.10 - - [07/Mar/2004:16:10:02 -0800] \"GET /mailman/listinfo/hsdivision HTTP/1.1\" 200 6291";
        InMemoryEmitter<Integer> emitter = InMemoryEmitter.create();

        // When
        new ExtractHttpCodeDoFn().process(ligne, emitter);
        List<Integer> result = emitter.getOutput();

        // Then
        assertThat(result).contains(200);
    }
}
