package com.octo.bof.crunch.level0;


import org.apache.crunch.*;
import org.apache.crunch.fn.Aggregators;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.fs.Path;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
