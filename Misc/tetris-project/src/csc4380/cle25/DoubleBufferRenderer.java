package csc4380.cle25;

import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JFrame;

public class DoubleBufferRenderer {
    Graphics g;
    public Graphics bufferedGraphics;
    private Image bufferedImg;
    private JFrame dummyPanel;
    public DoubleBufferRenderer(Graphics theGraphics, int width, int height, 
            JFrame context) {
        dummyPanel = context;
        g = theGraphics;
        bufferedImg = dummyPanel.createImage(width, height);
        if (bufferedImg != null)
            bufferedGraphics = bufferedImg.getGraphics();
    }
    
    public void resize(int newWidth, int newHeight) {
        bufferedImg = dummyPanel.createImage(newWidth, newHeight);
        if (bufferedImg != null)
            bufferedGraphics = bufferedImg.getGraphics();
    }
    
    public void render() {
        g.drawImage(bufferedImg, 0, 0, null);
    }
}
