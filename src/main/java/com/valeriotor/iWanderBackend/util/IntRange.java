package com.valeriotor.iWanderBackend.util;

import java.util.ArrayList;
import java.util.List;

public class IntRange {

    public static IntRange of(int start, int end) {
        if(start < 0 || end < start + 1)
            return null;
        return new IntRange(start, end);
    }

    private final int start;
    private final int end;

    private IntRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /** Gives sublist according to IntRange parameters, and takes into account out of range indexes (stopping where needed).
     * Prefers ArrayLists
     */
    public <T> List<T> getSublist(List<? extends T> list) {
        List<T> newList = new ArrayList<>();
        for(int i = start; i < end; i++) {
                if(list.size() > i) {
                    newList.add(list.get(i));
                }
        }
        return newList;
    }

}
