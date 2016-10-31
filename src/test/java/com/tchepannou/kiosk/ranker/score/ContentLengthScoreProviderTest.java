package com.tchepannou.kiosk.ranker.score;

import com.tchepannou.kiosk.ranker.Rankable;
import com.tchepannou.kiosk.ranker.RankerContext;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContentLengthScoreProviderTest {

    @Test
    public void testGet() throws Exception {
        final RankerContext ctx = mock(RankerContext.class);
        when(ctx.getContentMaxLength()).thenReturn(2000);

        final Rankable r = mock(Rankable.class);
        when(r.getContentLength()).thenReturn(1000);

        assertThat(new ContentLengthScoreProvider().get(r, ctx)).isEqualTo(50);
    }

    @Test
    public void testGet1000() throws Exception {
        final RankerContext ctx = mock(RankerContext.class);
        when(ctx.getContentMaxLength()).thenReturn(100);

        final Rankable r = mock(Rankable.class);
        when(r.getContentLength()).thenReturn(1000);

        assertThat(new ContentLengthScoreProvider().get(r, ctx)).isEqualTo(100);
    }
}
