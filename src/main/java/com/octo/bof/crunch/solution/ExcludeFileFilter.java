package com.octo.bof.crunch.solution;

import org.apache.crunch.FilterFn;

/**
 * Powered by o<+o
 */
public class ExcludeFileFilter extends FilterFn<String> {
    @Override
    public boolean accept(String input) {
        if(input.endsWith(".jpg"))
            return false;
        if(input.endsWith(".gif"))
            return false;
        if(input.endsWith(".ico"))
            return false;
        if(input.endsWith(".txt"))
            return false;
        if(input.endsWith(".png"))
            return false;
        return true;
    }
}
