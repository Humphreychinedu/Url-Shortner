package com.interswitch.urlshortener.api.Util;

public class UrlShortner {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final int BASE = ALPHABET.length();

//    public static String encode(long num) {
//        StringBuilder stringBuilder =  new StringBuilder();
//        while(num > 0) {
//            stringBuilder.append(ALPHABET.charAt((int) num % BASE));
//            num /= BASE;
//        }
//        return stringBuilder.reverse().toString();
//    }


    public static String encode(long i) {
        StringBuilder sb = new StringBuilder("");
        while (i > 0) {
            i = fromBase10(i, sb);
        }
        return sb.reverse().toString();
    }

    private static long fromBase10(long i, final StringBuilder sb) {
        int rem = (int) (i % BASE);
        sb.append(ALPHABET.charAt(rem));
        return i / BASE;
    }

    public static long toBase10(String str) {
        return toBase10(new StringBuilder(str).reverse().toString().toCharArray());
    }

    private static long toBase10(char[] chars) {
        long n = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            n += toBase10(ALPHABET.indexOf(chars[i]), i);
        }
        return n;
    }

    private static long toBase10(long n, long pow) {
        return n * (long) Math.pow(BASE, pow);
    }
}
