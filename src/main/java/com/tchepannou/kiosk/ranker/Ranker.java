package com.tchepannou.kiosk.ranker;

import java.util.List;
import java.util.stream.Collectors;

public class Ranker {
    public List<Score> rank(final List<Rankable> rankables, final RankerContext context) {
        final List<Score> scores = rankables.stream()
                .map(r -> new Score(r))
                .collect(Collectors.toList());

        for (final Dimension dimension : context.getDimensions()){
            compute(scores, dimension, context);
        }

        return scores.stream()
                .sorted((u, v) -> u.getValue() - v.getValue())
                .collect(Collectors.toList());
    }

    private void compute(final List<Score> scores, final Dimension dimension, final RankerContext context) {

        // Raw score
        long max = 0;
        for (final Score score : scores){
            final long value = dimension.getScoreProvider().get(score.getRankable(), context);
            if (value > max){
                max = value;
            }

            score.setValue(dimension, value);
        }
        if (max == 0){
            return;
        }

        // Adjusted score
        for (final Score score : scores){
            final long value = 100*score.getValue(dimension)/max;
            score.setValue(dimension, value);
        }
    }

}
