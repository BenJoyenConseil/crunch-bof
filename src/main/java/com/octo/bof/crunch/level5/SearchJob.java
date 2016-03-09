package com.octo.bof.crunch.level5;

import org.apache.crunch.FilterFn;
import org.apache.crunch.PCollection;
import org.apache.crunch.Pipeline;
import org.apache.crunch.io.From;
import org.apache.crunch.types.avro.Avros;
import org.apache.hadoop.fs.Path;

import java.io.Serializable;

/**
 * Powered by o<+o (bjc).
 */

public class SearchJob implements Serializable{

    public Iterable<InvertedIndexItem> run(Pipeline pipeline, String invertedIndexPath, String searchStr) {

        PCollection<InvertedIndexItem> avroRecords = read(pipeline, invertedIndexPath);
        Iterable<InvertedIndexItem> result = search(avroRecords, searchStr);
        pipeline.done();
        return result;
    }

    public Iterable<InvertedIndexItem> search(PCollection<InvertedIndexItem> records, String searchStr) {
        Iterable<InvertedIndexItem> result = records.filter(new FilterFn<InvertedIndexItem>() {
            @Override
            public boolean accept(InvertedIndexItem input) {
                if (input.word.equalsIgnoreCase(searchStr))
                    return true;
                return false;
            }
        }).materialize();

        return result;
    }

    public PCollection<InvertedIndexItem> read(Pipeline pipeline, String invertedIndexPath) {
        return pipeline.read(From.avroFile(
                        new Path(invertedIndexPath),
                        Avros.reflects(InvertedIndexItem.class))
            );
    }
}
