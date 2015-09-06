package rubik.s.cube.timer;
import java.awt.Color;
import java.lang.Runnable;
import java.awt.event.KeyEvent;
import java.lang.Thread;
public class TheTimer extends javax.swing.JFrame {
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftIndicator = new javax.swing.JPanel();
        rightIndicator = new javax.swing.JPanel();
        bothIndicator = new javax.swing.JPanel();
        displayLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rubik's cube timer");
        setBackground(new java.awt.Color(102, 0, 102));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        leftIndicator.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout leftIndicatorLayout = new javax.swing.GroupLayout(leftIndicator);
        leftIndicator.setLayout(leftIndicatorLayout);
        leftIndicatorLayout.setHorizontalGroup(
            leftIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        leftIndicatorLayout.setVerticalGroup(
            leftIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        rightIndicator.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout rightIndicatorLayout = new javax.swing.GroupLayout(rightIndicator);
        rightIndicator.setLayout(rightIndicatorLayout);
        rightIndicatorLayout.setHorizontalGroup(
            rightIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        rightIndicatorLayout.setVerticalGroup(
            rightIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        bothIndicator.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout bothIndicatorLayout = new javax.swing.GroupLayout(bothIndicator);
        bothIndicator.setLayout(bothIndicatorLayout);
        bothIndicatorLayout.setHorizontalGroup(
            bothIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );
        bothIndicatorLayout.setVerticalGroup(
            bothIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        displayLabel.setBackground(new java.awt.Color(51, 0, 0));
        displayLabel.setFont(new java.awt.Font("Courier New", 0, 36)); // NOI18N
        displayLabel.setForeground(new java.awt.Color(255, 0, 0));
        displayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        displayLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        displayLabel.setOpaque(true);

        jButton1.setText("Reset");
        jButton1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton1FocusGained(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton1KeyReleased(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("<- Ctrl");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Enter->");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(leftIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1))
                            .addComponent(bothIndicator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rightIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(displayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 121, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bothIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(displayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(rightIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(leftIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    Color BUTTON_DOWN = new Color(50, 255, 0);
    Color BUTTON_UP = new Color(10, 50, 0);
    Color BOTH_DOWN = new Color(50, 255, 0);
    Color BOTH_UP = new Color(10, 50, 0);
    Color READY = new Color(10, 110, 255);
    Color BLINK_ON = Color.orange;
    Color BLINK_OFF = Color.green;
    long BLINK_INTERVAL = 300;
    long TIMER_INTERVAL = 50;
    long TIME_DOWN = 700;
    String TIME_FORMAT = "%02d:%02d:%02d:%03d";
    boolean blink = false;
    int blinkTime = 0;
    int timeDown = 0;
    int mode = 0;
    int LEFT_BUTTON = KeyEvent.VK_CONTROL;
    int RIGHT_BUTTON = KeyEvent.VK_ENTER;
    long timerStart;
    long timerCurrent;
    boolean leftDown = false;
    boolean rightDown = false;
    boolean bothDown = false;
    long second;
    long minute;
    long hour;
    long millis;
    
    
    String time = String.format(TIME_FORMAT, hour, minute, second, millis);
    private class MyTimer implements Runnable {
        public void run() {
                try{
                    while(true){
                        //For the indicator
                        if (leftDown) {
                            leftIndicator.setBackground(BUTTON_DOWN);
                        } else {
                            leftIndicator.setBackground(BUTTON_UP);
                        }
                        if (rightDown) {
                            rightIndicator.setBackground(BUTTON_DOWN);
                        } else {
                            rightIndicator.setBackground(BUTTON_UP);
                        }
                        bothDown = leftDown && rightDown;
                        
                        //Running modes
                        if (mode == 0) {
                            if (bothDown) {
                                bothIndicator.setBackground(BOTH_DOWN);
                                timeDown++;
                                if (timeDown >= TIME_DOWN / TIMER_INTERVAL) { //Divide cuz interval is 50ms
                                    mode = 1;
                                }
                            } else {
                                bothIndicator.setBackground(BOTH_UP);
                                timeDown = 0;
                            }
                        } else if (mode == 1) {
                            if (bothDown) {
                                bothIndicator.setBackground(READY);
                            } else {
                                timerStart = System.currentTimeMillis();
                                blinkTime = 0;
                                mode = 2;
                            }
                        } else if (mode == 2) {
                            if (!bothDown) {
                                timerCurrent = System.currentTimeMillis();
                                displayTime();
                                blinkTime++;
                                if (blinkTime >= BLINK_INTERVAL / TIMER_INTERVAL) {
                                    blinkTime = 0;
                                    blink = !blink;
                                    if (blink) {
                                        bothIndicator.setBackground(BLINK_ON);
                                    } else {
                                        bothIndicator.setBackground(BLINK_OFF);
                                    }
                                }
                            } else {
                                displayTime();
                                mode = 3;
                                bothIndicator.setBackground(BUTTON_DOWN);
                                jButton1.requestFocus();
                            }
                        }
                        Thread.sleep(TIMER_INTERVAL);
                    }
                } catch (Exception e) {}
            }
    }
    
    private void displayTime() {
        millis = timerCurrent - timerStart;
        second = (millis / 1000) % 60;
        minute = (millis / (1000 * 60)) % 60;
        hour = (millis / (1000 * 60 * 60)) % 24;
        millis = millis % 1000;
        time = String.format(TIME_FORMAT, hour, minute, second, millis);
        displayLabel.setText(time);
    }
    public TheTimer() {
        initComponents();
        displayLabel.setText(String.format(TIME_FORMAT, 0, 0, 0, 0));
        this.getContentPane().setBackground(this.getBackground());
        MyTimer timerRunnable = new MyTimer();
        Thread timerThread = new Thread(timerRunnable);
        timerThread.start();
    }
    
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == LEFT_BUTTON) leftDown = true;
        if (evt.getKeyCode() == RIGHT_BUTTON) rightDown = true;
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (evt.getKeyCode() == LEFT_BUTTON) leftDown = false;
        if (evt.getKeyCode() == RIGHT_BUTTON) rightDown = false;
    }//GEN-LAST:event_formKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mode = 0;
        timeDown = 0;
        bothIndicator.setBackground(BUTTON_UP);
        displayLabel.setText(String.format(TIME_FORMAT, 0, 0, 0, 0));
        leftDown = false;
        rightDown = false;
        bothDown = false;
        this.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton1FocusGained
        if (mode != 3)
            this.requestFocus();
    }//GEN-LAST:event_jButton1FocusGained

    private void jButton1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyReleased
        if (evt.getKeyCode() == LEFT_BUTTON) leftDown = false;
        if (evt.getKeyCode() == RIGHT_BUTTON) rightDown = false;
        bothIndicator.setBackground(BUTTON_UP);
    }//GEN-LAST:event_jButton1KeyReleased
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(TheTimer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TheTimer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TheTimer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TheTimer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TheTimer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bothIndicator;
    private javax.swing.JLabel displayLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel leftIndicator;
    private javax.swing.JPanel leftIndicator2;
    private javax.swing.JPanel rightIndicator;
    // End of variables declaration//GEN-END:variables
}
