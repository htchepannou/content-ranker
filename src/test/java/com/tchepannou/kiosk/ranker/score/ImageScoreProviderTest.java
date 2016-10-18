package com.tchepannou.kiosk.ranker.score;

import com.tchepannou.kiosk.ranker.Rankable;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ImageScoreProviderTest {

    @Test
    public void testGetWithImage() throws Exception {
        Rankable r = mock(Rankable.class);
        when(r.isWithImage()).thenReturn(true);

        assertThat(new ImageScoreProvider().get(r)).isEqualTo(100);

    }

    @Test
    public void testGetWithoutImage() throws Exception {
        Rankable r = mock(Rankable.class);
        when(r.isWithImage()).thenReturn(false);

        assertThat(new ImageScoreProvider().get(r)).isEqualTo(0);

    }
}
