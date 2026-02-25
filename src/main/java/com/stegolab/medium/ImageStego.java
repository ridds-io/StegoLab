package com.stegolab.medium;

import com.stegolab.algorithms.StegoAlgorithm;
import com.stegolab.base.StegoBase;
import com.stegolab.benchmark.BenchmarkResult;
import com.stegolab.benchmark.BenchmarkEngine;

/**
 * ImageStego — Image Carrier Medium
 *
 * Handles steganographic encoding in image data using an injected
 * StegoAlgorithm (e.g., LSBAlgorithm or DCTAlgorithm).
 *
 * In a full implementation, carrier would be a BufferedImage or byte[].
 * For this phase, we use a String representation to demonstrate the
 * OOP structure clearly.
 *
 * OOP Concept: INHERITANCE — extends StegoBase, inheriting the contract
 * and utility methods while providing image-specific implementation.
 *
 * OOP Concept: STRATEGY PATTERN — algorithm is swappable at runtime.
 * The same ImageStego works with LSB or DCT without any code change.
 */
public class ImageStego extends StegoBase {

    private final StegoAlgorithm algorithm;
    private final BenchmarkEngine benchmarkEngine;

    /**
     * @param algorithm The steganographic algorithm to apply to image data
     */
    public ImageStego(StegoAlgorithm algorithm) {
        super("Image", algorithm.getAlgorithmName());
        this.algorithm       = algorithm;
        this.benchmarkEngine = new BenchmarkEngine();
    }

    /**
     * Encodes a secret message into image data using the injected algorithm.
     */
    @Override
    public String encode(String carrier, String secretMessage) {
        System.out.println("[ImageStego] Encoding with: " + algorithm.getAlgorithmName());
        return algorithm.encode(carrier, secretMessage);
    }

    /**
     * Decodes and extracts the hidden message from image data.
     */
    @Override
    public String decode(String encodedCarrier) {
        System.out.println("[ImageStego] Decoding with: " + algorithm.getAlgorithmName());
        return algorithm.decode(encodedCarrier);
    }

    /**
     * Runs the full encode-decode cycle and returns benchmark metrics.
     */
    @Override
    public BenchmarkResult benchmark(String carrier, String secretMessage) {
        System.out.println("[ImageStego] Benchmarking: " + algorithm.getAlgorithmName());
        return benchmarkEngine.run(this, carrier, secretMessage);
    }
}
