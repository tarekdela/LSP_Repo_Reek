package org.howard.edu.lsp.finale.question1;

/**
 * PasswordGeneratorService is a singleton service responsible for generating passwords
 * using various algorithms selected at runtime.
 *
 * ========================================================================
 * DESIGN PATTERN DOCUMENTATION
 * ========================================================================
 *
 * PATTERNS USED:
 * 1. SINGLETON PATTERN
 * 2. STRATEGY PATTERN
 *
 * PATTERN JUSTIFICATION:
 *
 * SINGLETON PATTERN:
 * - Requirement: "Provide a single shared access point"
 * - Requirement: "Only one instance of the service may exist"
 * - The Singleton pattern ensures exactly one instance of PasswordGeneratorService
 *   exists throughout the application lifetime.
 * - The getInstance() method provides the global point of access to this single instance.
 * - This centralizes password generation logic and maintains consistent state.
 *
 * STRATEGY PATTERN:
 * - Requirement: "Support multiple approaches to password generation"
 * - Requirement: "Allow the caller to select the approach at run time"
 * - Requirement: "Make password-generation behavior swappable"
 * - Requirement: "Support future expansion of password-generation approaches"
 * - The Strategy pattern encapsulates each password generation algorithm (basic, enhanced,
 *   letters) into separate, interchangeable classes implementing PasswordGenerationStrategy.
 * - setAlgorithm() allows clients to switch between strategies at runtime.
 * - New algorithms can be added by implementing PasswordGenerationStrategy without
 *   modifying PasswordGeneratorService or client code.
 * - This promotes Open-Closed Principle: open for extension, closed for modification.
 *
 * ========================================================================
 *
 * @author Student
 * @version 1.0
 */
public class PasswordGeneratorService {

    /**
     * Static instance variable holding the single instance of PasswordGeneratorService.
     * This implements the Singleton pattern.
     */
    private static PasswordGeneratorService instance = null;

    /**
     * The current password generation strategy being used.
     * This can be switched at runtime via setAlgorithm().
     */
    private PasswordGenerationStrategy strategy;

    /**
     * Private constructor to prevent external instantiation.
     * Required for the Singleton pattern.
     */
    private PasswordGeneratorService() {
        this.strategy = null;
    }

    /**
     * Retrieves the single instance of PasswordGeneratorService.
     * Implements lazy initialization for the Singleton pattern.
     *
     * @return the single instance of PasswordGeneratorService
     */
    public static PasswordGeneratorService getInstance() {
        if (instance == null) {
            instance = new PasswordGeneratorService();
        }
        return instance;
    }

    /**
     * Sets the password generation algorithm by name.
     *
     * Supported algorithm names:
     * - "basic": Generates digits only using java.util.Random
     * - "enhanced": Generates letters and digits using java.security.SecureRandom
     * - "letters": Generates letters only using java.util.Random
     *
     * @param name the name of the algorithm to use
     * @throws IllegalArgumentException if the algorithm name is not recognized
     */
    public void setAlgorithm(String name) {
        switch (name.toLowerCase()) {
            case "basic":
                this.strategy = new BasicPasswordStrategy();
                break;
            case "enhanced":
                this.strategy = new EnhancedPasswordStrategy();
                break;
            case "letters":
                this.strategy = new LettersPasswordStrategy();
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
    }

    /**
     * Generates a password of the specified length using the currently selected strategy.
     *
     * @param length the desired length of the generated password
     * @return a generated password string
     * @throws IllegalStateException if no algorithm has been set before calling this method
     */
    public String generatePassword(int length) {
        if (this.strategy == null) {
            throw new IllegalStateException(
                    "No password generation algorithm has been selected. " +
                    "Call setAlgorithm() first."
            );
        }
        return this.strategy.generatePassword(length);
    }
}
