package org.howard.edu.lsp.finale.question1;

/**
 * PasswordGenerationStrategy interface defines the contract for all password generation algorithms.
 * This interface is part of the Strategy design pattern, allowing different password generation
 * approaches to be encapsulated and made interchangeable.
 *
 * @author Student
 * @version 1.0
 */
public interface PasswordGenerationStrategy {

    /**
     * Generates a password of the specified length according to the algorithm's specific rules.
     *
     * @param length the desired length of the generated password
     * @return a generated password string of the specified length
     */
    String generatePassword(int length);
}
