package com.tchepannou.kiosk.ranker;

public interface ScoreProvider {
    long get(Rankable rankable, RankerContext context);
}
