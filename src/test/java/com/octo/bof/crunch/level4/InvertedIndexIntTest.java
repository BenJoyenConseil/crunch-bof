package com.octo.bof.crunch.level4;

import com.octo.bof.crunch.level3.IpLocationIndicator;
import org.apache.commons.io.FileUtils;
import org.apache.crunch.impl.mem.OctoMemPipeline;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.CrunchAssertions.assertThat;

/**
 * Powered by o<+o
 */

public class InvertedIndexIntTest {
    String in = "src/test/resources/level4/ddhc-1789.txt";
    String out = "target/test/result";

    @Before
    public void setUp() throws Exception {
        FileUtils.deleteDirectory(new File(out));
    }

    @Test
    public void run_nominalCase() throws IOException {
        // Given
        InvertedIndex job = new InvertedIndex();

        // When
        job.run(OctoMemPipeline.getInstance(), in, out);

        // Then
        assertThat(out + "/out.txt").isEqualTo("src/test/resources/level4/expected.txt");
    }
}
