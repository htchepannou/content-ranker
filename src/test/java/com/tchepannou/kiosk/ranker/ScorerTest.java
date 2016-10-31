package com.tchepannou.kiosk.ranker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScorerTest {

    @Test
    public void shouldComputeScore() throws Exception {
        // Given
        final RankerContext ctx = mock(RankerContext.class);
        final Rankable r1 = mock(Rankable.class);

        final ScoreProvider sp1 = mock(ScoreProvider.class);
        final ScoreProvider sp2 = mock(ScoreProvider.class);
        when(sp1.get(r1, ctx)).thenReturn(50L);
        when(sp2.get(r1, ctx)).thenReturn(100L);

        final Dimension d1 = createDimension(.6, sp1);
        final Dimension d2 = createDimension(.4, sp2);
        when(ctx.getDimensions()).thenReturn(Arrays.asList(d1, d2));

        // When
        final Score score = new Scorer().compute(r1, ctx);

        // Then
        assertThat(score.getRankable()).isEqualTo(r1);
        assertThat(score.getValue(d1)).isEqualTo(50);
        assertThat(score.getValue(d2)).isEqualTo(100);
        assertThat(score.getValue()).isEqualTo(70);
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
