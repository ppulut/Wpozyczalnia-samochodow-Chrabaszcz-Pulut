package SharedServerClientClasses;


import java.io.Serializable;
import java.util.Vector;

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