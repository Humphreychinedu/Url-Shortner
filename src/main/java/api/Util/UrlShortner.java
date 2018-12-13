package api.Util;

public class UrlShortner {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = ALPHABET.length();

    public static String encode(long num) {
        StringBuilder stringBuilder =  new StringBuilder();
        while(num > 0) {
            stringBuilder.append(ALPHABET.charAt((int)num % BASE));
            num /= BASE;
        }
        return stringBuilder.reverse().toString();
    }
}
