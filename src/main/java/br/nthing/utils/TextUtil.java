package br.nthing.utils;

public class TextUtil {
    private TextUtil() {}

    public static String normalizeSpaces(String text) {
        if (text == null) return null;
        return text.trim().replaceAll("\\s+", " ");
    }
}
