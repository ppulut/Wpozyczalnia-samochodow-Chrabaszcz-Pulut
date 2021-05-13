package SharedServerClientClasses;

import java.io.Serializable;

public class TableObject implements Serializable {

   public Object object;

    public TableObject(Object object) {
     this.object = object;
    }

    public Object getObject() {
        return object;
    }
}
