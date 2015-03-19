package com.octo.bof.crunch.level3;

import com.octo.bof.crunch.level2.TopFiveUrlIndicator;
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

public class IpLocationIndicatorIntTest {
    String in = "src/test/resources/access_log";
    String out = "target/test/result";
    private Path outputFile = Paths.get(out + "/out.txt");
    private Path expectedFile = Paths.get("src/test/resources/level3/expected.txt");;
    private IpLocationIndicator indicator;

    @Before
    public void setUp() throws Exception {
        FileUtils.deleteDirectory(new File(out));
        indicator = new IpLocationIndicator(MemPipeline.getInstance(), in, out, "src/test/resources/countries_ip");
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
