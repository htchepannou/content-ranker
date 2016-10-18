package com.tchepannou.kiosk.ranker.score;

import com.tchepannou.kiosk.ranker.Rankable;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContentLengthScoreProviderTest {

    @Test
    public void testGet() throws Exception {
        Rankable r = mock(Rankable.class);
        when(r.getContentLength()).thenReturn(1000);

        assertThat(new ContentLengthScoreProvider().get(r)).isEqualTo(1000);
    }
}
