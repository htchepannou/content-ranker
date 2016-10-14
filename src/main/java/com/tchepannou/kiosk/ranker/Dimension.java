package com.tchepannou.kiosk.ranker;

import java.util.Comparator;

public interface Dimension {
    double getWeight();
    Comparator<Rankable> getComparator();
    String getName();
}
