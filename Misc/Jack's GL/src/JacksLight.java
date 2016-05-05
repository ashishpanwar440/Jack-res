public class JacksLight {
    public static final int TYPE_POINT = 0;
    public static final int TYPE_DIRECTIONAL = 1;
    int type;
    float energy = 3;
    int r = 255;
    int g = 255;
    int b = 255;
    float x = 0;
    float y = 0;
    float z = 0;
    JacksVector direction = new JacksVector(0, -1, 0);
    
    JacksLight() {}
    
    JacksLight(int type) {
        this.type = type;
    }
    
    JacksLight(int type, float energy, int r, int g, int b,
            float x, float y, float z) {
        this();
        setAttribute(type, energy, r, g, b, x, y, z);
    }
    
    void setAttribute(int type, float energy, int r, int g, int b,
            float x, float y, float z) {
        this.type = type;
        this.energy = energy;
        this.r = r;
        this.g = g;
        this.b = b;
        if (type == TYPE_POINT) {
            this.x = x;
            this.y = y;
            this.z = z;
        } else {
            direction.setXYZ(x, y, z);
        }
    }
}
