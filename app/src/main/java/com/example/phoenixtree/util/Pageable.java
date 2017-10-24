package com.example.phoenixtree.util;

/**
 * Created by ej on 10/23/2017.
 */

public class Pageable {
    public long page;
    public long size;
    public String sort;

    public Pageable(long page, long size, String sort) {
        this.page = page;
        this.size = size;
        this.sort = sort;
    }
}
