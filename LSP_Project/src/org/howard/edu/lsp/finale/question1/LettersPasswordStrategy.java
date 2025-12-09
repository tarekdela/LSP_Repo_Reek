package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * LettersPasswordStrategy implements PasswordGenerationStrategy to generate passwords
 * containing only letters (A-Z and a-z) using java.util.Random.
 *
 * This algorithm is useful when passwords must contain letters only, excluding digits
 * and special characters.
 *
 * @author Student
 * @version 1.0
 */
public class LettersPasswordStrategy implements PasswordGenerationStrategy {

    private static final String LETTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz";

    private Random random;

    /**
     * Constructs a LettersPasswordStrategy and initializes the Random number generator.
     */
    public LettersPasswordStrategy() {
        this.random = new Random();
    }

    /**
     * Generates a password of the specified length using only letters (A-Z and a-z).
     *
     * @param length the desired length of the generated password
     * @return a password string containing only letters
     */
    @Override
    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETTERS.length());
            password.append(LETTERS.charAt(index));
        }
        return password.toString();
    }
}
