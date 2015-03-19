package com.octo.bof.crunch.solution;


import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractUrlDoFn extends DoFn<String, String> {

    @Override
    public void process(String input, Emitter<String> emitter) {
        Matcher matcher = Pattern.compile("\"\\w{1,4}\\s(.*)\\sHTTP/\\d\\.\\d\"").matcher(input);
        if(!matcher.find())
            return;
        String url = matcher.group(1);
        emitter.emit(url);
    }
}
