package com.tchepannou.kiosk.ranker.score;

import com.tchepannou.kiosk.ranker.Rankable;
import com.tchepannou.kiosk.ranker.ScoreProvider;

public class ContentLengthScoreProvider implements ScoreProvider {
    @Override
    public int get(final Rankable rankable) {
        return rankable.getContentLength();
    }
}