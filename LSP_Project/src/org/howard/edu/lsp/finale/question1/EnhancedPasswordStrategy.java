package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * EnhancedPasswordStrategy implements PasswordGenerationStrategy to generate passwords
 * containing uppercase letters (A-Z), lowercase letters (a-z), and digits (0-9)
 * using java.security.SecureRandom for cryptographically secure randomness.
 *
 * This algorithm provides stronger password generation for enhanced security requirements.
 *
 * @author Student
 * @version 1.0
 */
public class EnhancedPasswordStrategy implements PasswordGenerationStrategy {

    private static final String ALLOWED_CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789";

    private SecureRandom secureRandom;

    /**
     * Constructs an EnhancedPasswordStrategy and initializes the SecureRandom number generator.
     */
    public EnhancedPasswordStrategy() {
        this.secureRandom = new SecureRandom();
    }

    /**
     * Generates a password of the specified length using uppercase letters, lowercase letters,
     * and digits. Uses cryptographically secure random number generation.
     *
     * @param length the desired length of the generated password
     * @return a password string containing letters and digits
     */
    @Override
    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(ALLOWED_CHARS.length());
            password.append(ALLOWED_CHARS.charAt(index));
        }
        return password.toString();
    }
}
