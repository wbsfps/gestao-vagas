package br.com.wbs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FirstTest {

    @Test
    public void must_need_calculate_two_numbers() {
        var result = calculate(1,2);
        assertEquals(3.0, result);
    }

    @Test
    public void validate_value_incorrect() {
        assertNotEquals(2, calculate(1,2));
    }

    public static double calculate(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber;
    }
}
