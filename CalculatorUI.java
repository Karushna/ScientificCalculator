import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculatorUI extends JFrame implements ActionListener {

    JTextField textField;
    JButton[] numButtons = new JButton[10];

    JButton add, sub, mul, div, eq, clr;
    JButton sqrt, percent, power, sin, cos, log;

    double num1, num2;
    char operator;

    CalculatorLogic logic = new CalculatorLogic();

    CalculatorUI() {
        setTitle("Scientific Calculator");
        setSize(340, 550);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textField = new JTextField();
        textField.setBounds(30, 20, 260, 40);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        add(textField);

        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].addActionListener(this);
        }

        add = new JButton("+");
        sub = new JButton("-");
        mul = new JButton("*");
        div = new JButton("/");
        eq = new JButton("=");
        clr = new JButton("C");

        sqrt = new JButton("√");
        percent = new JButton("%");
        power = new JButton("x²");
        sin = new JButton("sin");
        cos = new JButton("cos");
        log = new JButton("log");

        JButton[] all = {
            add, sub, mul, div, eq, clr,
            sqrt, percent, power, sin, cos, log
        };

        for (JButton b : all) b.addActionListener(this);

        int x = 30, y = 80, count = 1;

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

        add(add); add(sub); add(mul); add(div);
        add(eq); add(clr);
        add(sqrt); add(percent); add(power);
        add(sin); add(cos); add(log);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numButtons[i]) {
                textField.setText(textField.getText() + i);
            }
        }

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

        if (e.getSource() == eq) {
            num2 = Double.parseDouble(textField.getText());

            double result = 0;

            switch (operator) {
                case '+': result = logic.add(num1, num2); break;
                case '-': result = logic.sub(num1, num2); break;
                case '*': result = logic.mul(num1, num2); break;
                case '/': result = logic.div(num1, num2); break;
            }

            textField.setText(String.valueOf(result));
        }

        if (e.getSource() == clr) textField.setText("");

        if (e.getSource() == sqrt)
            textField.setText(String.valueOf(logic.sqrt(Double.parseDouble(textField.getText()))));

        if (e.getSource() == percent)
            textField.setText(String.valueOf(logic.percent(Double.parseDouble(textField.getText()))));

        if (e.getSource() == power)
            textField.setText(String.valueOf(logic.power(Double.parseDouble(textField.getText()))));

        if (e.getSource() == sin)
            textField.setText(String.valueOf(logic.sin(Double.parseDouble(textField.getText()))));

        if (e.getSource() == cos)
            textField.setText(String.valueOf(logic.cos(Double.parseDouble(textField.getText()))));

        if (e.getSource() == log)
            textField.setText(String.valueOf(logic.log(Double.parseDouble(textField.getText()))));
    }

    public static void main(String[] args) {
        new CalculatorUI();
    }
}