package com.octo.bof.crunch.level4;

import org.apache.commons.io.FileUtils;
import org.apache.crunch.impl.mem.MemPipeline;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Powered by o<+o
 */

public class AdvertisingIntTest {
    String in = "src/test/resources/users";
    String out = "target/test/result";
    private Path outputFile = Paths.get(out + "/out.txt");
    private Path expectedFile = Paths.get("src/test/resources/level4/expected.txt");;
    private Advertising job;

    @Before
    public void setUp() throws Exception {
        FileUtils.deleteDirectory(new File(out));
        job = new Advertising(MemPipeline.getInstance(), in, out);
    }

    @Test
    public void processIndicator_nominalCase() throws IOException {
        // Given
        String expected = new String(Files.readAllBytes(expectedFile));

        // When
        job.processJob();
        String result = new String(Files.readAllBytes(outputFile));

        // Then
        assertThat(result).isEqualTo(expected);
    }
}
