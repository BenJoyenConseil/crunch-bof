package com.octo.bof.crunch.level2;


import org.apache.crunch.*;
import org.apache.crunch.fn.Aggregators;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.fs.Path;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        PCollection<Integer> httpCodes = records.parallelDo(new ExtractHttpCodeDoFn(), Writables.ints());
        PTable<Integer, Long> result = httpCodes.count();

        write(result);
    }

    private void write(PCollection result) {
        pipeline.write(result, To.textFile(out), Target.WriteMode.APPEND);
        pipeline.done();
    }
}
