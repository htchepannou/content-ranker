package com.tchepannou.kiosk.ranker.comparator;

import com.tchepannou.kiosk.ranker.Rankable;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContentLengthComparatorTest {

    @Test
    public void testCompare() throws Exception {
        // Given
        final Rankable r1 = createRankable(100);
        final Rankable r2 = createRankable(300);

        // when
        final int result = new ContentLengthComparator().compare(r1, r2);

        // Then
        assertThat(result).isEqualTo(-200);
    }

    final Rankable createRankable(final int contentLength){
        final Rankable r = mock(Rankable.class);
        when(r.getContentLength()).thenReturn(contentLength);
        return r;
    }}
