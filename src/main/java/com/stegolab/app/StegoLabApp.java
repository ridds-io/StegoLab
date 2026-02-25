package com.stegolab.app;

import com.stegolab.base.StegoBase;
import com.stegolab.benchmark.BenchmarkResult;
import com.stegolab.factory.StegoFactory;
import com.stegolab.registry.AlgorithmRegistry;

import java.util.Scanner;

/**
 * StegoLabApp — Main Entry Point
 *
 * The top-level class that ties together all components of StegoLab.
 * Handles user interaction, delegates to the factory, and displays results.
 *
 * This class demonstrates POLYMORPHISM at the application level:
 * it works with StegoBase references and never knows which concrete
 * subclass it's operating on at compile time.
 */
public class StegoLabApp {

    private static final AlgorithmRegistry registry = new AlgorithmRegistry();

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         StegoLab v1.0                ║");
        System.out.println("║  Multi-Medium Steganography Platform  ║");
        System.out.println("╚══════════════════════════════════════╝");

        // --- Demo Mode (no user input required) ---
        System.out.println("\n[Running Demo Mode]\n");

        runDemo(
            "text",
            "zerowidth",
            "The quick brown fox jumps over the lazy dog near the river bank today.",
            "Hi"
        );

        runDemo(
            "code",
            "style",
            "for (int i = 0; i != 10; i++) { System.out.println('Hello'); }",
            "A"
        );

        runDemo(
            "ascii",
            "charsub",
            "  _____   \n |O   O|  \n |  S  |  \n |_____|  ",
            "B"
        );

        runDemo(
            "image",
            "lsb",
            "PIXEL_DATA: RRGGBBRRGGBBRRGGBBRRGGBBRRGGBBRRGGBBRRGGBBRRGGBB",
            "Hi"
        );

        // --- Benchmark Comparison ---
        System.out.println("\n\n[Comparing Two Algorithms Side by Side]");
        String sharedCarrier = "The quick brown fox jumps over the lazy dog. " +
                               "A gentle breeze swept across the open field today.";
        String sharedMessage = "AB";

        StegoBase t1 = StegoFactory.create("text", "zerowidth");
        StegoBase t2 = StegoFactory.create("text", "synonym");

        // Note: SynonymAlgorithm is a placeholder — swap with real impl in Phase 2
        BenchmarkResult r1 = t1.benchmark(sharedCarrier, sharedMessage);
        System.out.println(r1);

        System.out.println("\n--- Registry Contents ---");
        registry.printAll();
    }

    /**
     * Runs a single encode-decode-benchmark cycle and prints the result.
     * Demonstrates POLYMORPHISM: stego is a StegoBase reference that works
     * with any medium at runtime.
     */
    private static void runDemo(String medium, String algorithm, String carrier, String message) {
        System.out.println("─────────────────────────────────────────");
        System.out.println("Medium: " + medium + " | Algorithm: " + algorithm);
        System.out.println("Message to hide: \"" + message + "\"");

        // Factory creates the right object — caller doesn't need to know which class
        StegoBase stego = StegoFactory.create(medium, algorithm);

        // Encode
        String encoded = stego.encode(carrier, message);
        System.out.println("Encoded (excerpt): " + encoded.substring(0, Math.min(60, encoded.length())) + "...");

        // Decode
        String decoded = stego.decode(encoded);
        System.out.println("Decoded message  : \"" + decoded + "\"");

        // Benchmark
        BenchmarkResult result = stego.benchmark(carrier, message);
        System.out.println(result);
        System.out.println();
    }
}
