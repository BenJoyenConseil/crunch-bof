package com.octo.bof.crunch.level3;

import com.octo.bof.crunch.level2.ExcludeFileFilter;
import com.octo.bof.crunch.level2.ExtractUrlDoFn;
import org.apache.crunch.*;
import org.apache.crunch.fn.Aggregators;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.lib.PTables;
import org.apache.crunch.lib.Sort;
import org.apache.crunch.lib.join.*;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.fs.Path;

import static org.apache.crunch.lib.Sort.ColumnOrder.by;

/**
 * Powered by o<+o
 */

public class IpLocationIndicator {
    private String in;
    private String out;
    private Pipeline pipeline;

    public IpLocationIndicator(Pipeline pipeline, String in, String out){
        this.pipeline = pipeline;
        this.in = in;
        this.out = out;
    }

    public PCollection<String> read(){
        return pipeline.read(From.textFile(new Path(in)));
    }

    public void processIndicator() {
        PTable<String, String> countryByIp = getCountryByIp();

        PCollection<String> records = read();
        PCollection<String> ip = records.parallelDo(new ExtractIpDoFn(), Writables.strings());
        PTable<String, Long> countIp = ip.count();
        PTable<String, Pair<String, Long>> join = countryByIp.join(countIp);
        PTable<String, Long> countCountry = PTables.asPTable(join.values());
        PTable<String, Long> result = countCountry.groupByKey().combineValues(Aggregators.SUM_LONGS());
        write(result);
    }

    private PTable<String, String> getCountryByIp() {
        PCollection<String> records = pipeline.readTextFile("src/test/resources/countries_ip");
        return records.parallelDo(new ExtractCountriesIpDoFn(), Writables.tableOf(Writables.strings(), Writables.strings()));
    }

    private void write(PCollection result) {
        pipeline.write(result, To.textFile(out), Target.WriteMode.APPEND);
        pipeline.done();
    }
}
