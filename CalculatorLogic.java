public class CalculatorLogic {

    public double add(double a, double b) {
        return a + b;
    }

    public double sub(double a, double b) {
        return a - b;
    }

    public double mul(double a, double b) {
        return a * b;
    }

    public double div(double a, double b) {
        return a / b;
    }

    public double sqrt(double a) {
        return Math.sqrt(a);
    }

    public double percent(double a) {
        return a / 100;
    }

    public double power(double a) {
        return Math.pow(a, 2);
    }

    public double sin(double a) {
        return Math.sin(Math.toRadians(a));
    }

    public double cos(double a) {
        return Math.cos(Math.toRadians(a));
    }

    public double log(double a) {
        return Math.log10(a);
    }
}