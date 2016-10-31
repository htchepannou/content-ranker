package com.tchepannou.kiosk.ranker.score;

import com.tchepannou.kiosk.ranker.Rankable;
import com.tchepannou.kiosk.ranker.RankerContext;
import com.tchepannou.kiosk.ranker.ScoreProvider;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PublishedDateScoreProviderTest {
    private ScoreProvider provider = new PublishedDateScoreProvider();

    @Test
    public void dateWithinSameHourHaveSameScore() throws Exception {
        final DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:00:00");

        final Date d1 = fmt.parse(fmt.format(new Date()));
        final Rankable r1 = mock(Rankable.class);
        when(r1.getPublishedDate()).thenReturn(d1);

        final Date d2 = DateUtils.addMinutes(d1, 59);
        final Rankable r2 = mock(Rankable.class);
        when(r2.getPublishedDate()).thenReturn(d2);

        RankerContext ctx = mock(RankerContext.class);

        assertThat(provider.get(r1, ctx)).isEqualTo(provider.get(r2, ctx));
    }

    @Test
    public void dateWithinDifferentHourHaveDifferentScore() throws Exception {
        final DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:00:00");

        final Date d1 = fmt.parse(fmt.format(new Date()));
        final Rankable r1 = mock(Rankable.class);
        when(r1.getPublishedDate()).thenReturn(d1);

        final Date d2 = DateUtils.addMinutes(d1, 61);
        final Rankable r2 = mock(Rankable.class);
        when(r2.getPublishedDate()).thenReturn(d2);

        RankerContext ctx = mock(RankerContext.class);

        assertThat(provider.get(r1, ctx)).isLessThan(provider.get(r2, ctx));
    }
}
