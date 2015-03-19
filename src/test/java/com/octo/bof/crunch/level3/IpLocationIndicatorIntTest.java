package com.octo.bof.crunch.level3;

import org.apache.commons.io.FileUtils;
import org.apache.crunch.impl.mem.CustomMemPipeline;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.CrunchAssertions.assertThat;

/**
 * Powered by o<+o
 */

public class IpLocationIndicatorIntTest {

    String in = "src/test/resources/access_log";
    String out = "target/test/result";
    String countriesFile = "src/test/resources/countries_ip";

    @Before
    public void setUp() throws Exception {
        FileUtils.deleteDirectory(new File(out));
    }

    @Test
    public void processIndicator_nominalCase() throws IOException {
        // Given
        IpLocationIndicator job = new IpLocationIndicator();

        // When
        job.run(CustomMemPipeline.getInstance(), in, out, countriesFile);

        // Then
        assertThat(out + "/out.txt").isEqualTo("src/test/resources/level3/expected.txt");
    }
}
