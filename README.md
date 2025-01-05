# 📌사칙연산 계산기

### 🔻조건

1. 간단한 사칙연산을 할 수 있다.
2. 양수로만 계산할 수 있다.
3. 나눗셈에서 0을 나누는 경우 IllegalArgument 예외를 발생시킨다.
4. MVC패턴(Model-View-Controller) 기반으로 구현한다.

### 🔻환경

- OS : Windows 11
- Language : Java 17

    >openjdk version "17.0.13" 2024-10-15</br>OpenJDK Runtime Environment Temurin-17.0.13+11 (build 17.0.13+11)</br>OpenJDK 64-Bit Server VM Temurin-17.0.13+11 (build 17.0.13+11, mixed mode, sharing)<br>

- IDE : IntelliJ IDEA Ultimate 2024.3.1.1</br>
- Build Tool : Gradle
- Test Tool : JUnit 5
- Library : AssertJ

### 🔻Case1

```java
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
```

```java
// class Calculator
public static int calculate(int operand1, String operator, int operand2) {
    if ("+".equals(operator)) {
        return operand1 + operand2;
    } else if ("-".equals(operator)) {
        return operand1 - operand2;
    } else if ("*".equals(operator)) {
        return operand1 * operand2;
    } else if ("/".equals(operator)) {
        return operand1 / operand2;
    }
    return 0;
}
```

### 🔻Case2

```java
@DisplayName("사칙연산을 수행한다. ver2")
@ParameterizedTest
@MethodSource("formulaAndResult")
void calculateTest2(int operand1, String operator, int operand2, int result) {
    int calculateResult = Calculator.calculate2(operand1, operator, operand2);

    assertThat(calculateResult).isEqualTo(result);
}
```

```java
// class Calculator
public static int calculate2(int operand1, String operator, int operand2) {
    return ArithmeticOperator.calculate(operand1, operator, operand2);
}
```
- [enum ArithmeticOperator URL](https://github.com/Qussong/project_oop-practice-calculator_Java/blob/main/oop-practice-caculator/src/main/java/org/example/ArithmeticOperator.java)

### 🔻Case3

```java
@DisplayName("사칙연산을 수행한다. ver3")
@ParameterizedTest
@MethodSource("formulaAndResult")
void calculateTest3(int operand1, String operator, int operand2, int result) {
    int calculateResult = Calculator.calculate3(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

    assertThat(calculateResult).isEqualTo(result);
}
```

```java
// class Calculator
private static final List<NewArithmeticOperator> arithmeticOperators =
        List.of(new AdditionOperator(),
                new SubtractionOperator(),
                new MultiplicationOperator(),
                new DivisionOperator());

public static int calculate3(PositiveNumber operand1, String operator, PositiveNumber operand2) {
    return arithmeticOperators.stream()
            .filter(arithmeticOperator -> arithmeticOperator.supports(operator))
            .map(arithmeticOperator -> arithmeticOperator.calculate(operand1, operand2))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));
}
```

- [NewArithmeticOperator](https://github.com/Qussong/project_oop-practice-calculator_Java/blob/main/oop-practice-caculator/src/main/java/org/example/calculate/NewArithmeticOperator.java)
- [AdditionOperator](https://github.com/Qussong/project_oop-practice-calculator_Java/blob/main/oop-practice-caculator/src/main/java/org/example/calculate/AdditionOperator.java)
- [SubtractionOperator](https://github.com/Qussong/project_oop-practice-calculator_Java/blob/main/oop-practice-caculator/src/main/java/org/example/calculate/SubtractionOperator.java)
- [MultiplicationOperator](https://github.com/Qussong/project_oop-practice-calculator_Java/blob/main/oop-practice-caculator/src/main/java/org/example/calculate/MultiplicationOperator.java)
- [DivisionOperator](https://github.com/Qussong/project_oop-practice-calculator_Java/blob/main/oop-practice-caculator/src/main/java/org/example/calculate/DivisionOperator.java)
