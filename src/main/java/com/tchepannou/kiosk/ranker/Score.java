package com.tchepannou.kiosk.ranker;

import java.util.HashMap;
import java.util.Map;

public class Score {
    private final Rankable rankable;
    private final Map<Dimension, Long> values = new HashMap<>();
    private Integer value;

    public Score(final Rankable rankable) {
        this.rankable = rankable;
    }

    public Rankable getRankable() {
        return rankable;
    }

    public int getValue() {
        if (value == null) {
            value = values.keySet().stream()
                    .mapToInt(dim -> (int)(dim.getWeight() * values.get(dim)))
                    .sum();
        }
        return value;
    }

    public void setValue(final Dimension dimension, final long score) {
        values.put(dimension, score);
        value = null;
    }

    public long getValue(final Dimension dimension) {
        return values.containsKey(dimension) ? values.get(dimension) : 0;
    }
}
