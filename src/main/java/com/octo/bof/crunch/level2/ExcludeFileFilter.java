package com.octo.bof.crunch.level2;

import org.apache.crunch.FilterFn;

/**
 * Powered by o<+o
 */
public class ExcludeFileFilter extends FilterFn<String> {
    @Override
    public boolean accept(String input) {
        // exclude jpg gif ico txt png extensiosn

        return false;
    }
}
