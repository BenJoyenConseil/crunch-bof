package com.octo.bof.crunch.level2;

import org.apache.commons.io.FileUtils;
import org.apache.crunch.impl.mem.OctoMemPipeline;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.CrunchAssertions.assertThat;

public class TopFiveUrlIndicatorIntTest {

    String in = "src/test/resources/access_log";
    String out = "target/test/result";

    @Before
    public void setUp() throws Exception {
        FileUtils.deleteDirectory(new File(out));
    }

    @Test
    public void processIndicator_nominalCase() throws IOException {
        // Given
        TopFiveUrlIndicator job = new TopFiveUrlIndicator();

        // When
        job.run(OctoMemPipeline.getInstance(), in, out);

        // Then
        assertThat(out + "/out.txt").isEqualTo("src/test/resources/level2/expected.txt");
    }
}
