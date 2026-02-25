package com.stegolab.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * CodeStyleAlgorithm — Source Code Style Encoding
 *
 * Hides data by exploiting the fact that many code style choices
 * produce functionally identical programs. For example:
 *   - single quotes vs double quotes  → 0 or 1
 *   - != vs <>                        → 0 or 1
 *   - for loop vs while loop          → 0 or 1
 *
 * The carrier code runs identically regardless of which style is chosen.
 * No steganalysis tool currently targets source code as a carrier,
 * making this a novel and practically undetectable technique.
 *
 * OOP Concept: INTERFACE IMPLEMENTATION — implements StegoAlgorithm,
 * providing the concrete encode/decode logic for code style encoding.
 */
public class CodeStyleAlgorithm implements StegoAlgorithm {

    // Style rule pairs: index 0 encodes bit 0, index 1 encodes bit 1
    private static final String[][] STYLE_RULES = {
        {"'",  "\""},        // single quote = 0, double quote = 1
        {"!=", "<>"},        // != = 0, <> = 1
        {"i++", "i += 1"},   // i++ = 0, i += 1 = 1
        {"true", "True"},    // true = 0, True = 1
    };

    @Override
    public String encode(String carrier, String message) {
        // Convert message to binary bit stream
        StringBuilder bitStream = new StringBuilder();
        for (char c : message.toCharArray()) {
            bitStream.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }

        String result = carrier;
        int bitIndex = 0;

        // For each style rule pair, replace occurrences to encode bits
        for (String[] rule : STYLE_RULES) {
            if (bitIndex >= bitStream.length()) break;
            int bit = bitStream.charAt(bitIndex++) - '0';

            if (bit == 0) {
                // Ensure style[0] is used (replace any style[1] occurrences)
                result = result.replace(rule[1], rule[0]);
            } else {
                // Ensure style[1] is used (replace any style[0] occurrences)
                result = result.replace(rule[0], rule[1]);
            }
        }

        return result;
    }

    @Override
    public String decode(String carrier) {
        StringBuilder bitStream = new StringBuilder();

        // Read which style is present at each rule position
        for (String[] rule : STYLE_RULES) {
            if (carrier.contains(rule[1])) {
                bitStream.append('1');
            } else {
                bitStream.append('0');
            }
        }

        // Convert bits back to characters
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
        return "Code Style Encoding";
    }
}
