package com.interswitch.urlshortener.urlshortnertest;

import api.Util.UrlShortner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UrlShortnerTest {

    @Test
    public void testCharList() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++) {
            sb.append(c);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            sb.append(c);
        }
        for (int i = 0; i <= 9; i++) {
            sb.append(i);
        }
        assertEquals(sb.toString(), UrlShortner.ALPHABET);
    }

    @Test
    public void testStringFromInt() throws Exception {
        int n = 0;
        String str = "6JaY2";
        char[] chars = str.toCharArray();
        n += UrlShortner.ALPHABET.indexOf(chars[0]) * (int) Math.pow(62, 4);
        n += UrlShortner.ALPHABET.indexOf(chars[1]) * (int) Math.pow(62, 3);
        n += UrlShortner.ALPHABET.indexOf(chars[2]) * (int) Math.pow(62, 2);
        n += UrlShortner.ALPHABET.indexOf(chars[3]) * (int) Math.pow(62, 1);
        n += UrlShortner.ALPHABET.indexOf(chars[4]) * (int) Math.pow(62, 0);
        assertEquals(str, UrlShortner.encode(n));
    }

    @Test
    public void testIntegerFromString() throws Exception {
        assertEquals(125, UrlShortner.toBase10("cb"));
    }
}
