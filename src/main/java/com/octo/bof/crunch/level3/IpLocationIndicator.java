package com.octo.bof.crunch.level3;

import org.apache.crunch.*;
import org.apache.crunch.fn.Aggregators;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.lib.PTables;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.fs.Path;

/**
 * Powered by o<+o
 */

public class IpLocationIndicator {
    public void run(Pipeline pipeline, String in, String out, String countriesFile) {

        PTable<String, String> countryByIp = pipeline.readTextFile(countriesFile)
                .parallelDo(
                        new ExtractCountriesIpDoFn(),
                        Writables.tableOf(Writables.strings(), Writables.strings())
                );

        PCollection<String> ipAddresses = pipeline.read(From.textFile(new Path(in))).parallelDo(new ExtractIpDoFn(), Writables.strings());

        PTable<String, Long> countIp = ipAddresses.count();
        PTable<String, Pair<String, Long>> join = countryByIp.join(countIp);
        PTable<String, Long> countCountry = PTables.asPTable(join.values());
        PTable<String, Long> result = countCountry.groupByKey().combineValues(Aggregators.SUM_LONGS());

        pipeline.write(result, To.textFile(out), Target.WriteMode.APPEND);
        pipeline.done();

    }
}
