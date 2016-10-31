package com.tchepannou.kiosk.ranker;

import java.util.List;

public interface RankerContext {
    List<Dimension> getDimensions();
    int getContentMaxLength();
}
