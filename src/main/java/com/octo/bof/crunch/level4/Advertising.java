package com.octo.bof.crunch.level4;

import org.apache.crunch.*;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.types.avro.Avros;

/**
 * Powered by o<+o
 */

public class Advertising {
    private String in;
    private String out;
    private Pipeline pipeline;

    public Advertising(Pipeline pipeline, String in, String out){
        this.pipeline = pipeline;
        this.in = in;
        this.out = out;
    }

    public void processJob() {
        PCollection<String> usersRawData = pipeline.read(From.textFile(in));

        PCollection<User> result = usersRawData.parallelDo(new ExtractUserDoFn(), Avros.reflects(User.class));


        pipeline.write(result, To.avroFile(out));
    }

    private void write(PCollection result) {
        pipeline.write(result, To.textFile(out), Target.WriteMode.APPEND);
        pipeline.done();
    }
}
