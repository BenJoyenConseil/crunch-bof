package com.octo.bof.crunch.level3;

import com.octo.bof.crunch.BaseCrunchBofTest;
import org.apache.commons.io.FileUtils;
import org.apache.crunch.impl.mem.MemPipeline;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.CrunchAssertions.assertThat;

/**
 * Powered by o<+o
 */

public class IpLocationIndicatorIntTest extends BaseCrunchBofTest {


    private final String countriesFile;

    public IpLocationIndicatorIntTest(){
        in = "src/test/resources/access_log";
        out = "target/test/result";
        countriesFile = "src/test/resources/countries_ip";
    }

    @Test
    public void run_nominalCase() throws IOException {
        // Given
        IpLocationIndicator job = new IpLocationIndicator();

        // When
        job.run(MemPipeline.getInstance(), in, out, countriesFile);

        // Then
        assertThat(out).isEqualTo("src/test/resources/level3/expected.txt");
    }
}
