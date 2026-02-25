package com.stegolab.base;

import com.stegolab.benchmark.BenchmarkResult;

/**
 * StegoBase — Abstract Base Class
 *
 * Defines the contract that every steganographic medium must follow.
 * Any new medium (Image, Code, ASCII, Text) must extend this class
 * and provide concrete implementations of encode(), decode(), and benchmark().
 *
 * OOP Concept: ABSTRACTION — internal implementation details are hidden;
 * only the necessary interface is exposed to the outside world.
 */
public abstract class StegoBase {

    // Encapsulated fields — private to each subclass's context
    protected String algorithmName;
    protected String mediumType;

    /**
     * Constructor to initialise medium and algorithm identifiers.
     */
    public StegoBase(String mediumType, String algorithmName) {
        this.mediumType = mediumType;
        this.algorithmName = algorithmName;
    }

    /**
     * Encodes a secret message into the given carrier content.
     *
     * @param carrier       The cover content (image bytes, code string, ASCII art, plain text)
     * @param secretMessage The message to hide
     * @return              The carrier with the hidden message embedded
     */
    public abstract String encode(String carrier, String secretMessage);

    /**
     * Decodes and extracts a hidden message from the given carrier content.
     *
     * @param encodedCarrier The carrier that contains a hidden message
     * @return               The extracted secret message
     */
    public abstract String decode(String encodedCarrier);

    /**
     * Runs the full encode-decode cycle and returns benchmark metrics.
     * Measures accuracy, encode time, decode time, and memory used.
     *
     * @param carrier       The cover content
     * @param secretMessage The message to hide and retrieve
     * @return              A BenchmarkResult containing all measured metrics
     */
    public abstract BenchmarkResult benchmark(String carrier, String secretMessage);

    /**
     * Calculates accuracy between the original and decoded message.
     * Compares character by character and returns a percentage.
     *
     * OOP Concept: ENCAPSULATION — shared utility logic lives here in the base class,
     * not duplicated across every subclass.
     */
    protected double calculateAccuracy(String original, String decoded) {
        if (original == null || decoded == null) return 0.0;
        int maxLen = Math.max(original.length(), decoded.length());
        if (maxLen == 0) return 100.0;

        int matches = 0;
        int compareLen = Math.min(original.length(), decoded.length());
        for (int i = 0; i < compareLen; i++) {
            if (original.charAt(i) == decoded.charAt(i)) matches++;
        }
        return (matches / (double) maxLen) * 100.0;
    }

    // Getters
    public String getAlgorithmName() { return algorithmName; }
    public String getMediumType()    { return mediumType; }

    @Override
    public String toString() {
        return "[" + mediumType + " | " + algorithmName + "]";
    }
}
