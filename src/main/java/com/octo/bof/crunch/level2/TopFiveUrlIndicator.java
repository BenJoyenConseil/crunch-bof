package com.octo.bof.crunch.level2;

import org.apache.crunch.PCollection;
import org.apache.crunch.PTable;
import org.apache.crunch.Pipeline;
import org.apache.crunch.Target;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.fs.Path;

/**
 * Powered by o<+o
 */

public class TopFiveUrlIndicator {
    private String in;
    private String out;
    private Pipeline pipeline;

    public TopFiveUrlIndicator(Pipeline pipeline, String in, String out){
        this.pipeline = pipeline;
        this.in = in;
        this.out = out;
    }

    public PCollection<String> read(){
        return pipeline.read(From.textFile(new Path(in)));
    }

    public void processIndicator() {
        PCollection<String> records = read();

        // extract urls from logs
        // exclude files
        // count ulrs & top

        //write(result);
    }

    private void write(PCollection result) {
        pipeline.write(result, To.textFile(out), Target.WriteMode.APPEND);
        pipeline.done();
    }
}
