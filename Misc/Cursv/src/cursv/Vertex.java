package cursv;

import java.awt.Point;
import java.io.Serializable;

public class Vertex implements Serializable {
    static final int TYPE_NORMAL = 0;
    static final int TYPE_DISCONNECTED = 1;
    
    int type = 0;
    boolean fold = false;
    Point location = new Point(), extrusion = new Point();
    double velocity = 40;
    
    public Vertex(int x, int y) {
        location.x = x;
        location.y = y;
        extrusion.x = 60;
        extrusion.y = 0;
    }
    
    public Point getExtrusionLocation() {
        return new Point(location.x + extrusion.x, location.y + extrusion.y);
    }
    
    public void setExtrusionLocation(int x, int y) {
        extrusion.x = x - location.x;
        extrusion.y = y - location.y;
    }
    
    public Vertex clone() {
        Vertex newVertex = new Vertex(location.x, location.y);
        newVertex.extrusion.x = extrusion.x;
        newVertex.extrusion.y = extrusion.y;
        newVertex.type = type;
        newVertex.fold = fold;
        return newVertex;
    }
}
