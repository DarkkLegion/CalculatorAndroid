package com.example.calculator;
import java.util.Stack;
public class computeFunctions {
    public static double evaluate(String expression) {
        char[] tokens = expression.toCharArray();
        // Stack for numbers: 'values'
        Stack < Double > values = new
                Stack<>();
        // Stack for Operators: 'ops'
        Stack < Character > ops;
        ops = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            // Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;
            // Current token is a number, push it to stack for numbers
            if (tokens[i] >= '0' &&
                    tokens[i] <= '9') {
                StringBuilder sbuf = new
                        StringBuilder();
                // There may be more than one digits in number
                while (i < tokens.length &&
                        tokens[i] >= '0' &&
                        tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Double.parseDouble(sbuf.toString()));
                i--;
            }
            // Current token is an opening brace, push it to 'ops'
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
                // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(),
                            values.pop(),
                            values.pop()));
                ops.pop();
            }
            // Current token is an operator.
            else if (tokens[i] == '+' ||
                    tokens[i] == '-' ||
                    tokens[i] == '*' ||
                    tokens[i] == '/') {
                while (!ops.empty() &&
                        hasPrecedence(tokens[i],
                                ops.peek()))
                    values.push(applyOp(ops.pop(),
                            values.pop(),
                            values.pop()));
                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }
        // Entire expression has been parsed at this point, apply remaining ops to remaining values
        while (!ops.empty())
            values.push(applyOp(ops.pop(),
                    values.pop(),
                    values.pop()));

        // Top of 'values' contains result, return it
        return values.pop();
    }
    // Returns true if 'op2' has higher or same precedence as 'op1', otherwise returns false.
    public static boolean hasPrecedence(
            char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') &&
                (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
    // A utility method to apply an operations on a and b
    public static double applyOp(char op,
                                 double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException(
                            "Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
}
