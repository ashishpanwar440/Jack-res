package mega.math.machine;
import java.math.BigDecimal;
public class Unit {
    String name;
    String coefficient;
    String offset;
    
    Unit(String n, String c, String o) {
        name = n;
        coefficient = c;
        offset = o;
    }
    
    public Unit clone () {
        return new Unit(name, coefficient, offset);
    }
}
