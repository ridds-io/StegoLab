package com.stegolab.medium;

import com.stegolab.algorithms.StegoAlgorithm;
import com.stegolab.base.StegoBase;
import com.stegolab.benchmark.BenchmarkResult;
import com.stegolab.benchmark.BenchmarkEngine;

/**
 * AsciiStego — ASCII Art Carrier Medium
 *
 * Handles steganographic encoding inside ASCII art by substituting
 * visually identical characters to embed hidden bits.
 *
 * OOP Concept: INHERITANCE — extends StegoBase.
 * OOP Concept: STRATEGY PATTERN — CharSubAlgorithm is injected at runtime.
 */
public class AsciiStego extends StegoBase {

    private final StegoAlgorithm algorithm;
    private final BenchmarkEngine benchmarkEngine;

    public AsciiStego(StegoAlgorithm algorithm) {
        super("ASCII Art", algorithm.getAlgorithmName());
        this.algorithm       = algorithm;
        this.benchmarkEngine = new BenchmarkEngine();
    }

    @Override
    public String encode(String carrier, String secretMessage) {
        System.out.println("[AsciiStego] Encoding with: " + algorithm.getAlgorithmName());
        return algorithm.encode(carrier, secretMessage);
    }

    @Override
    public String decode(String encodedCarrier) {
        System.out.println("[AsciiStego] Decoding with: " + algorithm.getAlgorithmName());
        return algorithm.decode(encodedCarrier);
    }

    @Override
    public BenchmarkResult benchmark(String carrier, String secretMessage) {
        System.out.println("[AsciiStego] Benchmarking: " + algorithm.getAlgorithmName());
        return benchmarkEngine.run(this, carrier, secretMessage);
    }
}
