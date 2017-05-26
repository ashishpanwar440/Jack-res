package cursv;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

public class Cursv extends javax.swing.JFrame {
    final private double BEZIER_RESOLUTION = .1;
    final private float dash[] = {3.0f};
    final int VERTEX_SIZE = 15, EXTRUSION_SIZE = 8;
    Color vertexColor[] = {Color.RED, Color.GREEN, Color.YELLOW};
    ArrayList<Vertex> vertexList = new ArrayList<>();
    Vertex selectedVertex;
    Vertex selectedExtrusion;
    BufferedImage bufferedImage;
    Graphics2D bufferGraphics;
    int prevMouseX, prevMouseY;
    boolean lockSettings = true;

    public Cursv() {
        initComponents();
    }

    void refresh() {
        calculateExtrusion();
        bufferGraphics.setColor(Color.WHITE);
        bufferGraphics.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        drawCurves();
        drawVertices();
    }

    void drawCurves() {
        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.setStroke(new BasicStroke(2));
        for (int i = 0; i < vertexList.size() - 1; i++) {
            if (vertexList.get(i + 1).type != Vertex.TYPE_DISCONNECTED) {
                Vertex currentVertex = vertexList.get(i);
                Vertex nextVertex = vertexList.get(i + 1);
                drawBezier(bufferGraphics, currentVertex.location,
                        currentVertex.getExtrusionLocation(), nextVertex.location);
            }
        }
    }

    Point getIntersection(Point lineA1, Point lineA2, Point lineB1, Point lineB2) {
        if (lineA1.x == lineA2.x && lineB1.x == lineB2.x) {
            return null;
        }
        if (lineA1.y == lineA2.y && lineB1.y == lineB2.y) {
            return null;
        }
        if (lineA1.x == lineA2.x) {
            double alpha = (double) (lineA1.x - lineB1.x) / (lineB2.x - lineB1.x);
            return new Point((int) (lineB1.x + alpha * (lineB2.x - lineB1.x)),
                    (int) (lineB1.y + alpha * (lineB2.y - lineB1.y)));
        }
        if (lineB1.x == lineB2.x) {
            double alpha = (double) (lineB1.x - lineA1.x) / (lineA2.x - lineA1.x);
            return new Point((int) (lineA1.x + alpha * (lineA2.x - lineA1.x)),
                    (int) (lineA1.y + alpha * (lineA2.y - lineA1.y)));
        }
        double a1 = (double) (lineA2.y - lineA1.y) / (lineA2.x - lineA1.x);
        double a2 = (double) (lineB2.y - lineB1.y) / (lineB2.x - lineB1.x);
        double b1 = lineA1.y - a1 * lineA1.x;
        double b2 = lineB1.y - a2 * lineB1.x;
        double newX = (b2 - b1) / (a1 - a2);
        double newY = a1 * newX + b1;
        return new Point((int) (newX), (int) (newY));
    }

    void drawBezier(Graphics2D graphics, Point point1, Point point2, Point point3) {
        int previousX = point1.x;
        int previousY = point1.y;
        for (double t = 0; t <= 1; t += BEZIER_RESOLUTION) {
            int x = (int) ((1 - t) * (1 - t) * point1.x + 2 * (1 - t) * t * point2.x
                    + t * t * point3.x);
            int y = (int) ((1 - t) * (1 - t) * point1.y + 2 * (1 - t) * t * point2.y
                    + t * t * point3.y);
            graphics.drawLine(previousX, previousY, x, y);
            previousX = x;
            previousY = y;
        }
    }

    void drawVertices() {
        for (Vertex vertex : vertexList) {
            bufferGraphics.setColor(Color.BLACK);
            bufferGraphics.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
            Point extrusion = vertex.getExtrusionLocation();
            bufferGraphics.drawLine(vertex.location.x, vertex.location.y,
                    extrusion.x, extrusion.y);
            bufferGraphics.setStroke(new BasicStroke());
            if (vertex != selectedVertex) {
                bufferGraphics.setColor(vertexColor[vertex.type]);
            } else {
                bufferGraphics.setColor(Color.ORANGE);
            }
            bufferGraphics.fillOval(vertex.location.x - VERTEX_SIZE / 2,
                    vertex.location.y - VERTEX_SIZE / 2, VERTEX_SIZE, VERTEX_SIZE);
            bufferGraphics.drawOval(extrusion.x - EXTRUSION_SIZE / 2,
                    extrusion.y - EXTRUSION_SIZE / 2, EXTRUSION_SIZE, EXTRUSION_SIZE);
        }
        jPanel1.getGraphics().drawImage(bufferedImage, 0, 0, null);
    }

    void calculateExtrusion() {
        for (int i = 1; i < vertexList.size(); i++) {
            Vertex currentVertex = vertexList.get(i);
            if (!currentVertex.fold && currentVertex.type != Vertex.TYPE_DISCONNECTED) {
                Vertex previousVertex = vertexList.get(i - 1);
                double inVectorX = 2 * (currentVertex.location.x
                        - previousVertex.getExtrusionLocation().x); // Derivative of Bezier with t = 1
                double inVectorY = 2 * (currentVertex.location.y
                        - previousVertex.getExtrusionLocation().y);
                double vectorLength = Math.sqrt(inVectorX * inVectorX
                        + inVectorY * inVectorY);
                inVectorX /= vectorLength;
                inVectorY /= vectorLength;
                currentVertex.setExtrusionLocation(
                        (int) (currentVertex.location.x + inVectorX
                        * currentVertex.velocity),
                        (int) (currentVertex.location.y + inVectorY
                        * currentVertex.velocity));
                if (i < vertexList.size() - 1) {
                    int deltaX = vertexList.get(i + 1).location.x
                            - currentVertex.location.x;
                    int deltaY = vertexList.get(i + 1).location.y
                            - currentVertex.location.y;
                    Point cutPoint = new Point((int) (currentVertex.location.x
                            + .7 * deltaX), (int) (currentVertex.location.y
                            + .7 * deltaY));
                    Point sidePoint = new Point(cutPoint.x + deltaY, cutPoint.y
                            - deltaX);
                    Point intersection = getIntersection(currentVertex.location,
                            currentVertex.getExtrusionLocation(), cutPoint, sidePoint);
                    if (intersection != null) {
                        double alpha = (double) (intersection.x - currentVertex.location.x)
                                / (currentVertex.getExtrusionLocation().x - currentVertex.location.x);
                        if (alpha > 0 && alpha < 1) {
                            currentVertex.setExtrusionLocation(intersection.x,
                                    intersection.y);
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        pnlEdit = new javax.swing.JPanel();
        pnlVertexEdit = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        spnVelocity = new javax.swing.JSpinner();
        cbxFold = new javax.swing.JCheckBox();
        rdioTypeNormal = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        rdioTypeBegin = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );

        pnlEdit.setBackground(new java.awt.Color(255, 255, 255));
        pnlEdit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlEdit.setLayout(new java.awt.CardLayout());

        pnlVertexEdit.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Velocity:");

        spnVelocity.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 0.1d));
        spnVelocity.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnVelocityStateChanged(evt);
            }
        });

        cbxFold.setText("Fold");
        cbxFold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFoldActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdioTypeNormal);
        rdioTypeNormal.setSelected(true);
        rdioTypeNormal.setText("Normal");
        rdioTypeNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdioTypeNormalActionPerformed(evt);
            }
        });

        jLabel2.setText("Type:");

        buttonGroup1.add(rdioTypeBegin);
        rdioTypeBegin.setText("Begin");
        rdioTypeBegin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdioTypeBeginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlVertexEditLayout = new javax.swing.GroupLayout(pnlVertexEdit);
        pnlVertexEdit.setLayout(pnlVertexEditLayout);
        pnlVertexEditLayout.setHorizontalGroup(
            pnlVertexEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVertexEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlVertexEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlVertexEditLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdioTypeNormal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdioTypeBegin))
                    .addGroup(pnlVertexEditLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnVelocity, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxFold)))
                .addContainerGap(470, Short.MAX_VALUE))
        );
        pnlVertexEditLayout.setVerticalGroup(
            pnlVertexEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVertexEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlVertexEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(spnVelocity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxFold))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlVertexEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdioTypeNormal)
                    .addComponent(jLabel2)
                    .addComponent(rdioTypeBegin))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pnlEdit.add(pnlVertexEdit, "crdVertex");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        pnlEdit.add(jPanel3, "crdNoVertex");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        resize();
    }//GEN-LAST:event_formComponentResized

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        if (SwingUtilities.isLeftMouseButton(evt)) {
            Vertex selected = checkVertexOnClick(evt.getX(), evt.getY());
            if (selected != null) {
                selectedVertex = selected;
            } else if (selectedVertex != null) {
                selectedVertex = null;
            } else {
                vertexList.add(new Vertex(evt.getX(), evt.getY()));
                if (vertexList.size() == 1) {
                    vertexList.get(0).fold = true;
                    vertexList.get(0).type = Vertex.TYPE_DISCONNECTED;
                }
            }
        } else if (SwingUtilities.isRightMouseButton(evt)) {
            Vertex selected = checkVertexOnClick(evt.getX(), evt.getY());
            if (selected != null) {
                vertexList.remove(selected);
            }
        } else if (SwingUtilities.isMiddleMouseButton(evt)) {
            selectedVertex = checkVertexOnClick(evt.getX(), evt.getY());
            if (selectedVertex == null) {
                selectedExtrusion = checkExtrusionOnClick(evt.getX(), evt.getY());
                System.out.println(selectedExtrusion);
            }
        }
        populateVertexEdit();
        refresh();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        if (SwingUtilities.isMiddleMouseButton(evt)) {
            if (selectedVertex != null) {
                selectedVertex.location.x = evt.getX();
                selectedVertex.location.y = evt.getY();
                refresh();
            } else if (selectedExtrusion != null) {
                selectedExtrusion.setExtrusionLocation(evt.getX(), evt.getY());
                refresh();
            }
        }
    }//GEN-LAST:event_jPanel1MouseDragged

    private void cbxFoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFoldActionPerformed
        applyVertexChange();
    }//GEN-LAST:event_cbxFoldActionPerformed

    private void spnVelocityStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnVelocityStateChanged
        applyVertexChange();
    }//GEN-LAST:event_spnVelocityStateChanged

    private void rdioTypeNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdioTypeNormalActionPerformed
        applyVertexChange();
    }//GEN-LAST:event_rdioTypeNormalActionPerformed

    private void rdioTypeBeginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdioTypeBeginActionPerformed
        applyVertexChange();
    }//GEN-LAST:event_rdioTypeBeginActionPerformed

    void applyVertexChange() {
        if (!lockSettings) {
            selectedVertex.velocity = (Double) spnVelocity.getValue();
            selectedVertex.fold = cbxFold.isSelected();
            selectedVertex.type = rdioTypeBegin.isSelected()
                    ? Vertex.TYPE_DISCONNECTED : Vertex.TYPE_NORMAL;
        }
        refresh();
    }

    Vertex checkVertexOnClick(int x, int y) {
        for (Vertex vertex : vertexList) {
            int deltaX = vertex.location.x - x;
            int deltaY = vertex.location.y - y;
            if (deltaX * deltaX + deltaY * deltaY <= VERTEX_SIZE * VERTEX_SIZE) {
                return vertex;
            }
        }
        return null;
    }

    Vertex checkExtrusionOnClick(int x, int y) {
        for (Vertex vertex : vertexList) {
            if (vertex.fold || vertex.type == Vertex.TYPE_DISCONNECTED) {
                Point extrusion = vertex.getExtrusionLocation();
                int deltaX = extrusion.x - x;
                int deltaY = extrusion.y - y;
                if (deltaX * deltaX + deltaY * deltaY <= EXTRUSION_SIZE * EXTRUSION_SIZE) {
                    return vertex;
                }
            }
        }
        return null;
    }

    void resize() {
        bufferedImage = new BufferedImage(jPanel1.getWidth(),
                jPanel1.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        bufferGraphics = (Graphics2D) bufferedImage.getGraphics();
        refresh();
    }

    void populateVertexEdit() {
        if (selectedVertex != null) {
            lockSettings = true;
            ((CardLayout) pnlEdit.getLayout()).show(pnlEdit, "crdVertex");
            spnVelocity.setValue((Double) selectedVertex.velocity);
            cbxFold.setSelected(selectedVertex.fold);
            rdioTypeBegin.setSelected(selectedVertex.type == Vertex.TYPE_DISCONNECTED);
            rdioTypeNormal.setSelected(selectedVertex.type == Vertex.TYPE_NORMAL);
            lockSettings = false;
        } else {
            ((CardLayout) pnlEdit.getLayout()).show(pnlEdit, "crdNoVertex");
        }
    }

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
            java.util.logging.Logger.getLogger(Cursv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cursv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cursv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cursv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cursv cursv = new Cursv();
                cursv.setLocationRelativeTo(null);
                cursv.show();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbxFold;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlVertexEdit;
    private javax.swing.JRadioButton rdioTypeBegin;
    private javax.swing.JRadioButton rdioTypeNormal;
    private javax.swing.JSpinner spnVelocity;
    // End of variables declaration//GEN-END:variables
}
