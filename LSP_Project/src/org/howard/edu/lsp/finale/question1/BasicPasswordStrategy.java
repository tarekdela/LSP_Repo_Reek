package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * BasicPasswordStrategy implements PasswordGenerationStrategy to generate passwords
 * containing only digits (0-9) using java.util.Random.
 *
 * This is the simplest password generation algorithm, suitable for basic use cases
 * where security requirements are minimal.
 *
 * @author Student
 * @version 1.0
 */
public class BasicPasswordStrategy implements PasswordGenerationStrategy {

    private static final String DIGITS = "0123456789";
    private Random random;

    /**
     * Constructs a BasicPasswordStrategy and initializes the Random number generator.
     */
    public BasicPasswordStrategy() {
        this.random = new Random();
    }

    /**
     * Generates a password of the specified length using only digits (0-9).
     *
     * @param length the desired length of the generated password
     * @return a password string containing only digits
     */
    @Override
    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DIGITS.length());
            password.append(DIGITS.charAt(index));
        }
        return password.toString();
    }
}
