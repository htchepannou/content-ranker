package com.tchepannou.kiosk.ranker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ranker {
    public List<RankEntry> rank(final List<Rankable> rankables, final RankerContext context) {
        final Map<Rankable, RankEntry> entries = new HashMap<>();
        final List<Dimension> dimensions = context.getDimensionSetProvider().get();
        for (final Dimension dimension : dimensions) {
            sort(rankables, entries, dimension);
        }

        final List<RankEntry> result = new ArrayList<>(entries.values());
        Collections.sort(result);
        return result;
    }

    private void sort(
            final List<Rankable> rankables,
            final Map<Rankable, RankEntry> entries,
            final Dimension dimension
    ) {
        final List<Rankable> items = new ArrayList<>(rankables);
        Collections.sort(items, dimension.getComparator());

        int i = 1;
        for (final Rankable item : items){
            final RankEntry entry = entries.containsKey(item) ? entries.get(item) : new RankEntry(item);
            entry.setRank(dimension, i++);
            entries.put(item, entry);
        }
    }
}
