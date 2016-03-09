package com.octo.bof.crunch.level1;


import com.octo.bof.crunch.BaseCrunchBofTest;
import org.apache.commons.io.FileUtils;
import org.apache.crunch.impl.mem.MemPipeline;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.CrunchAssertions.assertThat;

public class HttpCodeIndicatorIntTest extends BaseCrunchBofTest {


    public HttpCodeIndicatorIntTest() {
        in = "src/test/resources/access_log";
        out = "target/test/result";
    }

    @Test
    public void run_nominalCase() throws IOException {
        // Given
        HttpCodeIndicator job = new HttpCodeIndicator();

        // When
        job.run(MemPipeline.getInstance(), in, out);

        // Then
        assertThat(out).isEqualTo("src/test/resources/level1/expected.txt");
    }
}
