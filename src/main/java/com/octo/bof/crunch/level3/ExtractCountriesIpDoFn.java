package com.octo.bof.crunch.level3;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;

/**
 * Powered by o<+o
 */

public class ExtractCountriesIpDoFn extends DoFn<String, Pair<String, String>> {

    @Override
    public void process(String input, Emitter<Pair<String, String>> emitter) {
        String[] split = input.split("\t");
        emitter.emit(Pair.of(split[0], split[1]));
    }
}
