package com.octo.bof.crunch.level1;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExtractHttpCodeDoFn extends DoFn<String, Integer> {

    @Override
    public void process(String input, Emitter<Integer> emitter) {
        // regex to match HttpStatusCode "\"\\s(\\d{3})"

        // emit code
    }
}