package com.octo.bof.crunch.level1;


import org.apache.crunch.*;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.fs.Path;

public class HttpCodeIndicator {

    private String in;
    private String out;
    private Pipeline pipeline;

    public HttpCodeIndicator(Pipeline pipeline, String in, String out){
        this.pipeline = pipeline;
        this.in = in;
        this.out = out;
    }

    public PCollection<String> read(){
        return pipeline.read(From.textFile(new Path(in)));
    }

    public void processIndicator() {
        PCollection<String> records = read();

        // Extraction & count httpCode

        // write(result);
    }

    private void write(PCollection result) {
        pipeline.write(result, To.textFile(out), Target.WriteMode.APPEND);
        pipeline.done();
    }
}