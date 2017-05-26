package cursv;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class CursvDesigner extends javax.swing.JFrame {

    boolean lockSettings = false;
    ArrayList<CursvCharacter> characterList = new ArrayList<>();
    CursvCharacter currentCharacter;

    public CursvDesigner() {
        initComponents();
        try {
            FileInputStream inStream = new FileInputStream("character-set.crv");
            ObjectInputStream objStream = new ObjectInputStream(inStream);
            characterList = (ArrayList<CursvCharacter>) objStream.readObject();
        } catch (Exception e) {
            System.out.println("doot");
            for (char i = 'a'; i <= 'z'; i++) {
                characterList.add(new CursvCharacter(i));
                characterList.add(new CursvCharacter((char) (i + 'A' - 'a')));
            }
        }
        refreshCharacterList();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCharacter = new javax.swing.JList<String>();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pnlVertexEdit = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        spnVelocity = new javax.swing.JSpinner();
        cbxFold = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        rdioTypeNormal = new javax.swing.JRadioButton();
        rdioTypeDisconnected = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        sldCharacterWidth = new javax.swing.JSlider();
        spnBeginPadding = new javax.swing.JSpinner();
        spnEndPadding = new javax.swing.JSpinner();
        cbxShowVertices = new javax.swing.JCheckBox();
        crsvPanel = new cursv.CursvPanel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hand");

        lstCharacter.setModel(new DefaultListModel<String>());
        lstCharacter.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstCharacterValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstCharacter);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlVertexEdit.setLayout(new java.awt.CardLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Velocity:");

        spnVelocity.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        spnVelocity.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnVelocityStateChanged(evt);
            }
        });

        cbxFold.setBackground(new java.awt.Color(255, 255, 255));
        cbxFold.setText("Fold");
        cbxFold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFoldActionPerformed(evt);
            }
        });

        jLabel2.setText("Type:");

        rdioTypeNormal.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdioTypeNormal);
        rdioTypeNormal.setSelected(true);
        rdioTypeNormal.setText("Normal");
        rdioTypeNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdioTypeNormalActionPerformed(evt);
            }
        });

        rdioTypeDisconnected.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdioTypeDisconnected);
        rdioTypeDisconnected.setText("Disconnected");
        rdioTypeDisconnected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdioTypeDisconnectedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdioTypeNormal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdioTypeDisconnected))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnVelocity, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxFold)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(spnVelocity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxFold))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdioTypeNormal)
                    .addComponent(jLabel2)
                    .addComponent(rdioTypeDisconnected))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pnlVertexEdit.add(jPanel3, "crdVertex");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );

        pnlVertexEdit.add(jPanel4, "crdNoVertex");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Letter Width:");

        sldCharacterWidth.setBackground(new java.awt.Color(255, 255, 255));
        sldCharacterWidth.setMinimum(1);
        sldCharacterWidth.setValue(100);
        sldCharacterWidth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldCharacterWidthStateChanged(evt);
            }
        });

        spnBeginPadding.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnBeginPaddingStateChanged(evt);
            }
        });

        spnEndPadding.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnEndPaddingStateChanged(evt);
            }
        });

        cbxShowVertices.setBackground(new java.awt.Color(255, 255, 255));
        cbxShowVertices.setSelected(true);
        cbxShowVertices.setText("Show Vertices");
        cbxShowVertices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxShowVerticesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sldCharacterWidth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(spnBeginPadding, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(spnEndPadding, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxShowVertices)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sldCharacterWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnBeginPadding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnEndPadding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxShowVertices)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(pnlVertexEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlVertexEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel2, "crdCharacter");

        crsvPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                crsvPanelMouseDragged(evt);
            }
        });
        crsvPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                crsvPanelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout crsvPanelLayout = new javax.swing.GroupLayout(crsvPanel);
        crsvPanel.setLayout(crsvPanelLayout);
        crsvPanelLayout.setHorizontalGroup(
            crsvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        crsvPanelLayout.setVerticalGroup(
            crsvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crsvPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crsvPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void spnVelocityStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnVelocityStateChanged
        applyVertexChange();
    }//GEN-LAST:event_spnVelocityStateChanged

    private void cbxFoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFoldActionPerformed
        applyVertexChange();
    }//GEN-LAST:event_cbxFoldActionPerformed

    private void rdioTypeNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdioTypeNormalActionPerformed
        applyVertexChange();
    }//GEN-LAST:event_rdioTypeNormalActionPerformed

    private void rdioTypeDisconnectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdioTypeDisconnectedActionPerformed
        applyVertexChange();
    }//GEN-LAST:event_rdioTypeDisconnectedActionPerformed

    void refresh() {
        crsvPanel.refresh();
        Graphics2D g = (Graphics2D) crsvPanel.getGraphics();
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.cyan);
        g.drawLine(0, 0, CursvCharacter.TEXT_UNIT_PIXEL, 0);
        g.drawLine(0, CursvCharacter.TEXT_UNIT_PIXEL, CursvCharacter.TEXT_UNIT_PIXEL, CursvCharacter.TEXT_UNIT_PIXEL);
        g.setStroke(new BasicStroke(1));
        g.drawLine(0, CursvCharacter.TEXT_UNIT_PIXEL / 2,
                CursvCharacter.TEXT_UNIT_PIXEL, CursvCharacter.TEXT_UNIT_PIXEL / 2);
        g.drawLine(CursvCharacter.TEXT_UNIT_PIXEL, 0,
                CursvCharacter.TEXT_UNIT_PIXEL, CursvCharacter.TEXT_UNIT_PIXEL);
        g.drawLine(0, 0, 0, CursvCharacter.TEXT_UNIT_PIXEL);
        g.setColor(Color.MAGENTA);
        int x = (int) (currentCharacter.characterWidth * CursvCharacter.TEXT_UNIT_PIXEL);
        g.drawLine(x, 0, x, CursvCharacter.TEXT_UNIT_PIXEL);
    }

    private void crsvPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crsvPanelMousePressed
        if (crsvPanel.vertexList == null) {
            return;
        }
        if (SwingUtilities.isLeftMouseButton(evt)) {
            Vertex selected = checkVertexOnClick(evt.getX(), evt.getY());
            if (selected != null) {
                crsvPanel.selectedVertex = selected;
            } else if (crsvPanel.selectedVertex != null) {
                crsvPanel.selectedVertex = null;
            } else {
                crsvPanel.vertexList.add(new Vertex(evt.getX(), evt.getY()));
                if (crsvPanel.vertexList.size() == 1) {
                    crsvPanel.vertexList.get(0).fold = true;
                    crsvPanel.vertexList.get(0).type = Vertex.TYPE_DISCONNECTED;
                }
            }
        } else if (SwingUtilities.isRightMouseButton(evt)) {
            Vertex selected = checkVertexOnClick(evt.getX(), evt.getY());
            if (selected != null) {
                crsvPanel.vertexList.remove(selected);
            }
        } else if (SwingUtilities.isMiddleMouseButton(evt)) {
            crsvPanel.selectedVertex = checkVertexOnClick(evt.getX(), evt.getY());
            if (crsvPanel.selectedVertex == null) {
                crsvPanel.selectedExtrusion = checkExtrusionOnClick(evt.getX(), evt.getY());
            }
        }
        populateVertexEdit();
        refresh();
    }//GEN-LAST:event_crsvPanelMousePressed

    private void crsvPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crsvPanelMouseDragged
        if (SwingUtilities.isMiddleMouseButton(evt)) {
            if (crsvPanel.selectedVertex != null) {
                crsvPanel.selectedVertex.location.x = evt.getX();
                crsvPanel.selectedVertex.location.y = evt.getY();
                refresh();
            } else if (crsvPanel.selectedExtrusion != null) {
                crsvPanel.selectedExtrusion.setExtrusionLocation(evt.getX(), evt.getY());
                refresh();
            }
        }
    }//GEN-LAST:event_crsvPanelMouseDragged

    private void lstCharacterValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstCharacterValueChanged
        int index = lstCharacter.getSelectedIndex();
        if (index >= 0) {
            currentCharacter = characterList.get(index);
            crsvPanel.vertexList = currentCharacter.vertexList;
            populateCharacterEdit();
            refresh();
        }
    }//GEN-LAST:event_lstCharacterValueChanged

    private void sldCharacterWidthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldCharacterWidthStateChanged
        applyCharacterSettings();
    }//GEN-LAST:event_sldCharacterWidthStateChanged

    private void spnBeginPaddingStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnBeginPaddingStateChanged
        applyCharacterSettings();
    }//GEN-LAST:event_spnBeginPaddingStateChanged

    private void spnEndPaddingStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnEndPaddingStateChanged
        applyCharacterSettings();
    }//GEN-LAST:event_spnEndPaddingStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        save();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cbxShowVerticesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxShowVerticesActionPerformed
        crsvPanel.showVertices = cbxShowVertices.isSelected();
        refresh();
    }//GEN-LAST:event_cbxShowVerticesActionPerformed

    void save() {
        try {
            FileOutputStream foutStream = new FileOutputStream("character-set.crv");
            ObjectOutputStream objOutStream = new ObjectOutputStream(foutStream);
            objOutStream.writeObject(characterList);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saving file!");
        }
    }

    Vertex checkVertexOnClick(int x, int y) {
        for (Vertex vertex : crsvPanel.vertexList) {
            int deltaX = vertex.location.x - x;
            int deltaY = vertex.location.y - y;
            if (deltaX * deltaX + deltaY * deltaY <= CursvPanel.VERTEX_SIZE * CursvPanel.VERTEX_SIZE) {
                return vertex;
            }
        }
        return null;
    }

    Vertex checkExtrusionOnClick(int x, int y) {
        for (Vertex vertex : crsvPanel.vertexList) {
            if (vertex.fold || vertex.type == Vertex.TYPE_DISCONNECTED) {
                Point extrusion = vertex.getExtrusionLocation();
                int deltaX = extrusion.x - x;
                int deltaY = extrusion.y - y;
                if (deltaX * deltaX + deltaY * deltaY <= CursvPanel.EXTRUSION_SIZE
                        * CursvPanel.EXTRUSION_SIZE) {
                    return vertex;
                }
            }
        }
        return null;
    }

    void populateVertexEdit() {
        if (crsvPanel.selectedVertex != null) {
            lockSettings = true;
            ((CardLayout) pnlVertexEdit.getLayout()).show(pnlVertexEdit, "crdVertex");
            spnVelocity.setValue((Double) crsvPanel.selectedVertex.velocity);
            cbxFold.setSelected(crsvPanel.selectedVertex.fold);
            rdioTypeDisconnected.setSelected(crsvPanel.selectedVertex.type == Vertex.TYPE_DISCONNECTED);
            rdioTypeNormal.setSelected(crsvPanel.selectedVertex.type == Vertex.TYPE_NORMAL);
            lockSettings = false;
        } else {
            ((CardLayout) pnlVertexEdit.getLayout()).show(pnlVertexEdit, "crdNoVertex");
        }
    }

    void populateCharacterEdit() {
        lockSettings = true;
        sldCharacterWidth.setValue((int) (currentCharacter.characterWidth
                * sldCharacterWidth.getMaximum()));
        spnBeginPadding.setValue(currentCharacter.beginPadding);
        spnEndPadding.setValue(currentCharacter.endPadding);
        lockSettings = false;
    }

    void applyVertexChange() {
        if (!lockSettings) {
            crsvPanel.selectedVertex.velocity = (Double) spnVelocity.getValue();
            crsvPanel.selectedVertex.fold = cbxFold.isSelected();
            crsvPanel.selectedVertex.type = rdioTypeDisconnected.isSelected()
                    ? Vertex.TYPE_DISCONNECTED : Vertex.TYPE_NORMAL;
        }
        refresh();
    }

    void applyCharacterSettings() {
        if (!lockSettings) {
            currentCharacter.characterWidth
                    = (double) sldCharacterWidth.getValue()
                    / sldCharacterWidth.getMaximum();
            currentCharacter.beginPadding = (Integer) spnBeginPadding.getValue();
            currentCharacter.endPadding = (Integer) spnEndPadding.getValue();
        }
        refresh();
    }

    void refreshCharacterList() {
        DefaultListModel<String> model
                = (DefaultListModel<String>) lstCharacter.getModel();
        for (CursvCharacter character : characterList) {
            model.addElement(character.characterName + "");
        }
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
            java.util.logging.Logger.getLogger(CursvDesigner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CursvDesigner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CursvDesigner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CursvDesigner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CursvDesigner cursv = new CursvDesigner();
                cursv.setLocationRelativeTo(null);
                cursv.show();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbxFold;
    private javax.swing.JCheckBox cbxShowVertices;
    private cursv.CursvPanel crsvPanel;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstCharacter;
    private javax.swing.JPanel pnlVertexEdit;
    private javax.swing.JRadioButton rdioTypeDisconnected;
    private javax.swing.JRadioButton rdioTypeNormal;
    private javax.swing.JSlider sldCharacterWidth;
    private javax.swing.JSpinner spnBeginPadding;
    private javax.swing.JSpinner spnEndPadding;
    private javax.swing.JSpinner spnVelocity;
    // End of variables declaration//GEN-END:variables
}
