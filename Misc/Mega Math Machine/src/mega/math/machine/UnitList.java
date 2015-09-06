package mega.math.machine;
import java.math.BigDecimal;
import java.util.ArrayList;
public class UnitList {
    String name;
    ArrayList<Unit> unitList;
    
    UnitList(String n) {
        name = n;
        unitList = new ArrayList<Unit>();
    }
}
