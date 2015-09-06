package minesweeper;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Sweep extends javax.swing.JFrame {
    int wid = 9, hei = 9;
    int block[][] = new int[hei][wid];
    javax.swing.JToggleButton blox[][];
    boolean start, canPlay;
    ImageIcon bomb = new ImageIcon(getClass().getResource("mine-xp.gif"));
    ActionListener listener = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            int i = 0, j = 0;
            boolean found = false;
            for (i = 0; i < hei; i++) {
                for (j = 0; j < wid; j++) {
                    if(e.getSource() == blox[i][j]){
                        found = true;
                        break;
                    }
                }
                if(found) break;
            }
            if(canPlay){
                blox[i][j].setSelected(true);
                if(!start){
                    spawn(i,j);
                }
                if(block[i][j] != -1){
                    open(i,j);
                    reval();
                    if(checkWin()){
                        JOptionPane.showMessageDialog(null, "You won!!!");
                        canPlay = false;
                    }
                }else lose();
            }else{
                if(block[i][j] != 0){
                    blox[i][j].setSelected(true);
                }else if(block[i][j] != -1) blox[i][j].setSelected(false);
            }
        }
    };
    
    private void spawn(int i, int j){
        for(int k = 1; k < 10*hei*wid/81;k++){
            int x = (int)(Math.random() * (wid-.01));
            int y = (int)(Math.random() * (hei-.01));
            while( x == j && y == i && block[y][x] != -1){
                x = (int)(Math.random() * (wid-.01));
                y = (int)(Math.random() * (hei-.01));
            }
            block[y][x] = -1;
        }
        start = true;
        
    }
    
    private void open(int i, int j){
        int bombs = 0;
        if(!(j >= 0 && i >= 0 && j < wid && i < hei) || block[i][j] != 0) return;
        for(int y = i-1 ; y <= i+1; y++){
            for(int x = j-1 ; x <= j+1; x++){
                if(x >= 0 && y >= 0 && x < wid && y < hei){
                    if(block[y][x] == -1) bombs++;
                }
            }
        }
        if (bombs>0){
            block[i][j] = bombs;
        }else{
            block[i][j] = -2;
            for(int y = i-1 ; y <= i+1; y++){
                for(int x = j-1 ; x <= j+1; x++){
                    if(x != j || y != i) open(y,x);
                }
            }
        }
    }
    
    private void reval(){
        for (int i = 0; i < hei; i++) {
            for (int j = 0; j < wid; j++) {
                if(block[i][j] != 0 && block[i][j] != -1){
                    blox[i][j].setSelected(true);
                }
                if(block[i][j] >0) blox[i][j].setText(""+block[i][j]);
            }
        }
        jPanel1.repaint();
    }
    
    private boolean checkWin(){
        for (int i = 0; i < hei; i++) {
            for (int j = 0; j < wid; j++) {
                if(block[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }
    
    private void lose(){
        canPlay = false;
        for (int i = 0; i < hei; i++) {
            for (int j = 0; j < wid; j++) {
                if(block[i][j] == -1){
                    blox[i][j].setIcon(bomb);
                    blox[i][j].setSelected(true);
                }
            }
        }
        jPanel1.repaint();
    }
        
    public Sweep() {
        initComponents();
        createBlocks();
        canPlay = true;
    }
    
    private void createBlocks(){
        jPanel1.removeAll();
        blox = new javax.swing.JToggleButton[hei][wid];
        for (int i = 0; i < hei; i++) {
            for (int j = 0; j < wid; j++) {
                blox[i][j] = new javax.swing.JToggleButton();
                blox[i][j].setSize(jPanel1.getWidth()/wid, jPanel1.getHeight()/hei);
                blox[i][j].setLocation(j * jPanel1.getWidth()/wid, i * jPanel1.getHeight()/hei);
                blox[i][j].addActionListener(listener);
                int size = blox[i][j].getWidth()/3;
                blox[i][j].setFont(new Font("TimesRoman", Font.PLAIN,size));
                jPanel1.add(blox[i][j]);
            }
        }
        jPanel1.repaint();
    }
    
    private void resiz(){
        for (int i = 0; i < hei; i++) {
            for (int j = 0; j < wid; j++) {
                blox[i][j].setSize(jPanel1.getWidth()/wid, jPanel1.getHeight()/hei);
                blox[i][j].setLocation(j * jPanel1.getWidth()/wid, i * jPanel1.getHeight()/hei);
                int size = blox[i][j].getWidth()/3;
                blox[i][j].setFont(new Font("TimesRoman", Font.PLAIN,size));
            }
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 835, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        jMenu1.setText("Game");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("New game");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Option");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        resiz();
    }//GEN-LAST:event_jPanel1ComponentResized
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        int x = wid, y = hei;
        boolean valid = false;
        String wi = "", he = "";
        while(!valid){
            wi = "";
            he = "";
            valid = true;
            try{
                wi = JOptionPane.showInputDialog("Horizontal dimention (x >= 5): ");
                he = JOptionPane.showInputDialog("Vertical dimention: (y >= 5): ");
                x = Integer.parseInt(wi);
                y = Integer.parseInt(he);
                if(x < 5 || y < 5) valid = false;
            }catch(Exception e){
                valid = false;
            }
            if(wi.equals("") || he.equals("")){
                valid = true;
                x = wid;
                y = hei;
            }
            if(!valid) JOptionPane.showMessageDialog(null, "Invalid input!");
        }
        if(!(wi.equals("") || he.equals(""))){
            wid = x;
            hei = y;
            block = new int[hei][wid];
            createBlocks();
            canPlay = true;
            start = false;
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        for (int i = 0; i < hei; i++) {
            for (int j = 0; j < wid; j++) {
                block[i][j] = 0;
                blox[i][j].setIcon(null);
                blox[i][j].setText("");
                blox[i][j].setSelected(false);
            }
        }
        canPlay = true;
        start = false;
        jPanel1.repaint();
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    
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
            java.util.logging.Logger.getLogger(Sweep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sweep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sweep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sweep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sweep().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
