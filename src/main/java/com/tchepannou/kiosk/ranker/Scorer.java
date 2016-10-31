package com.tchepannou.kiosk.ranker;

public class Scorer {
    public Score compute(Rankable rankable, RankerContext context){
        final Score score = new Score(rankable);
        for (final Dimension dimension : context.getDimensions()){
            score.setValue(dimension, dimension.getScoreProvider().get(rankable, context));
        }
        return score;
    }
}
