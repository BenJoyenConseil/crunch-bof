package com.octo.bof.crunch.level2;

import org.apache.commons.io.FileUtils;
import org.apache.crunch.impl.mem.MemoryPipeline;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class TopFiveUrlIndicatorIntTest {

    String in = "src/test/resources/access_log";
    String out = "target/test/result";
    private Path outputFile = Paths.get(out + "/out.txt");
    private Path expectedFile = Paths.get("src/test/resources/level2/expected.txt");;
    private TopFiveUrlIndicator indicator;

    @Before
    public void setUp() throws Exception {
        FileUtils.deleteDirectory(new File(out));
        indicator = new TopFiveUrlIndicator(MemoryPipeline.getInstance(), in, out);
    }

    @Test
    public void processIndicator_nominalCase() throws IOException {
        // Given
        String expected = new String(Files.readAllBytes(expectedFile));

        // When
        indicator.processIndicator();
        String result = new String(Files.readAllBytes(outputFile));

        // Then
        assertThat(result).isEqualTo(expected);
    }
}