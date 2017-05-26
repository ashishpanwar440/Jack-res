package cursv;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CursvPanel extends javax.swing.JPanel {

    final private double BEZIER_RESOLUTION = .1;
    final static int VERTEX_SIZE = 15, EXTRUSION_SIZE = 8;
    final private int MAX_RANDOM_VELOCITY = 50;
    final private float dash[] = {3.0f};
    boolean showVertices = true;
    int strokeWidth = 2;
    Color inkColor = Color.BLACK;
    ArrayList<Vertex> vertexList = new ArrayList<>();
    Color vertexColor[] = {Color.RED, Color.GREEN, Color.YELLOW};
    private BufferedImage bufferedImage;
    private Graphics2D bufferGraphics;
    Vertex selectedVertex;
    Vertex selectedExtrusion;
    double extrusionCoefficient = 1;
    double randomVelocity = 0;
    public CursvPanel() {
        initComponents();
    }

    void refresh() {
        calculateExtrusion();
        bufferGraphics.setColor(Color.WHITE);
        bufferGraphics.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        drawCurves();
        drawVertices();
    }

    private void drawCurves() {
        bufferGraphics.setColor(inkColor);
        bufferGraphics.setStroke(new BasicStroke(strokeWidth));
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

    private void drawBezier(Graphics2D graphics, Point point1, Point point2, Point point3) {
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

    private void drawVertices() {
        if (!showVertices) {
            this.getGraphics().drawImage(bufferedImage, 0, 0, null);
            return;
        }
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
        this.getGraphics().drawImage(bufferedImage, 0, 0, null);
    }

    private void calculateExtrusion() {
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
                        * (currentVertex.velocity + randomVelocity * Math.random() * MAX_RANDOM_VELOCITY)),
                        (int) (currentVertex.location.y + inVectorY
                        * (currentVertex.velocity + randomVelocity * Math.random() * MAX_RANDOM_VELOCITY)));
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
            currentVertex.extrusion.x = (int)(extrusionCoefficient
                    * currentVertex.extrusion.x);
            currentVertex.extrusion.y = (int)(extrusionCoefficient
                    * currentVertex.extrusion.y);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        resize();
    }//GEN-LAST:event_formComponentResized

    private void resize() {
        bufferedImage = new BufferedImage(this.getWidth(),
                this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        bufferGraphics = (Graphics2D) bufferedImage.getGraphics();
        drawVertices();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
