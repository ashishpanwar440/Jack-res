package rotation;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;
import java.util.TimerTask;
public class Rotate extends javax.swing.JFrame  {
    BufferedImage img;
    double deg = 0;
    public Rotate() throws IOException{
        initComponents();
        img = ImageIO.read(getClass().getResource("10480163_10205681776144700_5879918229122051617_n.jpg"));
        Timer time = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
                jPanel1.getGraphics().drawImage(rotate(deg), 0, 0, Color.YELLOW, rootPane);
                deg += 0.30;
                if(deg > 3.14*2) deg -= 3.14*2;
            }
        };
        time.scheduleAtFixedRate(task, 0, 1);
    }
    
    private Image rotate(double degrees){
        int size = (int)(Math.sqrt(img.getWidth()*img.getWidth()+img.getHeight()*img.getHeight()));
        Image result = createImage(size,size);
        Graphics g = result.getGraphics();
        int centerX1 = img.getWidth()/2;
        int centerY1 = img.getHeight()/2;
        int center2 = size/2;
        for(int y = 0; y < img.getHeight();y++){
            for(int x = 0; x < img.getWidth();x++){
                int r = (int)(Math.sqrt((centerX1-x)*(centerX1-x)+(centerY1-y)*(centerY1-y)));
                double angle = Math.atan2(centerY1-y, centerX1-x);
                int locX = (int)(center2 - r*Math.cos(angle+degrees));
                int locY = (int)(center2 - r*Math.sin(angle+degrees));
                int blue = img.getRGB(x, y) & 0xff;
                int green = img.getRGB(x, y) >> 8 & 0xff;
                int red = img.getRGB(x, y) >> 16 & 0xff;
                g.setColor(new Color(red,green,blue));
                g.drawLine(locX, locY, locX, locY);
                g.drawLine(locX+1, locY, locX+1, locY);
                g.drawLine(locX, locY+1, locX, locY+1);
                g.drawLine(locX+1, locY+1, locX+1, locY+1);
            }
        }
        this.setTitle(""+(deg/Math.PI*180));

        return result;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(204, 255, 0));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

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

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked

    }//GEN-LAST:event_jPanel1MouseClicked

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
            java.util.logging.Logger.getLogger(Rotate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rotate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rotate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rotate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Rotate().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Rotate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
