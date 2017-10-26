package com.example.phoenixtree.util;

import java.util.List;

/**
 * Created by ej on 10/24/2017.
 */

public class RetrievePageInfo<T> {

    public T content ;

    public boolean last;

    public long totalPages;

    public long totalElements;

    public long size;

    public long number;

    public List<RetrieveSortInfo> sort ;

    public long numberOfElements;

    public boolean first;
}
