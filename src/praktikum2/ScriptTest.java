package praktikum2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ScriptTest {

  public static void main(String[] args) {
    String expression = "0.1 / 0.2";
    List<String> tokens = tokenize(expression);
    BigDecimal result = evaluate(tokens);
    System.out.println(result);
  }

  public static List<String> tokenize(String expression) {
    List<String> tokens = new ArrayList<>();
    StringBuilder currentToken = new StringBuilder();
    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);
      if (Character.isDigit(c) || c == '.') {
        currentToken.append(c);
      } else if (c == '+' || c == '-' || c == '*' || c == '/') {
        if (currentToken.length() > 0) {
          tokens.add(currentToken.toString());
          currentToken.setLength(0);
        }
        tokens.add(Character.toString(c));
      }
    }
    if (currentToken.length() > 0) {
      tokens.add(currentToken.toString());
    }
    return tokens;
  }

  public static BigDecimal evaluate(List<String> tokens) {
    List<String> postfixTokens = infixToPostfix(tokens);
    return evaluatePostfix(postfixTokens);
  }

  public static List<String> infixToPostfix(List<String> infixTokens) {
    List<String> postfixTokens = new ArrayList<>();
    List<String> operatorStack = new ArrayList<>();
    for (String token : infixTokens) {
      if (isNumber(token)) {
        postfixTokens.add(token);
      } else if (isOperator(token)) {
        while (!operatorStack.isEmpty() && precedence(operatorStack.get(operatorStack.size() - 1)) >= precedence(token)) {
          postfixTokens.add(operatorStack.remove(operatorStack.size() - 1));
        }
        operatorStack.add(token);
      }
    }
    while (!operatorStack.isEmpty()) {
      postfixTokens.add(operatorStack.remove(operatorStack.size() - 1));
    }
    return postfixTokens;
  }

  public static BigDecimal evaluatePostfix(List<String> postfixTokens) {
    List<BigDecimal> stack = new ArrayList<>();
    for (String token : postfixTokens) {
      if (isNumber(token)) {
        stack.add(new BigDecimal(token));
      } else if (isOperator(token)) {
        BigDecimal operand2 = stack.remove(stack.size() - 1);
        BigDecimal operand1 = stack.remove(stack.size() - 1);
        BigDecimal result = applyOperator(operand1, operand2, token);
        stack.add(result);
      }
    }
    return stack.get(0);
  }

  public static boolean isNumber(String token) {
    try {
      new BigDecimal(token);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static boolean isOperator(String token) {
    return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
  }

  public static int precedence(String operator) {
    switch (operator) {
    case "+":
    case "-":
      return 1;
    case "*":
    case "/":
      return 2;
    default:
      throw new IllegalArgumentException("Unbekannter Operator: " + operator);
    }
  }

  public static BigDecimal applyOperator(BigDecimal operand1, BigDecimal operand2, String operator) {
    switch (operator) {
    case "+":
      return operand1.add(operand2);
    case "-":
      return operand1.subtract(operand2);
    case "*":
      return operand1.multiply(operand2);
    case "/":
      return operand1.divide(operand2);
    default:
      throw new IllegalArgumentException("Unbekannter Operator: " + operator);
    }
  }
}
