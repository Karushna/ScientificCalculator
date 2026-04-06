import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScientificCalculator extends JFrame implements ActionListener {

    JTextField textField;

    JButton[] numButtons = new JButton[10];
    JButton add, sub, mul, div, eq, clr;
    JButton sqrt, percent, power;
    JButton sin, cos, log;

    double num1, num2, result;
    char operator;

    ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(340, 550);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textField = new JTextField();
        textField.setBounds(30, 20, 260, 40);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        add(textField);

        // Numbers
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].addActionListener(this);
        }

        // Basic operators
        add = new JButton("+");
        sub = new JButton("-");
        mul = new JButton("*");
        div = new JButton("/");
        eq = new JButton("=");
        clr = new JButton("C");

        // Scientific
        sqrt = new JButton("√");
        percent = new JButton("%");
        power = new JButton("x²");

        sin = new JButton("sin");
        cos = new JButton("cos");
        log = new JButton("log");

        JButton[] allButtons = {
            add, sub, mul, div, eq, clr,
            sqrt, percent, power,
            sin, cos, log
        };

        for (JButton b : allButtons) {
            b.addActionListener(this);
        }

        // Layout numbers
        int x = 30, y = 80;
        int count = 1;

        for (int i = 1; i <= 9; i++) {
            numButtons[i].setBounds(x, y, 60, 50);
            add(numButtons[i]);
            x += 70;

            if (count % 3 == 0) {
                x = 30;
                y += 60;
            }
            count++;
        }

        numButtons[0].setBounds(30, y, 60, 50);
        add(numButtons[0]);

        add.setBounds(100, y, 60, 50);
        sub.setBounds(170, y, 60, 50);
        mul.setBounds(240, y, 60, 50);

        y += 60;

        div.setBounds(30, y, 60, 50);
        eq.setBounds(100, y, 130, 50);
        clr.setBounds(240, y, 60, 50);

        y += 60;

        sqrt.setBounds(30, y, 80, 50);
        percent.setBounds(120, y, 80, 50);
        power.setBounds(210, y, 80, 50);

        y += 60;

        sin.setBounds(30, y, 80, 50);
        cos.setBounds(120, y, 80, 50);
        log.setBounds(210, y, 80, 50);

        // Add buttons
        add(add); add(sub); add(mul); add(div);
        add(eq); add(clr);
        add(sqrt); add(percent); add(power);
        add(sin); add(cos); add(log);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // Numbers
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numButtons[i]) {
                textField.setText(textField.getText() + i);
            }
        }

        // Basic operators
        if (e.getSource() == add) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }

        if (e.getSource() == sub) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }

        if (e.getSource() == mul) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }

        if (e.getSource() == div) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        // Equals
        if (e.getSource() == eq) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': result = num1 / num2; break;
            }

            textField.setText(String.valueOf(result));
        }

        // Clear
        if (e.getSource() == clr) {
            textField.setText("");
        }

        // √
        if (e.getSource() == sqrt) {
            double value = Double.parseDouble(textField.getText());
            result = Math.sqrt(value);
            textField.setText(String.valueOf(result));
        }

        // %
        if (e.getSource() == percent) {
            double value = Double.parseDouble(textField.getText());
            result = value / 100;
            textField.setText(String.valueOf(result));
        }

        // x²
        if (e.getSource() == power) {
            double value = Double.parseDouble(textField.getText());
            result = Math.pow(value, 2);
            textField.setText(String.valueOf(result));
        }

        // sin
        if (e.getSource() == sin) {
            double value = Double.parseDouble(textField.getText());
            result = Math.sin(Math.toRadians(value));
            textField.setText(String.valueOf(result));
        }

        // cos
        if (e.getSource() == cos) {
            double value = Double.parseDouble(textField.getText());
            result = Math.cos(Math.toRadians(value));
            textField.setText(String.valueOf(result));
        }

        // log
        if (e.getSource() == log) {
            double value = Double.parseDouble(textField.getText());
            result = Math.log10(value);
            textField.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
}