package com.octo.bof.crunch.solution;

import org.apache.crunch.*;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;

/**
 * Powered by o<+o
 */

public class Advertising {
    private String in;
    private String out;
    private Pipeline pipeline;

    public Advertising(Pipeline pipeline, String in, String out){
        this.pipeline = pipeline;
        this.in = in;
        this.out = out;
    }

    public void processJob() {
        PCollection<String> usersRawData = pipeline.read(From.textFile(in));
        usersRawData.parallelDo(new DoFn<String, Pair<String, >>() {
        })


        pipeline.write(usersRawData, To.avroFile(out));
    }

    private void write(PCollection result) {
        pipeline.write(result, To.textFile(out), Target.WriteMode.APPEND);
        pipeline.done();
    }
}
