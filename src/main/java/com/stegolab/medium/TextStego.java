package com.stegolab.medium;

import com.stegolab.algorithms.StegoAlgorithm;
import com.stegolab.base.StegoBase;
import com.stegolab.benchmark.BenchmarkResult;
import com.stegolab.benchmark.BenchmarkEngine;

/**
 * TextStego — Plain Text Carrier Medium
 *
 * Handles steganographic encoding in plain text documents using
 * an injected StegoAlgorithm (e.g., ZeroWidthAlgorithm or SynonymAlgorithm).
 *
 * OOP Concept: INHERITANCE — extends StegoBase, inheriting the common
 * interface and utility methods while providing text-specific encode/decode.
 *
 * OOP Concept: STRATEGY PATTERN — the algorithm is injected at construction,
 * so the same TextStego class can use Zero-Width Encoding or Synonym
 * Substitution interchangeably without any code change.
 */
public class TextStego extends StegoBase {

    // Encapsulated algorithm — private, injected via constructor
    private final StegoAlgorithm algorithm;
    private final BenchmarkEngine benchmarkEngine;

    /**
     * @param algorithm The steganographic algorithm to apply to text
     */
    public TextStego(StegoAlgorithm algorithm) {
        super("Plain Text", algorithm.getAlgorithmName());
        this.algorithm       = algorithm;
        this.benchmarkEngine = new BenchmarkEngine();
    }

    /**
     * Encodes a secret message into plain text using the injected algorithm.
     *
     * @param carrier       The cover plain text document
     * @param secretMessage The message to hide
     * @return              The plain text with the hidden message embedded
     */
    @Override
    public String encode(String carrier, String secretMessage) {
        System.out.println("[TextStego] Encoding with: " + algorithm.getAlgorithmName());
        return algorithm.encode(carrier, secretMessage);
    }

    /**
     * Decodes and extracts the hidden message from the plain text carrier.
     *
     * @param encodedCarrier The plain text containing a hidden message
     * @return               The extracted secret message
     */
    @Override
    public String decode(String encodedCarrier) {
        System.out.println("[TextStego] Decoding with: " + algorithm.getAlgorithmName());
        return algorithm.decode(encodedCarrier);
    }

    /**
     * Runs the full encode-decode cycle and returns benchmark metrics.
     */
    @Override
    public BenchmarkResult benchmark(String carrier, String secretMessage) {
        System.out.println("[TextStego] Benchmarking: " + algorithm.getAlgorithmName());
        return benchmarkEngine.run(this, carrier, secretMessage);
    }
}
