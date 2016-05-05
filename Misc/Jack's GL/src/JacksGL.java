
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JacksGL extends javax.swing.JFrame {
    JFrame thisFrame = this;
    float yRot = 0;
    float xRot = 0;
    float xPos = 0;
    float yPos = 0;
    float zPos = 0;
    float distance = 8;
    int prevX = -1;
    int prevY = -1;
    JFileChooser chooser = new JFileChooser();

    Timer timer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            thisFrame.setTitle(glPanel.getWidth() + "x" + glPanel.getHeight()
                    + " - " + (1000000000.0 / (float) glPanel.deltaTime) + " FPS");
        }
    });
    public JacksGL() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        JacksObject cube = new JacksObject();
        JacksMesh meshie = cube.addMesh();
        meshie.addVertex(1, 1, 1);
        meshie.addVertex(1, 1, -1);
        meshie.addVertex(-1, 1, -1);
        meshie.addVertex(-1, 1, 1);
        meshie.addVertex(1, -1, 1);
        meshie.addVertex(1, -1, -1);
        meshie.addVertex(-1, -1, -1);
        meshie.addVertex(-1, -1, 1);
        meshie.addFace(0, 1, 2, 3);
        meshie.addFace(0, 4, 5, 1);
        meshie.addFace(3, 2, 6, 7);
        meshie.addFace(0, 3, 7, 4);
        meshie.addFace(1, 5, 6, 2);
        meshie.addFace(4, 7, 6, 5);
        glPanel.objectList.add(cube);
        JacksObject second = cube.clone();
        JacksObject third = cube.clone();
        second.x = .5f;
        second.z = .5f;
        second.y = .5f;
        third.x = -.5f;
        third.z = -.5f;
        third.y = -.5f;
        second.rotateX = (float) Math.PI / 4;
        second.rotateY = (float) Math.PI / 4;
        third.rotateZ = (float) Math.PI / 3;
        glPanel.objectList.add(second);
        glPanel.objectList.add(third);
        glPanel.lightList.add(new JacksLight(0, 2, 128, 255, 0,
                1.5f, -.8f, 1.5f));
        glPanel.lightList.add(new JacksLight(0, 2, 255, 0, 128,
                -1.5f, -.8f, 1.5f));
        glPanel.lightList.add(new JacksLight(1, .5f, 128, 128, 255,
                0, -1, 1));

        updateView();
        glPanel.startUpdating();
        timer.start();
    }

    void updateView() {
        glPanel.cameraY = distance * (float) Math.sin(xRot) + yPos;
        glPanel.cameraX = distance * (float) (Math.cos(xRot) * Math.sin(yRot)) + xPos;
        glPanel.cameraZ = distance * (float) (Math.cos(xRot) * Math.cos(yRot)) + zPos;
        glPanel.cameraRotationX = -xRot;
        glPanel.cameraRotationY = yRot;
        glPanel.updateOrigin();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        glPanel = new JacksGLPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        glPanel.setBackground(new java.awt.Color(0, 0, 0));
        glPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                glPanelMouseDragged(evt);
            }
        });
        glPanel.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                glPanelMouseWheelMoved(evt);
            }
        });
        glPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                glPanelMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                glPanelMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout glPanelLayout = new javax.swing.GroupLayout(glPanel);
        glPanel.setLayout(glPanelLayout);
        glPanelLayout.setHorizontalGroup(
            glPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        glPanelLayout.setVerticalGroup(
            glPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Show light sources");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("Rotate Background X ^");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Rotate Background X v");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Rotate Background Y <");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Rotate Background Y >");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Reset backgorund rotation");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Add light");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Remove all lights");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Remove light index");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Remove previous light");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Edit previous light");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jSlider1.setValue(0);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel1.setText("Ambient light:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Open OBJ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Load Background");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem3.setText("Remove Background");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(glPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(glPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void glPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_glPanelMouseDragged
        int deltaX = 0;
        int deltaY = 0;
        if (prevX >= 0) {
            deltaX = evt.getX() - prevX;
        }
        if (prevY >= 0) {
            deltaY = evt.getY() - prevY;
        }
        if (SwingUtilities.isLeftMouseButton(evt)) {
            yRot -= (float) deltaX / 40.0;
            xRot += (float) deltaY / 40.0;
            yRot = rotate(yRot, (float) Math.PI * 2);
            xRot = rotate(xRot, (float) Math.PI * 2);
        } else if (SwingUtilities.isRightMouseButton(evt)) {
            xPos -= ((float) deltaX / 40.0) * Math.cos(yRot);
            zPos += ((float) deltaX / 40.0) * Math.sin(yRot);
            yPos += (float) deltaY / 40.0;
        }
        updateView();
        prevX = evt.getX();
        prevY = evt.getY();
    }//GEN-LAST:event_glPanelMouseDragged

    private void glPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_glPanelMouseReleased
        prevX = -1;
        prevY = -1;
    }//GEN-LAST:event_glPanelMouseReleased

    private void glPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_glPanelMouseClicked
        if (evt.getClickCount() == 2) {
            xRot = 0;
            yRot = 0;
            xPos = 0;
            yPos = 0;
            zPos = 0;
            updateView();
        }
    }//GEN-LAST:event_glPanelMouseClicked

    private void glPanelMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_glPanelMouseWheelMoved
        distance += evt.getWheelRotation() / 2.0;
        if (distance < 0) {
            distance = 0;
        }
        if (distance > 80) {
            distance = 80;
        }
        updateView();
    }//GEN-LAST:event_glPanelMouseWheelMoved

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        chooser.setFileFilter(
                new FileNameExtensionFilter("Wavefront OBJ", "obj"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            long fileLength = chooser.getSelectedFile().length();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    long progress = 0;
                    JacksObject newObject = new JacksObject();
                    JacksMesh newMesh = newObject.addMesh();

                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(
                                new FileReader(chooser.getSelectedFile()));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            progress += line.length() + 2;
                            jProgressBar1.setValue((int) (100 * progress / fileLength));
                            if (line.startsWith("v ")) {
                                float[] newVertex = new float[3];
                                String[] att = line.split("\\s+");
                                newVertex[0] = Float.parseFloat(att[1]);
                                newVertex[1] = Float.parseFloat(att[2]);
                                newVertex[2] = Float.parseFloat(att[3]);
                                newMesh.addVertex(newVertex[0], newVertex[1], newVertex[2]);
                            } else if (line.startsWith("f ")) {
                                String[] att = line.replaceAll("\\s+", " ").split(" ");
                                int[] newFace = new int[att.length - 1];
                                for (int i = 0; i < att.length - 1; i++) {
                                    if (att[i + 1].contains("/")) {
                                        att[i + 1]
                                                = att[i + 1].substring(0, att[i + 1].indexOf("/"));
                                    }
                                    newFace[i] = Integer.parseInt(att[i + 1]) - 1;
                                }
                                JacksFace newJacksFace = newMesh.addFace(newFace);
                                newJacksFace.specularExponent = 16;
                                newJacksFace.specular = .6f;
                            }
                        }

                        reader.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        if (reader != null) {
                            try {
                                reader.close();
                            } catch (IOException ex) {
                            }
                        }
                    }
                    jProgressBar1.setValue(0);
                    glPanel.objectList.clear();
                    glPanel.objectList.add(newObject);
                }
            };

            Thread thread = new Thread(run);
            thread.start();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        chooser.setFileFilter(
                new FileNameExtensionFilter("Image", "jpg", "png", "bmp"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage temp = ImageIO.read(chooser.getSelectedFile());
                BufferedImage hdri = new BufferedImage(temp.getWidth(), temp.getHeight(),
                        BufferedImage.TYPE_3BYTE_BGR);
                hdri.getGraphics().drawImage(temp, 0, 0, null);
                glPanel.addHdri(hdri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        glPanel.removeHdri();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        glPanel.showLightSources = jCheckBox1.isSelected();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        glPanel.backgroundRotationX += Math.PI / 18;
        glPanel.backgroundRotationX = rotate(glPanel.backgroundRotationX,
                (float) Math.PI * 2);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        glPanel.backgroundRotationX = 0;
        glPanel.backgroundRotationY = 0;
        glPanel.backgroundRotationZ = 0;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        glPanel.backgroundRotationX -= Math.PI / 18;
        glPanel.backgroundRotationX = rotate(glPanel.backgroundRotationX,
                (float) Math.PI * 2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        glPanel.backgroundRotationY -= Math.PI / 18;
        glPanel.backgroundRotationY = rotate(glPanel.backgroundRotationY,
                (float) Math.PI * 2);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        glPanel.backgroundRotationY += Math.PI / 18;
        glPanel.backgroundRotationY = rotate(glPanel.backgroundRotationY,
                (float) Math.PI * 2);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String input = JOptionPane.showInputDialog(
                "Type (0 = point, 1 = directional), Energy, R, G, B, X, Y, Z");
        if (input != null) {
            try {
                String[] arguments = input.split(",");
                int type = Integer.parseInt(arguments[0].trim());
                float energy = Float.parseFloat(arguments[1].trim());
                int r = Integer.parseInt(arguments[2].trim());
                int g = Integer.parseInt(arguments[3].trim());
                int b = Integer.parseInt(arguments[4].trim());
                float x = Float.parseFloat(arguments[5].trim());
                float y = Float.parseFloat(arguments[6].trim());
                float z = Float.parseFloat(arguments[7].trim());
                glPanel.lightList.add(new JacksLight(type, energy, r, g, b, x, y, z));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error D: - " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        glPanel.lightList.clear();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String input = JOptionPane.showInputDialog("Enter index of light"
                + " you wanna remove");
        if (input != null) {
            try {
                int index = Integer.parseInt(input.trim());
                glPanel.lightList.remove(index);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error D: - " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            glPanel.lightList.remove(glPanel.lightList.size() - 1);
        } catch (Exception e) {}
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String input = JOptionPane.showInputDialog(
                "Type (0 = point, 1 = directional), Energy, R, G, B, X, Y, Z");
        if (input != null) {
            try {
                String[] arguments = input.split(",");
                int type = Integer.parseInt(arguments[0].trim());
                float energy = Float.parseFloat(arguments[1].trim());
                int r = Integer.parseInt(arguments[2].trim());
                int g = Integer.parseInt(arguments[3].trim());
                int b = Integer.parseInt(arguments[4].trim());
                float x = Float.parseFloat(arguments[5].trim());
                float y = Float.parseFloat(arguments[6].trim());
                float z = Float.parseFloat(arguments[7].trim());
                glPanel.lightList.get(glPanel.lightList.size() - 1)
                        .setAttribute(type, energy, r, g, b, x, y, z);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error D: - " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        glPanel.ambient = (float) jSlider1.getValue() / (float) jSlider1.getMaximum();
    }//GEN-LAST:event_jSlider1StateChanged

    float rotate(float number, float cap) {
        number %= cap;
        if (number < 0) number += cap;
        return number;
    }
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(JacksGL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JacksGL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JacksGL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JacksGL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JacksGL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JacksGLPanel glPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
}
