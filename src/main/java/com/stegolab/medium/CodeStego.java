package com.stegolab.medium;

import com.stegolab.algorithms.StegoAlgorithm;
import com.stegolab.base.StegoBase;
import com.stegolab.benchmark.BenchmarkResult;
import com.stegolab.benchmark.BenchmarkEngine;

/**
 * CodeStego — Source Code Carrier Medium
 *
 * Handles steganographic encoding inside source code files by exploiting
 * stylistic choices that produce functionally identical programs.
 *
 * OOP Concept: INHERITANCE — extends StegoBase.
 * OOP Concept: STRATEGY PATTERN — CodeStyleAlgorithm is injected,
 * keeping algorithm logic cleanly separated from medium handling.
 */
public class CodeStego extends StegoBase {

    private final StegoAlgorithm algorithm;
    private final BenchmarkEngine benchmarkEngine;

    public CodeStego(StegoAlgorithm algorithm) {
        super("Source Code", algorithm.getAlgorithmName());
        this.algorithm       = algorithm;
        this.benchmarkEngine = new BenchmarkEngine();
    }

    @Override
    public String encode(String carrier, String secretMessage) {
        System.out.println("[CodeStego] Encoding with: " + algorithm.getAlgorithmName());
        return algorithm.encode(carrier, secretMessage);
    }

    @Override
    public String decode(String encodedCarrier) {
        System.out.println("[CodeStego] Decoding with: " + algorithm.getAlgorithmName());
        return algorithm.decode(encodedCarrier);
    }

    @Override
    public BenchmarkResult benchmark(String carrier, String secretMessage) {
        System.out.println("[CodeStego] Benchmarking: " + algorithm.getAlgorithmName());
        return benchmarkEngine.run(this, carrier, secretMessage);
    }
}
