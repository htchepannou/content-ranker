package com.tchepannou.kiosk.ranker;

import java.util.Date;

public interface Rankable {
    int getContentLength();

    boolean isWithImage();

    Date getPublishedDate();
}
