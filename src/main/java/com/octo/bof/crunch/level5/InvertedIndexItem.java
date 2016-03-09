package com.octo.bof.crunch.level5;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Powered by o<+o (bjc).
 */

public class InvertedIndexItem implements Serializable {
    public String word;
    public ArrayList<String> docs;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(word)
                .append(' ')
                .append('|')
                .append(' ');
        docs.forEach(item -> builder.append(item).append(','));

        return builder.toString();
    }
}
