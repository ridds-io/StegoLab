package com.stegolab.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * CharSubAlgorithm — Character Substitution Encoding
 *
 * Hides data inside ASCII art by swapping visually identical characters.
 * A human viewer cannot distinguish between these character pairs,
 * but the encoder/decoder know exactly which encodes 0 and which encodes 1.
 *
 * Substitution pairs used:
 *   'O' vs '0'   → O = bit 0, 0 = bit 1
 *   'l' vs '1'   → l = bit 0, 1 = bit 1
 *   'I' vs '|'   → I = bit 0, | = bit 1
 *   'S' vs '5'   → S = bit 0, 5 = bit 1
 *
 * OOP Concept: INTERFACE IMPLEMENTATION — implements StegoAlgorithm,
 * providing the concrete encode/decode logic for character substitution.
 */
public class CharSubAlgorithm implements StegoAlgorithm {

    // Each pair: [bit-0 char, bit-1 char]
    private static final char[][] PAIRS = {
        {'O', '0'},
        {'l', '1'},
        {'I', '|'},
        {'S', '5'}
    };

    // Lookup: which pair index does this character belong to, and which bit?
    private final Map<Character, int[]> charIndex = new HashMap<>();

    public CharSubAlgorithm() {
        for (int p = 0; p < PAIRS.length; p++) {
            charIndex.put(PAIRS[p][0], new int[]{p, 0}); // bit 0
            charIndex.put(PAIRS[p][1], new int[]{p, 1}); // bit 1
        }
    }

    @Override
    public String encode(String carrier, String message) {
        // Build bit stream from message
        StringBuilder bitStream = new StringBuilder();
        for (char c : message.toCharArray()) {
            bitStream.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }

        char[] result = carrier.toCharArray();
        int bitIndex = 0;

        for (int i = 0; i < result.length && bitIndex < bitStream.length(); i++) {
            if (charIndex.containsKey(result[i])) {
                int pairIdx = charIndex.get(result[i])[0];
                int bit = bitStream.charAt(bitIndex++) - '0';
                result[i] = PAIRS[pairIdx][bit]; // Set character to match bit value
            }
        }

        return new String(result);
    }

    @Override
    public String decode(String carrier) {
        StringBuilder bitStream = new StringBuilder();

        for (char c : carrier.toCharArray()) {
            if (charIndex.containsKey(c)) {
                bitStream.append(charIndex.get(c)[1]); // Append the bit this char represents
            }
        }

        // Reconstruct message from bits
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
        return "Character Substitution";
    }
}
