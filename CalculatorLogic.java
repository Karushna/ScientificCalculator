import java.util.Stack;

public class CalculatorLogic {

    public double evaluate(String expr) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (c == ' ') continue;

            // Number parsing
            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();

                while (i < expr.length() &&
                        (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i++));
                }

                numbers.push(Double.parseDouble(sb.toString()));
                i--;
            }

            // Opening bracket
            else if (c == '(') {
                ops.push(c);
            }

            // Closing bracket
            else if (c == ')') {
                while (ops.peek() != '(') {
                    numbers.push(applyOp(ops.pop(), numbers.pop(), numbers.pop()));
                }
                ops.pop();
            }

            // Operator
            else if (isOperator(c)) {
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(c)) {
                    numbers.push(applyOp(ops.pop(), numbers.pop(), numbers.pop()));
                }
                ops.push(c);
            }
        }

        while (!ops.isEmpty()) {
            numbers.push(applyOp(ops.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    private double applyOp(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
        }
        return 0;
    }

    // Scientific functions
    public double sqrt(double a) { return Math.sqrt(a); }
    public double percent(double a) { return a / 100; }
    public double power(double a) { return Math.pow(a, 2); }
    public double sin(double a) { return Math.sin(Math.toRadians(a)); }
    public double cos(double a) { return Math.cos(Math.toRadians(a)); }
    public double log(double a) { return Math.log10(a); }
}