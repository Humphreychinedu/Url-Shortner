package com.interswitch.urlshortener.urlshortnertest;

import api.Util.UrlShortner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UrlShortnerTest {

    @Test
    public void encodeTest() {
        long time = 1429535409589L;
        assertEquals("zkyUT3t", UrlShortner.encode(time));
    }
}
