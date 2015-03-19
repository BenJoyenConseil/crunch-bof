package com.octo.bof.crunch.level4;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;

import java.util.HashMap;

/**
 * Powered by o<+o
 */

public class ExtractUserDoFn extends DoFn<String, User> {
    @Override
    public void process(String input, Emitter<User> emitter) {
        String[] splits = input.split(" ");
        String products = splits[4];
        String[] interestSplits = products.substring(1, products.length() - 1).split(",");
        String productId = interestSplits[0];
        Integer views = Integer.valueOf(interestSplits[1]);
        HashMap<String, Integer> interests = new HashMap<String, Integer>();
        interests.put(productId, views);
        User user = new User(splits[0], splits[1], Integer.valueOf(splits[2]), splits[3], interests);

        emitter.emit(user);
    }
}
