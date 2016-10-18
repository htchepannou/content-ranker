package com.tchepannou.kiosk.ranker;

public interface Dimension {
    double getWeight();
    ScoreProvider getScoreProvider();
    String getName();
}
