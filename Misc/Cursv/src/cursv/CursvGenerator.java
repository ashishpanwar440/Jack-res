package cursv;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CursvGenerator extends javax.swing.JFrame {

    final int MAX_SIZE = 256;
    ArrayList<CursvCharacter> characterList;
    ArrayList<Vertex> addLater = new ArrayList<>();

    int state;

    public CursvGenerator() {
        initComponents();
        crsvPanel.showVertices = false;
        try {
            FileInputStream inStream = new FileInputStream("character-set.crv");
            ObjectInputStream objStream = new ObjectInputStream(inStream);
            characterList = (ArrayList<CursvCharacter>) objStream.readObject();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not read the charcter set");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnRender = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        sldRanVelocity = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        sldSize = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();
        sldRoundness = new javax.swing.JSlider();
        cbxShowVertices = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        sldItalic = new javax.swing.JSlider();
        jSplitPane1 = new javax.swing.JSplitPane();
        crsvPanel = new cursv.CursvPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInputText = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cursv");

        btnRender.setText("Render");
        btnRender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenderActionPerformed(evt);
            }
        });

        jLabel1.setText("Randomize velocity");

        sldRanVelocity.setValue(0);
        sldRanVelocity.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldRanVelocityStateChanged(evt);
            }
        });

        jLabel2.setText("Size");

        sldSize.setMinimum(5);
        sldSize.setToolTipText("");
        sldSize.setValue(10);
        sldSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldSizeStateChanged(evt);
            }
        });

        jLabel4.setText("Roundness");

        sldRoundness.setValue(100);
        sldRoundness.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldRoundnessStateChanged(evt);
            }
        });

        cbxShowVertices.setText("Show Vertices");
        cbxShowVertices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxShowVerticesActionPerformed(evt);
            }
        });

        jLabel5.setText("Italic");

        sldItalic.setValue(0);
        sldItalic.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldItalicStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sldRanVelocity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(btnRender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sldRoundness, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sldItalic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxShowVertices)
                            .addComponent(jLabel2)
                            .addComponent(sldSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sldSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sldRanVelocity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sldRoundness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sldItalic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(cbxShowVertices)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRender)
                .addContainerGap())
        );

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.5);

        javax.swing.GroupLayout crsvPanelLayout = new javax.swing.GroupLayout(crsvPanel);
        crsvPanel.setLayout(crsvPanelLayout);
        crsvPanelLayout.setHorizontalGroup(
            crsvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );
        crsvPanelLayout.setVerticalGroup(
            crsvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        jSplitPane1.setTopComponent(crsvPanel);

        txtInputText.setColumns(20);
        txtInputText.setRows(5);
        txtInputText.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtInputTextCaretUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(txtInputText);

        jSplitPane1.setRightComponent(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSplitPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void render() {
        double cursorX = .2, cursorY = .1;
        crsvPanel.randomVelocity = (double) sldRanVelocity.getValue()
                / sldRanVelocity.getMaximum();
        double italicCoefficient = (double) sldItalic.getValue()
                / sldItalic.getMaximum();
        crsvPanel.extrusionCoefficient = (double) sldRoundness.getValue()
                / sldRoundness.getMaximum();
        String text = txtInputText.getText();
        state = 0;
        int scale = MAX_SIZE * sldSize.getValue() / sldSize.getMaximum();
        crsvPanel.vertexList.clear();
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            // If the character is a word character
            if (isWordChar(character)) {
                CursvCharacter currentCharacter = characterList.get(
                        (Character.toLowerCase(character) - 'a') * 2
                        + (Character.isUpperCase(character) ? 1 : 0));
                int j = 0;
                boolean connectFirst = false;
                if (state == 0) { // At the start of the word.
                    if (currentCharacter.beginPadding < 0) {
                        j -= currentCharacter.beginPadding;
                    }
                    state = 1;
                } else if (state == 1) {
                    if (currentCharacter.beginPadding > 0) {
                        j += currentCharacter.beginPadding;
                    }
                    connectFirst = true;
                }
                boolean firstVertex = true;
                for (; j < currentCharacter.vertexList.size(); j++) {
                    Vertex currentVertex = currentCharacter.vertexList.get(j);
                    double normalizedX = (double) currentVertex.location.x
                            / CursvCharacter.TEXT_UNIT_PIXEL;
                    double normalizedY = (double) currentVertex.location.y
                            / CursvCharacter.TEXT_UNIT_PIXEL;
                    Vertex newVertex = new Vertex(
                            (int) ((cursorX + normalizedX) * scale),
                            (int) ((cursorY + normalizedY) * scale));
                    newVertex.extrusion.x = scale * currentVertex.extrusion.x
                            / CursvCharacter.TEXT_UNIT_PIXEL;
                    newVertex.extrusion.y = scale * currentVertex.extrusion.y
                            / CursvCharacter.TEXT_UNIT_PIXEL;
                    newVertex.velocity = scale * currentVertex.velocity
                            / CursvCharacter.TEXT_UNIT_PIXEL;
                    newVertex.fold = currentVertex.fold;
                    if (connectFirst && firstVertex) {
                        newVertex.type = Vertex.TYPE_NORMAL;
                        firstVertex = false;
                    } else if (!connectFirst && firstVertex) {
                        newVertex.type = Vertex.TYPE_DISCONNECTED;
                        firstVertex = false;
                    }
                    newVertex.location.x += (int) (italicCoefficient * scale
                            * (1 - normalizedY));
                    crsvPanel.vertexList.add(newVertex);
                    if (j == currentCharacter.vertexList.size() - 1
                            || (j < currentCharacter.vertexList.size() - 1)
                            && currentCharacter.vertexList.get(j + 1).type == Vertex.TYPE_DISCONNECTED) {
                        if (((i < text.length() - 1 && !isWordChar(text.charAt(i + 1)))
                                || i == text.length() - 1) && currentCharacter.endPadding < 0) {
                            for (int k = 0; k > currentCharacter.endPadding; k--) {
                                crsvPanel.vertexList.remove(
                                        crsvPanel.vertexList.size() - 1);
                            }
                        }
                        if ((i < text.length() - 1 && isWordChar(text.charAt(i + 1)))
                                && currentCharacter.endPadding > 0) {
                            for (int k = 0; k < currentCharacter.endPadding; k++) {
                                crsvPanel.vertexList.remove(
                                        crsvPanel.vertexList.size() - 1);
                            }
                        }
                        if ((j < currentCharacter.vertexList.size() - 1)
                                && currentCharacter.vertexList.get(j + 1).type
                                == Vertex.TYPE_DISCONNECTED) {
                            for (int k = j + 1; k < currentCharacter.vertexList.size(); k++) {
                                currentVertex = currentCharacter.vertexList.get(k);
                                normalizedX = (double) currentVertex.location.x
                                        / CursvCharacter.TEXT_UNIT_PIXEL;
                                normalizedY = (double) currentVertex.location.y
                                        / CursvCharacter.TEXT_UNIT_PIXEL;
                                Vertex laterVertex = new Vertex(
                                        (int) ((cursorX + normalizedX) * scale),
                                        (int) ((cursorY + normalizedY) * scale));
                                laterVertex.extrusion.x = scale * currentVertex.extrusion.x
                                        / CursvCharacter.TEXT_UNIT_PIXEL;
                                laterVertex.extrusion.y = scale * currentVertex.extrusion.y
                                        / CursvCharacter.TEXT_UNIT_PIXEL;
                                laterVertex.velocity = scale * currentVertex.velocity
                                        / CursvCharacter.TEXT_UNIT_PIXEL;
                                laterVertex.fold = currentVertex.fold;
                                laterVertex.type = currentVertex.type;
                                laterVertex.location.x += (int) (italicCoefficient * scale
                                        * (1 - normalizedY));
                                addLater.add(laterVertex);
                            }
                            break;
                        }
                    }
                }
                cursorX += currentCharacter.characterWidth;

            } else {
                if (character != '\n') {
                    cursorX += .3;
                } else {
                    cursorX = .2;
                    cursorY++;
                }
                state = 0;
                for (Vertex vertex : addLater) {
                    crsvPanel.vertexList.add(vertex);
                }
                addLater.clear();
            }
            if ((cursorX + 1) * scale >= crsvPanel.getWidth()) {
                cursorX = .2;
                cursorY++;
                state = 0;
                for (Vertex vertex : addLater) {
                    crsvPanel.vertexList.add(vertex);
                }
                addLater.clear();
            }
        }
        for (Vertex vertex : addLater) {
            crsvPanel.vertexList.add(vertex);
        }
        addLater.clear();
        crsvPanel.refresh();
    }

    boolean isWordChar(char c) {
        return Character.toLowerCase(c) >= 'a'
                && Character.toLowerCase(c) <= 'z';
    }

    private void btnRenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenderActionPerformed
        render();
    }//GEN-LAST:event_btnRenderActionPerformed

    private void txtInputTextCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtInputTextCaretUpdate
        render();
    }//GEN-LAST:event_txtInputTextCaretUpdate

    private void cbxShowVerticesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxShowVerticesActionPerformed
        crsvPanel.showVertices = cbxShowVertices.isSelected();
        render();
    }//GEN-LAST:event_cbxShowVerticesActionPerformed

    private void sldSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldSizeStateChanged
        render();
    }//GEN-LAST:event_sldSizeStateChanged

    private void sldRanVelocityStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldRanVelocityStateChanged
        render();
    }//GEN-LAST:event_sldRanVelocityStateChanged

    private void sldRoundnessStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldRoundnessStateChanged
        render();
    }//GEN-LAST:event_sldRoundnessStateChanged

    private void sldItalicStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldItalicStateChanged
        render();
    }//GEN-LAST:event_sldItalicStateChanged
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
            java.util.logging.Logger.getLogger(CursvGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CursvGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CursvGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CursvGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CursvGenerator cursv = new CursvGenerator();
                cursv.setLocationRelativeTo(null);
                cursv.show();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRender;
    private javax.swing.JCheckBox cbxShowVertices;
    private cursv.CursvPanel crsvPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSlider sldItalic;
    private javax.swing.JSlider sldRanVelocity;
    private javax.swing.JSlider sldRoundness;
    private javax.swing.JSlider sldSize;
    private javax.swing.JTextArea txtInputText;
    // End of variables declaration//GEN-END:variables
}
