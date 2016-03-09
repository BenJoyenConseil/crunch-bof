package com.octo.bof.crunch.level5;

import com.octo.bof.crunch.BaseCrunchBofTest;
import org.apache.crunch.Pipeline;
import org.apache.crunch.impl.mem.MemPipeline;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Powered by o<+o (bjc).
 */

public class SearchJobIntTest extends BaseCrunchBofTest{

    public SearchJobIntTest() {
        in = "src/test/resources/level5/inverted-index.avro";
        out = "target/test/result";
    }

    @Test
    public void search_shouldReturn_Art7_Art11_Art12_whenSearchingFor_citoyen_word(){

        // Given
        Pipeline pipeline = MemPipeline.getInstance();
        String citoyen = "citoyen";

        // When
        Iterable<InvertedIndexItem> results = new SearchJob().run(pipeline, in, citoyen);

        // Then
        assertThat(results).hasSize(1);
        InvertedIndexItem firstResult = results.iterator().next();
        assertThat(firstResult.docs).containsExactly("Art.7.", "Art.11.", "Art.12.");
    }


}
