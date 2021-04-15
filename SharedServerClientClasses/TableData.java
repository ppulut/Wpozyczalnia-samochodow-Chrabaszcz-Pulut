package SharedServerClientClasses;

import java.io.Serializable;
import java.util.Vector;

//Klasa przechowujaca vectory w ktorych sa wiersze i kolumny zadanych JTables
public class TableData implements Serializable {

    private final Vector data;
    private final Vector columns;

    public TableData(Vector data, Vector columns) {
        this.data = data;
        this.columns = columns;
    }

    public Vector getVector1() {
        return data;
    }

    public Vector getVector2() {
        return columns;
    }

}