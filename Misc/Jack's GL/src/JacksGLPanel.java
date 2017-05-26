
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

    int lightOnScreenSize = 20;
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
    boolean showBackFace = false;
    boolean othogonal = false;
    float othogonalHeight = 10;
    
    float cameraAngle = (float) Math.PI * 60.0f / 180.0f;
    float lowClipping = 0f;
    private BufferedImage rendered;
    private BufferedImage hdri;
    private Raster renderedRaster;
    private byte[] renderedBytes;
    private byte[] hdriBytes;
    private Point[] locationMap;
    private float[] depthMap;

    private float tempFloat;
    private float vYLength;
    private float vXLength;
    private JacksVector v = new JacksVector();
    private JacksVector vX = new JacksVector();
    private JacksVector vY = new JacksVector();
    private JacksVector incrementX = new JacksVector();
    private JacksVector incrementY = new JacksVector();

    private JacksGeometry object;
    private int currentVertexIndex;
    private int activeVertexIndex;
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
    private Point point[] = new Point[3];
    private float depth[] = new float[3];
    private int order[] = new int[3];
    private int temp;
    private JacksLight lights[];
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
    JacksOrigin tempLightOrigin = new JacksOrigin();
    int tempX;
    int tempY;
    int tempZ;

    private float[][] depthBuffer;
    private JacksOrigin origin = new JacksOrigin();

    private ArrayList<JacksGeometry> geometryList = new ArrayList<>();
    private ArrayList<JacksLight> lightList = new ArrayList<>();
    ArrayList<JacksObject> selectedObjects = new ArrayList<>();
    JacksObject activeObject = null;

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
                updateTempLightList();
                updateOnScreenVertices();
                currentVertexIndex = 0;

                for (int objectIndex = 0; objectIndex < geometryList.size(); objectIndex++) {
                    object = geometryList.get(objectIndex);
                    if (activeObject != object) {
                        drawObject(object, false);
                    } else {
                        activeVertexIndex = currentVertexIndex;
                    }
                    currentVertexIndex += object.vertexList.length;
                }
                if (activeObject != null && activeObject instanceof JacksGeometry) {
                    currentVertexIndex = activeVertexIndex;
                    drawObject((JacksGeometry) activeObject, false);
                }
                rendered.setData(renderedRaster);

                // Draw light sources
                Graphics g = rendered.getGraphics();
                if (showLightSources) {
                    for (JacksLight light : lightList) {
                        tempLightOrigin.copyAttribute(origin);
                        transformOrigin(tempLightOrigin, light);
                        lightVertex.setXYZ(0, 0, 0);
                        lightVertex.project(tempLightOrigin);
                        Point lightPoint = xyzToOnScreenXY(lightVertex);

                        g.setColor(new Color(light.r, light.g, light.b));
                        if (activeObject == light) {
                            g.setColor(new Color(255, 128, 0));
                        }
                        g.drawOval(lightPoint.x - lightOnScreenSize / 2,
                                lightPoint.y - lightOnScreenSize / 2,
                                lightOnScreenSize, lightOnScreenSize);
                        if (light.lightType == 1) {
                            tempVector.copyXYZ(light.direction);
                            tempVector.project(tempLightOrigin);
                            tempVector.add(tempLightOrigin.translate);
                            Point secondPoint = xyzToOnScreenXY(tempVector.x,
                                    tempVector.y, tempVector.z);
                            g.drawLine(lightPoint.x, lightPoint.y,
                                    secondPoint.x, secondPoint.y);
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

    private void drawObject(JacksGeometry object, boolean alwaysOnTop) {
        tempOrigin.copyAttribute(origin);
        transformOrigin(tempOrigin, object);

        for (JacksFace face : object.faceList) {
            if (face.vertexList.length >= 3) {
                center.setXYZ(0, 0, 0);
                normal.copyXYZ(face.normal);
                normal.project(tempOrigin);
                normal.normalize();
                for (Integer index : face.vertexList) {
                    center.x += face.parent.vertexList[index].x;
                    center.y += face.parent.vertexList[index].y;
                    center.z += face.parent.vertexList[index].z;
                }
                center.x /= face.vertexList.length;
                center.y /= face.vertexList.length;
                center.z /= face.vertexList.length;
                center.project(tempOrigin);
                toCenter.setXYZ(center.x, center.y, center.z);
                if (!showBackFace && toCenter.dotProduct(normal) > 0) {
                    continue; // Skip back face.
                }
                toCenter.normalize();

                luminanceR = ambient;
                luminanceG = ambient;
                luminanceB = ambient;
                if (lightList.size() > 0) {
                    for (JacksLight light : lights) {
                        if (light.lightType == JacksLight.TYPE_POINT) {
                            lightVector.setXYZ(center.x - light.x, center.y - light.y, center.z - light.z);
                            specularVector.copyXYZ(lightVector);
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
                            // If light shines on face, not behind.
                            if (lightVector.dotProduct(normal) < 0) {
                                lightVector.normalize();
                                tempVector.copyXYZ(normal);
                                tempVector.multiply(-2 * lightVector.dotProduct(normal));
                                lightVector.add(tempVector);

                                luminanceS = -lightVector.dotProduct(toCenter);
                                if (showBackFace) {
                                    luminanceS = Math.abs(luminanceS);
                                }
                                if (luminanceS > 0) {
                                    tempFloat = luminanceS;
                                    for (int i = 1; i <= face.specularExponent; i++) {
                                        luminanceS *= tempFloat;
                                    }
                                    luminanceR += face.specular * (float) face.rS / 255.0f * luminanceS;
                                    luminanceG += face.specular * (float) face.gS / 255.0f * luminanceS;
                                    luminanceB += face.specular * (float) face.bS / 255.0f * luminanceS;
                                }
                            }
                        } else if (light.lightType == JacksLight.TYPE_DIRECTIONAL) {
                            lightVector.copyXYZ(light.direction);
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
                                if (showBackFace) {
                                    luminanceS = Math.abs(luminanceS);
                                }
                                if (luminanceS > 0) {
                                    tempFloat = luminanceS;
                                    for (int i = 1; i <= face.specularExponent; i++) {
                                        luminanceS *= tempFloat;
                                    }
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
                if (selectedObjects.contains(object)) {
                    luminanceR = .7f;
                    luminanceG = .4f;
                }
                if (activeObject == object) {
                    luminanceR = 1;
                    luminanceG = .5f;
                }
                for (int i = 0; i < face.vertexList.length; i += 2) {
                    // Rasterization
                    point[0] = locationMap[face.vertexList[i] + currentVertexIndex];
                    point[1] = locationMap[face.vertexList[(i + 1) % face.vertexList.length] + currentVertexIndex];
                    point[2] = locationMap[face.vertexList[(i + 2) % face.vertexList.length] + currentVertexIndex];

                    // If the face is flat (no area)
                    if (point[0].y == point[1].y && point[1].y == point[2].y) {
                        continue;
                    }

                    // If face is too big.
                    if (Math.abs((point[0].x * (point[2].y - point[1].y)
                            + point[1].x * (point[2].y - point[0].y)
                            + point[2].x * (point[0].y - point[1].y)) / 2) > panelWidth * panelHeight) {
                        continue;
                    }
                    depth[0] = depthMap[face.vertexList[i] + currentVertexIndex];
                    depth[1] = depthMap[face.vertexList[(i + 1) % face.vertexList.length] + currentVertexIndex];
                    depth[2] = depthMap[face.vertexList[(i + 2) % face.vertexList.length] + currentVertexIndex];

                    // If face is closer than clipping distance
                    if (depth[0] < lowClipping || depth[1] < lowClipping || depth[2] < lowClipping) {
                        continue;
                    }

                    order[0] = 0;
                    order[1] = 1;
                    order[2] = 2;

                    // Sort the points by y coordinate. (bubble sort...derps)
                    if (point[order[0]].y > point[order[1]].y) {
                        temp = order[0];
                        order[0] = order[1];
                        order[1] = temp;
                    }
                    if (point[order[1]].y > point[order[2]].y) {
                        temp = order[1];
                        order[1] = order[2];
                        order[2] = temp;
                    }
                    if (point[order[0]].y > point[order[1]].y) {
                        temp = order[0];
                        order[0] = order[1];
                        order[1] = temp;
                    }

                    //First half
                    for (int y = point[order[0]].y; y <= point[order[2]].y; y++) {
                        toDepth = linear(point[order[0]].y, point[order[2]].y, y, depth[order[0]], depth[order[2]]);
                        to = (int) linear(point[order[0]].y, point[order[2]].y, y, point[order[0]].x, point[order[2]].x);

                        if (y < point[order[1]].y) {
                            // First half
                            fromDepth = linear(point[order[0]].y, point[order[1]].y, y, depth[order[0]], depth[order[1]]);
                            from = (int) linear(point[order[0]].y, point[order[1]].y, y, point[order[0]].x, point[order[1]].x);
                        } else {
                            // Second half
                            fromDepth = linear(point[order[1]].y, point[order[2]].y, y, depth[order[1]], depth[order[2]]);
                            from = (int) linear(point[order[1]].y, point[order[2]].y, y, point[order[1]].x, point[order[2]].x);
                        }
                        increment = from > to ? -1 : 1;
                        for (int x = from; x != to; x += increment) {
                            if (x > 0 && x < rendered.getWidth()
                                    && y > 0 && y < rendered.getHeight()) {
                                currentDepth = linear(from, to, x, fromDepth, toDepth);
                                if (currentDepth < depthBuffer[y][x] || alwaysOnTop) {
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
                            if (toDepth < depthBuffer[y][to] || alwaysOnTop) {
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

    private void transformOrigin(JacksOrigin origin, JacksObject object) {
        origin.translate(object.x, object.y, object.z);
        origin.rotate(cameraRotationX, 0, 0);
        origin.rotate(0, 0, cameraRotationZ);
        origin.rotate(0, cameraRotationY, 0);
        origin.rotate(object.rotateX, object.rotateY, object.rotateZ);
        origin.rotate(0, -cameraRotationY, 0);
        origin.rotate(0, 0, -cameraRotationZ);
        origin.rotate(-cameraRotationX, 0, 0);
        origin.x.multiply(object.scaleX);
        origin.y.multiply(object.scaleY);
        origin.z.multiply(object.scaleZ);
    }

    private Point xyzToOnScreenXY(float x, float y, float z) {
        return new Point((int) (panelWidth * (.5 - x / (z * cameraWidth))),
                (int) (panelHeight * (.5 + y / (z * cameraHeight))));
    }

    private Point xyzToOnScreenXY(JacksVertex vertex) {
        if (!othogonal) {
            return new Point((int) (panelWidth * (.5 - vertex.x
                    / (vertex.z * cameraWidth))),
                    (int) (panelHeight * (.5 + vertex.y
                    / (vertex.z * cameraHeight))));
        } else {
            return new Point((int) (panelWidth * (.5 + vertex.x
                    / (othogonalHeight * panelWidth / panelHeight))),
                    (int) (panelHeight * (.5 - vertex.y
                    / othogonalHeight)));
        }
    }

    private void updateTempLightList() {
        int i = 0;
        for (JacksLight light : lightList) {
            tempLightOrigin.copyAttribute(origin);
            transformOrigin(tempLightOrigin, light);
            lights[i].copyAttribute(light);
            lights[i].x = 0;
            lights[i].y = 0;
            lights[i].z = 0;
            lights[i++].project(tempLightOrigin);
        }
    }

    private void updateTempLightListSize() {
        lights = new JacksLight[lightList.size()];
        int i = 0;
        for (JacksLight light : lightList) {
            JacksLight newLight = light.clone();
            lights[i++] = newLight;
        }
    }

    private void updateOnScreenVertices() {
        int i = 0;
        for (JacksGeometry object : geometryList) {
            tempOrigin.copyAttribute(origin);
            transformOrigin(tempOrigin, object);

            for (JacksVertex vertex : object.vertexList) {
                JacksVertex current = vertex.clone();
                current.project(tempOrigin);
                locationMap[i] = xyzToOnScreenXY(current);
                depthMap[i] = -current.z;
                i++;
            }
        }
    }

    private void clearDepthBuffer() {
        for (float[] depth : depthBuffer) {
            Arrays.fill(depth, Float.POSITIVE_INFINITY);
        }
    }

    private void updateVertexListSize() {
        int numberOfVertex = 0;
        for (JacksGeometry object : geometryList) {
            numberOfVertex += object.vertexList.length;
        }
        locationMap = new Point[numberOfVertex];
        depthMap = new float[numberOfVertex];
    }

    void updateOrigin() {
        origin.reset();
        origin.rotate(0, -cameraRotationY, 0);
        origin.rotate(0, 0, -cameraRotationZ);
        origin.rotate(-cameraRotationX, 0, 0);
        origin.translate(-cameraX, -cameraY, -cameraZ);
    }

    ArrayList<JacksGeometry> getGeometryList() {
        ArrayList<JacksGeometry> newList = new ArrayList<>();
        for (JacksGeometry geometry : geometryList) {
            newList.add(geometry);
        }
        return newList;
    }

    ArrayList<JacksLight> getLightList() {
        ArrayList<JacksLight> newList = new ArrayList<>();
        for (JacksLight light : lightList) {
            newList.add(light);
        }
        return newList;
    }

    void addGeometry(JacksGeometry newGeometry) {
        geometryList.add(newGeometry);
        updateVertexListSize();
    }

    void addLight(JacksLight newLight) {
        lightList.add(newLight);
        updateTempLightListSize();
    }

    void removeGeometry(JacksGeometry geometry) {
        geometry.destroy();
        geometryList.remove(geometry);
        updateVertexListSize();
        System.gc();
    }

    void removeLight(JacksLight light) {
        light.destroy();
        lightList.remove(light);
        updateTempLightListSize();
        System.gc();
    }

    void removeObject(JacksObject object) {
        if (object instanceof JacksGeometry) {
            removeGeometry((JacksGeometry) object);
        } else if (object instanceof JacksLight) {
            removeLight((JacksLight) object);
        }
        System.gc();
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

            v.rotateX(backgroundRotationX);
            v.rotateZ(backgroundRotationZ);
            v.rotateY(backgroundRotationY);
            vX.rotateX(backgroundRotationX);
            vX.rotateZ(backgroundRotationZ);
            vX.rotateY(backgroundRotationY);

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

    private float linear(float startProgress, float endProgress, float progress, float start, float end) {
        return startProgress == endProgress
                ? start
                : start + (end - start) * (progress - startProgress)
                / (endProgress - startProgress);
    }

    private int getRGBByVector(JacksVector vector) {
        int hdriWidth = hdri.getWidth();
        int hdriHeight = hdri.getHeight();

        int newX = (int) (hdriWidth
                * ((Math.PI - Math.atan2(vector.x, vector.z)) / (2 * Math.PI)));
        int newY = (int) (hdriHeight
                * (Math.acos(vector.y / vector.length()) / Math.PI));

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

    private boolean pointBelongsToPlane(Point point, Point... planePoints) {
        if (planePoints.length <= 2) {
            return false;
        }
        boolean skip = true;
        for (int i = 0; i < planePoints.length - 1; i++) {
            if (planePoints[i].y != planePoints[i + 1].y) {
                skip = false;
                break;
            }
        }
        if (skip) {
            return false;
        }
        skip = true;
        for (int i = 0; i < planePoints.length - 1; i++) {
            if (planePoints[i].x != planePoints[i + 1].x) {
                skip = false;
                break;
            }
        }
        if (skip) {
            return false;
        }

        boolean onLeft = (planePoints[1].x - planePoints[0].x)
                * (point.y - planePoints[0].y)
                - (point.x - planePoints[0].x)
                * (planePoints[1].y - planePoints[0].y) >= 0;
        for (int i = 1; i < planePoints.length; i++) {
            boolean currentOnLeft = (planePoints[(i + 1) % planePoints.length].x - planePoints[i].x)
                    * (point.y - planePoints[i].y)
                    - (point.x - planePoints[i].x)
                    * (planePoints[(i + 1) % planePoints.length].y - planePoints[i].y) >= 0;
            if (currentOnLeft != onLeft) {
                return false;
            }
        }
        return true;
    }

    JacksObject selectOnScreen(Point mouseLocation) {
        for (JacksLight light : lightList) {
            JacksOrigin tempOrigin = origin.clone();
            transformOrigin(tempOrigin, light);
            
            Point lightLocation = xyzToOnScreenXY(tempOrigin.translate.x,
                    tempOrigin.translate.y, tempOrigin.translate.z);
            if ((lightLocation.x - mouseLocation.x)
                    * (lightLocation.x - mouseLocation.x)
                    + (lightLocation.y - mouseLocation.y)
                    * (lightLocation.y - mouseLocation.y)
                    <= lightOnScreenSize * lightOnScreenSize / 4) {
                return light;
            }
        }
        int selectVertexIndex = 0;
        for (JacksGeometry geometry : geometryList) {
            for (JacksFace face : geometry.faceList) {
                Point[] facePoints = new Point[face.vertexList.length];
                int j = 0;
                for (int i : face.vertexList) {
                    facePoints[j++] = locationMap[i + selectVertexIndex];
                }
                if (pointBelongsToPlane(mouseLocation, facePoints)) {
                    return geometry;
                }
            }
            selectVertexIndex += geometry.vertexList.length;
        }
        return null;
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
