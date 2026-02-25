package com.stegolab.registry;

import java.util.*;

/**
 * AlgorithmRegistry — Registry Class
 *
 * Maintains a dynamic map of which algorithms are available for each medium.
 * Allows new algorithms to be registered at runtime without modifying
 * any existing class — a direct implementation of the Open/Closed Principle.
 *
 * OOP Concept: ENCAPSULATION — the internal map is private; the outside
 * world can only register or query through controlled methods.
 *
 * OOP Concept: OPEN/CLOSED PRINCIPLE — new algorithms are added via
 * register(), never by editing existing switch statements or class logic.
 */
public class AlgorithmRegistry {

    // Private registry map: medium → list of supported algorithm names
    private final Map<String, List<String>> registry = new HashMap<>();

    /**
     * Initialises the registry with all built-in medium-algorithm mappings.
     */
    public AlgorithmRegistry() {
        // Image medium
        register("image", "lsb");
        register("image", "dct");

        // Source code medium
        register("code", "style");

        // ASCII art medium
        register("ascii", "charsub");

        // Plain text medium
        register("text", "zerowidth");
        register("text", "synonym");
    }

    /**
     * Registers a new algorithm under a given medium.
     * This is how new algorithms are added without touching existing code.
     *
     * @param medium    The carrier medium (e.g., "image", "text")
     * @param algorithm The algorithm identifier (e.g., "lsb", "zerowidth")
     */
    public void register(String medium, String algorithm) {
        registry.computeIfAbsent(medium.toLowerCase(), k -> new ArrayList<>())
                .add(algorithm.toLowerCase());
    }

    /**
     * Returns all algorithms available for a given medium.
     *
     * @param medium The medium to query
     * @return       List of algorithm names, or empty list if medium unknown
     */
    public List<String> getAvailable(String medium) {
        return registry.getOrDefault(medium.toLowerCase(), Collections.emptyList());
    }

    /**
     * Returns all registered mediums.
     */
    public Set<String> getMediums() {
        return registry.keySet();
    }

    /**
     * Checks whether a given algorithm is registered for a given medium.
     */
    public boolean isValid(String medium, String algorithm) {
        List<String> algorithms = getAvailable(medium);
        return algorithms.contains(algorithm.toLowerCase());
    }

    /**
     * Prints all registered mediums and their algorithms to stdout.
     */
    public void printAll() {
        System.out.println("\n=== Available Mediums & Algorithms ===");
        for (Map.Entry<String, List<String>> entry : registry.entrySet()) {
            System.out.println("  " + entry.getKey() + " → " + entry.getValue());
        }
    }
}
