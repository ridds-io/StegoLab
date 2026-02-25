package com.stegolab.factory;

import com.stegolab.algorithms.*;
import com.stegolab.base.StegoBase;
import com.stegolab.medium.*;

/**
 * StegoFactory — Factory Pattern
 *
 * Centralises the creation of StegoBase instances based on user-selected
 * medium and algorithm. The rest of the application never calls
 * 'new ImageStego()' directly — it always asks the factory.
 *
 * OOP Concept: FACTORY PATTERN — object creation logic is encapsulated
 * in one place. Adding a new medium only requires adding a case here,
 * not hunting through the entire codebase.
 *
 * OOP Concept: OPEN/CLOSED PRINCIPLE — new mediums or algorithms can be
 * added by extending this factory, without modifying existing medium classes.
 */
public class StegoFactory {

    // Medium constants
    public static final String MEDIUM_IMAGE = "image";
    public static final String MEDIUM_CODE  = "code";
    public static final String MEDIUM_ASCII = "ascii";
    public static final String MEDIUM_TEXT  = "text";

    // Algorithm constants
    public static final String ALGO_LSB        = "lsb";
    public static final String ALGO_STYLE      = "style";
    public static final String ALGO_CHARSUB    = "charsub";
    public static final String ALGO_ZEROWIDTH  = "zerowidth";
    public static final String ALGO_SYNONYM    = "synonym";

    /**
     * Creates and returns the appropriate StegoBase subclass for the
     * given medium and algorithm combination.
     *
     * @param medium    One of: "image", "code", "ascii", "text"
     * @param algorithm One of: "lsb", "style", "charsub", "zerowidth", "synonym"
     * @return          A ready-to-use StegoBase instance
     */
    public static StegoBase create(String medium, String algorithm) {
        StegoAlgorithm algoInstance = resolveAlgorithm(algorithm);

        switch (medium.toLowerCase()) {
            case MEDIUM_IMAGE: return new ImageStego(algoInstance);
            case MEDIUM_CODE:  return new CodeStego(algoInstance);
            case MEDIUM_ASCII: return new AsciiStego(algoInstance);
            case MEDIUM_TEXT:  return new TextStego(algoInstance);
            default:
                throw new IllegalArgumentException(
                    "Unknown medium: '" + medium + "'. Valid options: image, code, ascii, text"
                );
        }
    }

    /**
     * Resolves the algorithm name string to a concrete StegoAlgorithm instance.
     */
    private static StegoAlgorithm resolveAlgorithm(String algorithm) {
        switch (algorithm.toLowerCase()) {
            case ALGO_LSB:       return new LSBAlgorithm();
            case ALGO_STYLE:     return new CodeStyleAlgorithm();
            case ALGO_CHARSUB:   return new CharSubAlgorithm();
            case ALGO_ZEROWIDTH: return new ZeroWidthAlgorithm();
            default:
                throw new IllegalArgumentException(
                    "Unknown algorithm: '" + algorithm + "'. Valid options: lsb, style, charsub, zerowidth"
                );
        }
    }
}
