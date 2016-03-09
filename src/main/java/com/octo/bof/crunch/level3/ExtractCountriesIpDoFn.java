package com.octo.bof.crunch.level3;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;

/**
 * Powered by o<+o
 */

public class ExtractCountriesIpDoFn extends DoFn<String, Pair<String, String>> {

    public static final String TAB = "\t";

    @Override
    public void process(String input, Emitter<Pair<String, String>> emitter) {
        String[] splits = input.split(TAB);
        String ip = splits[0];
        String countryCode = splits[1];
        emitter.emit(Pair.of(ip, countryCode));
    }
}
