package com.octo.bof.crunch.solution;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExtractHttpCodeDoFn extends DoFn<String, Integer> {

    @Override
    public void process(String input, Emitter<Integer> emitter) {
        Matcher matcher = Pattern.compile("\"\\s(\\d{3})").matcher(input);
        matcher.find();
        String group = matcher.group(1);
        Integer httpCode = Integer.valueOf(group);
        emitter.emit(httpCode);
    }
}
