package com.tchepannou.kiosk.ranker.score;

import com.tchepannou.kiosk.ranker.Rankable;
import com.tchepannou.kiosk.ranker.RankerContext;
import com.tchepannou.kiosk.ranker.ScoreProvider;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PublishedDateScoreProvider implements ScoreProvider{
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:00:00 Z");

    @Override
    public long get(final Rankable rankable, RankerContext context) {
        final Date date = rankable.getPublishedDate();
        try {
            final Date xdate = dateFormat.parse(dateFormat.format(date));
            return xdate.getTime();
        } catch (ParseException e){
            throw new IllegalStateException("Invalid date: " + date, e);
        }
    }
}
