import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculatorUI extends JFrame implements ActionListener {

    JTextField textField;
    JTextArea historyArea;

    JButton[] numButtons = new JButton[10];
    JButton add, sub, mul, div, eq, clr, clearHistoryBtn;
    JButton sqrt, percent, power, sin, cos, log;
    JButton openBracket, closeBracket;

    CalculatorLogic logic = new CalculatorLogic();

    public CalculatorUI() {

        setTitle("Scientific Calculator");
        setSize(360, 680);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 🧾 HISTORY
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBounds(20, 20, 300, 100);
        add(scrollPane);

        // 🧹 CLEAR HISTORY
        clearHistoryBtn = new JButton("Clear History");
        clearHistoryBtn.setBounds(20, 125, 300, 30);
        clearHistoryBtn.addActionListener(this);
        add(clearHistoryBtn);

        // 📟 DISPLAY (NOW EDITABLE ✅)
        textField = new JTextField();
        textField.setBounds(20, 160, 300, 50);
        textField.setFont(new Font("Arial", Font.BOLD, 22));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(true);
        add(textField);

        // ⌨️ KEYBOARD SUPPORT (REAL TYPING)
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                // ENTER → calculate
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    evaluate();
                    e.consume();
                }

                // ESC → clear
                else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    textField.setText("");
                }

                // Allow only valid characters
                char c = e.getKeyChar();

                if (Character.isDigit(c) ||
                        "+-*/().".indexOf(c) >= 0 ||
                        e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    // allow typing
                } else {
                    e.consume(); // block invalid keys
                }
            }
        });

        // 🖱️ CLICK HISTORY → REUSE RESULT
        historyArea.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    int offset = historyArea.viewToModel2D(e.getPoint());
                    int rowStart = javax.swing.text.Utilities.getRowStart(historyArea, offset);
                    int rowEnd = javax.swing.text.Utilities.getRowEnd(historyArea, offset);

                    String line = historyArea.getText().substring(rowStart, rowEnd).trim();

                    if (line.contains("=")) {
                        String result = line.split("=")[1].trim();
                        textField.setText(result);
                    }
                } catch (Exception ex) {
                    // ignore
                }
            }
        });

        // 🔢 NUMBERS
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].addActionListener(this);
        }

        // ➕ OPERATORS
        add = new JButton("+");
        sub = new JButton("-");
        mul = new JButton("*");
        div = new JButton("/");

        eq = new JButton("=");
        clr = new JButton("C");

        openBracket = new JButton("(");
        closeBracket = new JButton(")");

        // 🧠 SCIENTIFIC
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

        // 📐 LAYOUT
        int x = 20, y = 220, count = 1;

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

    // Evaluate expression
    private void evaluate() {
        try {
            String expression = textField.getText();
            double result = logic.evaluate(expression);

            historyArea.setText(expression + " = " + result + "\n" + historyArea.getText());
            textField.setText(String.valueOf(result));

        } catch (Exception ex) {
            textField.setText("Error");
        }
    }

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numButtons[i])
                textField.setText(textField.getText() + i);
        }

        if (e.getSource() == add) textField.setText(textField.getText() + "+");
        if (e.getSource() == sub) textField.setText(textField.getText() + "-");
        if (e.getSource() == mul) textField.setText(textField.getText() + "*");
        if (e.getSource() == div) textField.setText(textField.getText() + "/");

        if (e.getSource() == openBracket) textField.setText(textField.getText() + "(");
        if (e.getSource() == closeBracket) textField.setText(textField.getText() + ")");

        if (e.getSource() == eq) evaluate();

        if (e.getSource() == clr) textField.setText("");

        if (e.getSource() == clearHistoryBtn) historyArea.setText("");

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