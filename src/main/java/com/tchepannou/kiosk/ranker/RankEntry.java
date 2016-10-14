package com.tchepannou.kiosk.ranker;

import java.util.HashMap;
import java.util.Map;

public class RankEntry implements Comparable<RankEntry>{
    private final Rankable rankable;
    private final Map<Dimension, Integer> ranks = new HashMap<>();
    private Double finalRank;

    public RankEntry(final Rankable rankable) {
        this.rankable = rankable;
    }

    @Override
    public int compareTo(final RankEntry obj) {
        return  (int)(100 * (getFinalRank() - obj.getFinalRank()));
    }

    public void setRank(final Dimension dimension, final int rank){
        ranks.put(dimension, rank);
        finalRank = null;
    }

    public double getFinalRank (){
        if (finalRank == null){
            finalRank = computeFinalRank();
        }
        return finalRank;
    }

    public Rankable getRankable() {
        return rankable;
    }

    private double computeFinalRank(){
        float rank = 0;
        for (final Dimension dimension : ranks.keySet()){
            rank += dimension.getWeight() * ranks.get(dimension);
        }
        return rank;
    }
}
