
import java.util.HashMap;

public class JacksObject {
    static final int GEOMETRY_TYPE_MESH = 0;
    static final int GEOMETRY_TYPE_MATH = 1;
    static final int GEOMETRY_TYPE_PATH = 2;
    
    float x = 0;
    float y = 0;
    float z = 0;
    float rotateX = 0;
    float rotateY = 0;
    float rotateZ = 0;
    int type;
    JacksMesh mesh;
    
    JacksObject() {
        type = GEOMETRY_TYPE_MESH;
    }

    JacksObject(int type) {
        this.type = type;
    }

    JacksMesh addMesh() {
        mesh = new JacksMesh();
        return mesh;
    }
    
    protected JacksObject clone() {
        JacksObject newObject = new JacksObject(this.type);
        JacksMesh newMesh = newObject.addMesh();
        HashMap<JacksVertex, JacksVertex> vertexMap = new HashMap<>();
        for (JacksVertex current : mesh.vertexList) {
            vertexMap.put(current,
                    newMesh.addVertex(current.x, current.y, current.z));
        }
        for (JacksFace face : mesh.faceList) {
            JacksFace newFace = newMesh.addFace();
            for (JacksVertex vertex : face.vertexList) {
                newFace.addVertex(vertexMap.get(vertex));
            }
        }
        return newObject;
    }
}
