package com.tchepannou.kiosk.ranker.comparator;

import com.tchepannou.kiosk.ranker.Rankable;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PublishedDateComparatorTest {

    @Test
    public void testCompare() throws Exception {
        // Given
        final Date now = new Date();
        final Rankable r1 = createRankable(now);
        final Rankable r2 = createRankable(DateUtils.addHours(now, 1));

        // when
        final int result = new PublishedDateComparator().compare(r1, r2);

        // Then
        assertThat(result).isEqualTo(-3600);
    }

    private Rankable createRankable(final Date date){
        final Rankable r = mock(Rankable.class);
        when(r.getPublishedDate()).thenReturn(date);
        return r;
    }
}
