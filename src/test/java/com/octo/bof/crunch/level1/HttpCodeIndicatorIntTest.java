package com.octo.bof.crunch.level1;


import com.octo.bof.crunch.level0.ReadWriteData;
import org.apache.commons.io.FileUtils;
import org.apache.crunch.impl.mem.CustomMemPipeline;
import org.assertj.core.api.CrunchAssertions;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.CrunchAssertions.assertThat;

public class HttpCodeIndicatorIntTest {

    String in = "src/test/resources/access_log";
    String out = "target/test/result";

    @Before
    public void setUp() throws Exception {
        FileUtils.deleteDirectory(new File(out));
    }

    @Test
    public void processIndicator_nominalCase() throws IOException {
        // Given
        HttpCodeIndicator job = new HttpCodeIndicator();

        // When
        job.run(CustomMemPipeline.getInstance(), in, out);

        // Then
        assertThat(out + "/out.txt").isEqualTo("src/test/resources/level1/expected.txt");
    }
}
