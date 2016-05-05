
import java.util.ArrayList;
import java.util.Arrays;

public class JacksMesh {
    JacksVertex[] vertexList = null;
    JacksFace[] faceList = null;
    
    JacksVertex addVertex() {
        JacksVertex newVertex = new JacksVertex();
        ArrayList<JacksVertex> temp;
        if (vertexList != null) temp = new ArrayList<>(Arrays.asList(vertexList));
        else temp = new ArrayList<>();
        
        temp.add(newVertex);
        
        vertexList = new JacksVertex[temp.size()];
        temp.toArray(vertexList);
        
        return newVertex;
    }
    
    JacksVertex addVertex(float x, float y, float z) {
        JacksVertex newVertex = new JacksVertex(x, y, z);
        ArrayList<JacksVertex> temp;
        if (vertexList != null) temp = new ArrayList<>(Arrays.asList(vertexList));
        else temp = new ArrayList<>();
        
        temp.add(newVertex);
        
        vertexList = new JacksVertex[temp.size()];
        temp.toArray(vertexList);
        
        return newVertex;
    }
    
    JacksFace addFace() {
        JacksFace newFace = new JacksFace();
        ArrayList<JacksFace> temp;
        if (faceList != null) temp = new ArrayList<>(Arrays.asList(faceList));
        else temp = new ArrayList<>();
        
        temp.add(newFace);
        faceList = new JacksFace[temp.size()];
        temp.toArray(faceList);
        return newFace;
    }
    
    JacksFace addFace(int...vertexIndices) {
        JacksFace newFace = addFace();
        ArrayList<JacksVertex> temp = new ArrayList<>();
        for (int i : vertexIndices) {
            temp.add(vertexList[i]);
        }
        JacksVertex dummy[] = new JacksVertex[temp.size()];
        temp.toArray(dummy);
        newFace.setVertices(dummy);
        return newFace;
    }
}