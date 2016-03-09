package com.octo.bof.crunch.level0;


import com.octo.bof.crunch.BaseCrunchBofTest;
import org.apache.commons.io.FileUtils;
import org.apache.crunch.impl.mem.MemPipeline;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.CrunchAssertions.assertThat;

public class ReadWriteIntTest extends BaseCrunchBofTest {

    public ReadWriteIntTest () {
        in = "src/test/resources/access_log";
        out = "target/test/result";
    }

    @Test
    public void run_nominalCase() throws IOException {
        // Given
        ReadWriteData job = new ReadWriteData();

        // When
        job.run(MemPipeline.getInstance(), in, out);

        // Then
        assertThat(out + "/out1.txt").isEqualTo("src/test/resources/level0/expected.txt");
    }
}
