package tn.iit.diagram;


public class DBColumn {

    public String name;
    public String type;
    public String size;
    public boolean primaryKey;

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public DBColumn(String _name, String _type, String _size) {
        name = _name;
        type = _type;
        size = _size;

        primaryKey = false;

    }
}
