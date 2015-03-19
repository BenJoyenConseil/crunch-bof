package com.octo.bof.crunch.solution;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;

/**
 * Powered by o<+o
 */

public class ExtractIpDoFn extends DoFn<String, String> {

    @Override
    public void process(String input, Emitter<String> emitter) {
        String ip = input.split(" ")[0];
        emitter.emit(ip);
    }
}