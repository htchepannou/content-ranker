package com.tchepannou.kiosk.ranker.score;

import com.tchepannou.kiosk.ranker.Rankable;
import com.tchepannou.kiosk.ranker.ScoreProvider;

public class ImageScoreProvider implements ScoreProvider {
    @Override
    public long get(final Rankable rankable) {
        return rankable.isWithImage() ? 100 : 0;
    }
}
