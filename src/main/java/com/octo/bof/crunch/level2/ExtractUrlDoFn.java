package com.octo.bof.crunch.level2;


import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractUrlDoFn extends DoFn<String, String> {

    @Override
    public void process(String input, Emitter<String> emitter) {
        // regex url \"\\w{1,4}\\s(.*)\\sHTTP/\\d\\.\\d\"
    }
}