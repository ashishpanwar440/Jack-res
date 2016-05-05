
import java.util.ArrayList;
import java.util.Arrays;

public class JacksFace {
    JacksVertex vertexList[] = null;
    JacksVector normal = new JacksVector();
    boolean smooth = false;
    int r = 255;
    int g = 255;
    int b = 255;
    int rS = 255;
    int gS = 255;
    int bS = 255;
    float specular = 1;
    int specularExponent = 3;
    JacksFace() {}
    
    JacksFace(JacksVertex...vertices) {
        this();
        setVertices(vertices);
    }
    
    void addVertex(JacksVertex vertex) {
        ArrayList<JacksVertex> temp;
        if (vertexList != null) temp = new ArrayList<>(Arrays.asList(vertexList));
        else temp = new ArrayList<>();
        temp.add(vertex);
        
        vertexList = new JacksVertex[temp.size()];
        temp.toArray(vertexList);
        if (vertexList.length >= 3) {
            calculateNormal();
        }
    }
    
    void setVertices(JacksVertex[] vertices) {
        ArrayList<JacksVertex> temp;
        if (vertexList != null) temp = new ArrayList<>(Arrays.asList(vertexList));
        else temp = new ArrayList<>();
        for (JacksVertex vertex : vertices) temp.add(vertex);

        vertexList = new JacksVertex[temp.size()];
        temp.toArray(vertexList);
        if (vertexList.length >= 3) {
            calculateNormal();
        }
    }
    
    void calculateNormal() {
        normal = new JacksVector(vertexList[0], vertexList[1]);
        normal.crossProduct(new JacksVector(vertexList[1], vertexList[2]));
        normal.normalize();
    }
}
