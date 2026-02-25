package com.stegolab.benchmark;

/**
 * BenchmarkResult — Model Class
 *
 * Stores all performance metrics captured during a single encode/decode run.
 * Returned by every StegoBase.benchmark() call and used by the BenchmarkEngine
 * for display and comparison.
 *
 * OOP Concept: ENCAPSULATION — all fields are private; access is only through
 * getters. The outside world cannot directly modify benchmark data.
 */
public class BenchmarkResult {

    private final String algorithmName;
    private final String mediumType;
    private final double accuracyPercent;
    private final long encodeTimeMs;
    private final long decodeTimeMs;
    private final long memoryUsedKB;

    public BenchmarkResult(String algorithmName, String mediumType,
                           double accuracyPercent, long encodeTimeMs,
                           long decodeTimeMs, long memoryUsedKB) {
        this.algorithmName   = algorithmName;
        this.mediumType      = mediumType;
        this.accuracyPercent = accuracyPercent;
        this.encodeTimeMs    = encodeTimeMs;
        this.decodeTimeMs    = decodeTimeMs;
        this.memoryUsedKB    = memoryUsedKB;
    }

    // Getters
    public String getAlgorithmName()   { return algorithmName; }
    public String getMediumType()      { return mediumType; }
    public double getAccuracyPercent() { return accuracyPercent; }
    public long   getEncodeTimeMs()    { return encodeTimeMs; }
    public long   getDecodeTimeMs()    { return decodeTimeMs; }
    public long   getMemoryUsedKB()    { return memoryUsedKB; }

    /**
     * Compares this result against another and prints which performed better.
     */
    public void compareWith(BenchmarkResult other) {
        System.out.println("\n=== Comparison ===");
        System.out.printf("%-20s %-25s %-25s%n", "Metric", this.algorithmName, other.algorithmName);
        System.out.printf("%-20s %-25.2f %-25.2f%n", "Accuracy (%)", this.accuracyPercent, other.accuracyPercent);
        System.out.printf("%-20s %-25d %-25d%n", "Encode Time (ms)", this.encodeTimeMs, other.encodeTimeMs);
        System.out.printf("%-20s %-25d %-25d%n", "Decode Time (ms)", this.decodeTimeMs, other.decodeTimeMs);
        System.out.printf("%-20s %-25d %-25d%n", "Memory Used (KB)", this.memoryUsedKB, other.memoryUsedKB);
    }

    @Override
    public String toString() {
        return String.format(
            "\n=== Benchmark Results ===" +
            "\nAlgorithm     : %s" +
            "\nMedium        : %s" +
            "\nAccuracy      : %.2f%%" +
            "\nEncode Time   : %d ms" +
            "\nDecode Time   : %d ms" +
            "\nMemory Used   : %d KB",
            algorithmName, mediumType, accuracyPercent,
            encodeTimeMs, decodeTimeMs, memoryUsedKB
        );
    }
}
