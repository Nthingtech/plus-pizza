package br.nthing.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TexUtilsTest {

    @Test
    void normalizeSpacesRemovesExtraWhiteSpace() {
        // GIVEN
        String input =  "Quatro   Queijos  ";

        // WHEN
        String result = TextUtil.normalizeSpaces(input);

        // THEN
        assertEquals("Quatro Queijos", result);
    }

    @Test
    void normalizeSpaceHandlesEmptyString() {
        // GIVEN
        String input = "";

        // WHEN
        String result = TextUtil.normalizeSpaces(input);

        // THEN
        assertEquals("", result);
    }
}
