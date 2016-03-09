package com.octo.bof.crunch.level4;

import org.apache.crunch.*;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.fs.Path;

/**
 * Powered by o<+o
 */

public class InvertedIndex {
    public void run(Pipeline pipeline, String in, String out) {

        PCollection<String> lines = pipeline.read(From.textFile(new Path(in)));

        PTable<String, String> words = lines.parallelDo(
                new ExtractWordByArticleDoFn(),
                Writables.tableOf(
                        Writables.strings(),
                        Writables.strings()
        ));

        PTable<String, String> result = words.groupByKey().parallelDo(
                new WordRelatedArticlesDoFn(),
                Writables.tableOf(
                        Writables.strings(),
                        Writables.strings()
        ));


        pipeline.write(result, To.textFile(out), Target.WriteMode.APPEND);
        pipeline.done();

    }
}
