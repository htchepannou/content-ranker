package com.tchepannou.kiosk.ranker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RankerTest {

    @Test
    public void testRank() throws Exception {
        // Given
        final Rankable r1 = createRankable();
        final Rankable r2 = createRankable();
        final Rankable r3 = createRankable();

        final Comparator<Rankable> cmpA = createComparator(Arrays.asList(r1, r2, r3));
        final Comparator<Rankable> cmpB = createComparator(Arrays.asList(r1, r3, r2));
        final Comparator<Rankable> cmpC = createComparator(Arrays.asList(r2, r3, r1));

        final Dimension dimA = createDimension(.25, cmpA);
        final Dimension dimB = createDimension(.30, cmpB);
        final Dimension dimC = createDimension(.45, cmpC);

        final DimensionSetProvider dimensionSetProvider = createDimensionSetProvider(Arrays.asList(dimA, dimB, dimC));
        final RankerContext ctx = createRankerContext(dimensionSetProvider);

        // When
        List<RankEntry> entries = new Ranker().rank(Arrays.asList(r1, r2, r3), ctx);

        // Then
        assertThat(entries).hasSize(3);

        assertThat(entries.get(0).getRankable()).isEqualTo(r2);
        assertThat((float)entries.get(0).getFinalRank()).isEqualTo(1.85f);

        assertThat(entries.get(1).getRankable()).isEqualTo(r1);
        assertThat((float)entries.get(1).getFinalRank()).isEqualTo(1.9f);

        assertThat(entries.get(2).getRankable()).isEqualTo(r3);
        assertThat((float)entries.get(2).getFinalRank()).isEqualTo(2.25f);
    }


    private RankerContext createRankerContext(final DimensionSetProvider provider){
        return new RankerContext() {
            @Override
            public DimensionSetProvider getDimensionSetProvider() {
                return provider;
            }
        };
    }
    private DimensionSetProvider createDimensionSetProvider(final List<Dimension> dimensions){
        return () -> dimensions;
    }

    private Comparator<Rankable> createComparator(final List<Rankable> rankables){
        return (r1, r2) -> rankables.indexOf(r1) - rankables.indexOf(r2);
    }

    private Dimension createDimension(final double weight, final Comparator<Rankable> comparator) {
        final Dimension dim = mock(Dimension.class);
        when(dim.getWeight()).thenReturn(weight);
        when(dim.getComparator()).thenReturn(comparator);
        return dim;
    }

    private Rankable createRankable() {
        return mock(Rankable.class);
    }

}
