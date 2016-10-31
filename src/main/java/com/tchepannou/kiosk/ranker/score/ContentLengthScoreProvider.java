package com.tchepannou.kiosk.ranker.score;

import com.tchepannou.kiosk.ranker.Rankable;
import com.tchepannou.kiosk.ranker.RankerContext;
import com.tchepannou.kiosk.ranker.ScoreProvider;

public class ContentLengthScoreProvider implements ScoreProvider {
    @Override
    public long get(final Rankable rankable, RankerContext context) {
        final int maxLength = context.getContentMaxLength();
        final int length = rankable.getContentLength();

        return Math.min(100, (long)(100d*(double)length/(double)maxLength));
    }
}
