package com.stegolab.algorithms;

/**
 * LSBAlgorithm — Least Significant Bit Encoding
 *
 * Hides data by replacing the least significant bit of each character value
 * in the carrier string with bits from the secret message.
 *
 * In real image steganography, this operates on pixel RGB values.
 * Here we simulate it on character data for demonstration purposes.
 *
 * OOP Concept: INTERFACE IMPLEMENTATION — implements StegoAlgorithm,
 * providing the concrete encode/decode logic for the LSB technique.
 */
public class LSBAlgorithm implements StegoAlgorithm {

    private static final String DELIMITER = "1111111111111110"; // 16-bit end marker

    @Override
    public String encode(String carrier, String message) {
        StringBuilder bitStream = new StringBuilder();

        // Convert each character of the message to 8-bit binary
        for (char c : message.toCharArray()) {
            bitStream.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }
        bitStream.append(DELIMITER); // Append end-of-message marker

        char[] carrierChars = carrier.toCharArray();
        int bitIndex = 0;

        // Replace LSB of each carrier character with a message bit
        for (int i = 0; i < carrierChars.length && bitIndex < bitStream.length(); i++) {
            int charVal = carrierChars[i];
            int bit = bitStream.charAt(bitIndex++) - '0';
            // Clear LSB and set it to our message bit
            charVal = (charVal & ~1) | bit;
            carrierChars[i] = (char) charVal;
        }

        return new String(carrierChars);
    }

    @Override
    public String decode(String carrier) {
        StringBuilder bitStream = new StringBuilder();

        // Extract LSB from each character
        for (char c : carrier.toCharArray()) {
            bitStream.append(c & 1);
        }

        StringBuilder message = new StringBuilder();
        String bits = bitStream.toString();

        // Read 8 bits at a time and convert to characters
        for (int i = 0; i + 8 <= bits.length(); i += 8) {
            String byteBits = bits.substring(i, i + 8);
            if (bits.substring(i).startsWith(DELIMITER)) break; // End marker found
            int charVal = Integer.parseInt(byteBits, 2);
            message.append((char) charVal);
        }

        return message.toString();
    }

    @Override
    public String getAlgorithmName() {
        return "LSB (Least Significant Bit)";
    }
}
