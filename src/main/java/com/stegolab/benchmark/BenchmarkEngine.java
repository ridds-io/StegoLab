package com.stegolab.benchmark;

import com.stegolab.base.StegoBase;

/**
 * BenchmarkEngine — Utility Class
 *
 * Responsible for running encode/decode operations on any StegoBase instance
 * and measuring performance across accuracy, time, and memory metrics.
 *
 * OOP Concept: POLYMORPHISM — this class holds a reference to StegoBase,
 * not to any specific subclass. It works identically with ImageStego,
 * CodeStego, AsciiStego, or TextStego at runtime — this is polymorphism
 * in action. The correct encode/decode is dispatched automatically.
 *
 * OOP Concept: ENCAPSULATION — measurement logic is contained here,
 * not scattered across every medium class.
 */
public class BenchmarkEngine {

    /**
     * Runs the full encode-decode cycle on the given StegoBase instance
     * and returns a BenchmarkResult with all measured metrics.
     *
     * @param stego         Any StegoBase subclass (Image, Code, ASCII, Text)
     * @param carrier       The cover content to hide the message in
     * @param secretMessage The message to hide and retrieve
     * @return              A BenchmarkResult containing all metrics
     */
    public BenchmarkResult run(StegoBase stego, String carrier, String secretMessage) {
        Runtime runtime = Runtime.getRuntime();

        // --- Encode ---
        runtime.gc(); // Suggest GC before measuring
        long memBefore  = runtime.totalMemory() - runtime.freeMemory();
        long encodeStart = System.nanoTime();

        String encodedCarrier = stego.encode(carrier, secretMessage);

        long encodeEnd  = System.nanoTime();
        long memAfterEncode = runtime.totalMemory() - runtime.freeMemory();

        // --- Decode ---
        long decodeStart = System.nanoTime();

        String decodedMessage = stego.decode(encodedCarrier);

        long decodeEnd = System.nanoTime();
        long memAfterDecode = runtime.totalMemory() - runtime.freeMemory();

        // --- Calculate Metrics ---
        long encodeTimeMs = (encodeEnd - encodeStart) / 1_000_000;
        long decodeTimeMs = (decodeEnd - decodeStart) / 1_000_000;
        long memoryUsedKB = (Math.max(memAfterEncode, memAfterDecode) - memBefore) / 1024;
        double accuracy   = calculateAccuracy(secretMessage, decodedMessage);

        return new BenchmarkResult(
            stego.getAlgorithmName(),
            stego.getMediumType(),
            accuracy,
            encodeTimeMs,
            decodeTimeMs,
            Math.max(0, memoryUsedKB)
        );
    }

    /**
     * Compares two benchmark results and prints a side-by-side summary.
     */
    public void compare(BenchmarkResult a, BenchmarkResult b) {
        a.compareWith(b);
    }

    /**
     * Character-level accuracy between original and decoded message.
     */
    private double calculateAccuracy(String original, String decoded) {
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
}
