package com.octo.bof.crunch.level1;


import org.apache.crunch.PCollection;
import org.apache.crunch.PTable;
import org.apache.crunch.Pipeline;
import org.apache.crunch.Target;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.fs.Path;

public class HttpCodeIndicator {

    public void run(Pipeline pipeline, String in, String out) {
        PCollection<String> records = pipeline.read(From.textFile(new Path(in)));
        PCollection<Integer> httpCodes = records.parallelDo(new ExtractHttpCodeDoFn(), Writables.ints());
        PTable<Integer, Long> result = httpCodes.count();
        pipeline.write(result, To.textFile(out), Target.WriteMode.APPEND);
        pipeline.done();
    }
}
