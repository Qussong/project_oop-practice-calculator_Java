package org.example;

import org.example.calculate.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CalculatorTest {

    // 1 + 2 = 3
    @DisplayName("덧셈 연산을 수행한다.")
    @Test
    void additionTest() {
        int result = Calculator.calculate(1, "+", 2);

        assertThat(result).isEqualTo(3);
    }

    // 1 - 2 = -1
    @DisplayName("뺄셈 연산을 수행한다.")
    @Test
    void subtractionTest() {
        int result = Calculator.calculate(1, "-", 2);

        assertThat(result).isEqualTo(-1);
    }

    /**
     * ver 1
     */

    @DisplayName("사칙연산을 수행한다.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest(int operand1, String operator, int operand2, int result) {
        int calculateResult = Calculator.calculate(operand1, operator, operand2);

        assertThat(calculateResult).isEqualTo(result);
    }

    private static Stream<Arguments> formulaAndResult() {
        return Stream.of(
                arguments(1, "+", 2, 3),
                arguments(1, "-", 2, -1),
                arguments(4, "*", 2, 8),
                arguments(4, "/", 2, 2)
        );
    }

    /**
     * ver 2
     */

    @DisplayName("사칙연산을 수행한다. ver2")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest2(int operand1, String operator, int operand2, int result) {
        int calculateResult = Calculator.calculate2(operand1, operator, operand2);

        assertThat(calculateResult).isEqualTo(result);
    }

    /**
     * ver 3
     */

    @DisplayName("사칙연산을 수행한다. ver3")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest3(int operand1, String operator, int operand2, int result) {
        int calculateResult = Calculator.calculate3(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

        assertThat(calculateResult).isEqualTo(result);
    }
}
