package com.example.phoenixtree.util;

/**
 * Created by ej on 12/27/2017.
 */

public enum FragmentName {

    BROWSE("browse", 1), PARTICIPATE("participate", 2), COMPOSE("compose", 3), LOGIN("login", 4), PROFILE("profile", 5);

    public String name;
    public int index;

    FragmentName(String name, int index) {
        this.name = name;
        this.index = index;
    }
}
