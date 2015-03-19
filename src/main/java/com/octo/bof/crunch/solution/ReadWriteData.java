package com.octo.bof.crunch.solution;


import org.apache.crunch.PCollection;
import org.apache.crunch.Pipeline;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.hadoop.fs.Path;

public class ReadWriteData {
    private String in;
    private String out;
    private Pipeline pipeline;

    public ReadWriteData(Pipeline pipeline, String in, String out){
        this.pipeline = pipeline;
        this.in = in;
        this.out = out;
    }

    public void processIndicator() {
        PCollection<String> records = pipeline.read(From.textFile(new Path(in)));

        pipeline.write(records, To.textFile(out));
        pipeline.done();
    }
}
