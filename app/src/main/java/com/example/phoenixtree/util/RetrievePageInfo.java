package com.example.phoenixtree.util;

import java.util.List;

/**
 * Created by ej on 10/24/2017.
 */

public class RetrievePageInfo<T> {

    public T content ;

    public boolean last;

    public int totalPages;

    public int totalElements;

    public int size;

    public int number;

    public List<RetrieveSortInfo> sort ;

    public int numberOfElements;

    public boolean first;
}
