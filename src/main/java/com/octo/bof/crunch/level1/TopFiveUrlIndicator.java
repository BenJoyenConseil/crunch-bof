package com.octo.bof.crunch.level1;

import org.apache.crunch.*;
import org.apache.crunch.fn.Aggregators;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.fs.Path;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: 0<+0
 * Date: 16/03/15
 * Time: 13:33
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

        PCollection<String> urls = records.parallelDo(new ExtractUrlDoFn(), Writables.strings());
        PTable<String, Long> result = urls.count().top(5);

        write(result);
    }

    private void write(PCollection result) {
        pipeline.write(result, To.textFile(out), Target.WriteMode.APPEND);
        pipeline.done();
    }
}
