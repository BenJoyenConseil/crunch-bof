package com.octo.bof.crunch.level0;


import org.apache.commons.io.FileUtils;
import org.apache.crunch.impl.mem.CustomMemPipeline;
import org.apache.crunch.impl.mem.MemPipeline;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadWriteIntTest {

    String in = "src/test/resources/access_log";
    String out = "target/test/result";
    private Path resultFile = Paths.get(out + "/out.txt");
    private Path expectedFile = Paths.get("src/test/resources/level0/expected.txt");;
    private ReadWriteData indicator;

    @Before
    public void setUp() throws Exception {
        FileUtils.deleteDirectory(new File(out));
        indicator = new ReadWriteData(CustomMemPipeline.getInstance(), in, out);
    }

    @Test
    public void processIndicator_nominalCase() throws IOException {
        // Given
        String expected = new String(Files.readAllBytes(expectedFile));

        // When
        indicator.processIndicator();
        String result = new String(Files.readAllBytes(resultFile));

        // Then
        assertThat(result).isEqualTo(expected);
    }
}
