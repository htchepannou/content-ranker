package com.tchepannou.kiosk.ranker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RankerTest {

    @Test
    public void shouldRank() throws Exception {
        // Given
        final RankerContext ctx = mock(RankerContext.class);
        final Rankable r1 = mock(Rankable.class);
        final Rankable r2 = mock(Rankable.class);
        final Rankable r3 = mock(Rankable.class);

        final ScoreProvider sp1 = mock(ScoreProvider.class);
        when(sp1.get(r1, ctx)).thenReturn(0L);
        when(sp1.get(r2, ctx)).thenReturn(600L);
        when(sp1.get(r3, ctx)).thenReturn(1000L);

        final ScoreProvider sp2 = mock(ScoreProvider.class);
        when(sp2.get(r1, ctx)).thenReturn(100L);
        when(sp2.get(r2, ctx)).thenReturn(50L);
        when(sp2.get(r3, ctx)).thenReturn(75L);

        final Dimension d1 = createDimension(.6, sp1);
        final Dimension d2 = createDimension(.4, sp2);
        when(ctx.getDimensions()).thenReturn(Arrays.asList(d1, d2));

        // When
        final List<Score> scores = new Ranker().rank(Arrays.asList(r1, r2, r3), ctx);

        // Then
        assertThat(scores.get(0).getRankable()).isEqualTo(r1);
        assertThat(scores.get(0).getValue(d1)).isEqualTo(0);
        assertThat(scores.get(0).getValue(d2)).isEqualTo(100);
        assertThat(scores.get(0).getValue()).isEqualTo(40);

        assertThat(scores.get(1).getRankable()).isEqualTo(r2);
        assertThat(scores.get(1).getValue(d1)).isEqualTo(60);
        assertThat(scores.get(1).getValue(d2)).isEqualTo(50);
        assertThat(scores.get(1).getValue()).isEqualTo(56);

        assertThat(scores.get(2).getRankable()).isEqualTo(r3);
        assertThat(scores.get(2).getValue(d1)).isEqualTo(100);
        assertThat(scores.get(2).getValue(d2)).isEqualTo(75);
        assertThat(scores.get(2).getValue()).isEqualTo(90);
    }

    private RankerContext createRankerContext(Dimension...dimensions){
        return new RankerContext() {
            @Override
            public List<Dimension> getDimensions() {
                return Arrays.asList(dimensions);
            }

            @Override
            public int getContentMaxLength() {
                return 0;
            }
        };
    }

    private Dimension createDimension(final double v, final ScoreProvider sp1) {
        final Dimension dim = mock(Dimension.class);
        when(dim.getScoreProvider()).thenReturn(sp1);
        when(dim.getWeight()).thenReturn(v);
        return dim;
    }
}
