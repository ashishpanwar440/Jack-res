package tetris;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Tetris extends javax.swing.JFrame {
    
    
    int[][] state = new int[26][10];
    boolean[][] design;
    int type, nextType, scores;
    boolean play, wait;
    int interval = 500, tim = 0;
    Image offScrImg;
    Graphics offScrGraph;
    int locX, locY;
    
    public void paint(Graphics g){
        super.paint(g);
        for(int i = 0;i<this.getHeight();i+=1){
            int col = 255*i/this.getHeight();
            g.setColor(new Color(255-col/3,255-col/2,col));
            g.drawLine(0, i, this.getWidth(), i);
        }
        int he = this.getContentPane().getHeight()-22;
        int thisWidth = this.getContentPane().getWidth();
        jPanel1.setSize(he/2, he);
        jPanel1.setLocation(thisWidth/2 - jPanel1.getWidth()/2, 11);
        jPanel2.setSize(jPanel1.getWidth()/2, jPanel1.getWidth()/2);
        jPanel2.setLocation(jPanel1.getX()+ jPanel1.getWidth() + 20,jPanel1.getY());
        repain();
        
    }
    public Tetris() {
        initComponents();
        
        for(int i = 0; i < 10; i++) state[25][i] = 2;
        offScrImg = createImage(jPanel1.getWidth(), jPanel1.getHeight());
        offScrGraph = offScrImg.getGraphics();
        Timer time = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
                if(play){
                    tim++;
                    if(tim>= interval){
                        tim = 0;
                        moveDown();
                    }
                }
            }
        };
        time.scheduleAtFixedRate(task, 0, 1);
        nextPiece();
        type = nextType;
        spawn();
        play = true;
        repain();
    }
    
    private void moveDown(){
        if(canMove(2)){
            move(2);
        } else {
            solidify();
            checkScore();
            checkLose();
            spawn();
        }
        repain();
        
    }
    private void checkLose(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 10; j++){
                if(state[i][j] == 2){
                    play = false;
                    javax.swing.JOptionPane.showMessageDialog(null, "You lose!");
                    play = true;
                    state = new int[26][10];
                    for(int k = 0; k < 10; k++) state[25][k] = 2;
                    interval = 500;
                    scores = 0;
                    break;
                }
            }
        }
        
    }
    /*
    1: right
    2: down
    3: left
    4: rotate
    */
    private void move(int way){
        if(!wait){
            wait = true;
            switch (way){
                case 2:
                    locY++;
                    for(int i = 25 ; i >= 0 ; i--){
                        for(int j = 0 ; j < 10 ; j++){
                            if(state[i][j] == 1){
                                state[i][j] = 0;
                                state[i+1][j] = 1;
                            }                            
                        }
                    }
                    break;
                case 1:
                    locX++;
                    for(int i = 25 ; i >= 0 ; i--){
                        for(int j = 9 ; j >= 0 ; j--){
                            if(state[i][j] == 1){
                                state[i][j] = 0;
                                state[i][j+1] = 1;
                            }                            
                        }
                    }
                    break;
                case 3:
                    locX--;
                    for(int i = 25 ; i >= 0 ; i--){
                        for(int j = 0 ; j < 10 ; j++){
                            if(state[i][j] == 1){
                                state[i][j] = 0;
                                state[i][j-1] = 1;
                            }
                            
                        }
                    }
                    break;
                case 4:
                    boolean canRotate = true;
                    boolean[][] copy = new boolean[25][10];
                    for(int i = 0 ; i < 25 ; i++){
                        for(int j = 0 ; j < 10 ; j++){
                            if(state[i][j] == 1) copy[i][j] = true;                    }
                    }
                    boolean[][] toRotate = new boolean[5][5];
                    for(int i = 0 ; i < 5 ; i++){
                        for(int j = 0 ; j < 5 ; j++){
                            if( locX + j >= 0 &&
                                    locX + j < 10 &&
                                    locY + i >= 0 &&
                                    locY + i < 25){
                                toRotate[i][j] = copy[locY + i][locX + j];
                            }
                        }
                    }
                    repain();
                    
                    toRotate = rotate(toRotate);
                    for(int i = 0 ; i < 5 ; i++){
                        for(int j = 0 ; j < 5 ; j++){
                            if( locX + j >= 0 &&
                                    locX + j < 10 &&
                                    locY + i >= 0 &&
                                    locY + i < 25){
                                copy[locY+i][locX+j] = toRotate[i][j];
                            } else if(toRotate[i][j]) canRotate = false;
                        }
                    }
                    for(int i = 0 ; i < 25 ; i++){
                        for(int j = 0 ; j < 10 ; j++){
                            if(copy[i][j] && state[i][j] == 2) canRotate = false;
                        }
                    }
                    if(canRotate){
                        for(int i = 0 ; i < 25 ; i++){
                            for(int j = 0 ; j < 10 ; j++){
                                if(state[i][j] == 1) state[i][j] = 0;
                            }
                        }
                        for(int i = 0 ; i < 25 ; i++){
                            for(int j = 0 ; j < 10 ; j++){
                                if(copy[i][j]) state[i][j] = 1;
                            }
                        }
                    }
            }
            wait = false;
        }         
    }
    private boolean canMove(int way){
        
        switch(way){
            case 2:
                for(int i = 25 ; i >= 0 ; i--){
                    for(int j = 0 ; j < 10 ; j++){
                        if(state[i][j] == 1 && state[i+1][j] == 2) return false;
                    }
                }
                break;
            case 3:
                for(int i = 25 ; i >= 0 ; i--){
                    for(int j = 0 ; j < 10 ; j++){
                        if(state[i][j] == 1 &&
                                (j-1 < 0 || state[i][j-1] == 2)){
                            return false;
                        }
                    }
                }
                break;
            case 1:
                for(int i = 25 ; i >= 0 ; i--){
                    for(int j = 9 ; j >= 0 ; j--){
                        if(state[i][j] == 1 &&
                                (j+1 > 9 || state[i][j+1] == 2)){
                            return false;
                        }
                    }
                }
                break;
        }
        return true;
    }
    /*
    0: Square
    1: S
    2: Z
    3: L
    4: J
    5: T
    6: I
    */
    private void solidify(){
        for(int i = 0 ; i < 25 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                if(state[i][j] == 1) state[i][j] = 2;
            }
        }
    }
    private void checkScore(){
        for(int i = 0; i <25; i++){
            boolean score = true;
            if(state[i][0] == 2){
                for(int j = 0; j < 10; j++){
                    if(state[i][j] == 0) score = false;
                }
                if(score){
                    for(int j = 0; j < 10; j++) state[i][j] = 0;
                    for(int k = i-1; k > 0; k--){
                        for(int j = 0; j < 10; j++){
                            if(state[k][j] == 2){
                                state[k][j] = 0;
                                state[k+1][j] = 2;
                            }
                        }
                    }
                    if(interval > 10) interval-= 5;
                    scores += 100;
                }
            }
        }
    }
    
    private void nextPiece(){
        type = nextType;
        nextType = (int)(Math.random()*6.99);
        design = new boolean[5][5];
        switch(nextType){
            case 0:// Square
                design[1][1] = true;
                design[1][2] = true;
                design[2][2] = true;
                design[2][1] = true;
                break;
            case 1:// Swiggly _-
                design[2][2] = true;
                design[2][3] = true;
                design[3][2] = true;
                design[3][1] = true;
                break;
            case 2:// Swiggly -_
                design[2][1] = true;
                design[2][2] = true;
                design[3][2] = true;
                design[3][3] = true;
                break;
            case 3:// Regular L
                design[1][2] = true;
                design[2][2] = true;
                design[3][2] = true;
                design[3][3] = true;
                break;
            case 4:// Backward L
                design[1][2] = true;
                design[2][2] = true;
                design[3][2] = true;
                design[3][1] = true;
                break;
            case 5:// T
                design[2][1] = true;
                design[2][2] = true;
                design[2][3] = true;
                design[3][2] = true;
                break;
            case 6:// I
                design[0][2] = true;
                design[1][2] = true;
                design[2][2] = true;
                design[3][2] = true;
                break;
        }
    }
    private void spawn(){
        locX = 3;
        locY = 1;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(design[i][j]){
                    state[i+locY][j+locX] = 1;
                }
            }
        }
        nextPiece();
    }
    private boolean[][] rotate(boolean[][] design){
        if(type!=0){
            boolean[][] result = new boolean[5][5];
            if(type != 6){
                for(int i = 0; i < 5; i++){
                    for(int j = 0; j < 5; j++){
                        result[j][4-i] = design[i][j];
                    }
                }
                return result;
            } else {
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j < 4; j++){
                        result[j][3-i] = design[i][j];
                    }
                }
                return result;
            }
        }else return design;
    }
    private void repain(){
        
        for(int i = 5 ; i < 25 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                int x = j * jPanel1.getWidth()/10;
                int y = (i-5) * jPanel1.getHeight()/20;
                if(state[i][j] == 0) offScrGraph.setColor(Color.BLACK);
                if(state[i][j] == 1) offScrGraph.setColor(Color.RED);
                if(state[i][j] == 2) offScrGraph.setColor(Color.GREEN);
                offScrGraph.fillRect(x, y, jPanel1.getWidth()/10+1, jPanel1.getHeight()/20+1);
            }
        }
        jPanel1.getGraphics().drawImage(offScrImg, 0, 0, jPanel1);
        Image offsc = createImage(jPanel2.getWidth(),jPanel2.getHeight());
        Graphics off = offsc.getGraphics();
        
        for(int i = 0 ; i < 5 ; i++){
            for(int j = 0 ; j < 5 ; j++){
                int x = j * jPanel2.getWidth()/5;
                int y = i * jPanel2.getHeight()/5;
                if(design[i][j]) off.setColor(Color.red);
                else off.setColor(Color.black);
                off.fillRect(x, y, jPanel2.getWidth()/5+1, jPanel2.getHeight()/5+1);
            }
        }
        jPanel2.getGraphics().drawImage(offsc, 0, 0, jPanel2);
        this.setTitle(""+scores);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 335, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 11, -1, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 88, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 89, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 11, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        offScrImg = createImage(jPanel1.getWidth(), jPanel1.getHeight());
        offScrGraph = offScrImg.getGraphics();
        repain();
    }//GEN-LAST:event_jPanel1ComponentResized
    
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        repain();
    }//GEN-LAST:event_formComponentResized
    
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        switch(evt.getKeyCode()){
            case KeyEvent.VK_LEFT:
                if(canMove(3)){
                    move(3);
                    repain();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(canMove(1)){
                    move(1);
                    repain();
                }
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
            case KeyEvent.VK_UP:
                move(4);
                repain();
                break;
            case KeyEvent.VK_SPACE:
                while(canMove(2)){
                    moveDown();
                    repain();
                }
                moveDown();
                repain();
        }
    }//GEN-LAST:event_formKeyPressed
    
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
            java.util.logging.Logger.getLogger(Tetris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tetris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tetris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tetris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tetris().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
