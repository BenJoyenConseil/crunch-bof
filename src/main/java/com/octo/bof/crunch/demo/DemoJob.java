package com.octo.bof.crunch.demo;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.PCollection;
import org.apache.crunch.PTable;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.types.writable.Writables;
import org.apache.crunch.util.CrunchTool;
import org.apache.hadoop.fs.Path;

/**
 * Powered by o<+o
 */

public class DemoJob extends CrunchTool{


    public DemoJob(){
        super(true);
    }

    @Override
    public int run(String[] args) throws Exception {
        // count IPs from logs
        PCollection<String> records = getPipeline().read(From.textFile(args[0]));
        PCollection<String> ip = records.parallelDo(new DoFn<String, String>() {
            @Override
            public void process(String input, Emitter<String> emitter) {
                emitter.emit(input.split(" ")[0]);
            }
        }, Writables.strings());


        getPipeline().write(ip, To.textFile(new Path(args[1])));

        return 0;
    }
}
