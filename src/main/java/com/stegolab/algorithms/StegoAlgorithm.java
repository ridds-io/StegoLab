package com.stegolab.algorithms;

/**
 * StegoAlgorithm — Interface
 *
 * Separates the algorithm (HOW we hide data) from the medium (WHAT we hide it in).
 * Any algorithm that operates on raw byte/string data must implement this interface.
 *
 * OOP Concept: INTERFACE — defines a contract without any implementation,
 * allowing multiple algorithms to be swapped in and out at runtime.
 *
 * OOP Concept: STRATEGY PATTERN — the algorithm is injected into a medium class
 * at runtime, making the hiding strategy interchangeable without changing the medium.
 */
public interface StegoAlgorithm {

    /**
     * Encodes a secret message into carrier bytes.
     *
     * @param carrier The raw carrier data as a string
     * @param message The secret message to embed
     * @return        The modified carrier with the message hidden inside
     */
    String encode(String carrier, String message);

    /**
     * Decodes and extracts a hidden message from carrier bytes.
     *
     * @param carrier The carrier that contains a hidden message
     * @return        The extracted secret message
     */
    String decode(String carrier);

    /**
     * Returns the name of this algorithm, used for display and logging.
     */
    String getAlgorithmName();
}
