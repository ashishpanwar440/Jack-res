package the.calculator;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import javax.swing.JButton;
public class TheCalcFrame extends javax.swing.JFrame {
    int MAX_LENGTH = 15;
    String currentNumber = ""; // Current processing number. Best in string form.
    BigDecimal accumulator = BigDecimal.ZERO;
    BigDecimal argument = BigDecimal.ZERO;
    /* Operation code agreement:
     * 0: +
     * 1: -
     * 2: *
     * 3: /
     */
    int operation = 0;
    boolean dec = false;
    JButton clearButton;
    
    public TheCalcFrame() {
        initComponents();
        // String array for the text of the button.
        // They will be added in in this sequence.
        String buttonText[] = {"7", "8", "9",
            "4", "5", "6",
            "1", "2", "3",
            "0", ".", "=",
            "C", "-", "+", "x", "/"};// This portion is for the operator panel.
        for (int i = 0; i < buttonText.length; i++) {
            JButton newButton = new JButton();
            newButton.setFont(new Font("Verdana", Font.PLAIN, 28));
            newButton.setText(buttonText[i]);
            // All the buttons have one uniform action listener.
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonClicked(e); // Pass the button event in.
                }
            });
            // Cache the clear button for later use.
            if (buttonText[i].equals("C")) clearButton = newButton;
            if (i < 12) {
                numberButtonPanel.add(newButton); // Number pad buttons.
            } else {
                operationButtonPanel.add(newButton); // Operator buttons.
            }
        }
    }
    
    /* Method for each button click.
     * Precondition: the method takes in one action event argument.
     * Postcondition: the method takes input and display result according to the
     *                text of the button that sent the event.
     */
    private void buttonClicked(ActionEvent theEvent) {
        JButton theButton = (JButton) theEvent.getSource();
        // Extract the character of the button.
        char buttonText = theButton.getText().charAt(0);
        if (buttonText - '0' <= 9 && buttonText - '0' >= 0) {
            // If a number is clicked.
            // Only take input if max length is not reached and first 
            // number is not 0
            if (currentNumber.length() < MAX_LENGTH && 
                    !(currentNumber.equals("") && (buttonText == '0'))) {
                if (operation < 0) { // If previous button was a "="
                    // Wipe out all and 
                    argument = BigDecimal.ZERO;
                    accumulator = BigDecimal.ZERO;
                    operation = 0;
                    dec = false;
                }
                int theNumber = buttonText - '0';
                currentNumber += theNumber;
                clearButton.setText("CE");
                showNumber(false);
            }
        } else { // If an operator button is clicked.
            switch (theButton.getText()) {
                case ".":
                    if (!dec) {
                        // Set the decimal boolean to true to make sure it only
                        // happens once.
                        dec = true;
                        currentNumber += ".";
                    }
                    return;
                // Operator buttons.
                case "+":
                    doCalculation(); // Do calculation.
                    operation = 0; // Change operation code.
                    break;
                case "-":
                    doCalculation();
                    operation = 1;
                    break;
                case "x":
                    doCalculation();
                    operation = 2;
                    break;
                case "/":
                    doCalculation();
                    operation = 3;
                    break;
                    
                // Result button.
                case "=":
                    doCalculation();
                    operation = -1;
                    break;
                    
                // Clear entry button, only clear current entry.
                case "CE":
                    currentNumber = "";
                    dec = false;
                    clearButton.setText("C"); // Set the clear button to global clear.
                    showNumber(false);
                    return;
                //Global clear button, set up a new calculation.
                case "C":
                    currentNumber = "";
                    accumulator = BigDecimal.ZERO;
                    argument = BigDecimal.ZERO;
                    operation = 0;
                    dec = false;
                    showNumber(false);
                    return;
            }
            showNumber(true);
        }
    }
    
    /* Method to show number out to screen.
     * Precondition: takes in a boolean.
     * Postcondition: 
     *  showResult = true: show the content in the accumulator register.
     *  showResult = false: show the content in the current number register.
     */
    private void showNumber(boolean showResult) {
        BigDecimal numberToShow;
        if (showResult) {
            numberToShow = accumulator;
        } else {
            String temp = currentNumber;
            if (temp.endsWith(".") || temp.equals("")) temp += "0";
            numberToShow = new BigDecimal(temp);
        }
        String stringOfNumber = numberToShow.toPlainString();
        stringOfNumber = stringOfNumber.replaceAll("\\.0*$", "");
        if (stringOfNumber.length() > MAX_LENGTH) {
            stringOfNumber = stringOfNumber.substring(0, MAX_LENGTH - 3) + "...";
        }
        if (numberToShow.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO)) {
            resultText.setText(stringOfNumber + ".");
        } else {
            resultText.setText(stringOfNumber);
        }
    }
    
    private void doCalculation() {
        if (operation >= 0) {
            if (!currentNumber.isEmpty()) {
                argument = new BigDecimal(currentNumber.replaceAll("\\.$", ""));
            }
            switch (operation) {
                case 0:
                    accumulator = accumulator.add(argument);
                    break;
                case 1:
                    accumulator = accumulator.subtract(argument);
                    break;
                case 2:
                    accumulator = accumulator.multiply(argument);
                    break;
                case 3:
                    accumulator = accumulator.divide(argument, MAX_LENGTH,
                            BigDecimal.ROUND_HALF_EVEN);
                    break;
            }
            dec = false;
            currentNumber = "";
            clearButton.setText("C");
        }
    }

            @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonPanel = new javax.swing.JPanel();
        numberButtonPanel = new javax.swing.JPanel();
        operationButtonPanel = new javax.swing.JPanel();
        resultText = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kelsie the Calculator");

        numberButtonPanel.setLayout(new java.awt.GridLayout(4, 0));

        operationButtonPanel.setLayout(new java.awt.GridLayout(5, 0));

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addComponent(numberButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(operationButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(numberButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
            .addComponent(operationButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        resultText.setBackground(new java.awt.Color(0, 51, 0));
        resultText.setFont(new java.awt.Font("Courier New", 1, 36)); // NOI18N
        resultText.setForeground(new java.awt.Color(51, 255, 0));
        resultText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        resultText.setText("0.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultText)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultText, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
        */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TheCalcFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TheCalcFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TheCalcFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TheCalcFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TheCalcFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel numberButtonPanel;
    private javax.swing.JPanel operationButtonPanel;
    private javax.swing.JTextField resultText;
    // End of variables declaration//GEN-END:variables
}
