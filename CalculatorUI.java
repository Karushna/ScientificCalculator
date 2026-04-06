import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculatorUI extends JFrame implements ActionListener {

    JTextField textField;
    JTextArea historyArea;

    JButton[] numButtons = new JButton[10];
    JButton add, sub, mul, div, eq, clr;
    JButton sqrt, percent, power, sin, cos, log;
    JButton openBracket, closeBracket;

    CalculatorLogic logic = new CalculatorLogic();

    CalculatorUI() {
        setTitle("Scientific Calculator");
        setSize(360, 650);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 🧾 HISTORY AREA (TOP)
        historyArea = new JTextArea();
        historyArea.setBounds(20, 20, 300, 100);
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBounds(20, 20, 300, 100);
        add(scrollPane);

        // 📟 DISPLAY
        textField = new JTextField();
        textField.setBounds(20, 130, 300, 50);
        textField.setFont(new Font("Arial", Font.BOLD, 22));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        add(textField);

        // Numbers
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].addActionListener(this);
        }

        // Buttons
        add = new JButton("+");
        sub = new JButton("-");
        mul = new JButton("*");
        div = new JButton("/");

        eq = new JButton("=");
        clr = new JButton("C");

        openBracket = new JButton("(");
        closeBracket = new JButton(")");

        sqrt = new JButton("√");
        percent = new JButton("%");
        power = new JButton("x²");
        sin = new JButton("sin");
        cos = new JButton("cos");
        log = new JButton("log");

        JButton[] all = {
            add, sub, mul, div, eq, clr,
            sqrt, percent, power, sin, cos, log,
            openBracket, closeBracket
        };

        for (JButton b : all) b.addActionListener(this);

        // Layout
        int x = 20, y = 190, count = 1;

        for (int i = 1; i <= 9; i++) {
            numButtons[i].setBounds(x, y, 70, 50);
            add(numButtons[i]);
            x += 80;

            if (count % 3 == 0) {
                x = 20;
                y += 60;
            }
            count++;
        }

        numButtons[0].setBounds(20, y, 70, 50);
        add(numButtons[0]);

        add.setBounds(100, y, 70, 50);
        sub.setBounds(180, y, 70, 50);
        mul.setBounds(260, y, 60, 50);

        y += 60;

        div.setBounds(20, y, 70, 50);
        eq.setBounds(100, y, 150, 50);
        clr.setBounds(260, y, 60, 50);

        y += 60;

        openBracket.setBounds(20, y, 70, 50);
        closeBracket.setBounds(100, y, 70, 50);
        sqrt.setBounds(180, y, 70, 50);
        percent.setBounds(260, y, 60, 50);

        y += 60;

        power.setBounds(20, y, 70, 50);
        sin.setBounds(100, y, 70, 50);
        cos.setBounds(180, y, 70, 50);
        log.setBounds(260, y, 60, 50);

        add(add); add(sub); add(mul); add(div);
        add(eq); add(clr);
        add(openBracket); add(closeBracket);
        add(sqrt); add(percent); add(power);
        add(sin); add(cos); add(log);

        setVisible(true);
    }

    // Append helper
    private void append(String value) {
        textField.setText(textField.getText() + value);
        textField.setCaretPosition(textField.getText().length());
    }

    public void actionPerformed(ActionEvent e) {

        // Numbers
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numButtons[i]) {
                append(String.valueOf(i));
            }
        }

        // Operators
        if (e.getSource() == add) append("+");
        if (e.getSource() == sub) append("-");
        if (e.getSource() == mul) append("*");
        if (e.getSource() == div) append("/");

        if (e.getSource() == openBracket) append("(");
        if (e.getSource() == closeBracket) append(")");

        // Equals → evaluate + add to history
        if (e.getSource() == eq) {
            try {
                String expression = textField.getText();
                double result = logic.evaluate(expression);

                // Add to history (NEW LINE ON TOP)
                historyArea.setText(expression + " = " + result + "\n" + historyArea.getText());

                textField.setText(String.valueOf(result));
            } catch (Exception ex) {
                textField.setText("Error");
            }
        }

        // Clear
        if (e.getSource() == clr) textField.setText("");

        // Scientific
        try {
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

        } catch (Exception ex) {
            textField.setText("Error");
        }
    }

    public static void main(String[] args) {
        new CalculatorUI();
    }
}