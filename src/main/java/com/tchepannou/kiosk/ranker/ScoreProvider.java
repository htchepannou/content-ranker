package com.tchepannou.kiosk.ranker;

public interface ScoreProvider {
    int get(Rankable rankable);
}
