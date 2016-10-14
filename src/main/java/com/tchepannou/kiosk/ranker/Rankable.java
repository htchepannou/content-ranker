package com.tchepannou.kiosk.ranker;

import java.util.Date;

public interface Rankable {
    Date getPublishedDate();

    int getContentLength();

    boolean isWithImage();
}
