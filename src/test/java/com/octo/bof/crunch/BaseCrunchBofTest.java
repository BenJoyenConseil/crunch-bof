package com.octo.bof.crunch;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;

import java.io.File;

/**
 * Powered by o<+o (bjc).
 */

public class BaseCrunchBofTest {

    protected String in = null;
    protected String out = null;


    @Before
    public void setUp() throws Exception {
        FileUtils.deleteDirectory(new File(out));
    }


    @After
    public void cleanUp() throws Exception {
        FileUtils.deleteDirectory(new File(out));
    }
}
