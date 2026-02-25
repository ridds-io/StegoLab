package com.stegolab.algorithms;

/**
 * ZeroWidthAlgorithm — Zero-Width Character Encoding
 *
 * Hides data inside plain text by inserting invisible Unicode characters
 * between words. These characters take up no visual space and are
 * completely undetectable to any reader or printer.
 *
 * Characters used:
 *   U+200B  Zero-Width Space          → encodes bit 0
 *   U+200C  Zero-Width Non-Joiner     → encodes bit 1
 *   U+200D  Zero-Width Joiner         → end-of-message marker
 *
 * The cover text reads perfectly normally after encoding.
 * No steganalysis tool currently targets zero-width characters in prose.
 *
 * OOP Concept: INTERFACE IMPLEMENTATION — implements StegoAlgorithm,
 * providing the concrete encode/decode logic for zero-width encoding.
 */
public class ZeroWidthAlgorithm implements StegoAlgorithm {

    private static final char ZWS  = '\u200B'; // Zero-Width Space       → bit 0
    private static final char ZWNJ = '\u200C'; // Zero-Width Non-Joiner  → bit 1
    private static final char ZWJ  = '\u200D'; // Zero-Width Joiner      → end marker

    @Override
    public String encode(String carrier, String message) {
        // Build full bit stream from the message
        StringBuilder bitStream = new StringBuilder();
        for (char c : message.toCharArray()) {
            bitStream.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }

        // Split carrier into words and insert invisible characters between them
        String[] words = carrier.split(" ");
        StringBuilder result = new StringBuilder();
        int bitIndex = 0;

        for (int i = 0; i < words.length; i++) {
            result.append(words[i]);

            if (i < words.length - 1) {
                // Insert a zero-width character encoding the next bit
                if (bitIndex < bitStream.length()) {
                    char bit = bitStream.charAt(bitIndex++) == '0' ? ZWS : ZWNJ;
                    result.append(bit);
                } else if (bitIndex == bitStream.length()) {
                    result.append(ZWJ); // End marker
                    bitIndex++;
                }
                result.append(' '); // Normal space between words
            }
        }

        return result.toString();
    }

    @Override
    public String decode(String carrier) {
        StringBuilder bitStream = new StringBuilder();
        boolean endFound = false;

        // Scan every character looking for zero-width characters
        for (char c : carrier.toCharArray()) {
            if (c == ZWJ) {
                endFound = true;
                break;
            } else if (c == ZWS) {
                bitStream.append('0');
            } else if (c == ZWNJ) {
                bitStream.append('1');
            }
        }

        // Reconstruct the message from the bit stream
        StringBuilder message = new StringBuilder();
        String bits = bitStream.toString();
        for (int i = 0; i + 8 <= bits.length(); i += 8) {
            int charVal = Integer.parseInt(bits.substring(i, i + 8), 2);
            if (charVal == 0) break;
            message.append((char) charVal);
        }

        return message.toString();
    }

    @Override
    public String getAlgorithmName() {
        return "Zero-Width Character Encoding";
    }
}
