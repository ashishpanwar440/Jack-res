
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class JacksGLPanel extends javax.swing.JPanel {

    int numberOfThreads = 4;
    float deltaTime = 0;
    float cameraX = 0;
    float cameraY = 0;
    float cameraZ = 0;

    float cameraRotationX = 0;
    float cameraRotationY = 0;
    float cameraRotationZ = 0;

    float backgroundRotationX = 0;
    float backgroundRotationY = 0;
    float backgroundRotationZ = 0;
    float ambient = 0;
    
    boolean showLightSources = true;

    float cameraAngle = (float) Math.PI * 60.0f / 180.0f;
    float lowClipping = 0f;
    private BufferedImage rendered;
    private BufferedImage hdri;
    private Raster renderedRaster;
    private byte[] renderedBytes;
    private byte[] hdriBytes;
    private LinkedHashMap<JacksVertex, Point> locationMap = new LinkedHashMap<>();
    private LinkedHashMap<JacksVertex, Float> depthMap = new LinkedHashMap<>();

    private float tempFloat;
    private float vYLength;
    private float vXLength;
    private JacksVector v = new JacksVector();
    private JacksVector vX = new JacksVector();
    private JacksVector vY = new JacksVector();
    private JacksVector incrementX = new JacksVector();
    private JacksVector incrementY = new JacksVector();

    private float luminance;
    private float luminanceS;
    private float luminanceR;
    private float luminanceG;
    private float luminanceB;
    private float fromDepth;
    private float toDepth;
    private float currentDepth;
    private int from;
    private int to;
    private int increment;
    private int deltaY01;
    private int deltaY02;
    private int deltaY12;
    private Point point[] = new Point[3];
    private float depth[] = new float[3];
    private JacksVector normal = new JacksVector();
    private JacksVector tempVector = new JacksVector();
    private JacksVertex center = new JacksVertex();
    private JacksVector toCenter = new JacksVector();
    private JacksVertex lightVertex = new JacksVertex();
    private JacksVector lightVector = new JacksVector();
    private JacksVector specularVector = new JacksVector();
    private float lightDistance;
    private int panelWidth;
    private int panelHeight;

    float cameraHeight;
    float cameraWidth;
    JacksOrigin tempOrigin = new JacksOrigin();
    int tempX;
    int tempY;
    int tempZ;

    private float[][] depthBuffer;
    private JacksOrigin origin = new JacksOrigin();

    ArrayList<JacksObject> objectList = new ArrayList<>();
    ArrayList<JacksLight> lightList = new ArrayList<>();

    private Thread updateThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                deltaTime = update();
            }
        }
    });

    public JacksGLPanel() {
        initComponents();
        resize();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resize();
            }
        });
    }

    void addHdri(BufferedImage hdri) {
        hdriBytes = ((DataBufferByte) hdri.getData().getDataBuffer())
                .getData();
        this.hdri = hdri;
    }

    void removeHdri() {
        hdri = null;
    }

    void resize() {
        if (this.getWidth() > 0 && this.getHeight() > 0) {
            panelWidth = this.getWidth();
            panelHeight = this.getHeight();
            depthBuffer = new float[panelHeight][panelWidth];
            rendered = new BufferedImage(panelWidth, panelHeight,
                    BufferedImage.TYPE_3BYTE_BGR);
            renderedBytes = ((DataBufferByte) rendered.getData().getDataBuffer())
                    .getData();
            renderedRaster = Raster.createRaster(rendered.getSampleModel(), new DataBufferByte(renderedBytes, renderedBytes.length), null);
        }
    }

    long update() {
        long startTime = System.nanoTime();
        if (rendered != null && renderedBytes != null) {
            try {
                clearDepthBuffer();
                cameraHeight = 2 * (float) Math.tan(cameraAngle / 2);
                cameraWidth = cameraHeight * panelWidth / panelHeight;

                if (hdri != null) {
                    renderBackground();
                } else {
                    Arrays.fill(renderedBytes, (byte) 0);
                }

                for (JacksObject object : objectList) {
                    tempOrigin.copyAttribute(origin);
                    tempOrigin.translate(object.x, object.y, object.z);

                    tempOrigin.rotate(cameraRotationX, 0, 0);
                    tempOrigin.rotate(0, 0, cameraRotationZ);
                    tempOrigin.rotate(0, cameraRotationY, 0);
                    tempOrigin.rotate(object.rotateX, object.rotateY, object.rotateZ);
                    tempOrigin.rotate(0, -cameraRotationY, 0);
                    tempOrigin.rotate(0, 0, -cameraRotationZ);
                    tempOrigin.rotate(-cameraRotationX, 0, 0);

                    for (JacksVertex vertex : object.mesh.vertexList) {
                        JacksVertex current = vertex.clone();
                        current.project(tempOrigin);
                        locationMap.put(vertex, new Point(
                                (int) (panelWidth * (.5 - current.x
                                / (current.z * cameraWidth))),
                                (int) (panelHeight * (.5 + current.y
                                / (current.z * cameraHeight)))));
                        depthMap.put(vertex, -current.z);
                    }
                }

                for (int objectIndex = 0; objectIndex < objectList.size(); objectIndex++) {
                    JacksObject object = objectList.get(objectIndex);
                    tempOrigin.copyAttribute(origin);
                    tempOrigin.translate(object.x, object.y, object.z);
                    tempOrigin.rotate(cameraRotationX, 0, 0);
                    tempOrigin.rotate(0, 0, cameraRotationZ);
                    tempOrigin.rotate(0, cameraRotationY, 0);
                    tempOrigin.rotate(object.rotateX, object.rotateY, object.rotateZ);
                    tempOrigin.rotate(0, -cameraRotationY, 0);
                    tempOrigin.rotate(0, 0, -cameraRotationZ);
                    tempOrigin.rotate(-cameraRotationX, 0, 0);

                    for (JacksFace face : object.mesh.faceList) {
                        if (face.vertexList.length >= 3) {
                            center.setXYZ(0, 0, 0);
                            normal.copyXYZ(face.normal);
                            normal.project(tempOrigin);
                            for (JacksVertex faceVertex : face.vertexList) {
                                center.x += faceVertex.x;
                                center.y += faceVertex.y;
                                center.z += faceVertex.z;
                            }
                            center.x /= face.vertexList.length;
                            center.y /= face.vertexList.length;
                            center.z /= face.vertexList.length;
                            center.project(origin);
                            toCenter.setXYZ(center.x, center.y, center.z);
                            if (toCenter.dotProduct(normal) > 0) {
                                continue; // Skip back face.
                            }
                            toCenter.normalize();

                            luminanceR = ambient;
                            luminanceG = ambient;
                            luminanceB = ambient;
                            if (lightList.size() > 0) {
                                for (JacksLight light : lightList) {
                                    if (light.type == JacksLight.TYPE_POINT) {
                                        lightVertex.setXYZ(light.x, light.y, light.z);
                                        lightVertex.project(origin);
                                        lightVector.setXYZ(lightVertex, center);
                                        specularVector.setXYZ(lightVertex, center);
                                        lightDistance = lightVector.x * lightVector.x
                                                + lightVector.y * lightVector.y
                                                + lightVector.z * lightVector.z;

                                        luminance = -lightVector.dotProduct(normal)
                                                / lightDistance;

                                        if (luminance > 0) {
                                            luminanceR += light.energy * (float) light.r / 255.0f * luminance;
                                            luminanceG += light.energy * (float) light.g / 255.0f * luminance;
                                            luminanceB += light.energy * (float) light.b / 255.0f * luminance;
                                        }
                                        if (lightVector.dotProduct(normal) < 0) {
                                            lightVector.normalize();
                                            tempVector.copyXYZ(normal);
                                            tempVector.multiply(-2 * lightVector.dotProduct(normal));
                                            lightVector.add(tempVector);

                                            luminanceS = -lightVector.dotProduct(toCenter);
                                            if (luminanceS > 0) {
                                                tempFloat = luminanceS;
                                                for (int i = 1; i <= face.specularExponent; i++)
                                                    luminanceS *= tempFloat;
                                                luminanceR += face.specular * (float) face.rS / 255.0f * luminanceS;
                                                luminanceG += face.specular * (float) face.gS / 255.0f * luminanceS;
                                                luminanceB += face.specular * (float) face.bS / 255.0f * luminanceS;
                                            }
                                        }
                                    } else if (light.type == JacksLight.TYPE_DIRECTIONAL) {
                                        lightVector.copyXYZ(light.direction);
                                        lightVector.project(origin);
                                        lightVector.normalize();
                                        luminance = -lightVector.dotProduct(normal);
                                        if (luminance > 0) {
                                            luminanceR += light.energy * (float) light.r / 255.0f * luminance;
                                            luminanceG += light.energy * (float) light.g / 255.0f * luminance;
                                            luminanceB += light.energy * (float) light.b / 255.0f * luminance;
                                        }
                                        if (lightVector.dotProduct(normal) < 0) {
                                            tempVector.copyXYZ(normal);
                                            tempVector.multiply(-2 * lightVector.dotProduct(normal));
                                            lightVector.add(tempVector);

                                            luminanceS = -lightVector.dotProduct(toCenter);
                                            if (luminanceS > 0) {
                                                tempFloat = luminanceS;
                                                for (int i = 1; i <= face.specularExponent; i++)
                                                    luminanceS *= tempFloat;
                                                luminanceR += face.specular * (float) face.rS / 255.0f * luminanceS;
                                                luminanceG += face.specular * (float) face.gS / 255.0f * luminanceS;
                                                luminanceB += face.specular * (float) face.bS / 255.0f * luminanceS;
                                            }
                                        }
                                    }
                                }
                            } else {
                                luminanceR += -toCenter.dotProduct(normal);
                                luminanceG += -toCenter.dotProduct(normal);
                                luminanceB += -toCenter.dotProduct(normal);
                            }

                            if (luminanceR < 0) {
                                luminanceR = 0;
                            }
                            if (luminanceG < 0) {
                                luminanceG = 0;
                            }
                            if (luminanceB < 0) {
                                luminanceB = 0;
                            }
                            if (luminanceR > 1) {
                                luminanceR = 1;
                            }
                            if (luminanceG > 1) {
                                luminanceG = 1;
                            }
                            if (luminanceB > 1) {
                                luminanceB = 1;
                            }

                            for (int i = 0; i < face.vertexList.length; i += 2) {
                                //System.out.println("vertex!");
                                point[0] = locationMap.get(face.vertexList[i]);
                                point[1] = locationMap.get(face.vertexList[(i + 1) % face.vertexList.length]);
                                point[2] = locationMap.get(face.vertexList[(i + 2) % face.vertexList.length]);

                                if (point[0].y == point[1].y && point[1].y == point[2].y) {
                                    continue;
                                }

                                if (Math.abs((point[0].x * (point[2].y - point[1].y)
                                        + point[1].x * (point[2].y - point[0].y)
                                        + point[2].x * (point[0].y - point[1].y)) / 2) > panelWidth * panelHeight) {
                                    continue;
                                }
                                depth[0] = depthMap.get(face.vertexList[i]);
                                depth[1] = depthMap.get(face.vertexList[(i + 1) % face.vertexList.length]);
                                depth[2] = depthMap.get(face.vertexList[(i + 2) % face.vertexList.length]);

                                if (depth[0] < lowClipping || depth[1] < lowClipping || depth[2] < lowClipping) {
                                    continue;
                                }
                                int order[] = {0, 1, 2};

                                int temp;
                                boolean changed = true;
                                while (changed) {
                                    changed = false;
                                    if (point[order[0]].y > point[order[1]].y) {
                                        changed = true;
                                        temp = order[0];
                                        order[0] = order[1];
                                        order[1] = temp;
                                    }
                                    if (point[order[1]].y > point[order[2]].y) {
                                        changed = true;
                                        temp = order[1];
                                        order[1] = order[2];
                                        order[2] = temp;
                                    }
                                }
                                // System.out.println(Arrays.toString(order));

                                deltaY01 = point[order[1]].y - point[order[0]].y;
                                deltaY02 = point[order[2]].y - point[order[0]].y;
                                deltaY12 = point[order[2]].y - point[order[1]].y;

                                //First half
                                for (int y = point[order[0]].y; y <= point[order[2]].y; y++) {
                                    toDepth = point[order[2]].y == point[order[0]].y
                                            ? depth[order[0]]
                                            : depth[order[0]]
                                            + (depth[order[2]] - depth[order[0]])
                                            * (y - point[order[0]].y)
                                            / deltaY02;
                                    to = point[order[2]].y == point[order[0]].y
                                            ? point[order[0]].x
                                            : point[order[0]].x
                                            + (point[order[2]].x - point[order[0]].x)
                                            * (y - point[order[0]].y)
                                            / deltaY02;
                                    if (y < point[order[1]].y) {
                                        fromDepth = point[order[1]].y == point[order[0]].y
                                                ? depth[order[0]]
                                                : depth[order[0]]
                                                + (depth[order[1]] - depth[order[0]])
                                                * (y - point[order[0]].y)
                                                / deltaY01;

                                        from = point[order[1]].y == point[order[0]].y
                                                ? point[order[0]].x
                                                : point[order[0]].x
                                                + (point[order[1]].x - point[order[0]].x)
                                                * (y - point[order[0]].y)
                                                / deltaY01;

                                    } else {
                                        // Second half
                                        fromDepth = point[order[2]].y == point[order[1]].y
                                                ? depth[order[1]]
                                                : depth[order[1]]
                                                + (depth[order[2]] - depth[order[1]])
                                                * (y - point[order[1]].y)
                                                / deltaY12;
                                        from = point[order[2]].y == point[order[1]].y
                                                ? point[order[1]].x
                                                : point[order[1]].x
                                                + (point[order[2]].x - point[order[1]].x)
                                                * (y - point[order[1]].y)
                                                / deltaY12;
                                    }
                                    increment = from > to ? -1 : 1;
                                    for (int x = from; x != to; x += increment) {
                                        if (x > 0 && x < rendered.getWidth()
                                                && y > 0 && y < rendered.getHeight()) {
                                            currentDepth = from == to
                                                    ? fromDepth
                                                    : fromDepth
                                                    + (toDepth - fromDepth)
                                                    * (float) (x - from)
                                                    / (float) (to - from);
                                            if (currentDepth < depthBuffer[y][x]) {
                                                renderedBytes[(y * panelWidth + x) * 3]
                                                        = (byte) (luminanceB * 255);
                                                renderedBytes[(y * panelWidth + x) * 3 + 1]
                                                        = (byte) (luminanceG * 255);
                                                renderedBytes[(y * panelWidth + x) * 3 + 2]
                                                        = (byte) (luminanceR * 255);
                                                depthBuffer[y][x] = currentDepth;
                                            }
                                        }
                                    }
                                    if (to > 0 && to < rendered.getWidth()
                                            && y > 0 && y < rendered.getHeight()) {
                                        if (toDepth < depthBuffer[y][to]) {
                                            renderedBytes[(y * panelWidth + to) * 3]
                                                    = (byte) (luminanceB * 255);
                                            renderedBytes[(y * panelWidth + to) * 3 + 1]
                                                    = (byte) (luminanceG * 255);
                                            renderedBytes[(y * panelWidth + to) * 3 + 2]
                                                    = (byte) (luminanceR * 255);
                                            depthBuffer[y][to] = toDepth;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                rendered.setData(renderedRaster);
                if (showLightSources) {
                    for (JacksLight light : lightList) {
                        lightVertex.setXYZ(light.x, light.y, light.z);
                        lightVertex.project(origin);
                        Point lightPoint = new Point(
                                (int) (panelWidth * (.5 - lightVertex.x
                                        / (lightVertex.z * cameraWidth))),
                                (int) (panelHeight * (.5 + lightVertex.y
                                        / (lightVertex.z * cameraHeight))));
                        Graphics g = rendered.getGraphics();
                        g.setColor(new Color(light.r, light.g, light.b));
                        g.drawOval(lightPoint.x - 10, lightPoint.y - 10, 20, 20);
                        if (light.type == 1) {
                            tempVector.copyXYZ(light.direction);
                            tempVector.project(origin);
                            
                            Point secondPoint = new Point(
                                    (int) (panelWidth * (.5 - (lightVertex.x + tempVector.x)
                                            / ((lightVertex.z + tempVector.z) * cameraWidth))),
                                    (int) (panelHeight * (.5 + (lightVertex.y + tempVector.y)
                                            / ((lightVertex.z + tempVector.z) * cameraHeight))));
                            g.drawLine(lightPoint.x, lightPoint.y, secondPoint.x, secondPoint.y);
                        }
                    }
                }
                this.getGraphics().drawImage(rendered, 0, 0, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        long stopTime = System.nanoTime();
        return stopTime - startTime;
    }

    private void clearDepthBuffer() {
        for (float[] depth : depthBuffer) {
            Arrays.fill(depth, Float.POSITIVE_INFINITY);
        }
    }

    void updateOrigin() {
        origin.reset();
        origin.rotate(0, -cameraRotationY, 0);
        origin.rotate(0, 0, -cameraRotationZ);
        origin.rotate(-cameraRotationX, 0, 0);
        origin.translate(-cameraX, -cameraY, -cameraZ);
    }

    void startUpdating() {
        updateThread.start();
    }

    void stopUpdating() {
        updateThread.stop();
    }

    private void renderBackground() {
        if (hdri != null) {
            final int hdriHeight = hdri.getHeight();
            final int hdriWidth = hdri.getWidth();

            vYLength = (float) Math.tan(cameraAngle / 2);
            vXLength = vYLength * (float) panelWidth / (float) panelHeight;

            v.setXYZ(0, 0, -1);

            vX.setXYZ(-vXLength, 0, 0);
            
            v.rotateX(cameraRotationX);
            v.rotateZ(cameraRotationZ);
            v.rotateY(cameraRotationY);
            vX.rotateX(cameraRotationX);
            vX.rotateZ(cameraRotationZ);
            vX.rotateY(cameraRotationY);

            v.rotateY(backgroundRotationY);
            v.rotateZ(backgroundRotationZ);
            v.rotateX(backgroundRotationX);
            vX.rotateY(backgroundRotationY);
            vX.rotateZ(backgroundRotationZ);
            vX.rotateX(backgroundRotationX);
            
            vY.copyXYZ(JacksVector.crossProduct(v, vX));
            vY.normalize();
            vY.multiply(vYLength);
            incrementX.copyXYZ(vX);
            incrementY.copyXYZ(vY);
            incrementX.multiply(-2.0f / (float) panelWidth);
            incrementY.multiply(-2.0f / (float) panelHeight);
            LinkedList<Thread> threadList = new LinkedList();
            int indexIncrement = (renderedBytes.length / 3) / numberOfThreads;
            for (int i = 0; i < renderedBytes.length / 3;
                    i += indexIncrement) {
                final int startIndex = i;
                int candidateStopIndex = i + indexIncrement - 1;
                if (candidateStopIndex >= panelWidth * panelHeight - 1) {
                    candidateStopIndex = panelWidth * panelHeight - 1;
                }
                final int stopIndex = candidateStopIndex;
                final int startX = i % panelWidth;
                final int startY = i / panelWidth;
                final JacksVector threadVector = JacksVector.addNewVector(v, vX, vY);
                threadVector.add(JacksVector.multiply(startX, incrementX));
                threadVector.add(JacksVector.multiply(startY, incrementY));

                threadList.add(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JacksVector currentVector = threadVector.clone();
                        for (int j = startIndex; j <= stopIndex; j++) {
                            int newX = (int) (hdriWidth * (Math.PI
                                    - Math.atan2(currentVector.x, currentVector.z))
                                    / (2 * Math.PI));
                            int newY = (int) (hdriHeight * Math.acos(currentVector.y
                                    / currentVector.length()) / Math.PI);

                            newX = rotateNumber(newX, hdriWidth);
                            newY = rotateNumber(newY, hdriHeight);

                            renderedBytes[j * 3]
                                    = hdriBytes[(newY * hdriWidth + newX) * 3];
                            renderedBytes[j * 3 + 1]
                                    = hdriBytes[(newY * hdriWidth + newX) * 3 + 1];
                            renderedBytes[j * 3 + 2]
                                    = hdriBytes[(newY * hdriWidth + newX) * 3 + 2];
                            currentVector.add(incrementX);
                            if (j % panelWidth == 0 && j != startIndex) {
                                currentVector.add(incrementY);
                                currentVector.add(vX);
                                currentVector.add(vX);
                            }
                        }

                    }
                }));
                threadList.getLast().run();
            }
            for (Thread thread : threadList) {
                try {
                    thread.join();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    private int getRGBByVector(JacksVector vector) {
        int hdriWidth = hdri.getWidth();
        int hdriHeight = hdri.getHeight();
        
        int newX = (int) (hdriWidth *
                ((Math.PI - Math.atan2(vector.x, vector.z)) / (2 * Math.PI)));
        int newY = (int) (hdriHeight *
                (Math.acos(vector.y / vector.length()) / Math.PI));
        
        newX = rotateNumber(newX, hdriWidth);
        newY = rotateNumber(newY, hdriHeight);
        return hdri.getRGB(newX, newY);
    }

    private int rotateNumber(int x, int max) {
        x = x % max;
        if (x < 0) {
            x = max + x;
        }
        return x;
    }

    private double rotateNumber(double x, double max) {
        x = x % max;
        if (x < 0) {
            x = max + x;
        }
        return x;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
