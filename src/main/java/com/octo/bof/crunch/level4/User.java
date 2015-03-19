package com.octo.bof.crunch.level4;

import java.util.HashMap;
import java.util.Map;

/**
 * Powered by o<+o
 */

public class User {
    String firstName;
    String lastName;
    Integer old;
    String email;
    Map<String, Integer> interests;

    public User(){
        firstName = "";
        lastName = "";
        old = 0;
        email = "";
        interests = new HashMap<String, Integer>();
    }

    public User(String firstName, String lastName, Integer old, String email, Map<String, Integer> interests){
        this.firstName = firstName;
        this.lastName = lastName;
        this.old = old;
        this.email = email;
        this.interests = interests;
    }
}
