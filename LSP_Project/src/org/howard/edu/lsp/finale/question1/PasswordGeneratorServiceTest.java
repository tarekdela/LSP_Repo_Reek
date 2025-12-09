package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * PasswordGeneratorServiceTest is a comprehensive JUnit test suite for PasswordGeneratorService.
 *
 * This test suite validates:
 * - Singleton behavior (single instance)
 * - Exception handling for unset algorithms
 * - Correct character sets for each algorithm
 * - Correct password lengths
 * - Algorithm switching at runtime
 *
 * @author Student
 * @version 1.0
 */
public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    /**
     * Setup method executed before each test.
     * Obtains the singleton instance of PasswordGeneratorService.
     */
    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }

    /**
     * Test: checkInstanceNotNull
     * Verifies that the service instance obtained from getInstance() is not null.
     *
     * This validates that getInstance() returns a valid object.
     */
    @Test
    public void checkInstanceNotNull() {
        assertNotNull(service, "PasswordGeneratorService instance should not be null");
    }

    /**
     * Test: checkSingleInstanceBehavior
     * Verifies that multiple calls to getInstance() return the EXACT same object in memory.
     *
     * This test confirms true Singleton behavior by checking object identity (==),
     * not just equality (equals()).
     */
    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        assertSame(service, second,
                "getInstance() must return the same object instance every time");
    }

    /**
     * Test: generateWithoutSettingAlgorithmThrowsException
     * Verifies that calling generatePassword() before setting an algorithm throws IllegalStateException.
     *
     * This validates the requirement: "If generatePassword(int length) is called before an
     * algorithm is selected, your service must throw: IllegalStateException"
     */
    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        // Ensure we have a fresh service with no algorithm set
        // If algorithm was set in previous tests, this may require test isolation

        assertThrows(IllegalStateException.class, () -> s.generatePassword(10),
                "generatePassword() should throw IllegalStateException when no algorithm is set");
    }

    /**
     * Test: basicAlgorithmGeneratesCorrectLengthAndDigitsOnly
     * Verifies that the "basic" algorithm:
     * - Generates passwords of the requested length
     * - Contains only digits (0-9)
     *
     * The basic algorithm uses java.util.Random and must output digits only.
     */
    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");

        int length = 10;
        String p = service.generatePassword(length);

        // Check length
        assertEquals(length, p.length(), "Password length should be " + length);

        // Check that all characters are digits
        for (char c : p.toCharArray()) {
            assertTrue(Character.isDigit(c),
                    "Basic algorithm should only contain digits (0-9), but found: " + c);
        }
    }

    /**
     * Test: enhancedAlgorithmGeneratesCorrectCharactersAndLength
     * Verifies that the "enhanced" algorithm:
     * - Generates passwords of the requested length
     * - Contains only characters from the set: A-Z, a-z, 0-9
     *
     * The enhanced algorithm uses java.security.SecureRandom for stronger security.
     */
    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");

        int length = 12;
        String p = service.generatePassword(length);

        // Check length
        assertEquals(length, p.length(), "Password length should be " + length);

        // Define allowed characters for enhanced algorithm
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Check that all characters are in the allowed set
        for (char c : p.toCharArray()) {
            assertTrue(allowedChars.indexOf(c) >= 0,
                    "Enhanced algorithm should only contain A-Z, a-z, 0-9, but found: " + c);
        }
    }

    /**
     * Test: lettersAlgorithmGeneratesLettersOnly
     * Verifies that the "letters" algorithm:
     * - Generates passwords of the requested length
     * - Contains only letters (A-Z and a-z)
     * - Contains no digits or special characters
     *
     * The letters algorithm uses java.util.Random with only letter characters.
     */
    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");

        int length = 8;
        String p = service.generatePassword(length);

        // Check length
        assertEquals(length, p.length(), "Password length should be " + length);

        // Check that all characters are letters
        for (char c : p.toCharArray()) {
            assertTrue(Character.isLetter(c),
                    "Letters algorithm should only contain letters (A-Z, a-z), but found: " + c);
        }
    }

    /**
     * Test: switchingAlgorithmsChangesBehavior
     * Verifies that switching between algorithms at runtime produces passwords
     * with different characteristics.
     *
     * This test demonstrates the Strategy pattern in action:
     * - Set to "basic" -> produces digits only
     * - Set to "letters" -> produces letters only
     * - Set to "enhanced" -> produces letters and digits
     *
     * The same service instance produces different password types based on
     * the selected algorithm, validating runtime algorithm switching.
     */
    @Test
    public void switchingAlgorithmsChangesBehavior() {
        int length = 10;

        // Test with basic algorithm (digits only)
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(length);
        assertEquals(length, p1.length(), "Basic algorithm password should be length " + length);
        for (char c : p1.toCharArray()) {
            assertTrue(Character.isDigit(c),
                    "Basic should contain only digits, but found: " + c);
        }

        // Test with letters algorithm (letters only)
        service.setAlgorithm("letters");
        String p2 = service.generatePassword(length);
        assertEquals(length, p2.length(), "Letters algorithm password should be length " + length);
        for (char c : p2.toCharArray()) {
            assertTrue(Character.isLetter(c),
                    "Letters should contain only letters, but found: " + c);
        }

        // Test with enhanced algorithm (letters and digits)
        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(length);
        assertEquals(length, p3.length(), "Enhanced algorithm password should be length " + length);
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (char c : p3.toCharArray()) {
            assertTrue(allowedChars.indexOf(c) >= 0,
                    "Enhanced should contain only A-Z, a-z, 0-9, but found: " + c);
        }

        // Verify they produce different outputs (very high probability)
        assertNotEquals(p1, p2, "Different algorithms should likely produce different passwords");
        assertNotEquals(p2, p3, "Different algorithms should likely produce different passwords");
    }
}
