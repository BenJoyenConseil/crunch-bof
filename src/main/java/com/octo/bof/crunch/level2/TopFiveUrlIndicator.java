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

    public void run(Pipeline pipeline, String in, String out) {

        PCollection<String> records = pipeline.read(From.textFile(new Path(in)));
        PCollection<String> urls = records.parallelDo(new ExtractUrlDoFn(), Writables.strings());
        PCollection<String> urlsFiltered = urls.filter(new ExcludeFileFilter());
        PTable<String, Long> result = urlsFiltered.count().top(5);
        pipeline.write(result, To.textFile(out), Target.WriteMode.APPEND);
        pipeline.done();

    }
}
