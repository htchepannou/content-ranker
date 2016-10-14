package com.tchepannou.kiosk.ranker.comparator;

import com.tchepannou.kiosk.ranker.Rankable;

import java.util.Comparator;

public class ContentLengthComparator implements Comparator<Rankable> {
    @Override
    public int compare(final Rankable o1, final Rankable o2) {
        return o1.getContentLength() - o2.getContentLength();
    }
}
