package com.octo.bof.crunch.level4;

import com.octo.bof.crunch.BaseCrunchBofTest;
import com.octo.bof.crunch.level3.IpLocationIndicator;
import org.apache.commons.io.FileUtils;
import org.apache.crunch.impl.mem.MemPipeline;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.CrunchAssertions.assertThat;

/**
 * Powered by o<+o
 */

public class InvertedIndexIntTest extends BaseCrunchBofTest {

    public InvertedIndexIntTest() {
        in = "src/test/resources/ddhc-1789.txt";
        out = "target/test/result";
    }

    @Test
    public void run_nominalCase() throws IOException {
        // Given
        InvertedIndex job = new InvertedIndex();

        // When
        job.run(MemPipeline.getInstance(), in, out);

        // Then
        assertThat(out).isEqualTo("src/test/resources/level4/expected.txt");
    }
}
