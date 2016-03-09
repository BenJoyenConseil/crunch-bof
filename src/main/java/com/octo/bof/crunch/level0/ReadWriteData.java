package com.octo.bof.crunch.level0;


import org.apache.crunch.PCollection;
import org.apache.crunch.Pipeline;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.hadoop.fs.Path;

/**
 * Powered by o<+o (bjc).
 */

public class ReadWriteData {

    public void run(Pipeline pipeline, String in, String out) {
        PCollection<String> records = pipeline.read(From.textFile(new Path(in)));

        pipeline.write(records, To.textFile(out));
        pipeline.done();
    }
}
