package com.tchepannou.kiosk.ranker.comparator;

import com.tchepannou.kiosk.ranker.Rankable;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ImageComparatorTest {

    @Test
    public void testCompare() throws Exception {
        // Given
        final Rankable r1 = createRankable(true);
        final Rankable r2 = createRankable(false);

        // when
        final int result = new ImageComparator().compare(r1, r2);

        // Then
        assertThat(result).isEqualTo(1);

    }

    private Rankable createRankable(final boolean withImage){
        final Rankable r = mock(Rankable.class);
        when(r.isWithImage()).thenReturn(withImage);
        return r;
    }

}
