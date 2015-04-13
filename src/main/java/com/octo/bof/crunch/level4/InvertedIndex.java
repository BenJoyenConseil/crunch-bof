package com.octo.bof.crunch.level4;

import com.octo.bof.crunch.level3.ExtractCountriesIpDoFn;
import com.octo.bof.crunch.level3.ExtractIpDoFn;
import org.apache.crunch.*;
import org.apache.crunch.fn.Aggregators;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.lib.PTables;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.fs.Path;

import java.util.Iterator;

/**
 * Powered by o<+o
 */

public class InvertedIndex {
    public void run(Pipeline pipeline, String in, String out) {

        PCollection<String> lines = pipeline.read(From.textFile(new Path(in)));

        PTable<String, String> words = lines.parallelDo(new ExtractWordByArticleDoFn(), Writables.tableOf(Writables.strings(), Writables.strings()));

        PTable<String, String> result = words.groupByKey().parallelDo(new DoFn<Pair<String, Iterable<String>>, Pair<String, String>>() {
            @Override
            public void process(Pair<String, Iterable<String>> input, Emitter<Pair<String, String>> emitter) {
                String word = input.first();
                String articles = "";
                Iterator<String> iterator = input.second().iterator();

                while (iterator.hasNext())
                    articles = articles + iterator.next() + ",";
                emitter.emit(Pair.of(word, articles));
            }
        }, Writables.tableOf(Writables.strings(), Writables.strings()));


        pipeline.write(result, To.textFile(out), Target.WriteMode.APPEND);
        pipeline.done();

    }
}
